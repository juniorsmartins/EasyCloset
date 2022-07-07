package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaAtualizar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaListar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaidaDetalhada;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.mappers.MapStructPessoa;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RecursoNaoEncontradoException;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.regras_negocio.pessoa.IPessoaRegrasDeNegocio;
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
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> cadastrar(PessoaDtoEntrada pessoaDtoEntrada, UriComponentsBuilder uriBuilder) {

        listaDeRegrasDeNegocio.forEach(regra -> regra.validar(pessoaDtoEntrada));

        final var pessoaDtoDeSaida = Optional.of(pessoaDtoEntrada)
                .map(mapStructPessoa::converterPessoaDtoEntradaParaEntity)
                .map(T -> cadastrarPessoa(T))
                .map(mapStructPessoa::converterEntityParaPessoaDtoSaida)
                .orElseThrow();
        return ResponseEntity.created(URI.create("/" + pessoaDtoDeSaida.getPessoaId())).body(pessoaDtoDeSaida);
    }

        private Pessoa cadastrarPessoa(Pessoa pessoa) {
            return iPessoaRepository.saveAndFlush(pessoa);
        }

        private void converterEntidadeParaSaida() {
            pessoaDeSaida = MapStructPessoa.INSTANCE.converterEntityParaPessoaDtoSaida(pessoaSalva);
        }

    // ----- Listar
    @Override
    public ResponseEntity<?> listar(PessoaDtoEntradaListar pessoaDtoEntradaListar) {
        filtrosParaPesquisa = pessoaDtoEntradaListar;

        criarExampleConfiguradoPorExampleMatcher();
        listaDePessoasSalvas = iPessoaRepository.findAll(exampleFiltro);

        if(!listaDePessoasSalvas.isEmpty() && (filtrosParaPesquisa.getPessoaId() != null
                || filtrosParaPesquisa.getCpf() != null)) {
            pessoaSalva = listaDePessoasSalvas.get(0);
            /*converterEntidadeParaSaidaDetalhada();*/
            return ResponseEntity.ok().body(pessoaDeSaidaDetalhada);
        }

        converterListaEntidadesParaSaida();
        return ResponseEntity.ok().body(listaDePessoasDeSaida);
    }

        private void criarExampleConfiguradoPorExampleMatcher() {
            // ExampleMatcher - permite configurar condições para serem aplicadas nos filtros
            ExampleMatcher matcher = ExampleMatcher
                    .matching()
                    .withIgnoreCase() // Ignore caixa alta ou baixa - quando String
                    .withIgnoreNullValues()
                    .withStringMatcher(ExampleMatcher
                            .StringMatcher.STARTING); // permite encontrar palavras tipo Like com Containing
            // Example - pega campos populados para criar filtros
/*            exampleFiltro = Example.of(modelMapper.map(filtrosParaPesquisa, Pessoa.class), matcher);*/
        }

        private void buscarTodos() {
            listaDePessoasSalvas = iPessoaRepository.findAll();
        }

/*        private void converterEntidadeParaSaidaDetalhada() {
            pessoaDeSaidaDetalhada = modelMapper.map(pessoaSalva, PessoaDtoSaidaDetalhada.class);
        }*/

        private void converterListaEntidadesParaSaida() {

/*            listaDePessoasDeSaida = listaDePessoasSalvas
                    .stream()
                    .map(PessoaDtoSaida::new)
                    .sorted(Comparator.comparing(PessoaDtoSaida::getPessoaId).reversed())
                    .toList();*/
        }

    // ----- Deletar
    @Override
    public ResponseEntity<?> deletar(Long id) {

        return iPessoaRepository.findById(id)
                .map(pessoa -> {
                    iPessoaRepository.delete(pessoa);
                    buscarTodos();
                    converterListaEntidadesParaSaida();
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
                    converterEntidadeParaSaida();
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