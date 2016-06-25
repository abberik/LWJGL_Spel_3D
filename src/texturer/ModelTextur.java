package texturer;

public class ModelTextur {

	private int texturID;
	private float shinedamper = 1;
	private float reflektivitet = 0;
	
	private boolean hasTransparency = false;
	private boolean anvanderFakeLjus = false;
	
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

	public boolean isHasTransparency() {
		return hasTransparency;
	}

	public void setHasTransparency(boolean hasTransparency) {
		this.hasTransparency = hasTransparency;
	}

	public boolean isAnvanderFakeLjus() {
		return anvanderFakeLjus;
	}

	public void setAnvanderFakeLjus(boolean anvanderFakeLjus) {
		this.anvanderFakeLjus = anvanderFakeLjus;
	}

	
	
	
}
