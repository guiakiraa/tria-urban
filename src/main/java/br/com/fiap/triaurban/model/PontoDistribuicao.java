package br.com.fiap.triaurban.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class PontoDistribuicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "O tipo do ponto é obrigatório")
    @Enumerated(EnumType.STRING)
    private TipoPontoEnum tipo;

    @NotEmpty(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotNull(message = "O ponto de distribuição deve ter um usuário")
    @ManyToOne
    private Usuario usuario;

    @NotNull(message = "O ponto de distribuição deve ter um endereço")
    @ManyToOne
    private Endereco endereco;
}
