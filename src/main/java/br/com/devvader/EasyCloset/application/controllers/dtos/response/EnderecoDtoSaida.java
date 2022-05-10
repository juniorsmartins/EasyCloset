package br.com.devvader.EasyCloset.application.controllers.dtos.response;

import br.com.devvader.EasyCloset.resource.persistence.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public final class EnderecoDtoSaida {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    private Long pessoaId;
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private int numero;
    private String complemento;

    // ---------- CONSTRUTORES ---------- //
    public EnderecoDtoSaida(Endereco endereco) {
        setPessoaId(endereco.getPessoaId());
        setCep(endereco.getCep());
        setEstado(endereco.getEstado());
        setCidade(endereco.getCidade());
        setBairro(endereco.getBairro());
        setLogradouro(endereco.getLogradouro());
        setNumero(endereco.getNumero());
        setComplemento(endereco.getComplemento());
    }
}
