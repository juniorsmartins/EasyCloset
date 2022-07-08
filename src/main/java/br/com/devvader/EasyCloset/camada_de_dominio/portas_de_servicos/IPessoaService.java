package br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaAtualizar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaListar;
import org.springframework.http.ResponseEntity;

public interface IPessoaService {

    ResponseEntity<?> cadastrar(PessoaDtoEntrada pessoaDtoEntrada);
    ResponseEntity<?> listar(PessoaDtoEntradaListar pessoaDtoEntradaListar);
    ResponseEntity<?> deletar(Long id);
    ResponseEntity<?> atualizar(PessoaDtoEntradaAtualizar pessoaDtoEntradaAtualizar);
    ResponseEntity<?> consultar(Long codigo);
}
