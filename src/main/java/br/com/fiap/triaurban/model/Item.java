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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "O nome  é obrigatório")
    @Size(min = 3, max = 100, message = "O nome completo deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotNull(message = "O item deve ter uma categoria")
    @Enumerated(EnumType.STRING)
    private CategoriaItemEnum categoria;
}
