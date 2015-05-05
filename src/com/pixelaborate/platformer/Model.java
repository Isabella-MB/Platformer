package com.pixelaborate.platformer;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11.*;


public class Model {
	
	  private  List<Vector3f> vertices;
	  private  List<Vector2f> textureCoordinates;
	  private  List<Vector3f> normals;
	
	  
	  public Model() {
		  vertices = new ArrayList<Vector3f>();
		  textureCoordinates = new ArrayList<Vector2f>();
		  normals = new ArrayList<Vector3f>();
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

	public String toString() {
		return "Number of Vertices: " + String.valueOf(numberOfVertices()) + "; Number of texture coordinates: " + String.valueOf(numberOfTextureCoordinates())+ "; Number of Normals: " + String.valueOf(numberOfNormals());
	}
	
	
	public void render() {
		GL11.glBegin(GL11.GL_TRIANGLES);

		GL11.glColor3f(1, 0, 0);
		GL11.glVertex3f(-0.5f, -0.5f, 0.0f);

		GL11.glColor3f(0, 1, 0);
		GL11.glVertex3f(+0.5f, -0.5f, 0.0f);

		GL11.glColor3f(0, 0, 1);
	      GL11.glVertex3f(+0.5f, +0.5f, 0.0f);

	      GL11.glEnd();
		
		
		
		
	}
}
