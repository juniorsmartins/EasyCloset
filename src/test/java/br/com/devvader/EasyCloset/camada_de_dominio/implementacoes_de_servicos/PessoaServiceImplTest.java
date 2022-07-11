package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.ContatoDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.EnderecoDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.ContatoDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.EnderecoDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.mappers.MapStructPessoa;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IPessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PessoaServiceImplTest {

    public static final long PESSOA_ID = 1L;
    public static final String NOME = "Jeff";
    public static final String SOBRENOME = "Sutherland";
    public static final String CPF = "085.033.460-87";
    public static final String CELULAR = "(86)98686-6688";
    public static final String EMAIL = "sutherland@gmail.com";
    public static final String CEP = "64207-095";
    public static final String ESTADO = "PI";
    public static final String CIDADE = "Parnaíba";
    public static final String BAIRRO = "Frei Higino";
    public static final String LOGRADOURO = "Rua Guaporé";
    public static final int NUMERO = 2988;
    public static final String COMPLEMENTO = "Segunda campainha";

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Mock
    private IPessoaRepository iPessoaRepository;

    @Mock
    private MapStructPessoa mapStructPessoa;

    private PessoaDtoEntrada pessoaDeEntrada;
    private PessoaDtoSaida pessoaDeSaida;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPessoas();
    }

    @Test
    void cadastrar() {
    }

    @Test
    void listar() {
    }

    @Test
    void consultar() {
    }

    @Test
    void deletar() {
    }

    @Test
    void atualizar() {
    }

    private void startPessoas() {
        pessoaDeEntrada = PessoaDtoEntrada.builder()
                .pessoaId(null)
                .nome(NOME)
                .sobrenome(SOBRENOME)
                .cpf(CPF)
                .contato(ContatoDtoEntrada.builder()
                        .celular(CELULAR)
                        .email(EMAIL)
                        .build())
                .endereco(EnderecoDtoEntrada.builder()
                        .cep(CEP)
                        .estado(ESTADO)
                        .cidade(CIDADE)
                        .bairro(BAIRRO)
                        .logradouro(LOGRADOURO)
                        .numero(NUMERO)
                        .complemento(COMPLEMENTO)
                        .build())
                .build();

        pessoaDeSaida = PessoaDtoSaida.builder()
                .pessoaId(PESSOA_ID)
                .nome(NOME)
                .sobrenome(SOBRENOME)
                .cpf(CPF)
                .contato(ContatoDtoSaida.builder()
                        .celular(CELULAR)
                        .email(EMAIL)
                        .build())
                .endereco(EnderecoDtoSaida.builder()
                        .cep(CEP)
                        .estado(ESTADO)
                        .cidade(CIDADE)
                        .bairro(BAIRRO)
                        .logradouro(LOGRADOURO)
                        .numero(NUMERO)
                        .complemento(COMPLEMENTO)
                        .build())
                .build();
    }
}