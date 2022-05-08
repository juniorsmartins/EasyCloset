package br.com.devvader.EasyCloset.resource.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "contatos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public final class Contato implements Serializable
{
    // ---------- MÉTODOS DE CLASSE ---------- //
    private static final long serialVersionUID = 1L;

    // ---------- MÉTODOS DE INSTÂNCIA ---------- //
    private String telefone;
    private String celular;
    private String email;
    // ----- Auditoria
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraUltimaAtualizacao;
}
