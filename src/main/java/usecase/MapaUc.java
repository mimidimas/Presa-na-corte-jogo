package usecase;

import java.util.ArrayList;
import java.util.List;

import entity.Mapa;

public class MapaUc {
    private Mapa local;
    private ContratoUC contratoUC;
    List<String> lugaresNoMapa = new ArrayList<>();

    public MapaUc(ContratoUC contratoUC) {
        this.contratoUC = contratoUC;
        this.local = new Mapa();
        lugaresNoMapa.add("Castelo");
        lugaresNoMapa.add("Pantano");
        lugaresNoMapa.add("Floresta");

    }

    public List<String> abrirMapa() {
        List<String> lugaresPossiveis = new ArrayList<>(lugaresNoMapa);
        lugaresPossiveis.remove(local.getLocal()); // tira a posibilidade de ir pro lugar em que a personagem já esta
        return lugaresPossiveis; // retorna alista de opcoes de ligares que deve ser exibina no view
    }

    public String viajaNoMapa(String destino) {
        local.setLocal(destino); // define o lugar atual no mapa

        if (destino.equals("Castelo")) {
            contratoUC.passarDia(); // passa o dia quando vai pro castelo
        }

        return "Você viajou pra : " + destino; // mensagem pro view mostrar da viagem
    }

}