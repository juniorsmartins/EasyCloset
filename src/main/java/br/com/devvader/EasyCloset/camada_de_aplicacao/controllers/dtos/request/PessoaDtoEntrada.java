package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class PessoaDtoEntrada {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    private ContatoDtoEntrada contato;
    @NotNull
    private EnderecoDtoEntrada endereco;
}
