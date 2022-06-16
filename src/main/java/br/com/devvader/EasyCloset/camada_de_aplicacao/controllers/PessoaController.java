package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PessoaDtoEntrada pessoaDtoEntrada, UriComponentsBuilder
            uriBuilder) {
        return iPessoaService.cadastrar(pessoaDtoEntrada, uriBuilder);
    }

    // ----- Listar
    @GetMapping("/v1")
    public ResponseEntity<?> listar(@RequestParam(required = false) PessoaDtoEntrada pessoaDtoEntrada,
                                       @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0,
                                               size = 25) Pageable paginacao) {
        return iPessoaService.listar(pessoaDtoEntrada);
    }

    // ----- Consultar
    // ----- Deletar
    // ----- Atualizar
}
