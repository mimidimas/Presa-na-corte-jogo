package entity;

import java.util.List;

public class Guarda implements npc {

    private String missao = "testo da charada aqui ";

    private List<String> opcoes = List.of("opc1", "opc2", "opc3");

    @Override
    public void implementarMissao() {

    }

    public String getMissao() {
        return missao;
    }

    public List<String> getOpcoes() {
        return opcoes;
    }
}
