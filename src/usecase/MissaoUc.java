package usecase;

import entity.Coroa;
import entity.GarotaHumana;
import entity.Gato;
import entity.Guarda;
import entity.Troll;
import entity.npc;

public class MissaoUc {

    private GarotaHumana garota;
    private Coroa coroa;
    private boolean missaoAtiva;

    public MissaoUc(GarotaHumana garota) {

        this.garota = garota;
    }

    public String coletarCoroa(String local) {
        boolean acertouCharada = false;
        npc npc;
        if ("Pantano".equalsIgnoreCase(local)) {//////////////////////////////////////////////
            npc = new Troll();
            acertouCharada = npc.implementarMissao(); // chama a charada aqui e retornta se acertou ou não

        } else if ("Floresta".equalsIgnoreCase(local)) {///////////////////////////////////////
            npc = new Gato();
            acertouCharada = npc.implementarMissao(); // chama a charada aqui e retornta se acertou ou não

        } else if ("Montanha".equalsIgnoreCase(local)) { //// um lugar novo???////////////////////
            npc = new Guarda();
            acertouCharada = npc.implementarMissao(); // chama a charada aqui e retornta se acertou ou não

        }

        if (acertouCharada) {
            missaoAtribuida(local);
            return missaoConcluida();

        } else {
            return missaoFalha();
        }

    }

    public void missaoAtribuida(String local) {
        missaoAtiva = true; // ativei a missao

        // de acordo com o npc que passa a missao defina a coroa real
        if (local.equalsIgnoreCase("Pantano")) {
            coroa = new Coroa("Troll", true, false);
        } else if (local.equalsIgnoreCase("Montanha")) {
            coroa = new Coroa("Guarda", true, false);
        } else if (local.equalsIgnoreCase("Floresta")) {
            coroa = new Coroa("Gato", true, true);
        }

    }

    public boolean validarCoroa() {
        if (coroa == null) {
            return false;
        }
        return coroa.isReal();

    }

    public String missaoConcluida() {
        missaoAtiva = false;
        garota.getInventario().setItem("Coroa do : " + coroa.getDonoNome());
        garota.getInventario().setQuantidade(1);
        return " Você decifrou a charada e conseguiu a do " + coroa.getDonoNome();

    }

    public String missaoFalha() {
        missaoAtiva = false; // permite tentar outra charada ou outro lugar

        return "Você errou a charada e sai de mãos vazias";

    }
}
