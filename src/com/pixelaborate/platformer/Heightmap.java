package com.pixelaborate.platformer;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 */

/**
 * @author Max
 *
 */
public class Heightmap {

	String filePath;
	private static float[][] data;
	
	public Heightmap() {
		
	}
	
	public Heightmap (String _filePath) {
		filePath = _filePath;
		System.out.println("calling getColorData");
		System.out.println(getColorData());
	}
	

	public float[][] getColorData() {
		try {
			System.out.println("called getColorData");
			BufferedImage heightmap = ImageIO.read(new File(filePath));
			data = new float[heightmap.getWidth()][heightmap.getHeight()];
			
			Color c;
			for (int x = 0; x < data.length; x++) {
				for (int y = 0; y < data.length; y++) {
					c = new Color(heightmap.getRGB(x, y));
					data[x][y] = c.getRed(); // it does not matter what color I retrieve because the heightmap is in black and white
					
					
					//System.out.println("["+x+"]"+"["+y+"]"+"="+c.getRed());
					
				}
			}
		
			return data;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	
	
	
	
}

