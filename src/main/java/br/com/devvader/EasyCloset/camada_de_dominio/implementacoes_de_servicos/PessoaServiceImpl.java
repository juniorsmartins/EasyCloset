package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.ContatoDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.EnderecoDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.ContatoDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.EnderecoDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.errors.ValidacaoException;
import br.com.devvader.EasyCloset.camada_de_dominio.entities.regras_de_negocio.regras_pessoa.PessoaValidacoes;
import br.com.devvader.EasyCloset.camada_de_dominio.portas.IPessoaService;
import br.com.devvader.EasyCloset.camada_de_recursos.persistence.Contato;
import br.com.devvader.EasyCloset.camada_de_recursos.persistence.Endereco;
import br.com.devvader.EasyCloset.camada_de_recursos.persistence.Pessoa;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.ContatoRepository;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.EnderecoRepository;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@Service
public final class PessoaServiceImpl implements IPessoaService {

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
    @Override
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

            /*cadastrarContato();
            cadastrarEndereco();*/
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
