package br.com.devvader.EasyCloset.camada_de_recursos.repositories;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface IPessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByCpf(@Param("cpf") String cpf);
}
