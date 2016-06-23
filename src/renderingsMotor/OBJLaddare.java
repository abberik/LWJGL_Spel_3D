package renderingsMotor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import modeller.RaaModel;

public class OBJLaddare {

	public static RaaModel laddaObjModel(String filnamn, Laddare laddare){
		
		FileReader fl = null;
		
		try {
			fl = new FileReader("res/" + filnamn + ".obj");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader lasare = new BufferedReader(fl);
		
		String rad;
		
		List<Vector3f> verticesList = new ArrayList<Vector3f>();
		List<Vector2f> texturesList = new ArrayList<Vector2f>();
		List<Vector3f> normalList = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		
		float[] verticesArray = null;
		float[] texturesArray = null;
		float[] normalArray = null;
		int[] indicesArray = null;
		
		try{
			
			while(true){
				
				rad = lasare.readLine();
			
				String[] inData = rad.split(" ");
				
				if(rad.startsWith("v ")){
					
					Vector3f vertex = new Vector3f(Float.parseFloat(inData[1]),Float.parseFloat(inData[2]),Float.parseFloat(inData[3]));
					verticesList.add(vertex);
					
				}else if(rad.startsWith("vn ")){
					
					Vector3f normal = new Vector3f(Float.parseFloat(inData[1]),Float.parseFloat(inData[2]),Float.parseFloat(inData[3]));
					normalList.add(normal);
					
				}else if(rad.startsWith("vt ")){
					
					Vector2f texture = new Vector2f(Float.parseFloat(inData[1]),Float.parseFloat(inData[2]));
					texturesList.add(texture);
					
				}else if(rad.startsWith("f ")){
					texturesArray = new float[verticesList.size() * 2];
					normalArray = new float[verticesList.size() * 3];
					break;
					
				}
			}	
				while(rad != null){
					
					if(!rad.startsWith("f ")){
						System.out.println("Langd: " );
						rad = lasare.readLine();
						continue;
					}
					
					String[] aktuellaRaden = rad.split(" ");
					
					String[] vertex1 = aktuellaRaden[1].split("/");
					String[] vertex2 = aktuellaRaden[2].split("/");
					String[] vertex3 = aktuellaRaden[3].split("/");
					
					processeraVertex(vertex1, indices, texturesList, normalList, texturesArray, normalArray);
					processeraVertex(vertex2, indices, texturesList, normalList, texturesArray, normalArray);
					processeraVertex(vertex3, indices, texturesList, normalList, texturesArray, normalArray);
					
					rad = lasare.readLine();
					
					
				}
				
			
			
			lasare.close();
			
		}catch(Exception ex){}
		
		verticesArray = new float[verticesList.size() * 3];
		indicesArray = new int[indices.size()];
		
		int vertexPekare = 0;
		
		for(Vector3f vertex : verticesList){
			verticesArray[vertexPekare++] = vertex.x;
			verticesArray[vertexPekare++] = vertex.y;
			verticesArray[vertexPekare++] = vertex.z;
			
		}
		
		for(int i = 0; i < indices.size(); i++){
			indicesArray[i] = indices.get(i);
			
		}

		return laddare.laddaTilVAO(verticesArray, texturesArray,normalArray, indicesArray);
	}
	
	
	
	private static void processeraVertex(String[] vertexData, List<Integer> indices, List<Vector2f> texturer, List<Vector3f> normaler, float[] texturArray, float[] normalArray ) {
		
		int aktuellaVertexPekaren = Integer.parseInt(vertexData[0]) - 1 ;
		indices.add(aktuellaVertexPekaren);
		
		Vector2f aktuellTextur = texturer.get(Integer.parseInt(vertexData[1]) - 1);
		texturArray[aktuellaVertexPekaren * 2] = aktuellTextur.x;
		texturArray[aktuellaVertexPekaren * 2 + 1] = 1 - aktuellTextur.y;
		
		Vector3f aktuellNormal = normaler.get(Integer.parseInt(vertexData[2]) - 1);
		normalArray[aktuellaVertexPekaren * 3] = aktuellNormal.x;
		normalArray[aktuellaVertexPekaren * 3 + 1] = aktuellNormal.y;
		normalArray[aktuellaVertexPekaren * 3 + 2] = aktuellNormal.z;
		
		
	}
	
}
