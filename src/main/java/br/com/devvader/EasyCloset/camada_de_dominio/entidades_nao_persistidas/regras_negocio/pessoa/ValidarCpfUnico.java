package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.regras_negocio.pessoa;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.MensagensPadronizadas;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RegraDeNegocioException;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class ValidarCpfUnico implements IPessoaRegrasDeNegocio {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public void validar(PessoaDtoEntrada pessoaDtoEntrada) {
        if (pessoaRepository.findByCpf(pessoaDtoEntrada.getCpf()).isPresent())
            throw new RegraDeNegocioException(MensagensPadronizadas.CPF_JA_CADASTRADO);
    }
}
