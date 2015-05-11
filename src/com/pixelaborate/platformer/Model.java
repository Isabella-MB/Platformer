package com.pixelaborate.platformer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL12.*;
import org.lwjgl.opengl.GL13.*;
import org.lwjgl.opengl.GL15.*;
import org.lwjgl.opengl.GL30.*;
import org.lwjgl.opengl.GL31.*;
import org.lwjgl.opengl.GL41.*;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import utility.BufferTools;
import utility.Camera;
import utility.EulerCamera;



public class Model {

	private List<Vector3f> vertices;
	private List<Vector2f> textureCoordinates;
	private List<Vector3f> normals;
	private List<Face> faces = new ArrayList<Face>();
	
	private static float[] lightPosition = {-2.19f, 1.36f, 11.45f, 1f};
	 

	private static int modelList;
	private float[] fVertices;
	private float[] norms;
	private int VBID;
	private FloatBuffer f;
	private FloatBuffer n;

	private static Camera camera;

	public Model() {
		vertices = new ArrayList<Vector3f>();
		textureCoordinates = new ArrayList<Vector2f>();
		normals = new ArrayList<Vector3f>();


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
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

		//Render model here
		
		renderVBOModel();





	}

	public void renderModel() {
		System.out.println("rendering model without VBO");
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		camera.applyTranslations();
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		//glPolygonMode(GL_FRONT_AND_BACK, GL11.GL_FILL);
		glShadeModel(GL_SMOOTH);
		glCallList(modelList);

	}

	public void renderVBOModel() {
		 glEnableClientState(GL_VERTEX_ARRAY);
		// glBindBuffer(GL_ARRAY_BUFFER, VBID);
		 glVertexPointer(3, GL_FLOAT, 0, 0);

		 GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, f.capacity());
		// glDrawRangeElements(GL_TRIANGLES, 0, f.capacity(), f.capacity(),
               //  GL_UNSIGNED_INT, 0);

	}
	public void prepareModel() {


		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

		modelList = glGenLists(1);
		glNewList(modelList, GL_COMPILE);
		glBegin(GL_TRIANGLES);

		


		glColor3f(1.0f, 1.0f, 1.0f);
		for (Face face : getFaces()) {
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
		glEnd();
		glEndList();
		
	}




	public void prepareVBOModel() {
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

		f.put(fVertices);
		f.flip();
		System.out.println(f.toString());
		
		n = BufferUtils.createFloatBuffer(norms.length);
		n.put(norms);
		n.flip();
		System.out.println(n.toString());


		VBID = createVBOID();
	
		vertexBufferData(VBID, f);
		

	}


	public static int createVBOID() {
		IntBuffer buffer = BufferUtils.createIntBuffer(1);
		GL15.glGenBuffers(buffer);
		return GL15.glGenBuffers();
	}

	public static void vertexBufferData(int id, FloatBuffer buffer) { //Not restricted to FloatBuffer
		
		int vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		//glEnableVertexAttribArray(0);

		//glBindBuffer(GL_ARRAY_BUFFER, vboID);
		//glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);

		//glBindBuffer(GL_ARRAY_BUFFER, 0);
		//glBindVertexArray(0);
		
		glEnableClientState(GL_VERTEX_ARRAY);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id); //Bind buffer (also specifies type of buffer)
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW); //Send up the data and specify usage hint.
		
	}

	public static void indexBufferData(int id, FloatBuffer buffer) { //Not restricted to IntBuffer
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, id);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
	}


	public static void checkInput() {
		//change these values to increase or decrease speed of camera and mouse
		 camera.processMouse(1, 80, -80);
	        camera.processKeyboard(60, 1, 1, 1);
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

	private static void setUpCamera() {

		camera = new EulerCamera.Builder().setAspectRatio((float) Display.getWidth() / Display.getHeight())
				.setRotation(-1.12f, 0.16f, 0f).setPosition(-1.38f, 1.36f, 7.95f).setFieldOfView(60).build();
		camera.applyOptimalStates();
		camera.applyPerspectiveMatrix();
	}

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


