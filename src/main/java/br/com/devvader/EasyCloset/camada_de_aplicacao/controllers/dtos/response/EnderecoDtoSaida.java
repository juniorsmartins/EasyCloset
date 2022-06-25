package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public final class EnderecoDtoSaida
{

    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private int numero;
    private String complemento;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDaUltimaModificacao;

    public EnderecoDtoSaida(Endereco endereco) {
        setCep(endereco.getCep());
        setEstado(endereco.getEstado());
        setCidade(endereco.getCidade());
        setBairro(endereco.getBairro());
        setLogradouro(endereco.getLogradouro());
        setNumero(endereco.getNumero());
        setComplemento(endereco.getComplemento());
    }
}
