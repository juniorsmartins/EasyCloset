package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaAtualizar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaListar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaidaDetalhada;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.mappers.MapStructPessoa;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.regras_negocio.pessoa.IPessoaRegrasDeNegocio;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.MensagensPadronizadas;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RecursoNaoEncontradoException;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IPessoaService;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Pessoa;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IContatoRepository;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IEnderecoRepository;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public final class PessoaServiceImpl implements IPessoaService {

    @Autowired
    private IPessoaRepository iPessoaRepository;

    @Autowired
    private IContatoRepository iContatoRepository;

    @Autowired
    private IEnderecoRepository iEnderecoRepository;

    @Autowired
    private MapStructPessoa mapStructPessoa;

    @Autowired
    private List<IPessoaRegrasDeNegocio> listaDeRegrasDeNegocio;

    private PessoaDtoEntrada pessoaDeEntrada;
    private Pessoa pessoaSalva;
    private PessoaDtoSaida pessoaDeSaida;
    private PessoaDtoSaidaDetalhada pessoaDeSaidaDetalhada;
    private List<Pessoa> listaDePessoasSalvas;
    private List<PessoaDtoSaida> listaDePessoasDeSaida;
    private PessoaDtoEntradaListar filtrosParaPesquisa;
    private Example exampleFiltro;

    // ----- Cadastrar
    @Override
    public ResponseEntity<?> cadastrar(PessoaDtoEntrada pessoaDtoEntrada) {

        listaDeRegrasDeNegocio.forEach(regra -> regra.validar(pessoaDtoEntrada));

        final var pessoaDtoDeSaida = Optional.of(pessoaDtoEntrada)
                .map(MapStructPessoa.INSTANCE::converterPessoaDtoEntradaParaPessoa)
                .map(pes -> cadastrarPessoa(pes))
                .map(MapStructPessoa.INSTANCE::converterPessoaParaPessoaDtoSaida)
                .orElseThrow();
        return ResponseEntity.created(URI.create("/" + pessoaDtoDeSaida.getPessoaId())).body(pessoaDtoDeSaida);
    }

        private Pessoa cadastrarPessoa(Pessoa pessoa) {
            var pessoaSalva = iPessoaRepository.save(pessoa);
            pessoaSalva.getContato().setPessoa(pessoaSalva);
            pessoaSalva.getEndereco().setPessoa(pessoaSalva);
            return iPessoaRepository.save(pessoaSalva);
        }

    // ----- Listar
    @Override
    public ResponseEntity<?> listar(PessoaDtoEntradaListar pessoaDtoEntradaListar) {
        filtrosParaPesquisa = pessoaDtoEntradaListar;

        criarExampleConfiguradoPorExampleMatcher();
        listaDePessoasSalvas = iPessoaRepository.findAll(exampleFiltro);

        if(!listaDePessoasSalvas.isEmpty() && (filtrosParaPesquisa.getPessoaId() != null
                || filtrosParaPesquisa.getCpf() != null)) {
            return ResponseEntity.ok().body(MapStructPessoa.INSTANCE
                    .converterPessoaParaPessoaDtoSaidaDetalhada(listaDePessoasSalvas.get(0)));
        }

        return ResponseEntity.ok().body(MapStructPessoa.INSTANCE
                        .converterListaDePessoasParaListaDePessoasDtoSaida(listaDePessoasSalvas)
                        .stream()
                        .sorted(Comparator.comparing(PessoaDtoSaida::getPessoaId).reversed()));
    }

        private void criarExampleConfiguradoPorExampleMatcher() {
            ExampleMatcher matcher = ExampleMatcher
                    .matching()
                    .withIgnoreCase()
                    .withIgnoreNullValues()
                    .withStringMatcher(ExampleMatcher
                            .StringMatcher.EXACT);
            exampleFiltro = Example.of(MapStructPessoa.INSTANCE
                    .converterPessoaDtoEntradaListarParaPessoa(filtrosParaPesquisa), matcher);
        }

    // ----- Consultar
    @Override
    public ResponseEntity<?> consultar(Long codigo) {
        return ResponseEntity.ok().body(
                iPessoaRepository.findById(codigo)
                        .map(MapStructPessoa.INSTANCE::converterPessoaParaPessoaDtoSaida)
                        .orElseThrow(() -> new RecursoNaoEncontradoException(
                                MensagensPadronizadas.RECURSO_NAO_ENCONTRADO))
        );
    }

    // ----- Deletar
    @Override
    public ResponseEntity<?> deletar(Long id) {

        return iPessoaRepository.findById(id)
                .map(pessoa -> {
                    iPessoaRepository.delete(pessoa);
                    listaDePessoasDeSaida = iPessoaRepository.findAll()
                            .stream()
                            .map(MapStructPessoa.INSTANCE::converterPessoaParaPessoaDtoSaida)
                            .sorted(Comparator.comparing(PessoaDtoSaida::getPessoaId).reversed()).toList();
                    return ResponseEntity.ok().body(listaDePessoasDeSaida);
                }).orElseThrow(() -> new RecursoNaoEncontradoException("{entidade.pessoa.nao-encontrada}"));
    }




    // ----- Atualizar
    @Override
    public ResponseEntity<?> atualizar(PessoaDtoEntradaAtualizar pessoaDtoEntradaAtualizar) {
        /*pessoaDeEntrada = modelMapper.map(pessoaDtoEntradaAtualizar, PessoaDtoEntrada.class);*/

        return iPessoaRepository.findById(pessoaDtoEntradaAtualizar.getPessoaId())
                .map(pessoa -> {
                    pessoaSalva = pessoa;
                    atualizarPessoa();
                    /*converterEntidadeParaSaida();*/
                    return ResponseEntity.ok().body(pessoaDeSaida);
                }).orElseThrow(() -> new RecursoNaoEncontradoException("{entidade.pessoa.nao-encontrada}"));
    }

    private void atualizarPessoa() {
            pessoaSalva.setNome(pessoaDeEntrada.getNome());
            pessoaSalva.setSobrenome(pessoaDeEntrada.getSobrenome());
            pessoaSalva.setCpf(pessoaDeEntrada.getCpf());
            pessoaSalva.getContato().setCelular(pessoaDeEntrada.getContato().getCelular());
            pessoaSalva.getContato().setEmail(pessoaDeEntrada.getContato().getEmail());
            pessoaSalva.getEndereco().setCep(pessoaDeEntrada.getEndereco().getCep());
            pessoaSalva.getEndereco().setEstado(pessoaDeEntrada.getEndereco().getEstado());
            pessoaSalva.getEndereco().setCidade(pessoaDeEntrada.getEndereco().getCidade());
            pessoaSalva.getEndereco().setBairro(pessoaDeEntrada.getEndereco().getBairro());
            pessoaSalva.getEndereco().setLogradouro(pessoaDeEntrada.getEndereco().getLogradouro());
            pessoaSalva.getEndereco().setNumero(pessoaDeEntrada.getEndereco().getNumero());
            pessoaSalva.getEndereco().setComplemento(pessoaDeEntrada.getEndereco().getComplemento());
        }



}