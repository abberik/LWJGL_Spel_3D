package renderingsMotor;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import modeller.RaaModel;
import modeller.TextureradModell;

public class Renderare {

	public void forbered(){
		
		GL11.glClearColor(0,1,0, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	public void renderera(TextureradModell texmodel){
		RaaModel model = texmodel.getRaaModel();
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texmodel.getTextur().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES,0, model.getAntal_vertexar(),0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
		
	}
	
}
