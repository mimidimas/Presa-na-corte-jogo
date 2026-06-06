package usecase;

import entity.ContratoMagico;
import entity.GarotaHumana;
import entity.PrincipeFeerico;
import resources.Colors;

public class ContratoUC {

    private ContratoMagico contrato;
    private GarotaHumana garota;
    private PrincipeFeerico principe;
    private Colors color = new Colors();

    private int diasUsados = 0;

    public ContratoUC(GarotaHumana garota, PrincipeFeerico principe) {
        this.garota = garota;
        this.principe = principe;
        this.contrato = new ContratoMagico();
        this.contrato.setEstado("Pendente");

    }

    public void assinaturaContrato() {
        contrato = new ContratoMagico();// cria o contrato aqui
        garota.setPrisioneira(true); // ainda é prisioneira, só deixara de ser se achar a coroa
        contrato.setPrazo(3);
    }

    public boolean validarPrazo() {
        boolean prazo = diasUsados < contrato.getPrazo();
        return prazo;
    }

    public String contratoCumprido() {
        return color.YELLOW + garota.getNome() + color.RESET + "Foi Libertada" + "\n"
                + color.GREEN_BOLD + "   +====================================+" + "\n" +
                color.GREEN_BOLD + "   |      A COROA FOI ENCONTRADA        |" + "\n" +
                color.GREEN_BOLD + "   |      O CONTRATO FOI CUMPRIDO       |" + "\n" +
                color.GREEN_BOLD + "   +====================================+\n" + color.RESET;

    }

    public String contratoExpirado() {

        return color.YELLOW + garota.getNome() + color.RESET + "Foi nao Libertada" + "\n"
                + color.RED_BOLD + "   +====================================+" + "\n" +
                color.RED_BOLD + "   |      A COROA NAO FOI ENCONTRADA    |" + "\n" +
                color.RED_BOLD + "   |      O CONTRATO FOI QUEBRADO       |" + "\n" +
                color.RED_BOLD + "   +====================================+\n" + color.RESET;
    }

    public void passarDia() {
        // passa o dia quando ela entra no gastelo novamente
        diasUsados++;
    }

}
