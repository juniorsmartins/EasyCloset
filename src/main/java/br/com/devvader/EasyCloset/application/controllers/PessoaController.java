package br.com.devvader.EasyCloset.application.controllers;

import br.com.devvader.EasyCloset.application.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.domain.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    private PessoaService pessoaService;

    // ---------- MÉTODOS CONTROLADORES ---------- //
    // ----- Cadastrar
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarPessoa(@RequestBody @Valid PessoaDtoEntrada pessoaDtoEntrada, UriComponentsBuilder uriBuilder) {
        return pessoaService.cadastrarPessoa(pessoaDtoEntrada, uriBuilder);
    }

    // ----- Listar
    // ----- Consultar
    // ----- Deletar
    // ----- Atualizar
}
