package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.RoupaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.RoupaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.portas_de_controladores.ICompraRepository;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.MensagensPadronizadas;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RequisicaoInvalidaException;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IRoupaService;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.RoupaEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IRoupaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
@AllArgsConstructor
public final class RoupaServiceImpl implements IRoupaService {

    ModelMapper modelMapper;
    IRoupaRepository iRoupaRepository;
    ICompraRepository iCompraRepository;

    // ----- Cadastrar
    @Override
    public ResponseEntity<?> cadastrar(RoupaDtoEntrada roupaDtoEntrada) {

        final var roupaDeSaida = Optional.of(roupaDtoEntrada)
                .map(roupaDeEntrada -> modelMapper.map(roupaDeEntrada, RoupaEntity.class))
                .map(roupaNova -> {
                    var roupaSalva = iRoupaRepository.save(roupaNova);
                    roupaSalva.getCompra().setRoupa(roupaSalva);
                    return iRoupaRepository.saveAndFlush(roupaSalva);
                })
                .map(roupa -> modelMapper.map(roupa, RoupaDtoSaida.class))
                .orElseThrow(() -> new RequisicaoInvalidaException(MensagensPadronizadas.REQUISICAO_INVALIDA));

        return ResponseEntity.created(URI.create("/" + roupaDeSaida.getId())).body(roupaDeSaida);
    }

    // ----- Listar
    @Override
    public ResponseEntity<?> listar(RoupaDtoEntrada roupaDtoEntrada) {
        return null;
    }

    // ----- Consultar
    @Override
    public ResponseEntity<?> consultar(Long id) {
        return null;
    }

    // ----- Deletar
    @Override
    public ResponseEntity<?> deletar(Long id) {
        return null;
    }

    // ----- Atualizar
    @Override
    public ResponseEntity<?> atualizar(Long id, RoupaDtoEntrada roupaDtoEntrada) {
        return null;
    }
}