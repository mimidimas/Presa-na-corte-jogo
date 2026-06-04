package entity;

import java.time.LocalDate;

public class ContratoMagico {
    private LocalDate prazo;
    private String estado;

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
