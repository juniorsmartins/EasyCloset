package br.com.devvader.EasyCloset.camada_de_recursos.repositories;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {
}
