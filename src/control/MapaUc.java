package control;

import java.util.ArrayList;
import java.util.List;

import entity.Mapa;
import entity.GarotaHumana;
import resources.Colors;

public class MapaUc {
    private Mapa local;
    private ContratoUC contratoUC;
    private MissaoUc missaoUc;
    private GarotaHumana garota;

    private List<String> lugaresNoMapa = new ArrayList<>();
    private List<String> locaisVisitados = new ArrayList<>();

    public MapaUc(ContratoUC contratoUC, MissaoUc missao, GarotaHumana garota) {
        this.contratoUC = contratoUC;
        this.missaoUc = missao;
        this.garota = garota;
        this.local = new Mapa();
        this.local.setLocal("Castelo");

        lugaresNoMapa.add("(C) Castelo");
        lugaresNoMapa.add("(P) Pantano");
        lugaresNoMapa.add("(F) Floresta");
        lugaresNoMapa.add("(M) Montanha");
    }

    public String abrirMapa() {
        if (!local.getLocal().equalsIgnoreCase("Castelo")) {
            return "(C) Castelo";
        }
        // Se ela estiver no Castelo, ela pode ir para os outros lugares
        List<String> lugaresPossiveis = new ArrayList<>(lugaresNoMapa);
        lugaresPossiveis.removeIf(lugar -> lugar.contains("Castelo"));

        return String.join(" | ", lugaresPossiveis);
    }

    public String viajaNoMapa(String destino) {
        local.setLocal(destino); // define o lugar atual no mapa
        String mensagemRetorno = " viajou para: " + destino;

        // fluxo 1a ( avisando que que o local foi visitado já)
        if (locaisVisitados.contains(destino)) {
            Colors color = new Colors();
            mensagemRetorno += "\n" + color.RED + "[Aviso] Você já explorou este lugar antes." + color.RESET;
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