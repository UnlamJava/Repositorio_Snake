package snakeTestPKG;

import snakePKG.Mapa;
import snakePKG.Posicion;
import snakePKG.Vibora;
import org.junit.Assert;
import org.junit.Test;

public class SnakeTestEntrega1 {

	//@Test
	public void ValidarUbicarInicialdeLaViboraEnMapa(){

		 Mapa mapa1 = new Mapa("Arena"); 
		 Vibora s1 = new Vibora();
		 mapa1.ubicarViboraEnMapa(s1, 3,3);
		 
		 /*VALIDO POSICION INCINIAL DE LA VIBORA*/
		 Assert.assertEquals(1,mapa1.getPosMatriz( new Posicion(3,3)));
	}
	
	
	//@Test
	public void ValidarUbicarViboraAlMoverse(){
		
		 Mapa mapa1 = new Mapa("Arena"); 
		 Vibora s1 = new Vibora();
		 mapa1.ubicarViboraEnMapa(s1, 3,3);
		 
		 /*VALIDO POSICION INCINIAL DE LA VIBORA*/
		 Assert.assertEquals(1,mapa1.getPosMatriz( new Posicion(3,3)));
		 
		 /*ME MUEVO DOS VECES A LA DERECHA*/
		 s1.mover("Derecha", mapa1);
		 s1.mover("Derecha", mapa1);
		 
		 /*VALIDO LA NUEVA POSICION DE LA VIBORA*/
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(3,3)));// POSICION VACIA
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(4,3)));// POSICION VACIA
		 Assert.assertEquals(1,mapa1.getPosMatriz( new Posicion(5,3)));// POSICION OCUPADA POR VIBORA
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(6,3)));// POSICION VACIA
		 
		 /*ME MUEVO DOS VECES HACIA ABAJO*/
		 s1.mover("Abajo", mapa1);
		 s1.mover("Abajo", mapa1);
		 /*VALIDO LA NUEVA POSICION DE LA VIBORA*/
		 Assert.assertEquals(0,mapa1.getPosMatriz( new Posicion(5,4)));// POSICION VACIA
		 Assert.assertEquals(1,mapa1.getPosMatriz( new Posicion(5,5)));// POSICION OCUPADA POR VIBORA
		 
		 /*ME MUEVO DOS VECES HACIA ABAJO*/
		 s1.mover("Arriba", mapa1);
		 Assert.assertEquals(1,mapa1.getPosMatriz( new Posicion(5,5)));// POSICION VACIA
	}
	//@Test
	public void ValidarColisionesEntreVivoras(){
		
		 Mapa mapa1 = new Mapa("Arena"); 
		 Vibora s1 = new Vibora();
		 mapa1.ubicarViboraEnMapa(s1, 3,3);
		 Vibora s2 = new Vibora();
		 mapa1.ubicarViboraEnMapa(s2, 5,3);
		 
		 Assert.assertEquals(1,mapa1.getPosMatriz( new Posicion(3,3)));// 
		 Assert.assertEquals(2,mapa1.getPosMatriz( new Posicion(5,3)));// 		 
		 
		 s1.mover("Derecha", mapa1);
		 s1.mover("Derecha", mapa1);
		 
		 Assert.assertEquals(false,s1.isEstoyVivo());// CHOCAN
		 Assert.assertEquals(false,s2.isEstoyVivo());// CHOCAN
	}
	
	//@Test 
	public void SalirDeMapa(){
		
		 Mapa mapa1 = new Mapa("Arena"); 
		 Vibora s1 = new Vibora(1);
		 mapa1.ubicarViboraEnMapa(s1, 3,3);
		 Assert.assertEquals(true, s1.isEstoyVivo());
		 for(int i= 0; i<7; i++){
			 mapa1.mostrarMapa();
			 s1.mover("Derecha", mapa1);
		 }
			 
		 mapa1.mostrarMapa();
		 Assert.assertEquals(false, s1.isEstoyVivo());
		
	}
	@Test
	public void ValidarColisionesEntreVivorasMurenAmbas(){
		
		 Mapa mapa1 = new Mapa("Arena"); 
		 Vibora s1 = new Vibora(1);
		 mapa1.ubicarViboraEnMapa(s1, 0,3);
		 Vibora s2 = new Vibora(2);
		 mapa1.ubicarViboraEnMapa(s2, 8,3);
		 
		 Assert.assertEquals(1,mapa1.getPosMatriz( new Posicion(0,3)));// 
		 Assert.assertEquals(2,mapa1.getPosMatriz( new Posicion(8,3)));// 		 
		 
		 for(int i = 0; i< 4; i++){
			 s1.mover("Derecha", mapa1);
			 s2.mover("Izquierda", mapa1);
			 mapa1.mostrarMapa();
		 }
			 
		 Assert.assertEquals(false,s1.isEstoyVivo());// CHOCAN
		 Assert.assertEquals(false,s2.isEstoyVivo());// CHOCAN
	}
}
