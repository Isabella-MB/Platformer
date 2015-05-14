package com.pixelaborate.platformer;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;



public class OBJ_Parser {

	public String path;
	public final static String VERTEX_TEXTURE = "vt";
	public final static String VERTEX_NORMAL = "vn";
	public final static String FACE = "f";
	public final static String VERTEX = "v";
	public final static String COMMENT = "#";
	private static BufferedReader r;

	public OBJ_Parser(String p) {
		path = p;
	}

	public OBJ_Parser() {
		path = "res/models/lumberJack.obj";
	}


	public Model parseOBJ() {

		try {
		return parseFile(new File(path));
		}

		catch(Exception e) {
			System.out.println("error occured while parsing .obj file");
			e.printStackTrace();
		}
		return new Model();
	}


	public Model parseOBJ(String p) throws Exception{


		try {
		return parseFile(new File(p));
		}

		catch(Exception e) {
			System.out.println("error occured while parsing .obj file");
			e.printStackTrace();
		}
		return new Model();
		
	}

	public Model parseFile(File f) throws IOException {
		r = new BufferedReader(new FileReader (f));
		Model m = new Model();
		String line;
		int lineCount = 0;

		while ((line = r.readLine()) != null) {
			String type = line.split(" ")[0];
			switch (type) {
			case COMMENT: 
				//unless comments need to be read, this can be left blank

				continue;

			case VERTEX_TEXTURE:

				m.getTextureCoordinates().add(parseTextureCoordinate(line));
				break;

			case VERTEX_NORMAL:

				m.getNormals().add(parseNormal(line));
				break;

			case VERTEX:

				m.getVertices().add(parseVertex(line));
				break;

				case FACE:

					m.addFace(parseFace(m.hasNormals(), line));
					break;

			default:

				//System.out.println("Line " + lineCount + " Cannot be parsed:" + line);

			}
			lineCount++;
			//System.out.println(lineCount++);
		}
		r.close();
		return m;
	}

	public static Vector3f parseVertex(String line) {
		String[] XYZ = line.split(" ");
		return new Vector3f(Float.valueOf(XYZ[1]), Float.valueOf(XYZ[2]), Float.valueOf(XYZ[3]));
	}

	public static Vector3f parseNormal(String line) {
		String[] XYZ = line.split(" ");
		return new Vector3f(Float.valueOf(XYZ[1]), Float.valueOf(XYZ[2]), Float.valueOf(XYZ[3]));
	}

	public static Vector2f parseTextureCoordinate(String line) {
		String[] XY = line.split(" ");
		return new Vector2f(Float.valueOf(XY[1]), Float.valueOf(XY[2]));
	}
	
	
	 private static Face parseFace(boolean hasNormals, String line) {
	        String[] faceIndices = line.split(" ");
	        int[] vertexIndicesArray = {Integer.parseInt(faceIndices[1].split("/")[0]),
	                Integer.parseInt(faceIndices[2].split("/")[0]), Integer.parseInt(faceIndices[3].split("/")[0])};
	        if (hasNormals) {
	            int[] normalIndicesArray = new int[3];
	            normalIndicesArray[0] = Integer.parseInt(faceIndices[1].split("/")[2]);
	            normalIndicesArray[1] = Integer.parseInt(faceIndices[2].split("/")[2]);
	            normalIndicesArray[2] = Integer.parseInt(faceIndices[3].split("/")[2]);
	            return new Face(vertexIndicesArray, normalIndicesArray);
	        } else {
	            return new Face((vertexIndicesArray));
	        }
	    }
	
}

