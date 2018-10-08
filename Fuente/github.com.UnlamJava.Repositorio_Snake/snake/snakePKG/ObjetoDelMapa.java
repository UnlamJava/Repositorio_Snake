package snakePKG;

public class ObjetoDelMapa {
	public static final String ID_FRUTA="Fruta";
	public static final String ID_OBSTACULO="Obstaculo";
	
	private String id;
	private Posicion posicion;
	public ObjetoDelMapa(int posicionX, int posicionY, String id) {
		this.id=id;
		this.posicion=new Posicion(posicionX, posicionY);
	}
	
}
