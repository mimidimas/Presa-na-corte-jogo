package usecase;

import entity.ContratoMagico;
import entity.GarotaHumana;
import entity.PrincipeFeerico;

public class ContratoUC {

    private ContratoMagico contrato;
    private GarotaHumana garota;
    private PrincipeFeerico principe;

    private int diasUsados = 0;
    private final int prazoContrato = 3;

    public ContratoUC(GarotaHumana garota, PrincipeFeerico principe) {
        this.garota = garota;
        this.principe = principe;

    }

    public void assinaturaContrato() {
        contrato = new ContratoMagico();// cria o contrato aqui
        garota.setPrisioneira(false); // não é mais prisioneira
    }

    public boolean validarPrazo() {
        boolean prazo = diasUsados <= prazoContrato;
        return prazo;
    }

    public String contratoExpirado() {
        garota.setPrisioneira(true);
        return "Contrato acabou e vc agora é prisioneira";
    }

    public String contratoCumprido() {
        garota.setPrisioneira(false);
        return "Contrato acabou e vcagora é livre";
    }

    public void passarDia() {
        // passa o dia quando ela entra no gastelo novamente
        diasUsados++;
    }

}
