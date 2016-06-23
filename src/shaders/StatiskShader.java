package shaders;

import org.lwjgl.util.vector.Matrix4f;

import entities.Kamera;
import verktygslada.Matematik;

public class StatiskShader extends ShaderProgram {

	private static final String VERTEX_FILE = "./src/shaders/vertexshader";
	private static final String FRAGMENT_FILE = "./src/shaders/fragmentshader";

	private int plats_forvandlingsmatris;
	private int plats_projektionsmatris;
	private int plats_vymatris;

	public StatiskShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "texturkoordinater");
	}

	@Override
	protected void getAllUniformLocation() {
		// TODO Auto-generated method stub
		plats_forvandlingsmatris = super.getUniformLocation("transformationsmatris");
		plats_projektionsmatris = super.getUniformLocation("projektionsmatris");
		plats_vymatris = super.getUniformLocation("vymatris");
	}

	public void laddaTransformationsMatris(Matrix4f matris) {
		super.loadMatrix(plats_forvandlingsmatris, matris);
	}

	public void laddaProjektionsMatris(Matrix4f projektion) {
		super.loadMatrix(plats_projektionsmatris, projektion);
	}

	public void laddaVyMatris(Kamera kamera) {
		Matrix4f vymatris = Matematik.SkapaVyMatris(kamera);
		super.loadMatrix(plats_vymatris, vymatris);
	}

}
