package usecase;

import entity.Coroa;
import entity.GarotaHumana;
import entity.Gato;
import entity.Guarda;
import entity.Troll;
import entity.Npc;

public class MissaoUc {

    private GarotaHumana garota;
    private Coroa coroa;
    // private boolean missaoAtiva;
    private Npc npcAtual;

    public MissaoUc(GarotaHumana garota) {

        this.garota = garota;
    }

    public String getCharada(String local) {

        if ("Pantano".equalsIgnoreCase(local)) {
            npcAtual = new Troll();
            String missao = npcAtual.getMissao() + "\n\n\n" + npcAtual.getOpcoes();
            return missao;

        } else if ("Floresta".equalsIgnoreCase(local)) {
            npcAtual = new Gato();
            String missao = npcAtual.getMissao() + "\n\n\n" + npcAtual.getOpcoes();
            return missao;

        } else if ("Montanha".equalsIgnoreCase(local)) {
            npcAtual = new Guarda();
            String missao = npcAtual.getMissao() + "\n\n\n" + npcAtual.getOpcoes();
            return missao;

        }
        return "este local não tem uma charada";
    }

    public String coletarCoroa(String resposta) {
        if (npcAtual == null) {
            return "erro PRERICO NPC NULL";
        }

        String respostaCerta = npcAtual.getResposta();

        if (respostaCerta.equalsIgnoreCase(resposta)) {
            coroa = npcAtual.getCoroa();
            garota.getInventario().setItem("Coroa do: " + coroa.getDonoNome());
            garota.getInventario().setQuantidade(1);
            return missaoConcluida();

        } else {
            return missaoFalha();
        }

    }

    public boolean validarCoroa() {
        if (coroa == null) {
            return false;
        }
        return coroa.isReal();

    }

    public String missaoConcluida() {
        return " Você decifrou a charada e conseguiu a do " + coroa.getDonoNome();
    }

    public String missaoFalha() {
        // missaoAtiva = false; // permite tentar outra charada ou outro lugar

        return "Você errou a charada e sai de mãos vazias";

    }

}
