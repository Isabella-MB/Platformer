package com.pixelaborate.platformer;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;



public class OBJ_Parser {

	public String path;//path of file
	public final static String VERTEX_TEXTURE = "vt";//texture coordinate
	public final static String VERTEX_NORMAL = "vn";//normal
	public final static String FACE = "f";//face
	public final static String VERTEX = "v";//vertex
	public final static String COMMENT = "#";//comment
	private static BufferedReader r;//reader used to parse .obj to model

	public OBJ_Parser(String p) {
		path = p;
	}

	public OBJ_Parser() {
		path = "res/models/lumberJack.obj";//default model, but should be specified when class is created with other constructor
	}


	public Model parseOBJ() {

		try {//needs to be in try catch to prevent errors
			return parseFile(new File(path));
		}

		catch(Exception e) {
			System.out.println("error occured while parsing .obj file");
			e.printStackTrace();
		}
		return new Model();
	}


	public Model parseOBJ(String p) throws Exception{//when passed the string of the file, it will parse it


		try {
			return parseFile(new File(p));
		}

		catch(Exception e) {
			System.out.println("error occured while parsing .obj file");
			e.printStackTrace();
		}
		return new Model();

	}

	public Model parseFile(File f) throws IOException { //the meat of the class
		r = new BufferedReader(new FileReader (f));
		Model m = new Model();
		String line;
		int lineCount = 0;

		while ((line = r.readLine()) != null) {
			String type = line.split(" ")[0];
			switch (type) {// does different things for different cases
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

	public static Vector3f parseVertex(String line) {//returns a vector of the vertex
		String[] XYZ = line.split(" ");
		return new Vector3f(Float.valueOf(XYZ[1]), Float.valueOf(XYZ[2]), Float.valueOf(XYZ[3]));
	}

	public static Vector3f parseNormal(String line) {//returns a vector of the normal
		String[] XYZ = line.split(" ");
		return new Vector3f(Float.valueOf(XYZ[1]), Float.valueOf(XYZ[2]), Float.valueOf(XYZ[3]));
	}

	public static Vector2f parseTextureCoordinate(String line) { //returns a vector of the texture coordinate
		String[] XY = line.split(" ");
		return new Vector2f(Float.valueOf(XY[1]), Float.valueOf(XY[2]));
	}


	private static Face parseFace(boolean hasNormals, String line) { //returns a face
		String[] faceIndices = line.split(" "); //split line of .obj file at spaces
		int[] vertexIndicesArray = {Integer.parseInt(faceIndices[1].split("/")[0]),
				Integer.parseInt(faceIndices[2].split("/")[0]), Integer.parseInt(faceIndices[3].split("/")[0])};
		if (hasNormals) {// if face has normals, return a face with normals, otherwise return a face without normals
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

