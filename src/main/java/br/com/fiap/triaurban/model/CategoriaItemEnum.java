package br.com.fiap.triaurban.model;

public enum CategoriaItemEnum {
    ALIMENTO_PERECIVEL("Alimento Perecível"),
    ALIMENTO_NAO_PERECIVEL("Alimento Não Perecível"),
    HIGIENE("Higiene"),
    ROUPA("Roupa"),
    MOVEL("Móvel"),
    INFANTIL("Infantil"),
    ELETRONICO("Eletrônico");

    private final String descricao;

    CategoriaItemEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

