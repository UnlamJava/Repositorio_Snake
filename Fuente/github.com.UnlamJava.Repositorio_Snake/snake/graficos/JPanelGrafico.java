package graficos;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;

import javax.swing.JPanel;

public class JPanelGrafico extends JPanel {

	private Collection<Cuadrado> cuadrados;
	

	
	public JPanelGrafico(Collection<Cuadrado> cuadrados) {	
		
		this.cuadrados = cuadrados;
		
		
	}

	public void paintComponent(Graphics g) {
		
		this.setBackground(Color.WHITE);
		
	    for(Cuadrado c : this.cuadrados) {
	    	
	    	if(c.esFruta()) {
	    		g.setColor(Color.RED);
	    		g.fillRect(c.getX() * Cuadrado.LADO , c.getY() * Cuadrado.LADO , Cuadrado.LADO, Cuadrado.LADO);	
	    	}else if(c.esJugador()) {
	    		
	    		g.setColor(Cuadrado.COLORES[c.getcodElemento() - 1]);
	    		
	    		g.fillRect(c.getX() * Cuadrado.LADO , c.getY() * Cuadrado.LADO , Cuadrado.LADO, Cuadrado.LADO);	
	    
	    	
	    	}else if(c.esObstaculo()) {
	    		g.setColor(Color.BLACK);
	    		g.fillRect(c.getX() * Cuadrado.LADO , c.getY() * Cuadrado.LADO , Cuadrado.LADO, Cuadrado.LADO);	
	    	}else {
	    		g.setColor(Color.WHITE);
	    		g.fillRect(c.getX() * Cuadrado.LADO , c.getY() * Cuadrado.LADO , Cuadrado.LADO, Cuadrado.LADO);	
	    	}
	    	
	    	
	    	
			
	    }
	
	}
	
	public void setCuadrados(Collection<Cuadrado> cuadrados) {
		this.cuadrados = cuadrados;
	}
	

	
}