package br.com.devvader.EasyCloset.camada_de_aplicacao.portas_de_controladores;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaAtualizar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaListar;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * @author JuniorMartins
 * @version 1
 */

@RestController
@RequestMapping("/v1/pessoas")
public interface IPessoaController {

    @PostMapping
    @Transactional
    ResponseEntity<?> cadastrar(@RequestBody @Valid PessoaDtoEntrada pessoaDtoEntrada, UriComponentsBuilder uriBuilder);

    @GetMapping
    ResponseEntity<?> listar(PessoaDtoEntradaListar pessoaDtoEntradaListar);

    @DeleteMapping("/{id}")
    @Transactional
    ResponseEntity<?> deletar(@PathVariable(name = "id") Long id);

    @PutMapping
    @Transactional
    ResponseEntity<?> atualizar(@RequestBody @Valid PessoaDtoEntradaAtualizar pessoaDtoEntradaAtualizar);
}
