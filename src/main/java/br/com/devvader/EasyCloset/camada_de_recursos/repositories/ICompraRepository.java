package br.com.devvader.EasyCloset.camada_de_recursos.repositories;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompraRepository extends JpaRepository<CompraEntity, Long> {
}
