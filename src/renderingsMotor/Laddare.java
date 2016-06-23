package renderingsMotor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import modeller.RaaModel;

public class Laddare {

	private ArrayList<Integer> vaos = new ArrayList<Integer>();
	private ArrayList<Integer> vbos = new ArrayList<Integer>();
	private ArrayList<Integer> texturer = new ArrayList<Integer>();

	public RaaModel laddaTilVAO(float[] positioner, float[] texturkoordinater, int[] indices) {

		int vaoID = skapaVAO();
		bindIndicesBuffer(indices);
		lagraDataIAttributLista(0, 3, positioner);
		lagraDataIAttributLista(1, 2, texturkoordinater);
		unbindVAO();
		return new RaaModel(vaoID, indices.length);

	}

	private int skapaVAO() {

		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;

	}

	public int laddaTextur(String filNamn) {
		Texture textur = null;
		try {

			textur = TextureLoader.getTexture("PNG", new FileInputStream("res/" + filNamn + ".png"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		texturer.add(textur.getTextureID());
		return textur.getTextureID();
	}

	private void lagraDataIAttributLista(int attributNummer, int koordinatstorlek, float[] data) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = lagraDataIFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributNummer, koordinatstorlek, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}

	private FloatBuffer lagraDataIFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	private IntBuffer lagraDataIIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	private void bindIndicesBuffer(int[] indices) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = lagraDataIIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}

	public void stada() {
		for (int aid : vaos)
			GL30.glDeleteVertexArrays(aid);
		for (int bid : vbos)
			GL15.glDeleteBuffers(bid);
		for (int tid : texturer)
			GL11.glDeleteTextures(tid);
	}

}
