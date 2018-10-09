package snakeTest;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import snakePKG.Mapa;
import snakePKG.Vibora;

public class VivoraTestEntrega {

	@Test
	public void ValidarUbicarViboraEnMapa() {
		Vibora v1=new Vibora ();
		v1.UbicarViboraEnMapa(4, 4, 5);
		
		
		//Ubicacion incial en la parte izquierda del mapa
		//posicion de la Cabeza de la vibora
		Assert.assertEquals(4, v1.getPosicion().get(0).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(0).getPosicionY() );
		//posicion de la primera parte del cuerpo
		Assert.assertEquals(3, v1.getPosicion().get(1).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(1).getPosicionY() );
		//posicion de la ultima parte del cuerpo
		Assert.assertEquals(2, v1.getPosicion().get(2).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(2).getPosicionY() );
		

		//Ubicacion incial en la parte derecha del mapa
		Vibora v2=new Vibora ();
		v2.UbicarViboraEnMapa(6, 4, 5);
		//posicion de la Cabeza de la vibora
		Assert.assertEquals(6, v2.getPosicion().get(0).getPosicionX() );
		Assert.assertEquals(4, v2.getPosicion().get(0).getPosicionY() );
		//posicion de la primera parte del cuerpo
		Assert.assertEquals(7, v2.getPosicion().get(1).getPosicionX() );
		Assert.assertEquals(4, v2.getPosicion().get(1).getPosicionY() );
		//posicion de la ultima parte del cuerpo
		Assert.assertEquals(8, v2.getPosicion().get(2).getPosicionX() );
		Assert.assertEquals(4, v2.getPosicion().get(2).getPosicionY() );
		

		ArrayList<Vibora> jugadores = new ArrayList<Vibora>();
		jugadores.add(v1);
		jugadores.add(v2);
		Mapa map = new Mapa("Arena",jugadores);
		map.colisionEntreCabezasDeVoboras();
		
		//valido que las dos viboras esten vivas
		Assert.assertEquals(false, v1.isEstadoMuerta());
		Assert.assertEquals(false, v2.isEstadoMuerta());
		
		//se mueve la vibora v1 para chocar contra v2
		v1.Moverse();
		map.colisionEntreCabezasDeVoboras();
		Assert.assertEquals(false, v1.isEstadoMuerta());
		Assert.assertEquals(false, v2.isEstadoMuerta());
		
		v1.Moverse();
		map.colisionEntreCabezasDeVoboras();
		//valido que las dos serpientes esten muertas y que su tama�o sea 0
		Assert.assertEquals(true, v1.isEstadoMuerta());
		Assert.assertEquals(true, v2.isEstadoMuerta());
		
		Assert.assertEquals(0, v1.getTamanio());
		Assert.assertEquals(0, v2.getTamanio());
	}
}
