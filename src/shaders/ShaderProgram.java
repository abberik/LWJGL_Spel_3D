package shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public abstract class ShaderProgram {

	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;

	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

	public ShaderProgram(String vertexFile, String fragmentFile) {

		vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);

		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		bindAttributes();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);

		getAllUniformLocation();
	}

	public void starta() {
		GL20.glUseProgram(programID);
	}

	public void stoppa() {
		GL20.glUseProgram(0);
	}

	public void stada() {
		stoppa();
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID); 
		GL20.glDeleteProgram(programID);
	}

	protected abstract void bindAttributes();

	protected void laddaInt(int plats, int varde){
		GL20.glUniform1i(plats, varde);
	}
	
	protected void loadFloat(int plats, float varde) {
		GL20.glUniform1f(plats, varde);
	}

	protected void loadVector(int plats, Vector3f vektor) {
		GL20.glUniform3f(plats, vektor.x, vektor.y, vektor.z);
	}

	protected void loadBoolean(int plats, boolean varde) {
		float attLadda = 0;
		if (varde) {
			attLadda = 1;
		}
		GL20.glUniform1f(plats, attLadda);
	}

	protected void loadMatrix(int plats, Matrix4f matris) {
		matris.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(plats, false, matrixBuffer);
	}

	protected void bindAttribute(int attribute, String variableName) {
		GL20.glBindAttribLocation(programID, attribute, variableName);
	}

	private static int loadShader(String namn, int type) {

		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader lasare = new BufferedReader(new FileReader(namn));
			String rad;
			while ((rad = lasare.readLine()) != null) {
				shaderSource.append(rad + "\n");
			}
			lasare.close();
		} catch (IOException ex) {
			System.err.println("Fil ej funnen.");
			ex.printStackTrace();
			System.exit(1);
		}

		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource.toString());
		GL20.glCompileShader(shaderID);
		if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.err.println("Misslyckades med kompilering.");
			System.exit(1);
		}
		return shaderID;

	}

	protected abstract void getAllUniformLocation();

	protected int getUniformLocation(String uniformName) {
		return GL20.glGetUniformLocation(programID, uniformName);
	}

}
