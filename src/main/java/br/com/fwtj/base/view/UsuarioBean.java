package br.com.fwtj.base.view;

import br.com.fwtj.base.model.Usuario;
import br.com.fwtj.base.service.UsuarioService;
import br.com.fwtj.base.util.jsf.FacesViewScope;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller // Equivalente à @ManagedBean
@Scope(FacesViewScope.NAME)
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String role;
    private Usuario usuario = new Usuario();
    private List<Usuario> todos = new ArrayList<Usuario>();

    @Autowired
    UsuarioService usuarioService;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getTodos() {
        return todos;
    }

    public void setTodos(List<Usuario> todos) {
        this.todos = todos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void salvar() {
        usuarioService.salvar(usuario);
        todos = usuarioService.todos();
        usuario = new Usuario();
        username = "";
        role = "";
    }

    public void excluir(Long id) {
        usuarioService.excluir(id);
        username = "";
        role = "";
        todos = usuarioService.todos();
    }
    
    public void editar(Long id) {
        Usuario buscar = usuarioService.buscar(id);
        username = "";
        role = "";
        usuario = buscar;
        todos = usuarioService.todos();
    }
    
    public void preparaLista() {
        todos = usuarioService.todos();
    }
    
    public void pesquisaPorUsername() {
        todos = usuarioService.pesquisaPorUsername(username);
    }
    
    public void pesquisaPorRole() {
        todos = usuarioService.pesquisaPorRole(role);
    }
    
}
