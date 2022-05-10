package br.com.devvader.EasyCloset.domain.services.validacoes;

import br.com.devvader.EasyCloset.application.controllers.dtos.request.PessoaDtoEntrada;

public interface PessoaValidacoes {

    void validar(PessoaDtoEntrada pessoaDtoEntrada);
}
