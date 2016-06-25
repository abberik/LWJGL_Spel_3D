package shaders;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.Kamera;
import entities.Ljus;
import verktygslada.Matematik;

public class TerrangShader extends ShaderProgram{

	private static final String VERTEX_FILE = "./src/shaders/terrangvertexshader";
	private static final String FRAGMENT_FILE = "./src/shaders/terrangfragmentshader";

	private int plats_forvandlingsmatris;
	private int plats_projektionsmatris;
	private int plats_vymatris;
	private int plats_ljus_position;
	private int plats_ljus_farg;
	private int plats_reflektivitet;
	private int plats_shineDamper;
	private int plats_himmelfarg;
	
	public TerrangShader() {
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
		
		plats_forvandlingsmatris = super.getUniformLocation("transformationsmatris");
		plats_projektionsmatris = super.getUniformLocation("projektionsmatris");
		plats_vymatris = super.getUniformLocation("vymatris");
		plats_ljus_farg = super.getUniformLocation("ljus_farg");
		plats_ljus_position = super.getUniformLocation("ljus_position");
		plats_shineDamper = super.getUniformLocation("shineDamper");
		plats_reflektivitet = super.getUniformLocation("reflektivitet");
		plats_himmelfarg = super.getUniformLocation("himmelFarg");
	}

	public void laddaHimmelFarg(float rod, float gron, float bla ){
		super.loadVector(plats_himmelfarg, new Vector3f(rod,gron,bla));
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
	
	public void loadShineVariables(float damper, float reflektivitet){
		super.loadFloat(plats_reflektivitet, reflektivitet);
		super.loadFloat(plats_shineDamper, damper);
	}

	public void laddaVyMatris(Kamera kamera) {
		Matrix4f vymatris = Matematik.SkapaVyMatris(kamera);
		super.loadMatrix(plats_vymatris, vymatris);
	}

	
	
}
