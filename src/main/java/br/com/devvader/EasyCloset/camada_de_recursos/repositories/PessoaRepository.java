package br.com.devvader.EasyCloset.camada_de_recursos.repositories;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Pessoa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "pessoas", path = "pessoas")
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long> {

    Optional<Pessoa> findByCpf(String cpf);
    List<Pessoa> findByName(@Param("name") String name);
}
