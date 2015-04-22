package com.pixelaborate.platformer;


import org.lwjgl.LWJGLException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector3f;

import utility.BufferTools;
import utility.Camera;
import utility.EulerCamera;
import utility.Model;
import utility.OBJLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;




public class Entity {
	public static String filePath;

	private static Camera camera;
	private static int vertexList;

	public Entity() {
		filePath = "res/models/bunny.obj";


	}

	public Entity(String path) {
		filePath = path;
	}

	public static void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		camera.applyTranslations();
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glCallList(vertexList);
	}
	public static void input() {


	}

	public static void update() {


	}


	private static void initializeCamera() {
		camera = new EulerCamera.Builder().setAspectRatio((float) Display.getWidth() / Display.getHeight())
				.setRotation(-1.12f, 0.16f, 0f).setPosition(-1.38f, 1.36f, 7.95f).setFieldOfView(60).build();
		camera.applyOptimalStates();
		camera.applyPerspectiveMatrix();
	}

	private static void checkInput() {//will eventually use Eli's input methods
		camera.processMouse(1, 80, -80);
		camera.processKeyboard(16, 1, 1, 1);
		if (Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
		} else if (Mouse.isButtonDown(1)) {
			Mouse.setGrabbed(false);
		}
	}

	private static void setUpDisplayLists() { //draw model to screen
		vertexList = glGenLists(1);
		glNewList(vertexList, GL_COMPILE);
		
			Model m = null;
			try {

				m = OBJLoader.loadModel(new File("res/models/Rock.obj"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Display.destroy();
				System.exit(1);
			} catch (IOException e) {
				e.printStackTrace();
				Display.destroy();
				System.exit(1);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			glBegin(GL_TRIANGLES);
			for (Model.Face face : m.getFaces()) {
				Vector3f n1 = m.getNormals().get(face.getNormalIndices()[0] - 1);
				glNormal3f(n1.x, n1.y, n1.z);
				Vector3f v1 = m.getVertices().get(face.getVertexIndices()[0] - 1);
				glVertex3f(v1.x, v1.y, v1.z);
				Vector3f n2 = m.getNormals().get(face.getNormalIndices()[1] - 1);
				glNormal3f(n2.x, n2.y, n2.z);
				Vector3f v2 = m.getVertices().get(face.getVertexIndices()[1] - 1);
				glVertex3f(v2.x, v2.y, v2.z);
				Vector3f n3 = m.getNormals().get(face.getNormalIndices()[2] - 1);
				glNormal3f(n3.x, n3.y, n3.z);
				Vector3f v3 = m.getVertices().get(face.getVertexIndices()[2] - 1);
				glVertex3f(v3.x, v3.y, v3.z);
			}
			glEnd();
		
		glEndList();
	}

	public static void main(String[] args) {
		initializeDisplay();
		setUpDisplayLists();
		initializeCamera();
		initializeLighting();
		while (!Display.isCloseRequested()) {
			render();
			checkInput();
			Display.update();
			Display.sync(60);
		}
		cleanUp();
		System.exit(0);
	}

	private static void initializeDisplay() {
		try {
			filePath = "res/models/Rock.obj";
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setVSyncEnabled(true);
			Display.setTitle("ROCKin'");
			Display.create();
		} catch (LWJGLException e) {
			System.err.println("ERROR ERROR ERROR ");
			Display.destroy();
			System.exit(1);
		}
	}

	private static void cleanUp() {
		glDeleteLists(vertexList, 1);
		Display.destroy();
	}
	
	
	 private static void initializeLighting() {
	        glShadeModel(GL_SMOOTH);
	        glEnable(GL_DEPTH_TEST);
	        glEnable(GL_LIGHTING);
	        glEnable(GL_LIGHT0);
	        glLightModel(GL_LIGHT_MODEL_AMBIENT, BufferTools.asFlippedFloatBuffer(new float[]{0.05f, 0.05f, 0.05f, 1f}));
	        glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(new float[]{0, 0, 0, 1}));
	        glEnable(GL_CULL_FACE);
	        glCullFace(GL_BACK);
	        glEnable(GL_COLOR_MATERIAL);
	        glColorMaterial(GL_FRONT, GL_DIFFUSE);
	    }
}
