package servidor;


import java.io.IOException;

public class HiloActualizarLobby extends Thread{
	
	private Lobby lobby;
	
	
	public HiloActualizarLobby(Lobby lobby) {
		
		this.lobby = lobby;
	}

	
	public void run() {
		
		while(true) {
			
			try {
				this.lobby.enviarSalasAtodos();
			
				Thread.sleep(3000);
			
			} catch (IOException | InterruptedException e) {
				
				e.printStackTrace();
				break;
			}
			
		}
		
		
	}
	
}