package br.com.devvader.EasyCloset.domain.services.validacoes;

import br.com.devvader.EasyCloset.application.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.application.controllers.errors.ValidacaoException;
import br.com.devvader.EasyCloset.resource.repositories.PessoaRepository;
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
