package view;

import java.util.Scanner;
import static java.lang.Thread.sleep;

import resources.Colors;
import entity.GarotaHumana;
import entity.PrincipeFeerico;
import usecase.ContratoUC;
import usecase.MapaUc;
import usecase.MissaoUc;

public class Main {
	public static final Scanner scan = new Scanner(System.in);
	public static final Colors color = new Colors();
	public static final PrincipeFeerico principe = new PrincipeFeerico();

	public static void main(String[] args) {
		String nome = "";
		digitar(color.GREEN_BOLD + "============ PRESA NA CORTE ============" + color.RESET);
		espera();
		while (nome.isEmpty()) {
			digitar("Insira o nome da garota humana: ");
			nome = scan.nextLine();
		}
		GarotaHumana garota = new GarotaHumana(nome);
		nome = color.YELLOW + garota.getNome() + color.RESET;

		espera();
		digitar("\n" + nome + " e uma prisioneira da corte das fadas," +
				"\nate que um dia ela recebe uma proposta: ");

		digitar(color.BLUE_BOLD + "ATENCAO! O nosso principe perdeu a coroa,"
				+ " caso a encontre em tres dias, seus crimes serao perdoados."
				+ "\nATENCAO! Toda vez que o castelo é visitado, um dia se passara!"
				+ color.RESET);
		espera();
		digitar("Sem pensar duas vezes, " + nome + " aceita a proposta e sai em busca da coroa.\n");

		ContratoUC contrato = new ContratoUC(garota, principe);
		contrato.assinaturaContrato();

		MissaoUc missao = new MissaoUc(garota);

		MapaUc mapa = new MapaUc(contrato, missao);

		while (contrato.validarPrazo()) {
			char opcao = menu(nome, mapa, garota, missao);
			if (opcao == 'X') {
				garota.setPrisioneira(false);
				break;
			}
		}

		if (garota.getPrisioneira()) {
			espera();

			digitar(contrato.contratoExpirado());
		} else {
			espera();
			digitar(contrato.contratoCumprido());
		}

	}

