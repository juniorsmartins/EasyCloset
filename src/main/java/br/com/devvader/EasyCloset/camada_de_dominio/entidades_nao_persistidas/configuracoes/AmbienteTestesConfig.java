package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.configuracoes;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Contato;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Endereco;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.PessoaEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IContatoRepository;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IEnderecoRepository;
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
    @Autowired
    private IContatoRepository iContatoRepository;
    @Autowired
    private IEnderecoRepository iEnderecoRepository;

    @Bean
    public void startDB() {
        var pessoaTest1 = PessoaEntity.builder()
                .id(null)
                .nome("Robert")
                .sobrenome("C. Martin")
                .cpf("590.889.470-23")
                .build();

        var contatoTest1 = Contato.builder()
                .contatoId(null)
                .celular("(81)99988-8811")
                .email("robert.martin@yahoo.com")
                .pessoa(pessoaTest1)
                .build();

        var enderecoTest1 = Endereco.builder()
                .enderecoId(null)
                .cep("54589-525")
                .estado("PE")
                .cidade("Cabo de Santo Agostinho")
                .bairro("Pontezinha")
                .logradouro("Rua Professor José Joaquim da Silva")
                .numero(1857)
                .complemento("Entrada pelos fundos.")
                .pessoa(pessoaTest1)
                .build();

        var pessoaTest2 = PessoaEntity.builder()
                .id(null)
                .nome("Carol")
                .sobrenome("Dweck")
                .cpf("256.327.270-09")
                .build();

        var contatoTest2 = Contato.builder()
                .contatoId(null)
                .celular("(65)99966-6655")
                .email("dweck@gmail.com")
                .pessoa(pessoaTest2)
                .build();

        var entedercoTest2 = Endereco.builder()
                .enderecoId(null)
                .cep("78020-400")
                .estado("MT")
                .cidade("Cuiabá")
                .bairro("Centro")
                .logradouro("Rua Comte Costa")
                .numero(2158)
                .complemento("Entrada pela lateral direita.")
                .pessoa(pessoaTest2)
                .build();

        iPessoaRepository.saveAll(List.of(pessoaTest1, pessoaTest2));
        iContatoRepository.saveAll(List.of(contatoTest1, contatoTest2));
        iEnderecoRepository.saveAll(List.of(enderecoTest1, entedercoTest2));

    }
}
