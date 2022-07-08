package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaAtualizar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaListar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.portas_de_controladores.IPessoaController;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author JuniorMartins
 * @version 1
 */

@RestController
@RequestMapping("/v1/pessoas")
public class PessoaControllerImpl implements IPessoaController {

    @Autowired
    private IPessoaService iPessoaService;

    @Override
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PessoaDtoEntrada pessoaDtoEntrada) {
        return iPessoaService.cadastrar(pessoaDtoEntrada);
    }

    @Override
    public ResponseEntity<?> listar(PessoaDtoEntradaListar pessoaDtoEntradaListar) {
        return iPessoaService.listar(pessoaDtoEntradaListar);
    }

    @Override
    public ResponseEntity<?> deletar(Long id) {
        return iPessoaService.deletar(id);
    }

    @Override
    public ResponseEntity<?> atualizar(@RequestBody @Valid PessoaDtoEntradaAtualizar pessoaDtoEntradaAtualizar) {
        return iPessoaService.atualizar(pessoaDtoEntradaAtualizar);
    }
}
