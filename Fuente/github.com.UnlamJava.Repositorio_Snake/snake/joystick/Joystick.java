package joystick;


import cliente.Cliente;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class Joystick implements Runnable {

	private static Controller joystickXbox;
	private boolean isController = false;
	private Cliente cli;
	private int idSala;
	private boolean vivo = true;

	public Joystick(Cliente cli, int idSala) {
		
		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
		
		Controller[] cs = ce.getControllers();
		
		for (int i = 0; i < cs.length; i++) {
			
			if (cs[i].getType() == Controller.Type.GAMEPAD) {
				joystickXbox = cs[i];
				
				isController = true;
			}

		}
		this.cli = cli;
		this.idSala = idSala;

	}

	@Override
	public void run() {
		while (isController && vivo) {
			joystickXbox.poll();
			Component[] components = joystickXbox.getComponents();
			
			for (int j = 0; j < components.length; j++) {
				Component component = components[j];
				
				if(component.getIdentifier().toString() == "pov"){
					float valor = component.getPollData();
					if( valor == 1){
						cli.enviarTeclaIzquierda(idSala);
					}
					
					if( valor >= 0.25 && valor <0.5){
						cli.enviarTeclaArriba(idSala);
					}
					
					if( valor >= 0.5 && valor < 0.75){
						cli.enviarTeclaDerecha(idSala);
					}
					
					if( valor >= 0.75 && valor < 1){
						cli.enviarTeclaAbajo(idSala);
					}
				}
				
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void detener(){
		
		this.vivo = false;
		
	}
}
	

