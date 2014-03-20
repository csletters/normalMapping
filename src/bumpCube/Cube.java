package bumpCube;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import loaders.ShaderHandles;

import android.opengl.GLES20;

public class Cube {

	float baseVertices[] = { 
			-0.0f, 1.0f, 0.0f,
			-0.0f, -0.0f, 0.0f,
			1.0f, -0.0f, 0.0f,
			
			-0.0f, 1.0f, 0.0f,
			1.0f, -0.0f, 0.0f,
			1.0f, 1.0f, 0.0f,
			
			0.0f,1.0f,-1.0f,
			0.0f,0.0f,-1.0f,
			0.0f,0.0f,0.0f,
			
			0.0f,1.0f,-1.0f,
			0.0f,0.0f,0.0f,
			0.0f,1.0f,0.0f,
			
			0.0f,1.0f,-1.0f,
			0.0f,0.0f,-1.0f,
			1.0f,0.0f,-1.0f,
			
			0.0f,1.0f,-1.0f,
			1.0f,0.0f,-1.0f,
			1.0f,1.0f,-1.0f,
			
			1.0f,1.0f,-1.0f,
			1.0f,0.0f,-1.0f,
			1.0f,0.0f,0.0f,
			
			1.0f,1.0f,-1.0f,
			1.0f,0.0f,0.0f,
			1.0f,1.0f,0.0f,
			
			0.0f,1.0f,-1.0f,
			0.0f,1.0f,0.0f,
			1.0f,1.0f,0.0f,
			
			0.0f,1.0f,-1.0f,
			1.0f,1.0f,0.0f,
			1.0f,1.0f,-1.0f,
			
			0.0f,0.0f,-1.0f,
			0.0f,0.0f,0.0f,
			1.0f,0.0f,0.0f,
			
			0.0f,0.0f,-1.0f,
			1.0f,0.0f,0.0f,
			1.0f,0.0f,-1.0f
	
	
	};
	
	float textureCords[] = {
			0.0f,1.0f,
			0.0f,0.0f,
			1.0f,0.0f,
			
			0.0f,1.0f,
			1.0f,0.0f,
			1.0f,1.0f,
			
			0.0f,1.0f,
			0.0f,0.0f,
			1.0f,0.0f,
			
			0.0f,1.0f,
			1.0f,0.0f,
			1.0f,1.0f,
			
			0.0f,1.0f,
			0.0f,0.0f,
			1.0f,0.0f,
			
			0.0f,1.0f,
			1.0f,0.0f,
			1.0f,1.0f,
			
			0.0f,1.0f,
			0.0f,0.0f,
			1.0f,0.0f,
			
			0.0f,1.0f,
			1.0f,0.0f,
			1.0f,1.0f,
			
			0.0f,1.0f,
			0.0f,0.0f,
			1.0f,0.0f,
			
			0.0f,1.0f,
			1.0f,0.0f,
			1.0f,1.0f,
			
			0.0f,1.0f,
			0.0f,0.0f,
			1.0f,0.0f,
			
			0.0f,1.0f,
			1.0f,0.0f,
			1.0f,1.0f,
	};
	
