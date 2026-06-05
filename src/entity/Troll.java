package entity;

import java.util.List;

public class Troll implements Npc {
	private String nome;

	private String missao = " Troll texto da charada aqui ";

	private List<String> opcoes = List.of("opc1", "opc2", "opc3");

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

		return opcoes.get(2);
	}

	@Override
	public List<String> getOpcoes() {
		return opcoes;
	}

	@Override
	public Coroa getCoroa() {
		return new Coroa("Troll", true, false);
	}
}
