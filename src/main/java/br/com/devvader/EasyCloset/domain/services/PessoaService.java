package br.com.devvader.EasyCloset.domain.services;

import br.com.devvader.EasyCloset.application.controllers.dtos.request.ContatoDtoEntrada;
import br.com.devvader.EasyCloset.application.controllers.dtos.request.EnderecoDtoEntrada;
import br.com.devvader.EasyCloset.application.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.application.controllers.dtos.response.ContatoDtoSaida;
import br.com.devvader.EasyCloset.application.controllers.dtos.response.EnderecoDtoSaida;
import br.com.devvader.EasyCloset.application.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.application.controllers.errors.ValidacaoException;
import br.com.devvader.EasyCloset.domain.services.validacoes.PessoaValidacoes;
import br.com.devvader.EasyCloset.resource.persistence.Contato;
import br.com.devvader.EasyCloset.resource.persistence.Endereco;
import br.com.devvader.EasyCloset.resource.persistence.Pessoa;
import br.com.devvader.EasyCloset.resource.repositories.ContatoRepository;
import br.com.devvader.EasyCloset.resource.repositories.EnderecoRepository;
import br.com.devvader.EasyCloset.resource.repositories.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@Service
public final class PessoaService {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ContatoRepository contatoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private List<PessoaValidacoes> validacoes;

    private PessoaDtoEntrada pessoaDtoEntrada;
    private Pessoa pessoaSalva;
    private PessoaDtoSaida pessoaDtoSaida;
    private ContatoDtoEntrada contatoDtoEntrada;
    private Contato contatoSalvo;
    private ContatoDtoSaida contatoDtoSaida;
    private EnderecoDtoEntrada enderecoDtoEntrada;
    private Endereco enderecoSalvo;
    private EnderecoDtoSaida enderecoDtoSaida;

    // ---------- MÉTODOS DE SERVIÇO ---------- //
    // ----- Cadastrar
    public ResponseEntity<?> cadastrarPessoa(PessoaDtoEntrada pessoaDtoEntrada, UriComponentsBuilder uriBuilder) {

        // ----- Pattern
        try {
            validacoes.forEach(v -> v.validar(pessoaDtoEntrada));
        }catch (ValidacaoException ve) {
            return ResponseEntity.badRequest().body(ve.getMessage());
        }

        this.pessoaDtoEntrada = pessoaDtoEntrada;
        contatoDtoEntrada = pessoaDtoEntrada.getContato();
        enderecoDtoEntrada = pessoaDtoEntrada.getEndereco();

        cadastrar();
        converterPessoaParaPessoaDeSaida();

        URI uri = uriBuilder.path("/v1/pessoas/cadastrar/{id}").buildAndExpand(pessoaDtoSaida.getPessoaId()).toUri();
        return ResponseEntity.created(uri).body(pessoaDtoSaida);
    }

        private void cadastrar() {
            pessoaSalva = pessoaRepository.saveAndFlush(modelMapper.map(pessoaDtoEntrada, Pessoa.class));

            cadastrarContato();
            cadastrarEndereco();
        }

            private void cadastrarContato() {
                contatoSalvo = contatoRepository.save(modelMapper.map(contatoDtoEntrada, Contato.class));
                pessoaSalva.setContato(contatoSalvo);
            }

            private void cadastrarEndereco() {
                enderecoSalvo = enderecoRepository.save(modelMapper.map(enderecoDtoEntrada, Endereco.class));
                pessoaSalva.setEndereco(enderecoSalvo);
            }

        private void converterPessoaParaPessoaDeSaida() {
            pessoaDtoSaida = new PessoaDtoSaida(pessoaSalva);
        }

    // ----- Listar
    // ----- Consultar
    // ----- Deletar
    // ----- Atualizar
}
