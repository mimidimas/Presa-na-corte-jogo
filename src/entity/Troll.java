package entity;

import java.util.List;

public class Troll implements Npc {
	private String nome;

	private String missao = "Segundo os principios da Corte das Fadas, "
					+ "um troll nao deve possuir mais que uma funcao."
					+ "\nQual o nome desse principio?";

	private List<String> opcoes = List.of("Responsabilidade Unica", 
										"Segregacao de especificacoes", 
										"Substituicao da Rainha Liskov");

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

		return opcoes.get(0);
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
