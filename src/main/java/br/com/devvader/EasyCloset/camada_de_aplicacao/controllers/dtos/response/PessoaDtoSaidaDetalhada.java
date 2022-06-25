package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Pessoa;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class PessoaDtoSaidaDetalhada {

    private Long pessoaId;
    private String nome;
    private String sobrenome;
    private String cpf;
    private ContatoDtoSaida contato;
    private EnderecoDtoSaida endereco;
    private AuditoriaDtoSaida auditoria;

    public PessoaDtoSaidaDetalhada(Pessoa pessoa) {
        setPessoaId(pessoa.getPessoaId());
        setNome(pessoa.getNome());
        setSobrenome(pessoa.getSobrenome());
        setCpf(pessoa.getCpf());
        setContato(new ContatoDtoSaida(pessoa.getContato()));
        setEndereco(new EnderecoDtoSaida(pessoa.getEndereco()));
    }
}
