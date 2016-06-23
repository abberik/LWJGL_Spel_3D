package entities;

import org.lwjgl.util.vector.Vector3f;

import modeller.TextureradModell;

public class Entity {

	private TextureradModell model;

	private Vector3f position;

	private float rotX, rotY, rotZ;

	private float scale;

	public Entity(TextureradModell model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}

	public void okaPosition(float dx, float dy, float dz) {

		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;

	}

	public void okaRotation(float drx, float dry, float drz) {

		this.rotX += drx;
		this.rotY += dry;
		this.rotZ += drz;

	}

	public TextureradModell getModel() {
		return model;
	}

	public void setModel(TextureradModell model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

}
