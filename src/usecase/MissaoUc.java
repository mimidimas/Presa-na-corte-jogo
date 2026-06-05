package usecase;

import resources.Colors;

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

    public static final Colors color = new Colors();

    public MissaoUc(GarotaHumana garota) {
        this.garota = garota;
    }

    public String getCharada(String local) {

        if ("Pantano".equalsIgnoreCase(local)) {
            npcAtual = new Troll();
            String missao = color.GREEN + npcAtual.getMissao() + color.RESET + "\n" + npcAtual.getOpcoes();
            return missao;

        } else if ("Floresta".equalsIgnoreCase(local)) {
            npcAtual = new Gato();
            String missao =  color.PURPLE + npcAtual.getMissao() + color.RESET +"\n" + npcAtual.getOpcoes();
            return missao;

        } else if ("Montanha".equalsIgnoreCase(local)) {
            npcAtual = new Guarda();
            String missao = color.CYAN + npcAtual.getMissao() + color.RESET + "\n" + npcAtual.getOpcoes();
            return missao;
        }

        return "Esse local nao tem uma charada";
    }

    public String coletarCoroa(String resposta) {
        if (npcAtual == null) {
            return "NPC NULL";
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
        return "\n" + color.YELLOW + garota.getNome() + color.RESET
                + " decifrou a charada e conseguiu a " + color.YELLOW + "coroa do " 
                + coroa.getDonoNome() + color.RESET + "!";
    }

    public String missaoFalha() {
        // missaoAtiva = false; // permite tentar outra charada ou outro lugar
        return "\n" + color.YELLOW + garota.getNome() + color.RESET
                + " errou a charada e foi convidada a se retirar do local.";
    }

}
