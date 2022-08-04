package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.mappers;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.RoupaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.RoupaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.RoupaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapStructRoupa {

    MapStructRoupa INSTANCE = Mappers.getMapper(MapStructRoupa.class);

    RoupaEntity converterRoupaDtoEntradaParaRoupaEntity(RoupaDtoEntrada roupaDtoEntrada);
    RoupaDtoSaida converterRoupaEntityParaRoupaDtoSaida(RoupaEntity roupaEntity);
}
