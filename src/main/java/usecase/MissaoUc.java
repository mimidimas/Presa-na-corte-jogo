package usecase;

import entity.Coroa;
import entity.Inventario;

public class MissaoUc {

    private Inventario inventario;
    private Coroa coroa;
    private boolean missaoAtiva;

    public MissaoUc(Inventario inventario) {
        this.inventario = inventario;
    }

    public boolean coletarCoroa(boolean resposta) {

        if (!missaoAtiva) { // verifica se a missao do npc esta ativa
            return false; // retorna falso se não tiver misao
        } else {

            return resposta; /*
                              * a View já manda a rsposta escolida(lá ela sabe se é a certa) entao ela ja vem
                              * verdadeiro
                              * se a resposta for averdadeira ela ganha a coroa se a resposta fora falca ela
                              * não ganha a coroa
                              */
        }
    }

    public void missaoAtribuida(String npc) {
        missaoAtiva = true; // ativei a missao

        // de acordo com o npc que passa a missao defina a coroa real
        if (npc.equals("Troll")) {
            coroa = new Coroa("Troll", true, false);
        } else if (npc.equals("Guarda")) {
            coroa = new Coroa("Guarda", true, false);
        } else if (npc.equals("Gato")) {
            coroa = new Coroa("Gato", true, true);
        }

    }

    public boolean validarCoroa() {
        boolean validacaoDaCoroa = false;

        validacaoDaCoroa = coroa.getReal();

        return validacaoDaCoroa;
    }

    public String missaoConcluida() {
        missaoAtiva = false;
        inventario.setItem("Coroa do : " + coroa.getDonoNome());
        inventario.setQuantidade(1);
        return " MENAGEM DE MISSAO TERMINADA COM A COROA";

    }

    public String missaoFalha() {
        missaoAtiva = false; // permite tentar outra charada ou outro lugar

        return "MENSAGEM DE MISSAO TERMINADA SEM RECEBER COROA";

    }
}
