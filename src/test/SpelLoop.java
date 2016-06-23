package test;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import entities.Kamera;
import modeller.RaaModel;
import modeller.TextureradModell;
import renderingsMotor.DisplayHanterare;
import renderingsMotor.Laddare;
import renderingsMotor.OBJLaddare;
import renderingsMotor.OBJLoader;
import renderingsMotor.Renderare;
import shaders.StatiskShader;
import texturer.ModelTextur;

public class SpelLoop {

	public static void main(String[] args) {

		DisplayHanterare.skapaDisplay();

		Laddare laddare = new Laddare();
		StatiskShader shader = new StatiskShader();
		Renderare renderare = new Renderare(shader);
		RaaModel model = OBJLoader.loadObjModel("cube", laddare);
//		RaaModel model = OBJLaddare.laddaObjModel("stall", laddare);
		ModelTextur modelTextur = new ModelTextur(laddare.laddaTextur("cube_texture"));
		TextureradModell texturerad_modell = new TextureradModell(model, modelTextur);

		Entity entity = new Entity(texturerad_modell, new Vector3f(0, 0, -1), 0, 0, 0, 1);

		Kamera kamera = new Kamera();

		while (!Display.isCloseRequested()) {

			entity.okaPosition(0, 0, 0);
			entity.okaRotation(1, 1, 1);
			kamera.move();
			renderare.forbered();
			shader.starta();
			shader.laddaVyMatris(kamera);
			renderare.renderera(entity, shader);
			shader.stoppa();

			DisplayHanterare.uppdateraDisplay();
		}
		shader.stada();
		laddare.stada();
		DisplayHanterare.stangDisplay();

	}

}
