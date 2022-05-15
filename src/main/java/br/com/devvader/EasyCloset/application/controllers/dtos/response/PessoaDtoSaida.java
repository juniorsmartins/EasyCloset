package br.com.devvader.EasyCloset.application.controllers.dtos.response;

import br.com.devvader.EasyCloset.resource.persistence.Pessoa;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public final class PessoaDtoSaida {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    private Long pessoaId;
    private String nome;
    private String sobrenome;
    private String cpf;

    private ContatoDtoSaida contato;
    private EnderecoDtoSaida endereco;

    // ---------- CONSTRUTORES ---------- //
    public PessoaDtoSaida(Pessoa pessoa) {
        setPessoaId(pessoa.getPessoaId());
        setNome(pessoa.getNome());
        setSobrenome(pessoa.getSobrenome());
        setCpf(pessoa.getCpf());
        setContato(new ContatoDtoSaida(pessoa.getContato()));
        setEndereco(new EnderecoDtoSaida(pessoa.getEndereco()));
    }
}
