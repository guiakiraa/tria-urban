package br.com.fiap.triaurban.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "O logradouro não pode estar em branco")
    @Size(max = 255, message = "O logradouro deve ter no máximo 255 caracteres")
    private String logradouro;

    @NotEmpty(message = "O número do endereço não pode ser em branco")
    private String numero;

    @NotEmpty(message = "O bairro não pode estar em branco")
    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
    private String bairro;

    @NotEmpty(message = "A cidade não pode estar em branco")
    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres")
    private String cidade;

    @Enumerated(EnumType.STRING)
    private UfEnum uf;

    @NotEmpty(message = "O CEP não pode estar em branco")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato XXXXX-XXX")
    private String cep;

    @Size(max = 255, message = "O complemento deve ter no máximo 255 caracteres")
    private String complemento;
}