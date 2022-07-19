package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.ContatoDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.EnderecoDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaListar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.ContatoDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.EnderecoDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.mappers.MapStructPessoa;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.regras_negocio.pessoa.IPessoaRegrasDeNegocio;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.MensagensPadronizadas;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RecursoNaoEncontradoException;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Contato;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Endereco;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Pessoa;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IPessoaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class PessoaServiceImplTest {

    public static final long PESSOA_ID = 1L;
    public static final String NOME = "Ken";
    public static final String SOBRENOME = "Schwaber";
    public static final String CPF = "533.071.120-74";
    public static final String CELULAR = "(68)96868-8866";
    public static final String EMAIL = "Schwaber@gmail.com";
    public static final String CEP = "69900-800";
    public static final String ESTADO = "AC";
    public static final String CIDADE = "Rio Branco";
    public static final String BAIRRO = "José Augusto";
    public static final String LOGRADOURO = "Rua Projetada";
    public static final int NUMERO = 552;
    public static final String COMPLEMENTO = "Entrada pela escadaria.";

    public static final long PESSOA1_ID = 2L;
    public static final String NOME1 = "Jeff";
    public static final String SOBRENOME1 = "Sutherland";
    public static final String CPF1 = "085.033.460-87";
    public static final String CELULAR1 = "(86)98686-6688";
    public static final String EMAIL1 = "sutherland@gmail.com";
    public static final String CEP1 = "64207-095";
    public static final String ESTADO1 = "PI";
    public static final String CIDADE1 = "Parnaíba";
    public static final String BAIRRO1 = "Frei Higino";
    public static final String LOGRADOURO1 = "Rua Guaporé";
    public static final int NUMERO1 = 2988;
    public static final String COMPLEMENTO1 = "Segunda campainha";

    public static final String RECURSO_ENCONTRADO = "Recurso encontrado no banco de dados!";

    public static final long PESSOA2_ID = 3L;

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Mock
    private IPessoaRepository iPessoaRepository;

    @Mock
    private List<IPessoaRegrasDeNegocio> listaDeRegrasDeNegocio;

    private PessoaDtoEntrada pessoaDeEntrada;
    private PessoaDtoEntradaListar pessoaDtoEntradaListar;
    private Optional<Pessoa> optionalPessoa1;
    private Pessoa pessoa;
    private Pessoa pessoa1;
    private PessoaDtoSaida pessoaDeSaida;
    private PessoaDtoSaida pessoaDeSaida2;
    private Contato contato;
    private Endereco endereco;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPessoas();
    }

    @Test
    void cadastrar_CaminhoFeliz() {
        Mockito.when(iPessoaRepository.save(Mockito.any())).thenReturn(pessoa1);

        var response = pessoaService.cadastrar(pessoaDeEntrada);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(PessoaDtoSaida.class, response.getBody().getClass());
        Assertions.assertEquals(NOME1, ((PessoaDtoSaida) response.getBody()).getNome());
        Assertions.assertEquals(SOBRENOME1, ((PessoaDtoSaida) response.getBody()).getSobrenome());
        Assertions.assertEquals(CPF1, ((PessoaDtoSaida) response.getBody()).getCpf());
        Assertions.assertEquals(CELULAR1, ((PessoaDtoSaida) response.getBody()).getContato().getCelular());
        Assertions.assertEquals(EMAIL1, ((PessoaDtoSaida) response.getBody()).getContato().getEmail());
        Assertions.assertEquals(CEP1, ((PessoaDtoSaida) response.getBody()).getEndereco().getCep());
        Assertions.assertEquals(ESTADO1, ((PessoaDtoSaida) response.getBody()).getEndereco().getEstado());
        Assertions.assertEquals(CIDADE1, ((PessoaDtoSaida) response.getBody()).getEndereco().getCidade());
        Assertions.assertEquals(BAIRRO1, ((PessoaDtoSaida) response.getBody()).getEndereco().getBairro());
        Assertions.assertEquals(LOGRADOURO1, ((PessoaDtoSaida) response.getBody()).getEndereco().getLogradouro());
        Assertions.assertEquals(NUMERO1, ((PessoaDtoSaida) response.getBody()).getEndereco().getNumero());
        Assertions.assertEquals(COMPLEMENTO1, ((PessoaDtoSaida) response.getBody()).getEndereco().getComplemento());
        Assertions.assertEquals(PESSOA1_ID, ((PessoaDtoSaida) response.getBody()).getPessoaId());
    }

    @Test
    void listar() {
        Mockito.when(iPessoaRepository.findAll()).thenReturn(List.of(pessoa, pessoa1));

        List<Pessoa> listaDePessoasSalvas = iPessoaRepository.findAll();

        // precisa criar o pessoaDtoEntradaListar
        ResponseEntity<?> listaDePessoasDeSaida = pessoaService.listar(pessoaDtoEntradaListar);

        Assertions.assertEquals(ResponseEntity.class, listaDePessoasDeSaida.getClass());
        Assertions.assertEquals(2, ((List<PessoaDtoSaida>) listaDePessoasDeSaida.getBody()).size());
        Assertions.assertEquals(PessoaDtoSaida.class, ((List<PessoaDtoSaida>) listaDePessoasDeSaida.getBody()).get(0).getClass());
        Assertions.assertEquals(PESSOA_ID, ((List<PessoaDtoSaida>) listaDePessoasDeSaida.getBody()).get(0).getPessoaId());
    }

    @Test
    void consultarPorId_CaminhoFeliz() {
        Mockito.when(iPessoaRepository.findById(Mockito.anyLong())).thenReturn(optionalPessoa1);
        ResponseEntity<?> pessoaDeSaida = pessoaService.consultar(1L);

        Assertions.assertNotNull(pessoaDeSaida);
        Assertions.assertEquals(PessoaDtoSaida.class, pessoaDeSaida.getBody().getClass());

        Assertions.assertEquals(PESSOA1_ID, ((PessoaDtoSaida) pessoaDeSaida.getBody()).getPessoaId());
        Assertions.assertEquals(NOME1, ((PessoaDtoSaida) pessoaDeSaida.getBody()).getNome());
        Assertions.assertEquals(SOBRENOME1, ((PessoaDtoSaida) pessoaDeSaida.getBody()).getSobrenome());
        Assertions.assertEquals(CPF1, ((PessoaDtoSaida) pessoaDeSaida.getBody()).getCpf());

        Assertions.assertEquals(CELULAR1, ((PessoaDtoSaida) pessoaDeSaida.getBody()).getContato().getCelular());
        Assertions.assertEquals(EMAIL1, ((PessoaDtoSaida) pessoaDeSaida.getBody()).getContato().getEmail());

        Assertions.assertEquals(CEP1, ((PessoaDtoSaida) pessoaDeSaida.getBody()).getEndereco().getCep());
        Assertions.assertEquals(ESTADO1, ((PessoaDtoSaida) pessoaDeSaida.getBody()).getEndereco().getEstado());
        Assertions.assertEquals(CIDADE1, ((PessoaDtoSaida) pessoaDeSaida.getBody()).getEndereco().getCidade());
        Assertions.assertEquals(BAIRRO1, ((PessoaDtoSaida) pessoaDeSaida.getBody()).getEndereco().getBairro());
        Assertions.assertEquals(LOGRADOURO1, ((PessoaDtoSaida) pessoaDeSaida.getBody()).getEndereco().getLogradouro());
        Assertions.assertEquals(NUMERO1, ((PessoaDtoSaida) pessoaDeSaida.getBody()).getEndereco().getNumero());
        Assertions.assertEquals(COMPLEMENTO1, ((PessoaDtoSaida) pessoaDeSaida.getBody()).getEndereco().getComplemento());

        Assertions.assertEquals(HttpStatus.OK, pessoaDeSaida.getStatusCode());
    }

    @Test
    void consultarPorId_Exception_RecursoNaoEncontradoException() {
        Mockito.when(iPessoaRepository.findById(Mockito.anyLong()))
                .thenThrow(new RecursoNaoEncontradoException(MensagensPadronizadas.RECURSO_NAO_ENCONTRADO));

        try{
            pessoaService.consultar(0L);
        }catch(Exception ex){
            Assertions.assertEquals(RecursoNaoEncontradoException.class, ex.getClass());
            Assertions.assertEquals(MensagensPadronizadas.RECURSO_NAO_ENCONTRADO, ex.getMessage());
            Assertions.assertNotEquals(RECURSO_ENCONTRADO, ex.getMessage());
        }
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
                .nome(NOME1)
                .sobrenome(SOBRENOME1)
                .cpf(CPF1)
                .contato(ContatoDtoEntrada.builder()
                        .celular(CELULAR1)
                        .email(EMAIL1)
                        .build())
                .endereco(EnderecoDtoEntrada.builder()
                        .cep(CEP1)
                        .estado(ESTADO1)
                        .cidade(CIDADE1)
                        .bairro(BAIRRO1)
                        .logradouro(LOGRADOURO1)
                        .numero(NUMERO1)
                        .complemento(COMPLEMENTO1)
                        .build())
                .build();

        pessoaDtoEntradaListar = PessoaDtoEntradaListar.builder()
                .pessoaId(null)
                .nome(null)
                .sobrenome(null)
                .cpf(null)
                .contato(null)
                .endereco(null)
                .build();

        optionalPessoa1 = Optional.of(Pessoa.builder()
                .pessoaId(PESSOA1_ID)
                .nome(NOME1)
                .sobrenome(SOBRENOME1)
                .cpf(CPF1)
                .contato(Contato.builder()
                        .contatoId(PESSOA1_ID)
                        .celular(CELULAR1)
                        .email(EMAIL1)
                        .build())
                .endereco(Endereco.builder()
                        .enderecoId(PESSOA1_ID)
                        .cep(CEP1)
                        .estado(ESTADO1)
                        .cidade(CIDADE1)
                        .bairro(BAIRRO1)
                        .logradouro(LOGRADOURO1)
                        .numero(NUMERO1)
                        .complemento(COMPLEMENTO1)
                        .build())
                .build());

        pessoa = Pessoa.builder()
                .pessoaId(PESSOA_ID)
                .nome(NOME)
                .sobrenome(SOBRENOME)
                .cpf(CPF)
                .contato(Contato.builder()
                        .contatoId(PESSOA_ID)
                        .celular(CELULAR)
                        .email(EMAIL)
                        .build())
                .endereco(Endereco.builder()
                        .enderecoId(PESSOA_ID)
                        .cep(CEP)
                        .estado(ESTADO)
                        .cidade(CIDADE)
                        .bairro(BAIRRO)
                        .logradouro(LOGRADOURO)
                        .numero(NUMERO)
                        .complemento(COMPLEMENTO)
                        .build())
                .build();

        pessoa1 = Pessoa.builder()
                .pessoaId(PESSOA1_ID)
                .nome(NOME1)
                .sobrenome(SOBRENOME1)
                .cpf(CPF1)
                .contato(Contato.builder()
                        .contatoId(PESSOA1_ID)
                        .celular(CELULAR1)
                        .email(EMAIL1)
                        .build())
                .endereco(Endereco.builder()
                        .enderecoId(PESSOA1_ID)
                        .cep(CEP1)
                        .estado(ESTADO1)
                        .cidade(CIDADE1)
                        .bairro(BAIRRO1)
                        .logradouro(LOGRADOURO1)
                        .numero(NUMERO1)
                        .complemento(COMPLEMENTO1)
                        .build())
                .build();

        pessoaDeSaida = PessoaDtoSaida.builder()
                .pessoaId(PESSOA1_ID)
                .nome(NOME1)
                .sobrenome(SOBRENOME1)
                .cpf(CPF1)
                .contato(ContatoDtoSaida.builder()
                        .celular(CELULAR1)
                        .email(EMAIL1)
                        .build())
                .endereco(EnderecoDtoSaida.builder()
                        .cep(CEP1)
                        .estado(ESTADO1)
                        .cidade(CIDADE1)
                        .bairro(BAIRRO1)
                        .logradouro(LOGRADOURO1)
                        .numero(NUMERO1)
                        .complemento(COMPLEMENTO1)
                        .build())
                .build();

        pessoaDeSaida2 = PessoaDtoSaida.builder()
                .pessoaId(PESSOA_ID)
                .nome(NOME)
                .sobrenome(SOBRENOME)
                .cpf(CPF)
                .contato(ContatoDtoSaida.builder()
                        .celular("")
                        .email("")
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