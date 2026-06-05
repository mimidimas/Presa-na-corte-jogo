package entity;

import java.util.List;

public class Gato implements Npc {
    private String nome;

    private String missao = "A familia Felidae tem um costume tradicional pouco apreciado por "
                            + "\noutras familias da Corte das Fadas. Desde filhotes, os felidaes sao "
                            + "\ninstruidos por suas maes na arte de derrubar objetos de qualquer superficie acessivel, "
                            + "\nna ausencia da mae, e esperado que os pequenos pratiquem essa arte no lugar dela."
                            + "\nQual o nome dado a esse costume hereditario?";

    private List<String> opcoes = List.of("Inversao de Dependencia Familiar", 
                                        "Substituicao da Rainha Liskov", 
                                        "Limpeza de Arquitetura");

    @Override
    public String getName() {
        return this.nome;
    }

    @Override
    public String getMissao() {
        return missao;
    }

    @Override
    public String getResposta() {

        return opcoes.get(1);
    }

    @Override
    public List<String> getOpcoes() {
        return opcoes;
    }

    @Override
    public Coroa getCoroa() {
        return new Coroa("Gato", true, true);
    }
}
