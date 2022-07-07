package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.configuracoes;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Contato;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Endereco;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Pessoa;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.util.List;

@Configuration
@Profile("testes")
public class AmbienteTestesConfig {

    @Autowired
    private IPessoaRepository iPessoaRepository;

    @Bean
    public void startDB() {
        var pessoaTest1 = Pessoa.builder()
                                        .pessoaId(null)
                                        .nome("Robert")
                                        .sobrenome("C. Martin")
                                        .cpf("590.889.470-23")
                                        .build();

        var pessoaTest2 = Pessoa.builder()
                                        .pessoaId(null)
                                        .nome("Carol")
                                        .sobrenome("Dweck")
                                        .cpf("256.327.270-09")
                                        .build();

        iPessoaRepository.saveAll(List.of(pessoaTest1, pessoaTest2));
    }
}
