package com.pixelaborate.platformer;

import static org.lwjgl.opengl.GL11.GL_BACK;

import static org.lwjgl.opengl.GL11.GL_COLOR_ARRAY;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_COLOR_MATERIAL;
import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_DIFFUSE;
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_LIGHT_MODEL_AMBIENT;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColorMaterial;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glLight;
import static org.lwjgl.opengl.GL11.glLightModel;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import utility.BufferTools;
import utility.Camera;
import utility.EulerCamera;



public class Model {

	private List<Vector3f> vertices;//list of vertices from parsed model
	private List<Vector2f> textureCoordinates;//list of texture coordinates from parsed model
	private List<Vector3f> normals;//list of normals from parsed model
	private List<Face> faces = new ArrayList<Face>();// list of Faces from parsed model

	private static float[] lightPosition = {-2.19f, 1.36f, 11.45f, 1f};


	private static int modelList; //ID for model, similar to Vertex Buffer ID
	private float[] fVertices;// list of vertices
	private float[] norms;// lsit of normals
	private int VBID;// vertex buffer ID needed to use VBO's
	private int IBID;// Index buffer ID needed to use VBO's
	private int CBID;// Color buffer ID needed to use VBO's
	private FloatBuffer f;//floatbuffer vertices
	private FloatBuffer n;//floarbuffer normals
	private FloatBuffer c;//floatbuffer color

	private static Camera camera;//camera (this particular one not written by us, but that should be changed before friday)

	public Model() {
		//instantiate lists
		vertices = new ArrayList<Vector3f>();
		textureCoordinates = new ArrayList<Vector2f>();
		normals = new ArrayList<Vector3f>();

		//enable some stuff
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);


		glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);


		setUpCamera();
		//setUpLighting();

		// GL11.glViewport(0, 0, 320, 240);

	}


	public int numberOfVertices() {
		return vertices.size();
	}

	public int numberOfTextureCoordinates() {
		return textureCoordinates.size();
	}

	public int numberOfNormals() {
		return normals.size();
	}

	public boolean hasTextureCoordinates() {
		return getTextureCoordinates().size() > 0;
	}

	public boolean hasNormals() {
		return getNormals().size() > 0;
	}

	/**
	 * @return the vertices
	 */
	public List<Vector3f> getVertices() {
		return vertices;
	}
	/**
	 * @param vertices the vertices to set
	 */
	public void setVertices(List<Vector3f> vertices) {
		this.vertices = vertices;
	}
	/**
	 * @return the textureCoordinates
	 */
	public List<Vector2f> getTextureCoordinates() {
		return textureCoordinates;
	}
	/**
	 * @param textureCoordinates the textureCoordinates to set
	 */
	public void setTextureCoordinates(List<Vector2f> textureCoordinates) {
		this.textureCoordinates = textureCoordinates;
	}
	/**
	 * @return the normals
	 */
	public List<Vector3f> getNormals() {
		return normals;
	}
	/**
	 * @param normals the normals to set
	 */
	public void setNormals(List<Vector3f> normals) {
		this.normals = normals;
	}
	/**
	 * @return the faces
	 */
	public List<Face> getFaces() {
		return faces;
	}
	public void addFace(Face face) {
		faces.add(face);
	}


	/**
	 * @param faces the faces to set
	 */
	public void setFaces(List<Face> faces) {
		this.faces = faces;
	}

	public String toString() {
		return "Number of Vertices: " + String.valueOf(numberOfVertices()) + "; Number of texture coordinates: " + String.valueOf(numberOfTextureCoordinates())+ "; Number of Normals: " + String.valueOf(numberOfNormals());
	}


	public void render() {
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);//make it render the model by drawing lines

		//Render model here

		renderModel();//renders model using Call Lists





	}

	public void renderModel() {
		System.out.println("rendering model without VBO");
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);//clear screen
		glLoadIdentity();
		camera.applyTranslations();
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		//glPolygonMode(GL_FRONT_AND_BACK, GL11.GL_FILL);
		glShadeModel(GL_SMOOTH);// stuff with lighting
		glCallList(modelList);//render model

	}


	public void prepareModel() {




		modelList = glGenLists(1);//initialize the modelList ID
		glNewList(modelList, GL_COMPILE);//create a new list
		glBegin(GL_TRIANGLES);//start drawing triangles to memory to be drawn later




		glColor3f(1.0f, 1.0f, 1.0f);
		for (Face face : getFaces()) {//loop through faces, add vertices and normals of triangle to GL_TRIANGLES
			//glColor3f((float)(Math.random()),(float)(Math.random()),(float)(Math.random()));
			Vector3f n1 = getNormals().get(face.getNormalIndices()[0] - 1);
			glNormal3f(n1.x, n1.y, n1.z);

			//GL11.glColor3f((float)(Math.random()),(float)(Math.random()),(float)(Math.random()));
			Vector3f v1 = getVertices().get(face.getVertexIndices()[0] - 1);
			glVertex3f(v1.x, v1.y, v1.z);

			//GL11.glColor3f((float)(Math.random()),(float)(Math.random()),(float)(Math.random()));
			Vector3f n2 = getNormals().get(face.getNormalIndices()[1] - 1);
			glNormal3f(n2.x, n2.y, n2.z);

			//GL11.glColor3f((float)(Math.random()),(float)(Math.random()),(float)(Math.random()));
			Vector3f v2 = getVertices().get(face.getVertexIndices()[1] - 1);
			glVertex3f(v2.x, v2.y, v2.z);

			//GL11.glColor3f((float)(Math.random()),(float)(Math.random()),(float)(Math.random()));
			Vector3f n3 = getNormals().get(face.getNormalIndices()[2] - 1);
			glNormal3f(n3.x, n3.y, n3.z);

			//GL11.glColor3f((float)(Math.random()),(float)(Math.random()),(float)(Math.random()));
			Vector3f v3 = getVertices().get(face.getVertexIndices()[2] - 1);
			glVertex3f(v3.x, v3.y, v3.z);
		}
		glEnd();//end the GLTriangles call
		glEndList();// end the CallList

	}



	//DOES NOT WORK - BETTER CODE IN VBO.java and Map.java
	public void prepareVBOModel() {
		//VBID = createVBOID();



		fVertices = new float[(vertices.size()*3)];
		System.out.println((vertices.size()*3));
		System.out.println((vertices.size()*3)-3);
		int count = -1;
		for (int i = 0; i < (vertices.size()); i++) {
			fVertices[++count] = vertices.get(i).getX();
			fVertices[++count] = vertices.get(i).getY();
			fVertices[++count] = vertices.get(i).getZ();
		}

		norms = new float[(normals.size()*3)];
		count = -1;
		for (int i = 0; i < (normals.size()); i++) {
			norms[++count] = normals.get(i).getX();
			norms[++count] = normals.get(i).getY();
			norms[++count] = normals.get(i).getZ();
		}



		f = BufferUtils.createFloatBuffer(fVertices.length);
		VBID = GL15.glGenBuffers();
		System.out.println(VBID);
		f.put(fVertices);
		f.flip();


		n = BufferUtils.createFloatBuffer(norms.length);
		IBID = GL15.glGenBuffers();

		n.put(norms);
		n.flip();

		c = BufferUtils.createFloatBuffer(fVertices.length);
		CBID = GL15.glGenBuffers();
		n.put(fVertices);
		n.flip();


		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, f, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

		//GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, CBID);
		//GL15.glBufferData(GL15.GL_ARRAY_BUFFER, c, GL15.GL_STATIC_DRAW);

		//GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, IBID);
		//GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, n, GL15.GL_STATIC_DRAW);
		//GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);




	}

	public void renderVBOModel() {
		glColor3f(1.0f, 1.0f, 1.0f);

		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBID);
		GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);

		// GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
		//  GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, CBID);
		//GL11.glColorPointer(4, GL11.GL_FLOAT, 0, 0);

		//GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, IBID);
		// GL11.glDrawElements(GL11.GL_TRIANGLES, fVertices.length, GL11.GL_UNSIGNED_INT, 0);
		GL11.glDrawArrays(GL_TRIANGLES, 0, fVertices.length);
		GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
		// GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);


	}
	//input stuff
	public static void checkInput() {
		//change these values to increase or decrease speed of camera and mouse
		camera.processMouse(1, 80, -80);
		camera.processKeyboard(6000, 1, 1, 1);//change this to change camera speed
		glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(lightPosition));
		if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
			lightPosition = new float[]{camera.x(), camera.y(), camera.z(), 1};
		}
		if (Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
		} else if (Mouse.isButtonDown(1)) {
			Mouse.setGrabbed(false);
		}
	}

	//camera method. constucts a euler camer and sets default values
	private static void setUpCamera() {

		camera = new EulerCamera.Builder().setAspectRatio((float) Display.getWidth() / Display.getHeight())
				.setRotation(-1.12f, 0.16f, 0f).setPosition(-1.38f, 1.36f, 7.95f).setFieldOfView(60).build();
		camera.applyOptimalStates();
		camera.applyPerspectiveMatrix();
	}
	//used to set up lighting
	private static void setUpLighting() {
		glShadeModel(GL_SMOOTH);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glLightModel(GL_LIGHT_MODEL_AMBIENT, BufferTools.asFlippedFloatBuffer(new float[]{.1f, .1f, .1f, 5f}));
		glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(new float[]{0, 0, 0, 1}));
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_FRONT_AND_BACK, GL_DIFFUSE);
	}
}


