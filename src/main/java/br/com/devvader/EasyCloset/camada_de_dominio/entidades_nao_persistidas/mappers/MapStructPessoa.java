package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.mappers;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaAtualizar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaListar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaidaDetalhada;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructPessoa {

    MapStructPessoa INSTANCE = Mappers.getMapper(MapStructPessoa.class);

    Pessoa converterPessoaDtoEntradaParaPessoa(PessoaDtoEntrada pessoaDtoEntrada);
    PessoaDtoSaida converterPessoaParaPessoaDtoSaida(Pessoa pessoa);


    Pessoa converterPessoaDtoEntradaListarParaPessoa(PessoaDtoEntradaListar pessoaDtoEntradaListar);
    PessoaDtoSaidaDetalhada converterPessoaParaPessoaDtoSaidaDetalhada(Pessoa pessoa);
    List<PessoaDtoSaida> converterListaDePessoasParaListaDePessoasDtoSaida(List<Pessoa> listaPessoas);
    Pessoa converterPessoaDtoEntradaAtualizarParaPessoa(PessoaDtoEntradaAtualizar pessoaDtoEntradaAtualizar);
}
