package verktygslada;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.Kamera;

public class Matematik {

	public static Matrix4f skapaTransformationsMatris(Vector3f translation, float rx, float ry, float rz, float skala){
		
		Matrix4f matris = new Matrix4f();
		matris.setIdentity();
		Matrix4f.translate(translation, matris, matris);
		Matrix4f.rotate((float)Math.toRadians(rx), new Vector3f(1,0,0), matris, matris);
		Matrix4f.rotate((float)Math.toRadians(ry), new Vector3f(0,1,0), matris, matris);
		Matrix4f.rotate((float)Math.toRadians(rz), new Vector3f(0,0,1), matris, matris);
		Matrix4f.scale(new Vector3f(skala,skala,skala), matris, matris);
		
		return matris;
	}
	
	public static Matrix4f SkapaVyMatris(Kamera kamera){
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(kamera.getPitch()), new Vector3f(1, 0, 0), viewMatrix,
                viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(kamera.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Vector3f cameraPos = kamera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
        Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
        return viewMatrix;
	}
	
}
