package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.mappers;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntradaListar;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaidaDetalhada;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MapStructPessoa {

    MapStructPessoa INSTANCE = Mappers.getMapper(MapStructPessoa.class);

    @Mapping(source = "pessoaId", target = "contato.pessoaId")
    @Mapping(source = "pessoaId", target = "endereco.pessoaId")
    Pessoa pessoaDtoEntradaToPessoa(PessoaDtoEntrada pessoaDtoEntrada);
    PessoaDtoSaida pessoaToPessoaDtoSaida(Pessoa pessoa);

    Pessoa pessoaDtoEntradaListarToPessoa(PessoaDtoEntradaListar pessoaDtoEntradaListar);
    PessoaDtoSaidaDetalhada pessoaToPessoaDtoSaidaDetalhada(Pessoa pessoa);
    List<PessoaDtoSaida> listaPessoaToListaPessoaDtoSaida(List<Pessoa> listaPessoas);
}
