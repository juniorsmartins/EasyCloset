package br.com.devvader.EasyCloset.resource.repositories;

import br.com.devvader.EasyCloset.resource.persistence.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
