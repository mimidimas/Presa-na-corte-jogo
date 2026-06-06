package entity;

import java.util.List;

public class Guarda implements Npc {

    private String missao = "Existem quatro classes sociais na Corte das Fadas, e apesar das "
            + "\nfalsas simpatias do nosso principe feerico, a sua riqueza nao deixa a esconder "
            + "\na classe a qual sempre pertenceu."
            + "\nQual a classe de mais alto nivel da Corte das Fadas?";

    private List<String> opcoes = List.of("Presenters", "Drivers", "Entidades");

    @Override
    public String getMissao() {
        return missao;
    }

    @Override
    public String getResposta() {

        return opcoes.get(2);
    }

    @Override
    public List<String> getOpcoes() {
        return opcoes;
    }

    @Override
    public Coroa getCoroa() {
        return new Coroa("Guarda", true, false);
    }
}
