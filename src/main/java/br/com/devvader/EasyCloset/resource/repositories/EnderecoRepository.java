package br.com.devvader.EasyCloset.resource.repositories;

import br.com.devvader.EasyCloset.resource.persistence.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
