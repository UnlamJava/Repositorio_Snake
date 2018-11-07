package snakePKG;

public class CuerpoVibora{
  private int idCuerpo;
  private Posicion pocision;
  
  public CuerpoVibora(int id){
	  
	 this.idCuerpo = id;
	 pocision = new Posicion(0,0);
	 
  }

  public Posicion getPocision() {
		return this.pocision;
	}

	public void setPocision(Posicion pocision) {
		this.pocision = pocision;
	}

	public int getIdCuerpo() {
		return this.idCuerpo;
	}

	public void setIdCuerpo(int idCuerpo) {
		this.idCuerpo = idCuerpo;
	}
	
	
  
}
