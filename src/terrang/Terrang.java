package terrang;

import modeller.RaaModel;
import renderingsMotor.Laddare;
import texturer.ModelTextur;
import texturer.TerrangTextur;
import texturer.TerrangTexturPaket;

public class Terrang {

	private static final float SIZE = 800;
	private static final int VERTEX_COUNT = 128;
	
	private float x;
	private float z;
	private RaaModel model;
	private TerrangTexturPaket textur_paket;
	private TerrangTextur blend_karta;
		
	public Terrang(float gridX, float gridZ,Laddare laddare ,TerrangTexturPaket textur_paket,TerrangTextur blend_karta) {
		
		this.x = gridX * SIZE;
		this.z = gridZ * SIZE;
		this.model = generateTerrain(laddare);
		this.blend_karta = blend_karta;
		this.textur_paket = textur_paket;
		
	}
	
	private RaaModel generateTerrain(Laddare loader){
		int count = VERTEX_COUNT * VERTEX_COUNT;
		float[] vertices = new float[count * 3];
		float[] normals = new float[count * 3];
		float[] textureCoords = new float[count*2];
		int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT-1)];
		int vertexPointer = 0;
		for(int i=0;i<VERTEX_COUNT;i++){
			for(int j=0;j<VERTEX_COUNT;j++){
				vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
				vertices[vertexPointer*3+1] = 0;
				vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
				normals[vertexPointer*3] = 0;
				normals[vertexPointer*3+1] = 1;
				normals[vertexPointer*3+2] = 0;
				textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1);
				textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1);
				vertexPointer++;
			}
		}
		int pointer = 0;
		for(int gz=0;gz<VERTEX_COUNT-1;gz++){
			for(int gx=0;gx<VERTEX_COUNT-1;gx++){
				int topLeft = (gz*VERTEX_COUNT)+gx;
				int topRight = topLeft + 1;
				int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
				int bottomRight = bottomLeft + 1;
				indices[pointer++] = topLeft;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = topRight;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = bottomRight;
			}
		}
		return loader.laddaTilVAO(vertices, textureCoords, normals, indices);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public RaaModel getModel() {
		return model;
	}

	public void setModel(RaaModel model) {
		this.model = model;
	}

	public TerrangTexturPaket getTextur_paket() {
		return textur_paket;
	}

	public void setTextur_paket(TerrangTexturPaket textur_paket) {
		this.textur_paket = textur_paket;
	}

	public TerrangTextur getBlend_karta() {
		return blend_karta;
	}

	public void setBlend_karta(TerrangTextur blend_karta) {
		this.blend_karta = blend_karta;
	}

	public static float getSize() {
		return SIZE;
	}

	public static int getVertexCount() {
		return VERTEX_COUNT;
	}

	
	
	
	
}
