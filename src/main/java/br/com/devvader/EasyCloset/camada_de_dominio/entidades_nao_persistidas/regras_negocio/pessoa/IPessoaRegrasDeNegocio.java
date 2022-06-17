package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.regras_negocio.pessoa;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;

public interface IPessoaRegrasDeNegocio {

    void validar(PessoaDtoEntrada pessoaDtoEntrada);
}