	float normals[] = {
			/*0.0f,0.0f,1.0f,
			0.0f,0.0f,1.0f,
			0.0f,0.0f,1.0f,
			
			0.0f,0.0f,1.0f,
			0.0f,0.0f,1.0f,
			0.0f,0.0f,1.0f,
			
			-1.0f,0.0f,0.0f,
			-1.0f,0.0f,0.0f,
			-1.0f,0.0f,0.0f,
			
			-1.0f,0.0f,0.0f,
			-1.0f,0.0f,0.0f,
			-1.0f,0.0f,0.0f,
			
			0.0f,0.0f,-1.0f,
			0.0f,0.0f,-1.0f,
			0.0f,0.0f,-1.0f,
			
			0.0f,0.0f,-1.0f,
			0.0f,0.0f,-1.0f,
			0.0f,0.0f,-1.0f,
			
			1.0f,0.0f,0.0f,
			1.0f,0.0f,0.0f,
			1.0f,0.0f,0.0f,
			
			1.0f,0.0f,0.0f,
			1.0f,0.0f,0.0f,
			1.0f,0.0f,0.0f,
			
			0.0f,1.0f,0.0f,
			0.0f,1.0f,0.0f,
			0.0f,1.0f,0.0f,
			
			0.0f,1.0f,0.0f,
			0.0f,1.0f,0.0f,
			0.0f,1.0f,0.0f,
			
			0.0f,-1.0f,0.0f,
			0.0f,-1.0f,0.0f,
			0.0f,-1.0f,0.0f,
			
			0.0f,-1.0f,0.0f,
			0.0f,-1.0f,0.0f,
			0.0f,-1.0f,0.0f*/
			 -0.33f,0.33f,0.33f,
			 -0.33f,-0.33f,0.33f,
			 0.33f,-0.33f,0.33f,
			 
			 -0.33f,0.33f,0.33f,
			 0.33f,-0.33f,0.33f,
			 0.33f,0.33f,0.33f,
			 
			 -0.33f,0.33f,-0.33f,
			 -0.33f,-0.33f,-0.33f,
			 -0.33f,-0.33f,0.33f,
			 
			 -0.33f,0.33f,-0.33f,
			 -0.33f,-0.33f,0.33f,
			 0.33f,0.33f,0.33f,
			 
			 
			 -0.33f,0.33f,-0.33f,
			 -0.33f,-0.33f,-0.33f,
			 0.33f,-0.33f,-0.33f,
			 
			 -0.33f,0.33f,-0.33f,
			 0.33f,-0.33f,-0.33f,
			 0.33f,0.33f,-0.33f,
			 
			 0.33f,0.33f,-0.33f,
			 0.33f,-0.33f,-0.33f,
			 0.33f,-0.33f,0.33f,
			 
			 0.33f,0.33f,-0.33f,
			 0.33f,-0.33f,0.33f,
			 0.33f,0.33f,0.33f,
			 
			 -0.33f,0.33f,-0.33f,
			 -0.33f,0.33f,0.33f,
			 0.33f,0.33f,0.33f,
			 
			 -0.33f,0.33f,-0.33f,
			 0.33f,0.33f,0.33f,
			 0.33f,0.33f,-0.33f,
			 
			 -0.33f,-0.33f,-0.33f,
			 -0.33f,-0.33f,0.33f,
			 0.33f,-0.33f,0.33f,
			 
			 -0.33f,-0.33f,-0.33f,
			 0.33f,-0.33f,0.33f,
			 0.33f,-0.33f,-0.33f
			 
	};
	
	float tangents[] = {
			1.0f,0.0f,0.0f,
			1.0f,0.0f,0.0f,
			1.0f,0.0f,0.0f,
			
			1.0f,0.0f,0.0f,
			1.0f,0.0f,0.0f,
			1.0f,0.0f,0.0f,
			
			0.0f,0.0f,1.0f,
			0.0f,0.0f,1.0f,
			0.0f,0.0f,1.0f,
			
			0.0f,0.0f,1.0f,
			0.0f,0.0f,1.0f,
			0.0f,0.0f,1.0f,
			
			-1.0f,0.0f,0.0f,
			-1.0f,0.0f,0.0f,
			-1.0f,0.0f,0.0f,
			
			-1.0f,0.0f,0.0f,
			-1.0f,0.0f,0.0f,
			-1.0f,0.0f,0.0f,
			
			0.0f,0.0f,-1.0f,
			0.0f,0.0f,-1.0f,
			0.0f,0.0f,-1.0f,
			
			0.0f,0.0f,-1.0f,
			0.0f,0.0f,-1.0f,
			0.0f,0.0f,-1.0f,
			
			1.0f,0.0f,0.0f,
			1.0f,0.0f,0.0f,
			1.0f,0.0f,0.0f,
			
			-1.0f,0.0f,0.0f,
			-1.0f,0.0f,0.0f,
			-1.0f,0.0f,0.0f,
			
	};
	
