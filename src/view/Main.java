package view;

import java.util.Scanner;
import static java.lang.Thread.sleep;

import resources.Colors;
import entity.GarotaHumana;
import usecase.ContratoUC;
import usecase.MapaUc;
import usecase.MissaoUc;

public class Main {
	public static final Scanner scan = new Scanner(System.in);
	public static final Colors color = new Colors();

	public static void main(String[] args) {
		String nome = "";
		System.out.println(color.GREEN_BOLD + "============ PRESA NA CORTE ============" + color.RESET);
		espera();
		while (nome.isEmpty()) {
			System.out.println("Insira o nome da garota humana:");
			nome = scan.nextLine();
		}
		GarotaHumana garota = new GarotaHumana(nome);
		nome = color.YELLOW + garota.getNome() + color.RESET;
		
		espera();
		System.out.println("\n" + nome + " e uma prisioneira da corte das fadas, ate que um dia ela recebe uma proposta: ");
		
		System.out.println(color.BLUE_BOLD + "ATENCAO! O nosso principe perdeu a coroa,"
				+ " caso a encontre em tres dias, seus crimes serao perdoados." + color.RESET);
		espera();
		System.out.println("Sem pensar duas vezes, " + nome + " aceita a proposta e sai em busca da coroa.\n");

		ContratoUC contrato = new ContratoUC(garota, null);
		contrato.assinaturaContrato();

		MissaoUc missao = new MissaoUc(garota);

		MapaUc mapa = new MapaUc(contrato, garota);

		while (contrato.validarPrazo()) {
			char opcao = menu(nome, mapa, garota, missao);
			if (opcao == 'X') {
				garota.setPrisioneira(false);
				break;
			}
		}

		if (garota.getPrisioneira()) {
			espera();
			System.out.println("\n" + color.RED_BOLD + "========================================\n");
			System.out.println(contrato.contratoExpirado());
			System.out.println("\n========================================" + color.RESET);
		} else {
			espera();
			System.out.println("\n" + color.GREEN_BOLD + "========================================\n");
			System.out.println(contrato.contratoCumprido());
			System.out.println("\n========================================" + color.RESET);
		}
	}

	public static char menu(String nome, MapaUc mapa, GarotaHumana garota, MissaoUc missao) {
		char opcao = ' ';
		String resposta;

		while (opcao != 'P' && opcao != 'C' && opcao != 'F' && opcao != 'M') {
			espera();
			System.out.println("Para onde " + nome + " ira agora?");
			System.out.println(mapa.abrirMapa());
			opcao = Character.toUpperCase(scan.nextLine().charAt(0));
			
			switch (opcao) {
				case 'P':
					System.out.println(nome + mapa.viajaNoMapa("Pantano"));

					espera();
					System.out.println("\nAo se aproximar do Pantano coberto de musgo, " + nome
									+ " se depara com um " + color.GREEN + " Troll " 
									+ color.RESET + " emergindo da agua esverdeada. ");
					espera();
					System.out.println("Ao notar a garota, o Troll diz: ");
					espera();
					System.out.println(color.GREEN + "\nEIII, o que procura aqui?!");
					System.out.println("Uma coroa? Eu vi uma coroa!");
					System.out.println("Responda a essa charada e eu a entregarei para voce!\n" + color.RESET);
					espera();
					System.out.println(missao.getCharada("Pantano")); // mostra o txt da charada

					resposta = scan.nextLine();
					espera();
					System.out.println(missao.coletarCoroa(resposta));// tenta responder a charada
					break;

				case 'C':
					System.out.println(nome + mapa.viajaNoMapa("Castelo"));

					espera();
					System.out.println(nome + " entra no castelo, e ao se apresentar ao principe, ele diz: ");
					espera();
					if (garota.getInventario().getQuantidade() < 1) {
						System.out.println( color.GREEN + "Ousas pisar aqui de maos vazias? Volte para sua cela entao, se nao pretende ser util e buscar minha coroa."
							+ "\nVoce passara o restante desse dia em sua cela apos esse desrespeito contra minha autoridade!" + color.RESET);
						espera();
						System.out.println(nome + " passou o restante do dia no Castelo.");
					} else if (missao.validarCoroa()) {
						System.out.println(color.GREEN + "\nFinalmente! Agora va, suma desse castelo, suma desse reino. "
										+ "Aproveite o resquicio de liberdade que encontrara fora daqui." + color.RESET);
						garota.setPrisioneira(false);
						return 'X'; // queba o laço caso a garota tenha a coroa verdadeira
					} else {
						System.out.println(color.GREEN + "\nO que e essa 'coroa'? Acha que sou ingenuo? "
										+ "\nEncontrou isso no lago do Pantano? Nos resquicios do antigo templo na Montanha? "
										+ "\nVoce passara o restante desse dia em sua cela apos esse desrespeito contra minha autoridade!" + color.RESET);
						espera();
						System.out.println(nome + " passou o restante do dia no Castelo.");
					}
					break;

				case 'M':
					System.out.println(nome + mapa.viajaNoMapa("Montanha"));

					espera();
					System.out.println("\nAo se aproximar da subida da Montanha, " + nome
									+ " foi parada na entrada, de onde conseguia observar um templo antigo e parcialmente destruido.");
					espera();
					System.out.println("O " + color.CYAN + " Guarda " + color.RESET + " que a parou, ao saber do seu objetivo, ofereceu: ");
					espera();
					System.out.println(color.CYAN + "\nSe respondes a minha pergunta corretamente, eres digna de retornar a coroa ao tirano." + color.RESET);
					espera();

					System.out.println(missao.getCharada("Montanha")); // mostra o txt da charada

					resposta = scan.nextLine();
					espera();
					System.out.println(missao.coletarCoroa(resposta));// tenta responder a charada
					break;

				case 'F':
					System.out.println(nome + mapa.viajaNoMapa("Floresta"));

					espera();
					System.out.println("Chegando a floresta, " + nome
									+ " se aproxima de um pequeno gato derrubando macas de uma arvore.");
					espera();
					System.out.println("Ao notar a humana, o gato diz:");
					espera();
					System.out.println(color.PURPLE + "Por que perturbas a minha floresta? Esse lugar "
									+ "nao e para gente da sua rale, que nao compreende a verdadeira cultura.");
					System.out.println("A coroa? A sim, esta comigo. Voce quer ela? Responda a uma pergunta e talvez a entregarei." + color.RESET);
					espera();
					System.out.println(missao.getCharada("Floresta")); // mostra o txt da charada

					resposta = scan.nextLine();
					espera();
					System.out.println(missao.coletarCoroa(resposta));// tenta responder a charada
					break;

				default:
					System.out.println(color.RED + "Local nao encontrado" + color.RESET);
			}
		}
		return opcao;
	}

	public static void espera() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.err.println( e );
		}
	}
}
