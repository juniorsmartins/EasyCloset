package br.com.devvader.EasyCloset.resource.repositories;

import br.com.devvader.EasyCloset.resource.persistence.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
