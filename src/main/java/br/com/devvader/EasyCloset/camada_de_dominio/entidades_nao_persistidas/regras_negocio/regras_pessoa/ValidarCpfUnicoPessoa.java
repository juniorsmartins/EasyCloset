package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.regras_negocio.regras_pessoa;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.MensagensPadronizadas;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.ValidacaoException;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class ValidarCpfUnicoPessoa implements IPessoaRegrasDeNegocio {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @Autowired
    private PessoaRepository pessoaRepository;

    // ---------- MÉTODOS DE VALIDAÇÃO ---------- //
    @Override
    public void validar(PessoaDtoEntrada pessoaDtoEntrada) {
        if (pessoaRepository.findByCpf(pessoaDtoEntrada.getCpf()).isPresent())
            throw new ValidacaoException(MensagensPadronizadas.CPF_NAO_UNICO);
    }
}
