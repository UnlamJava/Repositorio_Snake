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
		
		
	    for(Cuadrado c : this.cuadrados) {
	    	
	    	if(c.esFruta()) {
	    		g.setColor(Color.RED);
	    		g.fillOval(c.getX() * Cuadrado.LADO , c.getY() * Cuadrado.LADO , Cuadrado.LADO  , Cuadrado.LADO);
	    	}	
	    	else {
	    		g.setColor(Color.BLUE);
	    		g.fillRect(c.getX() * Cuadrado.LADO , c.getY() * Cuadrado.LADO , Cuadrado.LADO, Cuadrado.LADO);	
	    	}
	    		
	    	
			
	    }
	
	}
	
	public void setCuadrados(Collection<Cuadrado> cuadrados) {
		this.cuadrados = cuadrados;
	}
	

	
}