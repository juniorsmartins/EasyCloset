package br.com.devvader.EasyCloset.camada_de_recursos.repositories;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.RoupaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoupaRepository extends JpaRepository<RoupaEntity, Long> {
}
