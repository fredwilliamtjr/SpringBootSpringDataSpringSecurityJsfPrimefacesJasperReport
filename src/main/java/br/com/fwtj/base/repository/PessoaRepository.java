package br.com.fwtj.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fwtj.base.model.Pessoa;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	List<Pessoa> findByNomeIgnoreCaseContains(String nome);
        List<Pessoa> findByTelefoneIgnoreCaseContains(String telefone);
        List<Pessoa> findAllByOrderByNomeAsc();
    
}
