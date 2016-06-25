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

public class EntityRenderare {

	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;



	private StatiskShader statiskShader;
	
	
	public EntityRenderare(StatiskShader statiskShader, Matrix4f projektionsmatris) {
		this.statiskShader = statiskShader;
		statiskShader.starta();
		statiskShader.laddaProjektionsMatris(projektionsmatris);
		statiskShader.stoppa();

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
		
		if(modelTextur.isHasTransparency()) MastarRenderare.avAktiveraCulling();
		this.statiskShader.laddaFakeLjus(modelTextur.isAnvanderFakeLjus());
		this.statiskShader.loadShineVariables(modelTextur.getShinedamper(), modelTextur.getReflektivitet());
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureradModell.getTextur().getID());
		
	}
	
	private void unbindTextureradModell(){
		MastarRenderare.aktiveraCulling();
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
	


	

}
