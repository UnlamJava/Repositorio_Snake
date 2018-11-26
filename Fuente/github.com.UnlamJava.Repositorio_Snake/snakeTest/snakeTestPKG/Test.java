package snakeTestPKG;

import javax.activation.MailcapCommandMap;

import snakePKG.Mapa;
import snakePKG.Vibora;

public class Test {
	
	public static void main(String[] args) {
		
		Mapa mapa = new Mapa(Mapa.MAPA_1);
		
		Vibora v = new Vibora(1);
		
		mapa.ubicarViboraEnMapa(v);
		
		v.moverMejorado();
		
	}
}
