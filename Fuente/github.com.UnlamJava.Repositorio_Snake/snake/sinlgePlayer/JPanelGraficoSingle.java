package sinlgePlayer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import graficos.*;



import javax.swing.JPanel;

public class JPanelGraficoSingle extends JPanel {

	private Collection<Cuadrado> cuadrados;

	public JPanelGraficoSingle(Collection<Cuadrado> cuadrados) {
		
		this.cuadrados = cuadrados;
		setFocusable(true);
	}

	public void paintComponent(Graphics g) {
		
		
	    for(Cuadrado c : this.cuadrados) {
	    	
	    	if(c.esFruta()) {
	    		g.setColor(Color.RED);
	    		g.fillOval(c.getX() * Cuadrado.LADO , c.getY() * Cuadrado.LADO , Cuadrado.LADO  , Cuadrado.LADO);
	    	}	
	    	if(c.esJugador()) {
	    		g.setColor(c.getcodElemento() == Cuadrado.JUGADOR_1 ? Color.BLUE : Color.GREEN);
	    		g.fillRect(c.getX() * Cuadrado.LADO , c.getY() * Cuadrado.LADO , Cuadrado.LADO, Cuadrado.LADO);	
	    	}
	    	if(c.esObstaculo()) {
	    		g.setColor(Color.BLACK);
	    		g.fillRect(c.getX() * Cuadrado.LADO , c.getY() * Cuadrado.LADO , Cuadrado.LADO, Cuadrado.LADO);	
	    	}
	    	
			
	    }
	
	}
	
	public void setCuadrados(Collection<Cuadrado> cuadrados) {
		this.cuadrados = cuadrados;
	}
	

	
}