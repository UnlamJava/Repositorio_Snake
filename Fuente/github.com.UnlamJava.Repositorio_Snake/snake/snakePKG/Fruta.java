package snakePKG;

public class Fruta extends ObjetoDelMapa{
	public static final int FRUTA_AGRANDA=1;
	public static final int FRUTA_ACHICA=2;
	private int idFruta;

	public Fruta(int posicionX, int posicionY, String idObjeto, int idFruta ) {
		super(posicionX,  posicionY, idObjeto);
		this.idFruta=idFruta;
	}

	public int getIdFruta() {
		return idFruta;
	}
	
}
