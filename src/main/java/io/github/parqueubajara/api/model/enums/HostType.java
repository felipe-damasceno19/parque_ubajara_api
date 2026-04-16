package io.github.parqueubajara.api.model.enums;

public enum HostType {

    HOTEL("hotel"),
    ROOST("roost"),
    HOSTEL("hostel");

    private final String valor;

    HostType(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
