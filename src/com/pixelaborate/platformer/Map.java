package com.pixelaborate.platformer;

import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * @author Eli
 *
 */
public class Map {
	
	private final int WIDTH = 1024;
	private final int HEIGHT = 1024;
	private final int VERTEX_SIZE = 3;
	private final int COLOR_SIZE = 4;
	private final int vertexAmount = WIDTH * HEIGHT;
	private final int indexAmount = (WIDTH - 1) * (HEIGHT - 1) * 6;
	
	public BufferedImage heightData;
	
	FloatBuffer vertexData;
	int VBOID;
	IntBuffer indexData;
	int IBOID;
	FloatBuffer colorData;
	int CBOID;
	Texture texture;
	int TXTID;
	byte[] textureData;
	
	
	public Map(String path)
	{	
		try {
			heightData = ImageIO.read(new File("res/images/temperate.png"));
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/wood.png")));
			
		}
		catch(IOException e){}
		
		
		
		vertexData = BufferUtils.createFloatBuffer(vertexAmount * VERTEX_SIZE);
		VBOID = GL15.glGenBuffers();
		colorData = BufferUtils.createFloatBuffer(vertexAmount * COLOR_SIZE);
		CBOID = GL15.glGenBuffers();
		indexData = BufferUtils.createIntBuffer(indexAmount);
		IBOID = GL15.glGenBuffers();
		
		loadMap();
	}

	public void loadMap()
	{
		float[] vertices = new float[vertexAmount * VERTEX_SIZE];
		float[] colors = new float[vertexAmount * COLOR_SIZE];
		int[] indices = new int[indexAmount];
		
		// Loads Vertices
		for(int i = 0; i < WIDTH; i++)
		{
			for(int j = 0; j < HEIGHT; j++)
			{
				vertices[(i + (j * HEIGHT)) * VERTEX_SIZE] = i;
				
				int color = new Color(heightData.getRGB(i, j)).getBlue();
				
				vertices[(i + (j * HEIGHT)) * VERTEX_SIZE + 1] = color;
				
//				if(i == 0 || j == 0)
//					vertices[(i + (j * HEIGHT)) * VERTEX_SIZE + 1] = (float)Math.random() * 2;
//				else
//				{
//					float heightSum = 0;
//					heightSum += vertices[((i - 1) + (j * HEIGHT)) * VERTEX_SIZE + 1];
//					heightSum += vertices[((i - 1) + ((j - 1) * HEIGHT)) * VERTEX_SIZE + 1];
//					heightSum += vertices[(i + ((j - 1) * HEIGHT)) * VERTEX_SIZE + 1];
//					
//					if(Math.random() < .5f)
//						vertices[(i + (j * HEIGHT)) * VERTEX_SIZE + 1] = ((float)Math.random() * 1f) + (heightSum / 3.0f);
//					else
//						vertices[(i + (j * HEIGHT)) * VERTEX_SIZE + 1] = ((float)Math.random() * 1f) - (heightSum / 3.0f);
//				}
				
				vertices[(i + (j * HEIGHT)) * VERTEX_SIZE + 2] = j;
				
				colors[(i + (j * HEIGHT)) * COLOR_SIZE] = color / 255f;
				colors[(i + (j * HEIGHT)) * COLOR_SIZE + 1] = color / 510f;
				colors[(i + (j * HEIGHT)) * COLOR_SIZE + 2] = 0;
				colors[(i + (j * HEIGHT)) * COLOR_SIZE + 3] = 1f;
				
//				vertexData.put(i);
//				vertexData.put(0);
//				vertexData.put(j);
//				
//				colorData.put(i);
//				colorData.put(0);
//				colorData.put(j);
			}
		}
		
		// Loads Indices
		int indexCounter = 0;
		
		for(int i = 0; i < WIDTH; i++)
		{
			for(int j = 0; j < HEIGHT; j++)
			{
				if(i < WIDTH - 1 && j < HEIGHT - 1)
					indices[(6 * (i + (j * (HEIGHT - 1))))] = indexCounter;
				
				if(i > 0 && j > 0)
					indices[(6 * ((i - 1) + ((j - 1) * (HEIGHT - 1)))) + 5] = indexCounter;
				
				if(i < WIDTH - 1 && j > 0)
					indices[(6 * (i + ((j - 1) * (HEIGHT - 1)))) + 2] = indexCounter;
				
				if(i < WIDTH - 1 && j > 0)
					indices[(6 * (i + ((j - 1) * (HEIGHT - 1)))) + 4] = indexCounter;

				if(i > 0 && j < HEIGHT - 1)
					indices[(6 * ((i - 1) + (j * (HEIGHT - 1)))) + 3] = indexCounter;
				
				if(i > 0 && j < HEIGHT - 1)
					indices[(6 * ((i - 1) + (j * (HEIGHT - 1)))) + 1] = indexCounter;
				
				indexCounter++;
			}
		}
		
		vertexData.put(vertices);
		vertexData.flip();
		colorData.put(colors);
		colorData.flip();
		indexData.put(indices);
		indexData.flip();
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexData, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, CBOID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, colorData, GL15.GL_STATIC_DRAW);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, IBOID);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indexData, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		TXTID = texture.getTextureID();
		textureData = texture.getTextureData();
		System.out.println(textureData.toString());
	}
		
	public void update()
	{
		
	}
	
	public void render()
	{
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
	    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOID);
	    GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);
	     
	    GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
	    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, CBOID);
	    GL11.glColorPointer(4, GL11.GL_FLOAT, 0, 0);

	    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, IBOID);
	    GL11.glDrawElements(GL11.GL_TRIANGLES, indexAmount, GL11.GL_UNSIGNED_INT, 0);
	    
	    GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
	    GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
	}
}