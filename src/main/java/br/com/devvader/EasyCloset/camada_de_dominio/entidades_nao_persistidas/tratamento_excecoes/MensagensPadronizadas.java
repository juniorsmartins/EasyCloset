package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes;

public final class MensagensPadronizadas {

    public static final String RECURSO_NAO_ENCONTRADO = "Recurso não encontrado no banco de dados.";
    public static final String PESSOA_NAO_ENCONTRADA = "Pessoa não encontrada no banco de dados.";
    public static final String ARQUIVO_NAO_ENCONTRADO = "Arquivo não encontrado no banco de dados.";

    public static final String DATABASE_SEM_CONEXAO = "Banco de dados sem conexão.";

    public static final String CPF_JA_CADASTRADO = "CPF - recurso único já cadastrado no banco de dados!";
    public static final String EXCECAO_NULL_POINTER = "Valores nulos são inválidos!";
    public static final String REQUISICAO_INVALIDA = "Não foi possível cadastrar!";
}
