package renderingsMotor;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import modeller.RaaModel;
import modeller.TextureradModell;
import shaders.TerrangShader;
import terrang.Terrang;
import texturer.ModelTextur;
import texturer.TerrangTexturPaket;
import verktygslada.Matematik;

public class TerrangRenderare {

	private TerrangShader shader;
	
	public TerrangRenderare(TerrangShader shader, Matrix4f projektionsmatris) {
		this.shader = shader;
		shader.starta();
		shader.laddaProjektionsMatris(projektionsmatris);
		shader.anslutTexturEnheter();
		shader.stoppa();
	}
	
	public void render(List<Terrang> terranger){
		
		for(Terrang terrang : terranger){
		
			forberedTerrang(terrang);
			laddaModelMatris(terrang);
			GL11.glDrawElements(GL11.GL_TRIANGLES, terrang.getModel().getAntal_vertexar(), GL11.GL_UNSIGNED_INT, 0);
			unbindTextureradModell();
			
		}
		
	}
	
	private void forberedTerrang(Terrang terrang){
 
		RaaModel model = terrang.getModel();

		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		bindTexturer(terrang);
		shader.loadShineVariables(1,0); //will be added latur
		
	}
	
	private void bindTexturer(Terrang terrang){
		TerrangTexturPaket textur_paket = terrang.getTextur_paket();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textur_paket.getBakgrund().getTexturID());
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textur_paket.getrTextur().getTexturID());
		GL13.glActiveTexture(GL13.GL_TEXTURE2);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textur_paket.getgTextur().getTexturID());
		GL13.glActiveTexture(GL13.GL_TEXTURE3);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textur_paket.getbTextur().getTexturID());
		GL13.glActiveTexture(GL13.GL_TEXTURE4);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, terrang.getBlend_karta().getTexturID());
	}
	
	private void unbindTextureradModell(){
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
		
	}
	
	private void laddaModelMatris(Terrang terrang){
		Matrix4f transformationsmatris = Matematik.skapaTransformationsMatris(new Vector3f(terrang.getX(),0,terrang.getZ()), 0,
				0, 0, 1);
		shader.laddaTransformationsMatris(transformationsmatris);
	}
	
	
}
