package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.mappers;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MapStructPessoa {

    MapStructPessoa INSTANCE = Mappers.getMapper(MapStructPessoa.class);

    Pessoa converterPessoaDtoEntradaParaEntity(PessoaDtoEntrada pessoaDtoEntrada);
    PessoaDtoSaida converterEntityParaPessoaDtoSaida(Pessoa pessoa);
}
