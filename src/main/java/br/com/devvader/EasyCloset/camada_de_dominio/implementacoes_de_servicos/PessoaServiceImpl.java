package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.PessoaDtoEntrada;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.ContatoDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.EnderecoDtoSaida;
import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response.PessoaDtoSaida;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.ValidacaoException;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.regras_negocio.regras_pessoa.IPessoaRegrasDeNegocio;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IPessoaService;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Contato;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Endereco;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Pessoa;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.ContatoRepository;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.EnderecoRepository;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private List<IPessoaRegrasDeNegocio> listaDeRegrasDeNegocio;

    private PessoaDtoEntrada pessoaDeEntrada;
    private Pessoa pessoaSalva;
    private PessoaDtoSaida pessoaDeSaida;
    private Contato contatoSalvo;
    private ContatoDtoSaida contatoDeSaida;
    private Endereco enderecoSalvo;
    private EnderecoDtoSaida enderecoDeSaida;

    // ---------- MÉTODOS DE SERVIÇO ---------- //
    // ----- Cadastrar
    @Override
    public ResponseEntity<?> cadastrar(PessoaDtoEntrada pessoaDtoEntrada, UriComponentsBuilder uriBuilder) {
        pessoaDeEntrada = pessoaDtoEntrada;

        // ----- Pattern
        try {
            listaDeRegrasDeNegocio.forEach(v -> v.validar(pessoaDeEntrada));
        }catch (ValidacaoException ve) {
            return ResponseEntity.badRequest().body(ve.getMessage());
        }

        converterEntradaParaEntidade();
        cadastrar();
        converterEntidadeParaSaida();

        URI uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoaDeSaida.getPessoaId()).toUri();
        return ResponseEntity.created(uri).body(pessoaDeSaida);
    }

        private void converterEntradaParaEntidade() {
            pessoaSalva = modelMapper.map(pessoaDeEntrada, Pessoa.class);
            contatoSalvo = modelMapper.map(pessoaDeEntrada.getContato(), Contato.class);
            enderecoSalvo = modelMapper.map(pessoaDeEntrada.getEndereco(), Endereco.class);
        }

        private void cadastrar() {
            pessoaRepository.saveAndFlush(pessoaSalva);
            cadastrarContato();
            cadastrarEndereco();
        }

            private void cadastrarContato() {
                contatoSalvo.setPessoa(pessoaSalva);
                contatoRepository.save(contatoSalvo);
                pessoaSalva.setContato(contatoSalvo);
            }

            private void cadastrarEndereco() {
                enderecoSalvo.setPessoa(pessoaSalva);
                enderecoRepository.save(enderecoSalvo);
                pessoaSalva.setEndereco(enderecoSalvo);
            }

        private void converterEntidadeParaSaida() {
            pessoaDeSaida = new PessoaDtoSaida(pessoaSalva);
        }

    // ----- Listar
    @Override
    public Page<PessoaDtoSaida> listar(PessoaDtoEntrada pessoaDtoEntrada, Pageable paginacao) {
        return null;
    }


    // ----- Consultar
    // ----- Deletar
    // ----- Atualizar
}
