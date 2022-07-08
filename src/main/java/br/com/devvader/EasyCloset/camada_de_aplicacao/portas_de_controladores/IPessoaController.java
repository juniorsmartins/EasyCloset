package br.com.devvader.EasyCloset.camada_de_aplicacao.portas_de_controladores;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaAtualizar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaListar;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author JuniorMartins
 * @version 1
 */

public interface IPessoaController {

    @PostMapping
    ResponseEntity<?> cadastrar(PessoaDtoEntrada pessoaDtoEntrada);

    @GetMapping
    ResponseEntity<?> listar(PessoaDtoEntradaListar pessoaDtoEntradaListar);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletar(@PathVariable(name = "id") Long id);

    @PutMapping
    ResponseEntity<?> atualizar(PessoaDtoEntradaAtualizar pessoaDtoEntradaAtualizar);

    @GetMapping("/{id}")
    ResponseEntity<?> consultar(@PathVariable(name = "id") Long codigo);
}
