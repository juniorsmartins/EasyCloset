package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.mappers;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaAtualizar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaListar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaidaDetalhada;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.PessoaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructPessoa {

    MapStructPessoa INSTANCE = Mappers.getMapper(MapStructPessoa.class);

    PessoaEntity converterPessoaDtoEntradaParaPessoa(PessoaDtoEntrada pessoaDtoEntrada);
    PessoaDtoSaida converterPessoaParaPessoaDtoSaida(PessoaEntity pessoa);


    PessoaEntity converterPessoaDtoEntradaListarParaPessoa(PessoaDtoEntradaListar pessoaDtoEntradaListar);
    PessoaDtoSaidaDetalhada converterPessoaParaPessoaDtoSaidaDetalhada(PessoaEntity pessoa);
    List<PessoaDtoSaida> converterListaDePessoasParaListaDePessoasDtoSaida(List<PessoaEntity> listaPessoas);
    PessoaEntity converterPessoaDtoEntradaAtualizarParaPessoa(PessoaDtoEntradaAtualizar pessoaDtoEntradaAtualizar);
}
