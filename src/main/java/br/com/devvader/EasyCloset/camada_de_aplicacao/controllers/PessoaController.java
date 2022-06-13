package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos.PessoaServiceImpl;
import br.com.devvader.EasyCloset.camada_de_dominio.portas.IPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    @PostMapping("/v1/cadastrar")
    public ResponseEntity<?> cadastrarPessoa(@RequestBody @Valid PessoaDtoEntrada pessoaDtoEntrada, UriComponentsBuilder uriBuilder) {
        return iPessoaService.cadastrarPessoa(pessoaDtoEntrada, uriBuilder);
    }

    // ----- Listar
    // ----- Consultar
    // ----- Deletar
    // ----- Atualizar
}