package usecase;

import java.util.ArrayList;
import java.util.List;

import entity.GarotaHumana;
import entity.Mapa;

public class MapaUc {
    private Mapa local;
    private ContratoUC contratoUC;
    private MissaoUc missaoUc;

    private List<String> lugaresNoMapa = new ArrayList<>();
    private List<String> locaisVisitados = new ArrayList<>();

    public MapaUc(ContratoUC contratoUC, GarotaHumana garota) {
        this.contratoUC = contratoUC;
        this.missaoUc = new MissaoUc(garota);
        this.local = new Mapa();
        this.local.setLocal("Castelo");

        lugaresNoMapa.add("Castelo");
        lugaresNoMapa.add("Pantano");
        lugaresNoMapa.add("Floresta");
        lugaresNoMapa.add("Montanha");
    }

    public List<String> abrirMapa() {
        List<String> lugaresPossiveis = new ArrayList<>(lugaresNoMapa);
        lugaresPossiveis.remove(local.getLocal()); // tira a posibilidade de ir pro lugar em que a personagem já esta
        return lugaresPossiveis; // retorna alista de opcoes de ligares que deve ser exibina no view
    }

    public String viajaNoMapa(String destino) {
        local.setLocal(destino); // define o lugar atual no mapa
        String mensagemRetorno = " viajou para: " + destino;

        // fluxo 1a ( avisando que que o local foi visitado já)
        if (locaisVisitados.contains(destino)) {
            mensagemRetorno += "\n[Aviso] Você já explorou este lugar antes.";
        } else {
            locaisVisitados.add(destino);
        }

        if (destino.equals("Castelo")) {
            contratoUC.passarDia(); // passa o dia quando vai pro castelo
        }

        // dependendo do lugar chama tal npc
        if (destino.equalsIgnoreCase("Pantano")) {
            missaoUc.getCharada("Pantano");
        } else if (destino.equalsIgnoreCase("Floresta")) {
            missaoUc.getCharada("Floresta");
        } else if (destino.equalsIgnoreCase("Castelo")) {
            missaoUc.getCharada("Castelo");
        } else if (destino.equalsIgnoreCase("Montanha")) {
            missaoUc.getCharada("Montanha");
        }
        return mensagemRetorno;
    }

}