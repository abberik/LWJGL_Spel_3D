package renderingsMotor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Entity;
import entities.Kamera;
import entities.Ljus;
import modeller.TextureradModell;
import shaders.StatiskShader;

public class MastarRenderare {

	private StatiskShader shader = new StatiskShader();
	private Renderare renderare = new Renderare(shader);
	
	private Map<TextureradModell,List<Entity>> entities = new HashMap<TextureradModell,List<Entity>>();
	
	public void render(Ljus sol, Kamera kamera){
		renderare.forbered();
		shader.starta();
		shader.laddaLjus(sol);
		shader.laddaVyMatris(kamera);
		
		renderare.rendera(entities);
		
		shader.stoppa();
		entities.clear();
	}
	
	public void stada(){
		shader.stada();
	}
	
	public void processEntity(Entity entity){
		TextureradModell entitys_modell = entity.getModel();
		List<Entity> batch = entities.get(entitys_modell);
		if(batch != null){
			batch.add(entity);
		}else{
			List<Entity> nyBatch = new ArrayList<Entity>();
			nyBatch.add(entity);
			entities.put(entitys_modell, nyBatch);
		
			
		}
	}
}
