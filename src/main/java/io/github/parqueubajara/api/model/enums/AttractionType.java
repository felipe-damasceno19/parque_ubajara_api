package io.github.parqueubajara.api.model.enums;

public enum AttractionType {

    PARK("park"),
    WATERFALL("waterfall"),
    MUSEUM("museum"),
    FARM("farm"),
    ROUTE("route"),
    MARKET("market");

    private final String valor;

    AttractionType(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
