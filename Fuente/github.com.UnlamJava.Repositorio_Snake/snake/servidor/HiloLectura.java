package servidor;

import java.io.IOException;

import javax.swing.JOptionPane;

//import mensaje.Mensaje;
import snakePKG.Vibora;
import util.ClienteConn;
import util.Mensaje;

public class HiloLectura extends Thread {
	private ClienteConn con;
	private Mensaje mensaje;
	private Servidor server;

	public HiloLectura(ClienteConn con, Servidor server) {
		this.con = con;
		this.server = server;
	}

	public void run() {/*
		boolean juegoOn = true;
		try {

			
			
			mensaje =con.recibirInfo();
			interpretarMensaje();

			while (true) {
				mensaje =con.recibirInfo();
				this.interpretarMensaje();
			}
		}  catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error al eliminar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void interpretarMensaje() throws IOException {
		if(this.mensaje.getId().equals("Iniciar Seccion")) {
			this.con.setUsuario(this.mensaje.getContenido());
			this.con.enviarInfo("Conectado", "");
		}
		
		if (this.mensaje.equals("Crear Sala")){
			server.salas.add(new Sala(this.con));
		}
	*/}
	
}
