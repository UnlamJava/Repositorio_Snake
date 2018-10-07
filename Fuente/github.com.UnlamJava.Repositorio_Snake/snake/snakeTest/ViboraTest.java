package snakeTest;


import org.junit.Assert;
import org.junit.Test;

import snakePKG.Vibora;
public class ViboraTest {

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
		v1.UbicarViboraEnMapa(4, 4, 5);
		v1.Moverse(v1.DIRECCION_DERECHA);
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
			v1.Moverse(v1.DIRECCION_DERECHA);
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
		v1.Moverse(v1.DIRECCION_ARRIBA);
		//posicion de la Cabeza de la vibora
		Assert.assertEquals(8, v1.getPosicion().get(0).getPosicionX() );
		Assert.assertEquals(5, v1.getPosicion().get(0).getPosicionY() );
		//posicion de la primera parte del cuerpo
		Assert.assertEquals(8, v1.getPosicion().get(1).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(1).getPosicionY() );
		//posicion de la ultima parte del cuerpo
		Assert.assertEquals(7, v1.getPosicion().get(2).getPosicionX() );
		Assert.assertEquals(4, v1.getPosicion().get(2).getPosicionY() );
		
		v1.Moverse(v1.DIRECCION_ARRIBA);
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
		v1.UbicarViboraEnMapa(4, 4, 5);
		v1.ComerFruta();
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

		
	}
}
