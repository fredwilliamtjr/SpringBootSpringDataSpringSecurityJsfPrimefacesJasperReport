package br.com.fwtj.base;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Have to disable it for POST methods:
        // http://stackoverflow.com/a/20608149/1199132
        http.csrf().disable();

        // Logout and redirection:
        // http://stackoverflow.com/a/24987207/1199132
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login.jsf");

        http.authorizeRequests()
                .antMatchers("/500.jsf", "/denied.jsf","/login.jsf","/javax.faces.resource/**").permitAll() //Permit access for all to error and denied views
                .antMatchers("/config/**").hasRole("ADMIN") // Only access with admin role
                .antMatchers("/**").hasAnyRole("ADMIN", "USER") //Permit access only for some roles
                .and() //If user doesn't have permission, forward him to login page
                .formLogin().loginPage("/login.jsf").loginProcessingUrl("/login")
                .defaultSuccessUrl("/pessoas.jsf")
                .and().exceptionHandling().accessDeniedPage("/denied.jsf");
    }

    @Autowired
    DataSource dataSource;

    //Pesquisa Usuario no banco
    @Autowired 
    public void configAuthentication(AuthenticationManagerBuilder auth) {

        try {
            auth.jdbcAuthentication().dataSource(dataSource)
                    .usersByUsernameQuery(
                            "select username,password,enabled from usuario where username=?")
                    .authoritiesByUsernameQuery(
                            "select username, role from usuario where username=?");
            
        } catch (Exception ex) {
            Logger.getLogger(WebSecurityConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// pesquisa usuario em memoria.
//    @Autowired 
//    UsuarioService usuarioService;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        List<Usuario> todos = usuarioService.todos();
//        for (Usuario usuario : todos) {
//            auth.inMemoryAuthentication().withUser(usuario.getLogin()).password(usuario.getSenha()).roles(usuario.getRole());
//        }
//    }
    
}
