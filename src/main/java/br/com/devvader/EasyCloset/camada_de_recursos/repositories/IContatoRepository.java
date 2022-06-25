package br.com.devvader.EasyCloset.camada_de_recursos.repositories;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContatoRepository extends JpaRepository<Contato, Long> {
}
