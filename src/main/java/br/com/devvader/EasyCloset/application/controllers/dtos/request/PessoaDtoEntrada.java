package br.com.devvader.EasyCloset.application.controllers.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

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

    @NotBlank
    private ContatoDtoEntrada contato;
    private EnderecoDtoEntrada endereco;
}
