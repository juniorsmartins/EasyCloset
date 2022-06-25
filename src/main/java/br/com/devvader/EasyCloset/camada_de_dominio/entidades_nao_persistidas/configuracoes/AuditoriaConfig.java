package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.configuracoes;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditoriaConfig {
}
