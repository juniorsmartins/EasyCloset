package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.RoupaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.RoupaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.ICompraRepository;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.MensagensPadronizadas;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RecursoNaoEncontradoException;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RequisicaoInvalidaException;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IRoupaService;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.RoupaEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IPessoaRepository;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IRoupaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public final class RoupaServiceImpl implements IRoupaService {

    ModelMapper modelMapper;
    IPessoaRepository iPessoaRepository;
    IRoupaRepository iRoupaRepository;
    ICompraRepository iCompraRepository;

    // ----- Cadastrar
    @Override
    public ResponseEntity<?> cadastrar(RoupaDtoEntrada roupaDtoEntrada) {

        var pessoaEntity = iPessoaRepository
                .findById(roupaDtoEntrada.getPessoaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadronizadas.PESSOA_NAO_ENCONTRADA));

        final var roupaDeSaida = Optional.of(roupaDtoEntrada)
                .map(roupaDeEntrada -> modelMapper.map(roupaDeEntrada, RoupaEntity.class))
                .map(roupaEntityNova -> {
                    roupaEntityNova.setPessoa(pessoaEntity);
                    roupaEntityNova.getCompra().setRoupa(roupaEntityNova);
                    return iRoupaRepository.saveAndFlush(roupaEntityNova);
                })
                .map(roupaEntitySalva -> modelMapper.map(roupaEntitySalva, RoupaDtoSaida.class))
                .orElseThrow(() -> new RequisicaoInvalidaException(MensagensPadronizadas.REQUISICAO_INVALIDA));

        return ResponseEntity.created(URI.create("/" + roupaDeSaida.getRoupaId())).body(roupaDeSaida);
    }

    // ----- Listar
    @Override
    public ResponseEntity<?> listar(RoupaDtoEntrada roupaDtoEntrada) {
        return ResponseEntity
                .ok()
                .body(iRoupaRepository.findAll()
                        .stream()
                        .map(roupaDoDatabase -> modelMapper.map(roupaDoDatabase, RoupaDtoSaida.class))
                        .sorted(Comparator.comparing(RoupaDtoSaida::getRoupaId).reversed())
                        .collect(Collectors.toList()));
    }

    // ----- Consultar
    @Override
    public ResponseEntity<?> consultar(Long id) {
        return ResponseEntity
                .ok()
                .body(iRoupaRepository.findById(id)
                        .map(roupaEntity -> modelMapper.map(roupaEntity, RoupaDtoSaida.class))
                        .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadronizadas.RECURSO_NAO_ENCONTRADO)));
    }

    // ----- Deletar
    @Override
    public ResponseEntity<?> deletar(Long id) {
        return ResponseEntity
                .ok()
                .body(iRoupaRepository.findById(id)
                        .map(roupaEntity -> {
                            iRoupaRepository.delete(roupaEntity);
                            return iRoupaRepository.findAll().stream()
                                    .map(roupa -> modelMapper.map(roupa, RoupaDtoSaida.class))
                                    .sorted(Comparator.comparing(RoupaDtoSaida::getRoupaId).reversed())
                                    .collect(Collectors.toList());
                        }).orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadronizadas.RECURSO_NAO_ENCONTRADO)));
    }

    // ----- Atualizar
    @Override
    public ResponseEntity<?> atualizar(Long id, RoupaDtoEntrada roupaDtoEntrada) {
        return ResponseEntity
                .ok()
                .body(iRoupaRepository.findById(id)
                        .map(roupaDoDatabase -> {
                            var roupaAtualizada = modelMapper.map(roupaDtoEntrada, RoupaEntity.class);
                            roupaAtualizada.setRoupaId(roupaDoDatabase.getRoupaId());
                            roupaAtualizada.getCompra().setRoupa(roupaAtualizada);
                            return iRoupaRepository.saveAndFlush(roupaAtualizada);
                        }).map(roupaAtualizadaSalva -> modelMapper.map(roupaAtualizadaSalva, RoupaDtoSaida.class))
                        .orElseThrow(() -> new RecursoNaoEncontradoException(MensagensPadronizadas.RECURSO_NAO_ENCONTRADO)));
    }
}
