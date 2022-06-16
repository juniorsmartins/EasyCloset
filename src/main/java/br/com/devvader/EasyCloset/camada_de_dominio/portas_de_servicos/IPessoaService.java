package br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface IPessoaService {

    ResponseEntity<?> cadastrar(PessoaDtoEntrada pessoaDtoEntrada, UriComponentsBuilder uriBuilder);
    ResponseEntity<?> listar(PessoaDtoEntrada pessoaDtoEntrada);

}
