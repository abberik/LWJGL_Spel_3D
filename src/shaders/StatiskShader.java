package shaders;

import org.lwjgl.util.vector.Matrix4f;

import entities.Kamera;
import entities.Ljus;
import verktygslada.Matematik;

public class StatiskShader extends ShaderProgram {

	private static final String VERTEX_FILE = "./src/shaders/vertexshader";
	private static final String FRAGMENT_FILE = "./src/shaders/fragmentshader";

	private int plats_forvandlingsmatris;
	private int plats_projektionsmatris;
	private int plats_vymatris;
	private int plats_ljus_position;
	private int plats_ljus_farg;

	public StatiskShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "texturkoordinater");
		super.bindAttribute(2, "normal");
		
	}

	@Override
	protected void getAllUniformLocation() {
		// TODO Auto-generated method stub
		plats_forvandlingsmatris = super.getUniformLocation("transformationsmatris");
		plats_projektionsmatris = super.getUniformLocation("projektionsmatris");
		plats_vymatris = super.getUniformLocation("vymatris");
		plats_ljus_farg = super.getUniformLocation("ljus_farg");
		plats_ljus_position = super.getUniformLocation("ljus_position");
	}

	public void laddaTransformationsMatris(Matrix4f matris) {
		super.loadMatrix(plats_forvandlingsmatris, matris);
	}

	public void laddaProjektionsMatris(Matrix4f projektion) {
		super.loadMatrix(plats_projektionsmatris, projektion);
	}
	
	public void laddaLjus(Ljus ljus){
		super.loadVector(plats_ljus_farg, ljus.getFarg());
		super.loadVector(plats_ljus_position, ljus.getPosition());
	}

	public void laddaVyMatris(Kamera kamera) {
		Matrix4f vymatris = Matematik.SkapaVyMatris(kamera);
		super.loadMatrix(plats_vymatris, vymatris);
	}

}
