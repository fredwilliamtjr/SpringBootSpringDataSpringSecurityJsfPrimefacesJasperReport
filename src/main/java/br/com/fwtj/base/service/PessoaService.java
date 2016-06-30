package br.com.fwtj.base.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fwtj.base.model.Pessoa;
import br.com.fwtj.base.repository.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PessoaRepository pessoaRepository;

    public void salvar(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    public void excluir(Long id) {
        pessoaRepository.delete(id);
    }

    public List<Pessoa> todas() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscar(Long id) {
        return pessoaRepository.findOne(id);
    }

    public List<Pessoa> pesquisaPorNome(String nome) {
        return pessoaRepository.findByNomeIgnoreCaseContains(nome);
    }

    public List<Pessoa> pesquisaPorTelefone(String telefone) {
        return pessoaRepository.findByTelefoneIgnoreCaseContains(telefone);
    }

    public List<Pessoa> todasOrdenadaPorNome() {
        return pessoaRepository.findAllByOrderByNomeAsc();
    }

}
