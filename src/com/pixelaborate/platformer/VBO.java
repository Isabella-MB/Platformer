package com.pixelaborate.platformer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL15.*;
import org.lwjgl.opengl.GL15;

import utility.BufferTools;
public class VBO {

	public static int VAID;
	public static int CID;
	public final static int  VERTEX_SIZE = 3;
	public final static int VERTICES = 6;
	private static FloatBuffer vertices;
	private static FloatBuffer color;
	
	public static void main(String[] args) {

		try {
			Display.setDisplayMode(new DisplayMode(1280, 720));
			Display.setTitle("VBO demonstration");
			Display.create();

			glLoadIdentity(); // Resets any previous projection matrices


			glClear(GL_COLOR_BUFFER_BIT);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			initTriangle();

			while(!Display.isCloseRequested()) {
				Display.update();
				Display.sync(100);

				render();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}


	public static void initTriangle() {

		GL11.glViewport(0, 0, 1280, 720);

	

		vertices = BufferUtils.createFloatBuffer(VERTICES * VERTEX_SIZE);
		vertices.put(new float[] { -.3f, -1f, 0f, });
		vertices.put(new float[] { 1f, -1f, 0f, });
		vertices.put(new float[] { 1f, 1f, 0f, });
		
		vertices.put(new float[] { .3f, 1f, 0f, });
		vertices.put(new float[] { -1f, 1f, 0f, });
		vertices.put(new float[] { -1f, -1f, 0f, });
		
		vertices.flip();


		VAID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, VAID);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		
		
		color = BufferUtils.createFloatBuffer(VERTICES * VERTEX_SIZE);
		//color.put(new float[] { 1f, 1f, 1f, });
		//color.put(new float[] { 1f, 1f, 1f, });
		//color.put(new float[] { 1f, 1f, 1f, });
		
		//color.put(new float[] { 1f, 1f, 1f, });
		//color.put(new float[] { 1f, 1f, 1f, });
		//color.put(new float[] { 1f, 1f, 1f, });
		color.flip();
		
		CID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, CID);
		glBufferData(GL_ARRAY_BUFFER, color, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
	}

	public static void render() {
		glBindBuffer(GL_ARRAY_BUFFER, VAID);
		glVertexPointer(VERTEX_SIZE, GL_FLOAT, 0, 0l);

		glBindBuffer(GL_ARRAY_BUFFER, VAID);
		glColorPointer(VERTEX_SIZE, GL_FLOAT, 0, 0l);

		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);

		glDrawArrays(GL_TRIANGLES, 0, vertices.capacity());

		glDisableClientState(GL_COLOR_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);	
		}
}
