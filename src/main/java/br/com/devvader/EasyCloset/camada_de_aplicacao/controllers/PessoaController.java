package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
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
@RequestMapping("/pessoas")
public class PessoaController {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @Autowired
    private IPessoaService iPessoaService;

    // ---------- MÉTODOS CONTROLADORES ---------- //
    // ----- Cadastrar
    @PostMapping("/v1")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PessoaDtoEntrada pessoaDtoEntrada, UriComponentsBuilder
            uriBuilder) {
        return iPessoaService.cadastrar(pessoaDtoEntrada, uriBuilder);
    }

    // ----- Listar
    @GetMapping("/v1")
    public ResponseEntity<?> listar(PessoaDtoEntradaListar pessoaDtoEntradaListar) {
        return iPessoaService.listar(pessoaDtoEntradaListar);
    }

    // ----- Atualizar


    // ----- Deletar
    @DeleteMapping("/v1/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable(name = "id") Long id) {
        return iPessoaService.deletar(id);
    }
}