	public static char menu(String nome, MapaUc mapa, GarotaHumana garota, MissaoUc missao) {
		char opcao = ' ';
		String resposta;

		while (opcao != 'P' && opcao != 'C' && opcao != 'F' && opcao != 'M') {
			espera();
			digitar("Para onde " + nome + " ira agora?");
			String locaisDisponiveis = mapa.abrirMapa();
			digitar(locaisDisponiveis);
			opcao = Character.toUpperCase(scan.nextLine().charAt(0));

			if (locaisDisponiveis.equals("(C) Castelo") && opcao != 'C') {
				digitar(color.RED_BOLD
						+ "\n[AVISO] Você precisa obrigatoriamente retornar ao Castelo com seus resultados antes de viajar de novo!\n"
						+ color.RESET);
				opcao = ' '; // define a opcao forçar o loop
				continue; // Reinicia o laço while
			}

			switch (opcao) {
				case 'P':
					digitar(nome + mapa.viajaNoMapa("Pantano"));

					espera();
					digitar("\nAo se aproximar do Pantano coberto de musgo, " + nome
							+ "\n se depara com um " + color.GREEN + " Troll "
							+ "\n" + color.RESET + " emergindo da agua esverdeada. ");
					espera();
					digitar("Ao notar a garota, o Troll diz: ");
					espera();
					digitar(color.GREEN + "\nTroll: EIII, o que procura aqui?!\n");
					digitar(nome + color.YELLOW
							+ ": Olá Senhor Troll, sou uma prisioneira do reino, Serei libertada" +
							" \napenas se encontrar a coroa perdida do Principe, preciso da sua ajuda!"
							+ color.RESET);
					digitar(color.GREEN + "\nTroll: Uma coroa? Eu vi uma coroa!");
					digitar("\nTroll: Responda a essa charada e eu a entregarei para voce!\n" + color.RESET);
					espera();
					digitar(missao.getCharada("Pantano")); // mostra o txt da charada

					resposta = scan.nextLine();
					espera();
					digitar(missao.coletarCoroa(resposta));// tenta responder a charada
					break;

				case 'C':
					digitar(nome + mapa.viajaNoMapa("Castelo"));

					espera();
					digitar(nome + " entra no castelo, e ao se apresentar ao principe, ele diz: ");
					espera();
					if (garota.getInventario().getQuantidade() < 1) {
						digitar(color.BLUE
								+ "Principe: Ousas pisar aqui de maos vazias? Volte para sua cela entao,"
								+ "\n se nao pretende ser util e buscar minha coroa."
								+ "\nPrincipe: Voce passara o restante desse dia em sua cela apos esse desrespeito contra minha autoridade!"
								+ color.RESET);
						espera();
						digitar(nome + " passou o restante do dia no Castelo.");
					} else if (principe.verificarCoroa(missao.getCoroa())) {
						digitar(color.BLUE + "\nPrincipe: Finalmente! Agora va, suma desse castelo, suma desse reino. "
								+ "\nAproveite o resquicio de liberdade que encontrara fora daqui." + color.RESET);
						garota.setPrisioneira(false);
						espera();
						return 'X'; // quebra o laço caso a garota tenha a coroa verdadeira
					} else {
						digitar(color.BLUE + "\nPrincipe: O que e essa 'coroa'? Acha que sou ingenuo? "
								+ "\nEncontrou isso no lago do Pantano? Nos resquicios do antigo templo na Montanha? "
								+ color.RED
								+ "\nVoce passara o restante desse dia em sua cela apos esse desrespeito contra minha autoridade!"
								+ color.RESET);
						espera();
						digitar(nome + " passou o restante do dia no Castelo.");
						espera();
					}
					break;

				case 'M':
					digitar(nome + mapa.viajaNoMapa("Montanha"));

					espera();
					digitar("\nAo se aproximar da subida da Montanha, " + nome
							+ " foi parada na entrada, de onde conseguia observar um templo antigo"
							+ "\n e parcialmente destruido.\n");

					digitar(nome + color.YELLOW
							+ ": Olá Senhor Guarda, sou uma prisioneira do reino, serei libertada"
							+ "\napenas se encontrar a coroa perdida do principe, me ajude a encontrar!"
							+ color.RESET);
					espera();
					digitar("O " + color.CYAN + " Guarda " + color.RESET
							+ " que a parou, ao saber do seu objetivo, ofereceu: ");
					espera();
					digitar(color.CYAN
							+ "\nGuarda: Se respondes a minha pergunta corretamente,"
							+ " eres digna de retornar a coroa ao tirano."
							+ color.RESET);
					espera();

					digitar(missao.getCharada("Montanha")); // mostra o txt da charada

					resposta = scan.nextLine();
					espera();
					digitar(missao.coletarCoroa(resposta));// tenta responder a charada
					break;

				case 'F':
					digitar(nome + mapa.viajaNoMapa("Floresta"));

					espera();
					digitar("Chegando a floresta, " + nome
							+ " se aproxima de um pequeno gato derrubando macas de uma arvore.");
					espera();
					digitar("Ao notar a humana, o gato diz:");
					espera();
					digitar(color.PURPLE + "Gato: Por que perturbas a minha floresta? Esse lugar "
							+ "\nnao e para gente da sua rale, que nao compreende a verdadeira cultura.");
					espera();
					digitar(nome + color.YELLOW
							+ ": Olá Senhor Gato, sou uma prisioneira do reino, serei libertada"
							+ "\napenas se encontrar a coroa perdida do principe, me ajude a encontrar! Me desculpe por lhe incomodar!"
							+ color.RESET);
					espera();

					digitar(color.PURPLE
							+ "Gato: A coroa? A sim, esta comigo. Voce quer ela?"
							+ "\nResponda a uma pergunta e talvez a entregarei."
							+ color.RESET);
					espera();
					digitar(missao.getCharada("Floresta")); // mostra o txt da charada

					resposta = scan.nextLine();
					espera();
					digitar(missao.coletarCoroa(resposta));// tenta responder a charada
					break;

				default:
					digitar(color.RED + "Local nao encontrado" + color.RESET);
			}
		}
		return opcao;
	}

	public static void espera() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static void digitar(String texto) {
		int delay = 0;
		for (char caractere : texto.toCharArray()) {
			System.out.print(caractere);

			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

		}
		System.out.println();
	}
}
