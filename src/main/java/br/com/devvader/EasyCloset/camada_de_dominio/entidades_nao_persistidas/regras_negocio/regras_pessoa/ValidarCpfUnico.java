package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.regras_negocio.regras_pessoa;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.ValidacaoException;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidarCpfUnico implements PessoaValidacoes {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @Autowired
    private PessoaRepository pessoaRepository;

    // ---------- MÉTODOS DE VALIDAÇÃO ---------- //
    @Override
    public void validar(PessoaDtoEntrada pessoaDtoEntrada) {
        var pessoaDoDatabase = pessoaRepository.findByCpf(pessoaDtoEntrada.getCpf());
        if (pessoaDoDatabase.isPresent())
            throw new ValidacaoException("CPF já cadastrado!");
    }
}
