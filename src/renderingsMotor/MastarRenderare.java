package renderingsMotor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import entities.Entity;
import entities.Kamera;
import entities.Ljus;
import modeller.TextureradModell;
import shaders.StatiskShader;
import shaders.TerrangShader;
import terrang.Terrang;

public class MastarRenderare {


	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	
	private Matrix4f projektionsmatris;
	
	private StatiskShader entityShader = new StatiskShader();
	private EntityRenderare entityRenderare;
	
	private TerrangRenderare terrangRenderare;
	private TerrangShader terrangShader;
	
	private Map<TextureradModell,List<Entity>> entities = new HashMap<TextureradModell,List<Entity>>();
	private List<Terrang> terranger = new ArrayList<Terrang>();
	
	public MastarRenderare() {
	
		// TODO Auto-generated constructor stub
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);

		skapaProjektionsMatris();
		
		terrangShader = new TerrangShader();
		terrangRenderare = new TerrangRenderare(terrangShader, projektionsmatris);
			
		entityRenderare = new EntityRenderare(entityShader,projektionsmatris);
		terrangRenderare = new TerrangRenderare(terrangShader, projektionsmatris);
	}
	
	public void render(Ljus sol, Kamera kamera){
		forbered();
		entityShader.starta();
		entityShader.laddaLjus(sol);
		entityShader.laddaVyMatris(kamera);
		entityRenderare.rendera(entities);
		entityShader.stoppa();
		
		terrangShader.starta();
		terrangShader.laddaLjus(sol);
		terrangShader.laddaVyMatris(kamera);
		terrangRenderare.render(terranger);
		terrangShader.stoppa();
		
		
		terranger.clear();
		entities.clear();
	}
	
	public void processTerrang(Terrang terrang){
		terranger.add(terrang);
	}
	
	public void stada(){
		terrangShader.stada();
		entityShader.stada();
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
	
	public void forbered() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		GL11.glClearColor(0, 0, 0, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
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
