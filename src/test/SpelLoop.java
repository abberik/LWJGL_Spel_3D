package test;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import entities.Kamera;
import entities.Ljus;
import modeller.RaaModel;
import modeller.TextureradModell;
import renderingsMotor.DisplayHanterare;
import renderingsMotor.Laddare;
import renderingsMotor.MastarRenderare;
import renderingsMotor.OBJLaddare;
import texturer.ModelTextur;

public class SpelLoop {

	public static void main(String[] args) {

		DisplayHanterare.skapaDisplay();

		Laddare laddare = new Laddare();
		RaaModel model = OBJLaddare.laddaObjModel("dragon", laddare);
		ModelTextur modelTextur = new ModelTextur(laddare.laddaTextur("rod"));
		modelTextur.setReflektivitet(0.1f / 100f);
		// Shine damper kan sättas via : modelTextur.setShinedamper(0.01f); men
		// verkar inte fungera på ett korrekt sätt, bug i min kod?
		TextureradModell texturerad_modell = new TextureradModell(model, modelTextur);

		Entity entity = new Entity(texturerad_modell, new Vector3f(0, 0, -15), 0, 0, 1, 1);
		Ljus ljus = new Ljus(new Vector3f(10, 10, 0), new Vector3f(1, 1, 1));
		Kamera kamera = new Kamera();

		MastarRenderare mastarRenderare = new MastarRenderare();

		while (!Display.isCloseRequested()) {

			entity.okaPosition(0, 0, 0);

			entity.okaRotation(0, 2, 0);
			kamera.move();

			mastarRenderare.processEntity(entity);
			mastarRenderare.render(ljus, kamera);

			DisplayHanterare.uppdateraDisplay();
		}

		mastarRenderare.stada();
		laddare.stada();
		DisplayHanterare.stangDisplay();

	}

}
