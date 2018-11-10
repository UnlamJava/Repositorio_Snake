package snakeSinglePlayer;

import graficos.JVentanaGrafica;
import menu.JVentanaBasica;
import snakePKG.Mapa;
import snakePKG.Vibora;



public class Juego {
	private JVentanaGrafica jVentana;
     private Mapa mapa;
     private Vibora vibora;
     private boolean salir;
     private HiloMover hiloMover;
     private String opcionElegida;
     private final JVentanaBasica menu;
	 private HiloGenerarFruta hiloGenerarFruta;
     private Vibora viboraBot;
     private HiloMover hiloBot;
     public static int GAMELOOP = 100;
     
      public Juego(){
    	  
    	  this.salir = false; 	  
    	  this.opcionElegida = "JUGAR";
    	  this.menu = JVentanaBasica.iniciandoMenu();
    	
      }
      
      public void cargarJuego() {
    	  
    	  this.salir = false;
  
    	  this.mapa = new Mapa("Arena");
    	  
    	  this.vibora = new Vibora(1);
    	  
    	  this.viboraBot = new Vibora(2);
    	  
    	  this.mapa.ubicarViboraEnMapa(this.vibora,5,5);
    	  
    	  this.mapa.ubicarViboraEnMapa(this.viboraBot, 28,20);
    	  
    	  this.hiloMover = new HiloMover(vibora, mapa);
    	  
    	  this.hiloBot = new HiloMover(this.viboraBot, mapa);
  
    	  this.hiloGenerarFruta = new HiloGenerarFruta(this.mapa);
    	  
    	  this.jVentana = new JVentanaGrafica(this.mapa.getMapa(),this.vibora,this.hiloMover,this.hiloBot, this.hiloGenerarFruta, this.viboraBot);
    	  
      }
      
      public void iniciar(){
    	
	       while (!salir) { 
	    	   
	    	    inicarMenuPrincipal();
	    	    
	   			cargarJuego();
	   			
	   			juegoEnCurso();
	   			
	   			this.hiloMover.detener();
	   			
	   			this.hiloBot.detener();
	   			
	   			this.hiloGenerarFruta.detener();
	   		
	   			this.jVentana.setVisible(false);           
			}
	       
      }
      
      public void juegoEnCurso() {
    	  
    	  this.jVentana.setVisible(true);
			
    	  while(this.vibora.getTamanioVibora()>0) {
    		  
    		  this.jVentana.actualizarMapa(this.mapa.getMapa());
    		 
    	  }// en tiempo real
    	  
    				
      }

	public void inicarMenuPrincipal() {
		
		this.menu.setVisible(true);
		
		while(!this.opcionElegida.equals(this.menu.opcionDeMenu())) {
			
			//cada un segundo va preguntando al menu la opcion elegida
			try {
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.menu.setOpcionElegida("Inicio");
		
	}
}
