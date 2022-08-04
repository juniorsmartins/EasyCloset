package br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.RoupaDtoEntrada;
import org.springframework.http.ResponseEntity;

public interface IRoupaService {

    ResponseEntity<?> cadastrar(RoupaDtoEntrada roupaDtoEntrada);
    ResponseEntity<?> listar(RoupaDtoEntrada roupaDtoEntrada);
    ResponseEntity<?> consultar(Long id);
    ResponseEntity<?> deletar(Long id);
    ResponseEntity<?> atualizar(Long id, RoupaDtoEntrada roupaDtoEntrada);
}
