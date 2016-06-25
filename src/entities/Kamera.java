package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Kamera {

	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw; // Den som styr vart kameran siktar.
	private float roll;
	
	private static final float walkspeed = 0.5f;
	private static final float sensitivity = 0.25f;
	
	public Kamera() {

	}

	public void move() {
		
		//Kod för att styra riktning med musen
		
		yaw += (Mouse.getDX() * sensitivity);
		pitch += (Mouse.getDY() * (- sensitivity)); 
		
		//Kod för att styra riktning med musen
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			
			float enhets_forflyttning_langs_X = (float)Math.cos(Math.toRadians((double)(yaw - 90.0)));
			float enhets_forflyttning_langs_Z = (float)Math.sin(Math.toRadians((double)(yaw - 90.0)));
			
			float forflyttning_langs_X = enhets_forflyttning_langs_X * walkspeed; 
			float forflyttning_langs_Z = enhets_forflyttning_langs_Z * walkspeed;
		
			position.x += forflyttning_langs_X;
			position.z += forflyttning_langs_Z;
			
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {

			float enhets_forflyttning_langs_X = (float)Math.cos(Math.toRadians((double)(yaw - 270 )));
			float enhets_forflyttning_langs_Z = (float)Math.sin(Math.toRadians((double)(yaw - 270)));
			
			float forflyttning_langs_X = enhets_forflyttning_langs_X * walkspeed; 
			float forflyttning_langs_Z = enhets_forflyttning_langs_Z * walkspeed;
		
			position.x += forflyttning_langs_X;
			position.z += forflyttning_langs_Z;

		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D) ) {

			float enhets_forflyttning_langs_X = (float)Math.cos(Math.toRadians((double)(yaw )));
			float enhets_forflyttning_langs_Z = (float)Math.sin(Math.toRadians((double)(yaw )));
			
			float forflyttning_langs_X = enhets_forflyttning_langs_X * walkspeed; 
			float forflyttning_langs_Z = enhets_forflyttning_langs_Z * walkspeed;
		
			position.x += forflyttning_langs_X;
			position.z += forflyttning_langs_Z;

		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A) ) {

			float enhets_forflyttning_langs_X = (float)Math.cos(Math.toRadians((double)(yaw - 180.0)));
			float enhets_forflyttning_langs_Z = (float)Math.sin(Math.toRadians((double)(yaw - 180.0)));
			
			float forflyttning_langs_X = enhets_forflyttning_langs_X * walkspeed; 
			float forflyttning_langs_Z = enhets_forflyttning_langs_Z * walkspeed;
		
			position.x += forflyttning_langs_X;
			position.z += forflyttning_langs_Z;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {

			position.y += 0.5f;

		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {

			position.y -= 0.5f;

		}
	
		
//		Kod för att få kameran att rotera.
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			this.pitch -= 1.25f;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			
			this.pitch += 1.25f;
			
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			
			this.yaw += 1.25f;
			
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
	
			this.yaw -= 1.25f;
			
		}

	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

}
