package usecase;

import resources.Colors;

import java.util.List;

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
    private String formatarOpcoes(List<String> opcoes) {
        StringBuilder sb = new StringBuilder();
        char letra = 'A';
        for (String opcao : opcoes) {
            sb.append(letra).append(") ").append(opcao).append("\n");
            letra++;
        }
        return sb.toString().trim(); // Remove a quebra de linha sobrando no final
    }
    

    public String getCharada(String local) {

    	if ("Pantano".equalsIgnoreCase(local)) {
            npcAtual = new Troll();
            return color.GREEN + npcAtual.getMissao() + color.RESET + "\n" + formatarOpcoes(npcAtual.getOpcoes());

        } else if ("Floresta".equalsIgnoreCase(local)) {
            npcAtual = new Gato();
            return color.PURPLE + npcAtual.getMissao() + color.RESET +"\n" + formatarOpcoes(npcAtual.getOpcoes());

        } else if ("Montanha".equalsIgnoreCase(local)) {
            npcAtual = new Guarda();
            return color.CYAN + npcAtual.getMissao() + color.RESET + "\n" + formatarOpcoes(npcAtual.getOpcoes());
        }

        return "Esse local nao tem uma charada";
    }

    public String coletarCoroa(String resposta) {
    	if (npcAtual == null) {
            return "NPC NULL";
        }

        String respostaCerta = npcAtual.getResposta();
        String tentativa = resposta.trim(); // Limpa espaços acidentais antes e depois

        List<String> opcoes = npcAtual.getOpcoes();
        if (tentativa.equalsIgnoreCase("A") && opcoes.size() > 0) tentativa = opcoes.get(0);
        else if (tentativa.equalsIgnoreCase("B") && opcoes.size() > 1) tentativa = opcoes.get(1);
        else if (tentativa.equalsIgnoreCase("C") && opcoes.size() > 2) tentativa = opcoes.get(2);

        if (respostaCerta.equalsIgnoreCase(tentativa)) {
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
