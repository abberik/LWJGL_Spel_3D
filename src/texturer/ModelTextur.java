package texturer;

public class ModelTextur {

	private int texturID;
	private float shinedamper = 1;
	private float reflektivitet = 0;
	
	public ModelTextur(int id) {
		this.texturID = id;
	}

	public int getID() {
		return texturID;
	}

	public int getTexturID() {
		return texturID;
	}

	public void setTexturID(int texturID) {
		this.texturID = texturID;
	}

	public float getShinedamper() {
		return shinedamper;
	}

	public void setShinedamper(float shinedamper) {
		this.shinedamper = shinedamper;
	}

	public float getReflektivitet() {
		return reflektivitet;
	}

	public void setReflektivitet(float reflektivitet) {
		this.reflektivitet = reflektivitet;
	}


	
	
}
