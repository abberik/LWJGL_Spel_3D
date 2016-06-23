package modeller;

public class RaaModel {

	private int vaoID;
	private int antal_vertexar;

	public RaaModel(int vaoID, int antal_vertexar) {
		this.vaoID = vaoID;
		this.antal_vertexar = antal_vertexar;
	}

	public int getVaoID() {
		return vaoID;
	}

	public void setVaoID(int vaoID) {
		this.vaoID = vaoID;
	}

	public int getAntal_vertexar() {
		return antal_vertexar;
	}

	public void setAntal_vertexar(int antal_vertexar) {
		this.antal_vertexar = antal_vertexar;
	}

}
