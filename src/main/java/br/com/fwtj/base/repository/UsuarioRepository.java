package br.com.fwtj.base.repository;

import br.com.fwtj.base.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findByUsernameAndPassword(String username, String password);
    List<Usuario> findByUsernameIgnoreCaseContains(String username);
    List<Usuario> findByRoleIgnoreCaseContains(String role);
	
}
