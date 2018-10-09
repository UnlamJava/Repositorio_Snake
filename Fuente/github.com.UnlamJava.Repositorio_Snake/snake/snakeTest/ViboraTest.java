package snakeTest;

import org.junit.Assert;
import org.junit.Test;

import snakePKG.Fruta;
import snakePKG.ObjetoDelMapa;
import snakePKG.Vibora;
public class ViboraTest {
	
	
	//para instanciar a una vibora es necesario primero instanciar un mapa en el cual
	// se movera.
	
	
	//Mapa map = new Mapa(Mapa.MAPA_1);
	//Vibora v = new Vibora(map, 1); // mapa e id
	
	// entonces el metodo ubicarEnMapa se podria llamar directamente en el constructor
	// de vibora ya que siempre va a saber en que mapa esta
	
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
		
	}
	
	@Test
	public void TestMoverse() {
		Vibora v1=new Vibora ();
		//ubico la vibora mirando para el centro del mapa (sentido para la derecha)
		v1.UbicarViboraEnMapa(4, 4, 5);
		v1.Moverse();
		//posicion de la Cabeza de la vibora
		Assert.assertEquals(5, v1.getPosicion().get(0).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(0).getPosicionY() );
		//posicion de la primera parte del cuerpo
		Assert.assertEquals(4, v1.getPosicion().get(1).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(1).getPosicionY() );
		//posicion de la ultima parte del cuerpo
		Assert.assertEquals(3, v1.getPosicion().get(2).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(2).getPosicionY() );
		
		//muevo tres posiciones a la derecha
		for(int i=1;i<=3;i++) {
			v1.Moverse();
			//posicion de la Cabeza de la vibora
			Assert.assertEquals(5+i, v1.getPosicion().get(0).getPosicionX() );
			Assert.assertEquals(4, v1.getPosicion().get(0).getPosicionY() );
			//posicion de la primera parte del cuerpo
			Assert.assertEquals(4+i, v1.getPosicion().get(1).getPosicionX() );
			Assert.assertEquals(4, v1.getPosicion().get(1).getPosicionY() );
			//posicion de la ultima parte del cuerpo
			Assert.assertEquals(3+i, v1.getPosicion().get(2).getPosicionX() );
			Assert.assertEquals(4, v1.getPosicion().get(2).getPosicionY() );
		}
		
		//cambio el sentid hacia arriba
		v1.setSentidoDireccion(v1.DIRECCION_ARRIBA);
		v1.Moverse();
		//posicion de la Cabeza de la vibora
		Assert.assertEquals(8, v1.getPosicion().get(0).getPosicionX() );
		Assert.assertEquals(5, v1.getPosicion().get(0).getPosicionY() );
		//posicion de la primera parte del cuerpo
		Assert.assertEquals(8, v1.getPosicion().get(1).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(1).getPosicionY() );
		//posicion de la ultima parte del cuerpo
		Assert.assertEquals(7, v1.getPosicion().get(2).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(2).getPosicionY() );
		v1.Moverse();
		//posicion de la Cabeza de la vibora
		Assert.assertEquals(8, v1.getPosicion().get(0).getPosicionX() );
		Assert.assertEquals(6, v1.getPosicion().get(0).getPosicionY() );
		//posicion de la primera parte del cuerpo
		Assert.assertEquals(8, v1.getPosicion().get(1).getPosicionX() );
		Assert.assertEquals(5, v1.getPosicion().get(1).getPosicionY() );
		//posicion de la ultima parte del cuerpo
		Assert.assertEquals(8, v1.getPosicion().get(2).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(2).getPosicionY() );
		
		
		
	}
	
	@Test
	public void ComerFruta() {
		
		Vibora v1=new Vibora ();
		Fruta fruta=new Fruta(4, 4, ObjetoDelMapa.ID_FRUTA, Fruta.FRUTA_AGRANDA);
		v1.UbicarViboraEnMapa(4, 4, 5);
		v1.comerFruta(fruta);
		Assert.assertEquals(1, v1.getCantidadFrutaComida());
		Assert.assertEquals(4 , v1.getTamanio());

		//posicion de la Cabeza de la vibora
		Assert.assertEquals(5, v1.getPosicion().get(0).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(0).getPosicionY() );
		//posicion de la primera parte del cuerpo
		Assert.assertEquals(4, v1.getPosicion().get(1).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(1).getPosicionY() );
		//posicion de la segunda parte del cuerpo
		Assert.assertEquals(3, v1.getPosicion().get(2).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(2).getPosicionY() );
		//posicion de la ultima parte del cuerpo
		Assert.assertEquals(2, v1.getPosicion().get(3).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(3).getPosicionY() );

		//fruta que achica
		Fruta fruta1=new Fruta(5, 4, ObjetoDelMapa.ID_FRUTA, Fruta.FRUTA_ACHICA);
		v1.comerFruta(fruta1);
		Assert.assertEquals(2, v1.getCantidadFrutaComida());
		Assert.assertEquals(3 , v1.getTamanio());

		Assert.assertEquals(5, v1.getPosicion().get(0).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(0).getPosicionY() );
		//posicion de la primera parte del cuerpo
		Assert.assertEquals(4, v1.getPosicion().get(1).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(1).getPosicionY() );
		//posicion de la segunda parte del cuerpo
		Assert.assertEquals(3, v1.getPosicion().get(2).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(2).getPosicionY() );
		
		
	}
}
