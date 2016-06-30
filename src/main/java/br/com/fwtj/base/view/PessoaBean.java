package br.com.fwtj.base.view;

import br.com.fwtj.base.util.jsf.FacesViewScope;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.fwtj.base.model.Pessoa;
import br.com.fwtj.base.service.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

@Controller // Equivalente à @ManagedBean
@Scope(FacesViewScope.NAME)
public class PessoaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> todasPessoas = new ArrayList<Pessoa>();
    private String nome;
    private String telefone;

    @Autowired
    PessoaService pessoaService;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getTodas() {
        return todasPessoas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void salvar() {
        pessoaService.salvar(pessoa);
        todasPessoas = pessoaService.todas();
        pessoa = new Pessoa();
        nome = "";
        telefone = "";
    }

    public void excluir(Long id) {
        pessoaService.excluir(id);
        nome = "";
        telefone = "";
        todasPessoas = pessoaService.todas();
    }

    public void editar(Long id) {
        Pessoa buscar = pessoaService.buscar(id);
        nome = "";
        telefone = "";
        pessoa = buscar;
        todasPessoas = pessoaService.todas();
    }

    public void preparaLista() {
        todasPessoas = pessoaService.todas();
    }

    public void pesquisaPorNome() {
        todasPessoas = pessoaService.pesquisaPorNome(nome);
    }
    
    public void pesquisaPorTelefone() {
        todasPessoas = pessoaService.pesquisaPorNome(telefone);
    }
    
    public String getUsuarioLogado(){
    	Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username;
    	if (usuarioLogado  instanceof UserDetails ) {
    	   username= ( (UserDetails)usuarioLogado).getUsername();
    	} else {
    	   username = usuarioLogado .toString();
    	}
    	return username;
    }

}
