package snakeSinglePlayer;

import graficos.JVentanaGrafica;
import snakePKG.Mapa;
import snakePKG.Vibora;



public class Juego {
	private JVentanaGrafica jVentana;
     private Mapa mapa;
     private Vibora vibora;
     private boolean salir;
     private HiloMover hiloMover;
     
      public Juego(){
    	  
    	  this.salir = false;
    	  
    	  this.mapa = new Mapa("Arena");
    	  
    	  this.vibora = new Vibora(4);
    	  
    	  this.mapa.ubicarViboraEnMapa(this.vibora,5,5);
    	  
    	  this.hiloMover = new HiloMover(vibora, mapa);
    	  
    	  this.jVentana = new JVentanaGrafica(this.mapa.getMapa(),this.vibora,this.hiloMover);
    	  
    	  }
      
public void iniciar(){
	
         this.jVentana.setVisible(true);
    	  
    	  //hiloMover.start();
    	  
    	  //this.mapa.mostrarMapa();
    	
    	  
	       while (!salir) { 
				
				//mapa = (Integer[][]) this.conn.recibirInfo();
						
				this.jVentana.actualizarMapa(this.mapa.getMapa());

				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    	  
      }
}