	int positionsBufferIdx;
	int texCoordsBufferIdx;
	int normbuffidx,tanbuffidx;
	int buffers[] = new int[4];
	FloatBuffer vertexBuffer, texBuffer,normalBuffer,tanBuffer;
	
	
	
	public Cube()
	{
		// buffer for cube vertices
		ByteBuffer buffer = ByteBuffer.allocateDirect(4 * baseVertices.length);
		buffer.order(ByteOrder.nativeOrder());
				
		vertexBuffer = buffer.asFloatBuffer();
		vertexBuffer.put(baseVertices);
		vertexBuffer.position(0);
		
		// buffer for cube normals
		ByteBuffer nBuffer = ByteBuffer.allocateDirect(4 * normals.length);
		nBuffer.order(ByteOrder.nativeOrder());

		normalBuffer = nBuffer.asFloatBuffer();
		normalBuffer.put(normals);
		normalBuffer.position(0);
		
		// buffer for texture cords
		ByteBuffer tBuffer = ByteBuffer.allocateDirect(4 * textureCords.length);
		tBuffer.order(ByteOrder.nativeOrder());
		
		texBuffer = tBuffer.asFloatBuffer();
		texBuffer.put(textureCords);
		texBuffer.position(0);
		
		// buffer for texture cords
		ByteBuffer taBuffer = ByteBuffer.allocateDirect(4 * tangents.length);
		taBuffer.order(ByteOrder.nativeOrder());
		
		tanBuffer = taBuffer.asFloatBuffer();
		tanBuffer.put(tangents);
		tanBuffer.position(0);
		
		
		//create VBO map
		GLES20.glGenBuffers(4, buffers, 0);
				
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[0]);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vertexBuffer.capacity() * 4, vertexBuffer, GLES20.GL_STATIC_DRAW);
		
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[1]);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, texBuffer.capacity() * 4, texBuffer, GLES20.GL_STATIC_DRAW);
		
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[2]);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, normalBuffer.capacity() * 4, normalBuffer, GLES20.GL_STATIC_DRAW);
		
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[3]);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, tanBuffer.capacity() * 4, tanBuffer, GLES20.GL_STATIC_DRAW);
		
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		
		positionsBufferIdx = buffers[0];
		texCoordsBufferIdx = buffers[1];
		normbuffidx = buffers[2];
		tanbuffidx = buffers[3];
		vertexBuffer = null;
		texBuffer = null;
	}
	
	public void draw(float[] projection, float[] view, float[] model,int projectionHandle,int viewHandle,int modelHandle,int positionHandle,int mTextureCoordinateHandle, ArrayList<Integer> mtexture, int normalHandle, int tangentHandle, ShaderHandles handles) {
		
		// position
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, positionsBufferIdx);
		GLES20.glEnableVertexAttribArray(positionHandle);
		GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false,	0, 0);
		
		//normal
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, normbuffidx);
		GLES20.glEnableVertexAttribArray(normalHandle);
		GLES20.glVertexAttribPointer(normalHandle, 3, GLES20.GL_FLOAT, false,	0, 0);
		
		//tangent
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, tanbuffidx);
		GLES20.glEnableVertexAttribArray(tangentHandle);
		GLES20.glVertexAttribPointer(tangentHandle, 3, GLES20.GL_FLOAT, false,	0, 0);

		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mtexture.get(0));
		GLES20.glUniform1i(handles.mTextureUniformHandle.get(0), 0);
		
		GLES20.glActiveTexture(GLES20.GL_TEXTURE1);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mtexture.get(1));
		GLES20.glUniform1i(handles.mTextureUniformHandle.get(1),1);
		
		// texture
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, texCoordsBufferIdx);
		GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);
		GLES20.glVertexAttribPointer(mTextureCoordinateHandle, 2,
				GLES20.GL_FLOAT, false, 0, 0);

		// send uniforms
		GLES20.glUniformMatrix4fv(projectionHandle, 1, false, projection, 0);
		GLES20.glUniformMatrix4fv(viewHandle, 1, false, view, 0);
		GLES20.glUniformMatrix4fv(modelHandle, 1, false, model, 0);
		
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, baseVertices.length/3);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

	}
	
	
}
