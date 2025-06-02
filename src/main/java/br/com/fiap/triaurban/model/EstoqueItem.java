package br.com.fiap.triaurban.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class EstoqueItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int quantidade;

    private LocalDateTime dataAtualizacao;

    @NotNull(message = "O estoque deve ter um item")
    @ManyToOne
    private Item item;

    @NotNull(message = "O estoque deve ter um ponto de distribuição")
    @ManyToOne
    private PontoDistribuicao pontoDistribuicao;
}
