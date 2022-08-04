package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.RoupaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IRoupaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public final class RoupaServiceImpl implements IRoupaService {

    // ----- Cadastrar
    @Override
    public ResponseEntity<?> cadastrar(RoupaDtoEntrada roupaDtoEntrada) {
        return null;
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
