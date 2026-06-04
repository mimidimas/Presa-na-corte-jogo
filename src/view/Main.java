package view;

import java.util.Scanner;

import entity.Coroa;
import entity.GarotaHumana;
import entity.Gato;
import entity.Troll;
import entity.npc;
import usecase.ContratoUC;
import usecase.MapaUc;
import usecase.MissaoUc;

public class Main {
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";
	public static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		String nome = "";
		System.out.println(GREEN + "============ PRESA NA CORTE ============" + RESET);

		while (nome.isEmpty()) {
			System.out.println("Insira o nome da garota humana:");
			nome = scan.nextLine();
		}
		GarotaHumana garota = new GarotaHumana(nome);
		nome = YELLOW + garota.getNome() + RESET;

		System.out.println(nome + " é uma prisioneira da corte das fadas, até que um dia ela recebe uma proposta: ");

		System.out.println(CYAN + "ATENÇÃO! O nosso príncipe perdeu a coroa,"
				+ " caso a encontre em três dias, seus crimes serão perdoados." + RESET);

		System.out.println("Sem pensar duas vezes, " + nome + " aceita a proposta e sai em busca da coroa.");
		ContratoUC contrato = new ContratoUC(garota, null);
		contrato.assinaturaContrato();

		MissaoUc missao = new MissaoUc(garota);

		MapaUc mapa = new MapaUc(contrato, garota);

		while (contrato.validarPrazo()) {
			char opcao = menu(nome, mapa, garota, missao);
			if (opcao == 'X') {
				break;
			}

		}

		if (garota.getPrisioneira()) {
			System.out.println("\n" + RED + "========================================" + RESET);
			System.out.println(RED + contrato.contratoExpirado() + RESET);
			System.out.println("\n" + RED + "========================================" + RESET);

		} else {
			System.out.println("\n" + GREEN + "========================================" + RESET);
			System.out.println(GREEN + contrato.contratoCumprido() + RESET);
			System.out.println("\n" + GREEN + "========================================" + RESET);

		}
	}

	public static char menu(String nome, MapaUc mapa, GarotaHumana garota, MissaoUc missao) {
		char opcao = ' ';

		while (opcao != 'P' && opcao != 'C' && opcao != 'F' && opcao != 'M') {
			System.out.println("Para onde " + nome + " irá agora?");
			System.out.println(mapa.abrirMapa());
			opcao = Character.toUpperCase(scan.nextLine().charAt(0));
			switch (opcao) {
				case 'P':
					System.out.println(nome + mapa.viajaNoMapa("Pantano"));

					System.out.println(missao.coletarCoroa("Pantano"));
					break;
				case 'C':
					System.out.println(nome + mapa.viajaNoMapa("Castelo"));

					if (missao.validarCoroa()) {
						System.out.println("principe fala que a coroa é verdadeira");
						garota.setPrisioneira(false);
						return 'X'; // queba o laço caso a garota tenha a coroa verdadeira
					} else {
						System.out.println("principe fala que a coroa é falsa");

					}

					break;
				case 'M':
					System.out.println(nome + mapa.viajaNoMapa("Montanha"));
					System.out.println(missao.coletarCoroa("Montanha"));
					break;
				case 'F':
					System.out.println(nome + mapa.viajaNoMapa("Floresta"));
					System.out.println(missao.coletarCoroa("Floresta"));
					break;
				default:
					System.out.println(RED + "Local não encontrado" + RESET);
			}
		}
		return opcao;
	}
}
