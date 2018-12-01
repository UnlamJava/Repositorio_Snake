package snakeTestPKG;

import snakePKG.Fruta;
import snakePKG.Mapa;
import snakePKG.Obstaculo;
import snakePKG.Posicion;
import snakePKG.Vibora;
import org.junit.Assert;
import org.junit.Test;

public class SnakeTestEntrega1 {

	/*
	 * PARA TODOS LOS CASOS EL TAMAÑO INICIAL DE LA VIBORA ES 1
	 */
	@Test
	public void ValidarUbicacionInicialdeLaViboraEnMapa(){

		 Mapa mapa1 = new Mapa("Arena"); 
		 Vibora s1 = new Vibora(1);
		 mapa1.ubicarViboraEnMapa(s1, 3,3); 
		 /*VALIDO POSICION INICIAL DE LA VIBORA EN EL MAPA*/
		 Assert.assertEquals(1,mapa1.getPosMatriz( new Posicion(3,3)));
	}
	
	
	@Test
	public void ValidarUbicacionDeViboraAlMoverse(){
		
		 Mapa mapa1 = new Mapa("Arena"); 
		 Vibora s1 = new Vibora(2);
		 mapa1.ubicarViboraEnMapa(s1, 3,3);	 
		 /*ME MUEVO DOS VECES A LA DERECHA*/
		 s1.setSentidoMovActual("Derecha");
		 s1.moverMejorado();
		 s1.moverMejorado(); 
		 /*VALIDO LA NUEVA POSICION DE LA VIBORA*/
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(3,3)));// POSICION VACIA
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(4,3)));// POSICION VACIA
		 Assert.assertEquals(2,mapa1.getPosMatriz( new Posicion(5,3)));// POSICION OCUPADA POR VIBORA
		 /*ME MUEVO DOS VECES HACIA ABAJO*/
		 s1.setSentidoMovActual("Abajo");
		 s1.moverMejorado();
		 s1.moverMejorado(); 
		 /*VALIDO LA NUEVA POSICION DE LA VIBORA*/
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(5,3)));// POSICION VACIA
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(5,4)));// POSICION VACIA
		 Assert.assertEquals(2,mapa1.getPosMatriz( new Posicion(5,5)));// POSICION OCUPADA POR VIBORA	 
		 /*ME MUEVO DOS VECES HACIA LA IZQUIERDA*/
		 s1.setSentidoMovActual("Derecha");
		 s1.moverMejorado();
		 s1.moverMejorado();
		 /*VALIDO LA NUEVA POSICION DE LA VIBORA*/
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(5,5)));// POSICION VACIA
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(4,5)));// POSICION VACIA
		 Assert.assertEquals(2,mapa1.getPosMatriz( new Posicion(7,5)));// POSICION OCUPADA POR VIBORA
		 
		 /*ME MUEVO DOS VECES HACIA ARRIBA*/
		 s1.setSentidoMovActual("Arriba");
		 s1.moverMejorado();
		 s1.moverMejorado();
		 /*VALIDO LA NUEVA POSICION DE LA VIBORA*/
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(7,5)));// POSICION VACIA
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(7,4)));// POSICION VACIA
		 Assert.assertEquals(2,mapa1.getPosMatriz( new Posicion(7,3)));// POSICION OCUPADA POR VIBORA
	}
	
	@Test
	public void ValidoQueLaViboraNoPuedaRetroceder(){
		
		 Mapa mapa1 = new Mapa("Arena"); 
		 Vibora s1 = new Vibora(2);
		 mapa1.ubicarViboraEnMapa(s1, 3,3);	 
		 /*ME MUEVO 1 VEz A LA DERECHA*/
		 s1.setSentidoMovActual("Derecha");
		 s1.cambiarDir("Izquierda");
		 /*VALIDO LA NUEVA POSICION DE LA VIBORA*/
		 Assert.assertEquals(2,mapa1.getPosMatriz( new Posicion(3,3)));// POSICION OCUPADA POR LA VIBORA
		 /*INTENTO MOVERME EN EL SENTIDO CONTRARIO AL QUE TENIA*/
		 s1.setSentidoMovActual("Izquierda");
		 s1.cambiarDir("Derecha");
		 s1.moverMejorado();
		 /*VALIDO QUE AL INTENTAR MOVERME HACIA ATRAS NO CAMBIA LA POSICION*/
		
		 Assert.assertEquals(2,mapa1.getPosMatriz( new Posicion(2,3)));// POSICION OCUPADA POR LA VIBORAA
		 /*MISMA PRUEBA PERO CON LAS DIRECCIONES RESTANTES*/
		 Vibora s2 = new Vibora(3);
		 mapa1.ubicarViboraEnMapa(s2, 6,3);
		 s2.setSentidoMovActual("Abajo");
		 s2.cambiarDir("Arriba");
		 s2.moverMejorado();
		 Assert.assertEquals(3,mapa1.getPosMatriz( new Posicion(6,4)));// POSICION OCUPADA POR LA VIBORAA
	}
	
	@Test
	public void ValidarColisionDeVivoraConCuerpoDeOtra(){
		
		 Mapa mapa1 = new Mapa("Arena"); 
		 Vibora s1 = new Vibora(3);
		 mapa1.ubicarViboraEnMapa(s1, 4,4);
		 Vibora s2 = new Vibora(4);
		 mapa1.ubicarViboraEnMapa(s2, 6,4);
		 /*VALIDO POSICION INICIAL DE VIBORAS*/
		 Assert.assertEquals(3,mapa1.getPosMatriz( new Posicion(4,4)));//POSICION OCUPADA POR VIBORA S1 
		 Assert.assertEquals(4,mapa1.getPosMatriz( new Posicion(6,4)));//POSICION OCUPADA POR VIBORA S2 		 
		 
		 s2.setSentidoMovActual("Arriba");
		 s2.moverMejorado();
		 s1.setSentidoMovActual("Derecha");
		 s1.moverMejorado();
		 /*VALIDO QUE AMBAS VIBORAS ESTEN VIVAS*/
		 Assert.assertEquals(true,s1.isEstoyVivo()); 
		 Assert.assertEquals(true,s2.isEstoyVivo());
		 s1.moverMejorado();
		 /*HAGO QUE S1 CHOQUE CON EL CUERPO DE S2*/
		 Assert.assertEquals(true,s1.isEstoyVivo()); //MURIO 
		 Assert.assertEquals(true,s2.isEstoyVivo());  //VIVE
		
	}
	@Test 
	public void ValidarQueViboraNoExcedaLosLimitesDelMapa(){
		
		 Mapa mapa1 = new Mapa("Arena"); 
		 Vibora s1 = new Vibora(5);
		 mapa1.ubicarViboraEnMapa(s1, 3,3);
		 s1.setSentidoMovActual("Izquierda");
		
		 Assert.assertEquals(true, s1.isEstoyVivo());
		 /*ME MUEVO PARA SALIR DE LOS LIMITES DEL MAPA*/
		 Assert.assertEquals(5,mapa1.getPosMatriz( new Posicion(3,3)));//POSICION OCUPADA POR VIBORA S1 
		 for(int i= 0; i<7; i++)
			 s1.moverMejorado();
		 
		 /*VALIDO QUE LA VIBORA MUERE AL SALIR DEL MAPA*/
		 Assert.assertEquals(false, s1.isEstoyVivo());
		
	}
	
	@Test
	public void ValidarColisionDeCabezasEntreVivoras(){
		
		 Mapa mapa1 = new Mapa("Arena"); 
		 Vibora s1 = new Vibora(6);
		 mapa1.ubicarViboraEnMapa(s1, 0,0);
		 Vibora s2 = new Vibora(7);
		 mapa1.ubicarViboraEnMapa(s2, 10,0);
		 s1.setSentidoMovActual("Derecha");
		 s2.setSentidoMovActual("Izquierda");
		 /*VALIDO POSICION INICIAL DE VIBORAS*/
		 Assert.assertEquals(6,mapa1.getPosMatriz( new Posicion(0,0)));// 
		 Assert.assertEquals(7,mapa1.getPosMatriz( new Posicion(10,0)));// 		 
		 /*AMBAS VIBORAS SE MUEVEN*/
		
		 for(int i = 0; i< 4; i++){
			 
			 
			 s2.moverMejorado();
			
			 s1.moverMejorado();
			 mapa1.verificarPosicionDeCabezas();
			 
		 }
		 mapa1.mostrarMapa();
		 System.out.println("**************************************");
		 
		 s1.moverMejorado();
		 mapa1.mostrarMapa();
		 s2.moverMejorado();
		
		 mapa1.verificarPosicionDeCabezas();
		 
		 System.out.println("**************************************");
		 mapa1.mostrarMapa();
		 /*VALIDO SI AMBAS VIBORAS MUEREN AL CHOCAR CABEZAS*/
		 Assert.assertEquals(false,s1.isEstoyVivo());
		 Assert.assertEquals(false,s2.isEstoyVivo());
	}
	
	@Test
	public void ValidarTamanioDeVoboraAlComerFruta(){
		
		 Mapa mapa1 = new Mapa("Arena"); 
		 Vibora s1 = new Vibora(6);
		 mapa1.ubicarViboraEnMapa(s1, 0,3);
		 /*LAS CORDENADAS DE LA FRUTA SON (3,3)*/
		 Assert.assertEquals(Fruta.FRUTA_AGRANDA,mapa1.getPosMatriz( new Posicion(3,3)));
		 /*LA VIBORA SE MUEVE HASTA COMER FRUTA*/
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(2,3))); // POSICION VACIA
		 Assert.assertEquals(6,mapa1.getPosMatriz( new Posicion(0,3))); // POSICION CABEZA VIBORA
		 s1.setSentidoMovActual("Derecha");
		 s1.moverMejorado();
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(2,3))); // POSICION VACIA
		 Assert.assertEquals(6,mapa1.getPosMatriz( new Posicion(1,3))); // POSICION CABEZA VIBORA
		 s1.moverMejorado();
		 s1.moverMejorado();
		 /*VALIDO QUE LA VIBORA OCUPE DOS CORDENADAS EN EL MAPA*/
		 Assert.assertEquals(6,mapa1.getPosMatriz( new Posicion(3,3))); // POSICION CUERPO VIBORA
		 Assert.assertEquals(6,mapa1.getPosMatriz( new Posicion(2,3))); // POSICION CABEZA VIBORA
		 
		
	}
	
	
	
	
}
