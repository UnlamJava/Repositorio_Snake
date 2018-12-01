package servidor;

public class HiloGenerarFrutas extends Thread {

	private Juego juego;

	private boolean enCurso;
	
	public HiloGenerarFrutas(Juego juego) {
	
		this.juego = juego;
		
		this.enCurso = true;
	}
	
	public void run() {
		
		
		while(enCurso) {
			
			
			juego.generarFrutas();
	
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	public void detener() {
		this.enCurso = false;
	}
}
