package shaders;

public class StatiskShader extends ShaderProgram{

	private static final String VERTEX_FILE = "./src/shaders/vertexshader";
	private static final String FRAGMENT_FILE = "./src/shaders/fragmentshader";
	
	public StatiskShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0,"position");
		super.bindAttribute(1, "texturkoordinater");
	}
}
