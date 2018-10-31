package snakePKG;

public class ObjetoDelMapa {
	
	private int idObjeto;
	private Posicion posicion;
	
	public ObjetoDelMapa(int posicionX, int posicionY, int id) {
		this.idObjeto=id;
		this.posicion=new Posicion(posicionX, posicionY);
	}

	public int getPosicionX() {
		return posicion.getPosicionX();
	}
	
	public int getPosicionY() {
		return posicion.getPosicionY();
	}

	public int getIdObjeto(){
		
		return this.idObjeto;
	}
	
	
	

}

