package entity;

import java.util.List;
import java.util.Scanner;

public class Troll implements npc {

	private String missao = " Troll texto da charada aqui ";

	private List<String> opcoes = List.of("opc1", "opc2", "opc3");

	@Override
	public Boolean implementarMissao() {
		String respostaCerta = opcoes.get(2);
		Scanner scan = new Scanner(System.in);

		String resposta;
		System.out.println("Para continuar pelo caminho do Pantano" +
				"\n" + "primeiro voce deve responder a charada");

		System.out.println();
		System.out.println(missao);

		System.out.println(opcoes);
		resposta = scan.nextLine();

		if (respostaCerta.equalsIgnoreCase(resposta)) {
			return true;
		} else {
			return false;
		}

	}

	public String getMissao() {
		return missao;
	}

	public List<String> getOpcoes() {
		return opcoes;
	}
}
