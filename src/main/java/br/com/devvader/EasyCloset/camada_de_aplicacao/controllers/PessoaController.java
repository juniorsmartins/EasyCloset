package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaAtualizar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaListar;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PessoaController {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @Autowired
    private IPessoaService iPessoaService;

    // ---------- MÉTODOS CONTROLADORES ---------- //
    // ----- Cadastrar
    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PessoaDtoEntrada pessoaDtoEntrada, UriComponentsBuilder
            uriBuilder) {
        return iPessoaService.cadastrar(pessoaDtoEntrada, uriBuilder);
    }

    // ----- Listar
    @GetMapping
    public ResponseEntity<?> listar(PessoaDtoEntradaListar pessoaDtoEntradaListar) {
        return iPessoaService.listar(pessoaDtoEntradaListar);
    }

    // ----- Deletar
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable(name = "id") Long id) {
        return iPessoaService.deletar(id);
    }

    // ----- Atualizar
    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid PessoaDtoEntradaAtualizar pessoaDtoEntradaAtualizar) {
        return iPessoaService.atualizar(pessoaDtoEntradaAtualizar);
    }
}
