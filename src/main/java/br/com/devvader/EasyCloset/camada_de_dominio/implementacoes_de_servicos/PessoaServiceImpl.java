package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaAtualizar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaListar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaidaDetalhada;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.regras_negocio.pessoa.IPessoaRegrasDeNegocio;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.MensagensPadronizadas;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RecursoNaoEncontradoException;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RequisicaoInvalidaException;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IPessoaService;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.pessoa.PessoaEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IPessoaRepository;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;
    @Autowired
    private List<IPessoaRegrasDeNegocio> listaDeRegrasDeNegocio;

    private List<PessoaEntity> listaDePessoasSalvas;
    private List<PessoaDtoSaida> listaDePessoasDeSaida;
    private PessoaDtoEntradaListar filtrosParaPesquisa;
    private Example exampleFiltro;

    // ----- Cadastrar
    @Override
    public ResponseEntity<?> cadastrar(PessoaDtoEntrada pessoaDtoEntrada) {

        listaDeRegrasDeNegocio.forEach(regra -> regra.validar(pessoaDtoEntrada));

        final var pessoaDtoDeSaida = Optional.of(pessoaDtoEntrada)
                .map(pessoaDeEntrada -> modelMapper.map(pessoaDeEntrada, PessoaEntity.class))
                .map(pessoaEntity -> {
                            pessoaEntity.getContato().setPessoa(pessoaEntity);
                            pessoaEntity.getEndereco().setPessoa(pessoaEntity);
                            return iPessoaRepository.saveAndFlush(pessoaEntity);
                        })
                .map(pessoaEntity -> modelMapper.map(pessoaEntity, PessoaDtoSaida.class))
                .orElseThrow(() -> new RequisicaoInvalidaException(MensagensPadronizadas.REQUISICAO_INVALIDA));

        return ResponseEntity.created(URI.create("/" + pessoaDtoDeSaida.getPessoaId())).body(pessoaDtoDeSaida);
    }

    // ----- Listar
    @Override
    public ResponseEntity<?> listar(PessoaDtoEntradaListar pessoaDtoEntradaListar) {
        filtrosParaPesquisa = pessoaDtoEntradaListar;

        criarExampleConfiguradoPorExampleMatcher();
        listaDePessoasSalvas = iPessoaRepository.findAll(exampleFiltro);

        if(!listaDePessoasSalvas.isEmpty() && (filtrosParaPesquisa.getPessoaId() != null
                || filtrosParaPesquisa.getCpf() != null)) {
            return ResponseEntity.ok().body(modelMapper.map(listaDePessoasSalvas.get(0), PessoaDtoSaidaDetalhada.class));
        }

        return ResponseEntity.ok().body(listaDePessoasSalvas
                        .stream()
                        .map(pessoa -> modelMapper.map(pessoa, PessoaDtoSaida.class))
                        .collect(Collectors.toList())
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
            exampleFiltro = Example.of(modelMapper.map(filtrosParaPesquisa, PessoaEntity.class), matcher);
        }

    // ----- Consultar
    @Override
    public ResponseEntity<?> consultar(Long codigo) {
        return ResponseEntity.ok().body(
                iPessoaRepository.findById(codigo)
                        .map(pessoaEntity -> modelMapper.map(pessoaEntity, PessoaDtoSaida.class))
                        .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadronizadas.RECURSO_NAO_ENCONTRADO))
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
                            .map(pessoaEntity -> modelMapper.map(pessoaEntity, PessoaDtoSaida.class))
                            .sorted(Comparator.comparing(PessoaDtoSaida::getPessoaId).reversed())
                            .toList();
                    return ResponseEntity.ok().body(listaDePessoasDeSaida);
                }).orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadronizadas.RECURSO_NAO_ENCONTRADO));
    }

    // ----- Atualizar
    @Override
    public ResponseEntity<?> atualizar(PessoaDtoEntradaAtualizar pessoaDtoEntradaAtualizar) {

        // criar pattern de regras de negócio

        return iPessoaRepository.findById(pessoaDtoEntradaAtualizar.getPessoaId())
                .map(pessoaEntity -> {
                    var pessoaComDadosNovos = modelMapper.map(pessoaDtoEntradaAtualizar, PessoaEntity.class);
                    pessoaComDadosNovos.getContato().setPessoa(pessoaEntity);
                    pessoaComDadosNovos.getEndereco().setPessoa(pessoaEntity);
                    pessoaComDadosNovos.setDataDeCriacao(pessoaEntity.getDataDeCriacao());
                    var pessoaDeSaida = modelMapper.map(iPessoaRepository.save(pessoaComDadosNovos), PessoaDtoSaida.class);
                    return ResponseEntity.ok().body(pessoaDeSaida);
                }).orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadronizadas.RECURSO_NAO_ENCONTRADO));
    }
}

