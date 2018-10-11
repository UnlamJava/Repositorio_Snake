package paqueteSnake;

import org.junit.Assert;
import org.junit.Test;



public class SnakeTest {
@Test	
 public void prueba(){
	 
	 Mapa mapa1 = new Mapa("Arena");
	 mapa1.mostrarMapa();
	 
	 Vibora s1 = new Vibora();
	 mapa1.ubicarViboraEnMapa(s1);
	 
	 System.out.println("");
	 mapa1.mostrarMapa();
	 
	 s1.mover("Derecha", mapa1);
	 System.out.println("");
	 mapa1.mostrarMapa();
	 
	 s1.mover("Derecha", mapa1);
	 System.out.println("");
	 mapa1.mostrarMapa();
	 
	 s1.mover("Abajo", mapa1);
	 System.out.println("");
	 mapa1.mostrarMapa();
	 
	 s1.mover("Izquierda", mapa1);
	 System.out.println("");
	 mapa1.mostrarMapa();
	 
	 System.out.println("***************************************************************");
	 
	 s1.mover("Abajo", mapa1);
	 System.out.println("");
	 mapa1.mostrarMapa();
	 
	 System.out.println(s1.getCuerpoVibora(0).getIdCuerpo());
	 System.out.println("POS CABEZA: "+s1.getCuerpoVibora(0).getPocision().getPosicionX()+" "+s1.getCuerpoVibora(0).getPocision().getPosicionY());
	 
	 System.out.println(s1.getCuerpoVibora(1).getIdCuerpo());
	 System.out.println("POS CUELLO: "+s1.getCuerpoVibora(1).getPocision().getPosicionX()+" "+s1.getCuerpoVibora(1).getPocision().getPosicionY());
	 
	 System.out.println("***************************************************************");
	 
	 s1.mover("Abajo", mapa1);
	 System.out.println("");
	 mapa1.mostrarMapa();
	System.out.println("tamanio "+s1.getTamanioVibora());
	 System.out.println(s1.getCuerpoVibora(0).getIdCuerpo());
	 System.out.println("POS CABEZA: "+s1.getCuerpoVibora(0).getPocision().getPosicionX()+" "+s1.getCuerpoVibora(0).getPocision().getPosicionY());
	 
	 System.out.println(s1.getCuerpoVibora(1).getIdCuerpo());
	 System.out.println("POS CUELLO: "+s1.getCuerpoVibora(1).getPocision().getPosicionX()+" "+s1.getCuerpoVibora(1).getPocision().getPosicionY());
	 
System.out.println("***************************************************************");
	 
	 s1.mover("Derecha", mapa1);
	 System.out.println("");
	 mapa1.mostrarMapa();
	System.out.println("tamanio "+s1.getTamanioVibora());
	 System.out.println(s1.getCuerpoVibora(0).getIdCuerpo());
	 System.out.println("POS CABEZA: "+s1.getCuerpoVibora(0).getPocision().getPosicionX()+" "+s1.getCuerpoVibora(0).getPocision().getPosicionY());
	 
	 System.out.println(s1.getCuerpoVibora(1).getIdCuerpo());
	 System.out.println("POS CUELLO: "+s1.getCuerpoVibora(1).getPocision().getPosicionX()+" "+s1.getCuerpoVibora(1).getPocision().getPosicionY());
	 /*
	 Posicion pos = new Posicion(4,8);
     s1.getCuerpoVibora(1).setPocision(pos);
     
     for(int i=0;i<s1.getTamanioVibora();i++)
    	 System.out.println(s1.getCuerpoVibora(i).getPocision().toString());*/
	//Assert.assertEquals(pos.getPosicionX(), s1.getCuerpoVibora(1).getPocision().getPosicionX());
	//Assert.assertEquals(pos.getPosicionY(), s1.getCuerpoVibora(1).getPocision().getPosicionY());
 }
}
