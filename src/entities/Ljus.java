package entities;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class Ljus {

	private Vector3f position;
	private Vector3f farg;
	
	public Ljus(Vector3f position, Vector3f farg) {
		this.position = position;
		this.farg = farg;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getFarg() {
		return farg;
	}

	public void setFarg(Vector3f farg) {
		this.farg = farg;
	}

	
	
	
	
	
}
