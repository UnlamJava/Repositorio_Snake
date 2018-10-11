package snakePKG;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
//import java.util.Timer;

//import javax.swing.plaf.synth.SynthSeparatorUI;

public class Vibora {
	
	public static int id = 1;
	
	private int idVibora;
	private int cantidadFrutaComida;
	//private int tamanio;
	private List<CuerpoVibora> cuerpo;
	private int desplazamientoEnX;
	private int desplazamientoEnY;
	private int desplazamiento;
	private boolean estoyVivo;
	
	
	
	public Vibora(){
		this.idVibora = Vibora.id;
		id++;
		this.cantidadFrutaComida = 0;
		this.cuerpo = new ArrayList<CuerpoVibora>();
		CuerpoVibora cabeza = new CuerpoVibora(this.idVibora);
		this.cuerpo.add(0,cabeza);
		//this.tamanio = 1;	
		this.desplazamiento=1;
		this.estoyVivo = true;
	}
	
	public int getTamanioVibora(){
		return this.cuerpo.size();
	}
	public CuerpoVibora getCuerpoVibora(int pos) {
		return this.cuerpo.get(pos);
	}

	public void setCuerpo(List<CuerpoVibora> cuerpo) {
		this.cuerpo = cuerpo;
	}

	public void mover(String sentidoDelMovimiento, Mapa mapa){
				
		if(sentidoDelMovimiento=="Arriba") {
			this.desplazamientoEnX = 0;
			this.desplazamientoEnY = - this.desplazamiento;
		}
		if(sentidoDelMovimiento=="Abajo") {
			this.desplazamientoEnX = 0;
			this.desplazamientoEnY = this.desplazamiento;
		}
		if(sentidoDelMovimiento=="Izquierda") {
			this.desplazamientoEnX = -this.desplazamiento;
			this.desplazamientoEnY = 0;
		}
		if(sentidoDelMovimiento=="Derecha") {
			this.desplazamientoEnX = this.desplazamiento;
			this.desplazamientoEnY = 0;
		}
		//*********Esto va dentro del un evento que se ejecute cada cierto tiempo.********************//
		 // Cero representa que el mapa en esa ubicación quedo libre
		
		//Seteo las nuevas posiciones del cuerpo
		
		
		Posicion  posAux = new Posicion(this.cuerpo.get(this.cuerpo.size()-1).getPocision());
		System.out.println("tamanio: "+this.cuerpo.size());
		
		for(int i=this.cuerpo.size()-1; i>0 ;i--) {
			this.cuerpo.get(i).setPocision(this.cuerpo.get(i-1).getPocision());
			//System.out.println("pos: "+i+" "+this.cuerpo.get(i).getPocision().toString());
		}
		 
		
			//Seteo la nueva posicion de la cabeza de la vibora
		int x = this.cuerpo.get(0).getPocision().getPosicionX() + this.desplazamientoEnX;
		int y = this.cuerpo.get(0).getPocision().getPosicionY() + this.desplazamientoEnY;
		Posicion newPos = new Posicion(x,y);
		this.cuerpo.get(0).setPocision(newPos);
	
		/*System.out.println("CABEZA: "+this.cuerpo.get(0).getPocision().toString());
		
		if(this.cuerpo.size()==2)
		System.out.println("cuello: "+this.cuerpo.get(1).getPocision().toString());*/
		//System.out.println(this.cuerpo.get(0).getPocision().getPosicionX()+" "+this.cuerpo.get(0).getPocision().getPosicionY());
		
		
		if(this.caminoValido(mapa)) {
				
			 if(mapa.HayFruta(this.cuerpo.get(0).getPocision())) {
				 System.out.println("Hay fruta!!");
				
				this.crecer(posAux);
				 for(int i=0;i<this.cuerpo.size();i++) 
						mapa.setPosMatriz(this.cuerpo.get(i).getPocision(), this.cuerpo.get(i).getIdCuerpo());
				 
			    }
			    else {
			    	System.out.println("Me movi hacia "+sentidoDelMovimiento);
			    	for(int i=0;i<this.cuerpo.size();i++) 
			    	mapa.setPosMatriz(this.cuerpo.get(i).getPocision(), this.cuerpo.get(i).getIdCuerpo());
			    	
			    	mapa.setPosMatriz(posAux, 0);
			    }
			    	
			}
		else {
			  this.estoyVivo=false;
			  this.cuerpo.clear();
			  System.out.println("Me morii :c!!");
			
		     }
		
		//**********************************************************************************************//
			
	}
	
public boolean caminoValido(Mapa mapa){
	 //si choca con un obstaculo, otra vibora o los limites del mapa entonces el camino no es valido.
	 return mapa.getPosMatriz(this.cuerpo.get(0).getPocision())==0 || mapa.HayFruta(this.cuerpo.get(0).getPocision());
	 
}
	
	public void crecer(Posicion pos){
		CuerpoVibora cuerpo = new CuerpoVibora(2);
		cuerpo.setPocision(pos);
		this.cuerpo.add(cuerpo);
		System.out.println("Creciii!!!!");
		this.cantidadFrutaComida++;
	}
	
	
	public int getIdVibora() {
		return idVibora;
	}


	public void setIdVibora(int idVibora) {
		this.idVibora = idVibora;
	}


	public int getCantidadFrutaComida() {
		return cantidadFrutaComida;
	}


	public void setCantidadFrutaComida(int cantidadFrutaComida) {
		this.cantidadFrutaComida = cantidadFrutaComida;
	}
}
