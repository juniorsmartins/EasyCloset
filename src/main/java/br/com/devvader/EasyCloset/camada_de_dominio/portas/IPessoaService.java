package br.com.devvader.EasyCloset.camada_de_dominio.portas;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface IPessoaService {

    ResponseEntity<?> cadastrarPessoa(PessoaDtoEntrada pessoaDtoEntrada, UriComponentsBuilder uriBuilder);

}
