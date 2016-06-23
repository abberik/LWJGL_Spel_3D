package renderingsMotor;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import entities.Entity;
import modeller.RaaModel;
import modeller.TextureradModell;
import shaders.StatiskShader;
import texturer.ModelTextur;
import verktygslada.Matematik;

public class Renderare {

	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;

	private Matrix4f projektionsmatris;

	private StatiskShader statiskShader;
	
	
	public Renderare(StatiskShader statiskShader) {
		this.statiskShader = statiskShader;
		skapaProjektionsMatris();
		statiskShader.starta();
		statiskShader.laddaProjektionsMatris(projektionsmatris);
		statiskShader.stoppa();

	}

	public void forbered() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glClearColor(0, 0, 0, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	public void rendera(Map<TextureradModell,List<Entity>> entities){
		
//		System.out.println(entities.size()); printade 1, stämmer
		for(TextureradModell modell : entities.keySet()){
			
			forberedTextureradModell(modell);
			
			List<Entity> batch = entities.get(modell);											
			
//			System.out.println(entities.get(modell).size());  Printade 0, stämmer ej
			for(Entity entity : batch){
			
				forberedInstans(entity);
				GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getModel().getRaaModel().getAntal_vertexar(), GL11.GL_UNSIGNED_INT, 0);
			
			}
			
			unbindTextureradModell();
			
		}
		
	}
	
	private void forberedTextureradModell(TextureradModell textureradModell){
	
		RaaModel model = textureradModell.getRaaModel();

		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		ModelTextur modelTextur = textureradModell.getTextur();
		this.statiskShader.loadShineVariables(modelTextur.getShinedamper(), modelTextur.getReflektivitet());
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureradModell.getTextur().getID());
		
	}
	
	private void unbindTextureradModell(){
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
		
	}
	
	private void forberedInstans(Entity entity){
		Matrix4f transformationsmatris = Matematik.skapaTransformationsMatris(entity.getPosition(), entity.getRotX(),
				entity.getRotY(), entity.getRotZ(), entity.getScale());
		this.statiskShader.laddaTransformationsMatris(transformationsmatris);
	}
	


	private void skapaProjektionsMatris() {

		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		projektionsmatris = new Matrix4f();
		projektionsmatris.m00 = x_scale;
		projektionsmatris.m11 = y_scale;
		projektionsmatris.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projektionsmatris.m23 = -1;
		projektionsmatris.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projektionsmatris.m33 = 0;

	}

}
