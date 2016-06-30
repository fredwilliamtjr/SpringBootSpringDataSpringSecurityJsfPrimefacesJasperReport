package br.com.fwtj.base.service;

import br.com.fwtj.base.model.Usuario;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fwtj.base.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> todos() {
        System.out.println("LISTANDO TODOS USUARIOS");
        return usuarioRepository.findAll();
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findOne(id);
    }
    
    public Usuario buscarUsernamePassword(String username, String password){
        return usuarioRepository.findByUsernameAndPassword(username, password);
    }
    
    public void salvar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void excluir(Long id) {
        usuarioRepository.delete(id);
    }

    public List<Usuario> pesquisaPorUsername(String username) {
        return usuarioRepository.findByUsernameIgnoreCaseContains(username);
    }

    public List<Usuario> pesquisaPorRole(String role) {
        return usuarioRepository.findByRoleIgnoreCaseContains(role);
    }

}
