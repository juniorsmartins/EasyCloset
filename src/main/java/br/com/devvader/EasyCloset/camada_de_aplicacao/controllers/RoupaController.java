package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.RoupaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IRoupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author JuniorMartins
 * @version 1
 */
@RestController
@RequestMapping("/v1/roupas")
public class RoupaController {

    @Autowired
    IRoupaService iRoupaService;

    @PostMapping
    ResponseEntity<?> cadastrar(@RequestBody @Valid RoupaDtoEntrada roupaDtoEntrada) {
        return iRoupaService.cadastrar(roupaDtoEntrada);
    }

    @GetMapping
    ResponseEntity<?> listar(RoupaDtoEntrada roupaDtoEntrada) {
        return iRoupaService.listar(roupaDtoEntrada);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> consultar(@PathVariable(name = "id") Long id) {
        return iRoupaService.consultar(id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletar(@PathVariable(name = "id") Long id) {
        return iRoupaService.deletar(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> atualizar(@PathVariable(name = "id") Long id, @RequestBody @Valid RoupaDtoEntrada roupaDtoEntrada) {
        return iRoupaService.atualizar(id, roupaDtoEntrada);
    }
}
