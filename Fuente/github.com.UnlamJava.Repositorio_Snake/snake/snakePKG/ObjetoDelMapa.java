package snakePKG;

public class ObjetoDelMapa {

	
	private int id;
	private Posicion posicion;
	public ObjetoDelMapa(int posicionX, int posicionY, int id) {
		this.id=id;
		this.posicion=new Posicion(posicionX, posicionY);
	}
	
}
