package entity;

import java.time.LocalDate;
import java.util.*;

public class ContratoMagico {
    private LocalDate prazo;
    private Boolean estado;

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
