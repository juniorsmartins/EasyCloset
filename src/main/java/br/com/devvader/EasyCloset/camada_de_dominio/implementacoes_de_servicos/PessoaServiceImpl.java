package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaAtualizar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaListar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaidaDetalhada;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RecursoNaoEncontradoException;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.regras_negocio.pessoa.IPessoaRegrasDeNegocio;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IPessoaService;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Pessoa;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IContatoRepository;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IEnderecoRepository;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IPessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Comparator;
import java.util.List;

@Service
public final class PessoaServiceImpl implements IPessoaService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IPessoaRepository IPessoaRepository;
    @Autowired
    private IContatoRepository IContatoRepository;
    @Autowired
    private IEnderecoRepository IEnderecoRepository;
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
        pessoaDeEntrada = pessoaDtoEntrada;

        listaDeRegrasDeNegocio.forEach(regra -> regra.validar(pessoaDeEntrada));
        converterEntradaParaEntidade();
        cadastrar();
        converterEntidadeParaSaida();

        URI uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoaDeSaida.getPessoaId()).toUri();
        return ResponseEntity.created(uri).body(pessoaDeSaida);
    }

        private void converterEntradaParaEntidade() {
            pessoaSalva = modelMapper.map(pessoaDeEntrada, Pessoa.class);
        }

        private void cadastrar() {
            pessoaSalva.getContato().setPessoa(pessoaSalva);
            pessoaSalva.getEndereco().setPessoa(pessoaSalva);
            IPessoaRepository.saveAndFlush(pessoaSalva);
        }

        private void converterEntidadeParaSaida() {
            pessoaDeSaida = new PessoaDtoSaida(pessoaSalva);
        }

    // ----- Listar
    @Override
    public ResponseEntity<?> listar(PessoaDtoEntradaListar pessoaDtoEntradaListar) {
        filtrosParaPesquisa = pessoaDtoEntradaListar;

        criarExampleConfiguradoPorExampleMatcher();
        listaDePessoasSalvas = IPessoaRepository.findAll(exampleFiltro);

        if(!listaDePessoasSalvas.isEmpty() && (filtrosParaPesquisa.getPessoaId() != null
                || filtrosParaPesquisa.getCpf() != null)) {
            pessoaSalva = listaDePessoasSalvas.get(0);
            converterEntidadeParaSaidaDetalhada();
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
            exampleFiltro = Example.of(modelMapper.map(filtrosParaPesquisa, Pessoa.class), matcher);
        }

        private void buscarTodos() {
            listaDePessoasSalvas = IPessoaRepository.findAll();
        }

        private void converterEntidadeParaSaidaDetalhada() {
            pessoaDeSaidaDetalhada = modelMapper.map(pessoaSalva, PessoaDtoSaidaDetalhada.class);
        }

        private void converterListaEntidadesParaSaida() {

            listaDePessoasDeSaida = listaDePessoasSalvas
                    .stream()
                    .map(PessoaDtoSaida::new)
                    .sorted(Comparator.comparing(PessoaDtoSaida::getPessoaId).reversed())
                    .toList();
        }

    // ----- Deletar
    @Override
    public ResponseEntity<?> deletar(Long id) {

        return IPessoaRepository.findById(id)
                .map(pessoa -> {
                    IPessoaRepository.delete(pessoa);
                    buscarTodos();
                    converterListaEntidadesParaSaida();
                    return ResponseEntity.ok().body(listaDePessoasDeSaida);
                }).orElseThrow(() -> new RecursoNaoEncontradoException("{entidade.pessoa.nao-encontrada}"));
    }

    // ----- Atualizar
    @Override
    public ResponseEntity<?> atualizar(PessoaDtoEntradaAtualizar pessoaDtoEntradaAtualizar) {
        pessoaDeEntrada = modelMapper.map(pessoaDtoEntradaAtualizar, PessoaDtoEntrada.class);

        return IPessoaRepository.findById(pessoaDtoEntradaAtualizar.getPessoaId())
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