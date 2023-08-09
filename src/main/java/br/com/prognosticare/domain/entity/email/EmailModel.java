package br.com.prognosticare.domain.entity.email;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name="tb_email")
public class EmailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime sendDateEmail;

    private StatusEmail statusEmail;
}
