package com.example.normalmapping;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import loaders.RawResourceReader;
import loaders.ShaderHandles;
import loaders.ShaderHelper;
import loaders.TextureHelper;

import bumpCube.Cube;

import android.content.Context;
import android.opengl.Matrix;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.os.SystemClock;

public class MyRenderer implements Renderer {

	int widthView,heightView;
	Context mActivityContext;
	Cube rocktexcube;
	
	float[] projection = new float[16];
	float[] view = new float[16];
	float[] model = new float[16];
	float[] rotMatrix = new float[16];
	ArrayList<ShaderHandles> shaderPrograms = new ArrayList<ShaderHandles>();
	
	public MyRenderer(final Context activityContext)
	{
		mActivityContext = activityContext;
	}
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		GLES20.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		long time = SystemClock.uptimeMillis() % 4000L;
		float angle = 0.090f * ((int) time);
		Matrix.setRotateM(rotMatrix, 0, angle, 0.0f, 1.0f, 0.0f);
		
		Matrix.multiplyMM(model, 0, rotMatrix, 0, model, 0);
		Matrix.translateM(model, 0, -0.5f, -0.5f, 0.5f);
		GLES20.glUseProgram(shaderPrograms.get(0).programHandle);
		rocktexcube.draw(projection, view, model, shaderPrograms.get(0).projectionHandle, shaderPrograms.get(0).viewHandle, shaderPrograms.get(0).modelHandle, shaderPrograms.get(0).positionHandle, shaderPrograms.get(0).mTextureCoordinateHandle, shaderPrograms.get(0).mTextureDataHandle,shaderPrograms.get(0).normalHandle,shaderPrograms.get(0).tangentHandle,shaderPrograms.get(0));
		Matrix.setIdentityM(model, 0);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		GLES20.glViewport(0, 0, width, height);
		float ratio = (float)width/height;
		Matrix.perspectiveM(projection, 0, 90, ratio, 1, 1000);
		Matrix.setLookAtM(view, 0, 0.0f, 0.6f, 2, 0, 0.0f, 0, 0, 1, 0);
		Matrix.setIdentityM(model, 0);
		widthView = width;
		heightView = height;
		
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		 GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		 GLES20.glClearDepthf(1.0f);  
		 GLES20.glEnable( GLES20.GL_DEPTH_TEST );
		 GLES20.glDepthFunc( GLES20.GL_LESS);
		 GLES20.glDepthMask( true );
		 
		//create shader program handles and program for display textures
		ShaderHandles shader = new ShaderHandles();
		shader.programHandle = createShader(R.raw.vertextexture,R.raw.fragmenttexture);
		shader.mTextureDataHandle.add(TextureHelper.loadTexture(mActivityContext, R.drawable.normalmappic));
		shader.mTextureDataHandle.add(TextureHelper.loadTexture(mActivityContext, R.drawable.normalmap));
		initBasicHandlesWTexture(shader.programHandle,shader);
		shaderPrograms.add(shader);
		rocktexcube = new Cube();
	}
	
	public void initBasicHandlesWTexture(int mProgram, ShaderHandles shader)
	{
		//attributes
		shader.positionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
		shader.mTextureCoordinateHandle = GLES20.glGetAttribLocation(mProgram, "aTexCord");
		shader.normalHandle = GLES20.glGetAttribLocation(mProgram, "normal");
		shader.tangentHandle = GLES20.glGetAttribLocation(mProgram,"tangent");
									
		//uniforms
		shader.modelHandle =  GLES20.glGetUniformLocation(mProgram, "model");
		shader.viewHandle =  GLES20.glGetUniformLocation(mProgram, "view");
		shader.projectionHandle =  GLES20.glGetUniformLocation(mProgram, "projection");
		shader.mTextureUniformHandle.add(GLES20.glGetUniformLocation(mProgram, "uTexture"));
		shader.mTextureUniformHandle.add(GLES20.glGetUniformLocation(mProgram, "nTexture"));
		
		// Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
		/*GLES20.glUniform1i(shader.mTextureUniformHandle.get(0), 0);
		
		GLES20.glUniform1i(shader.mTextureUniformHandle.get(1),1);*/
		
	}
	
	public int createShader(int vertex, int fragment) {
		String vertexShaderCode = RawResourceReader
				.readTextFileFromRawResource(mActivityContext, vertex);
		String fragmentShaderCode = RawResourceReader
				.readTextFileFromRawResource(mActivityContext, fragment);

		int vertexShaderHandle = ShaderHelper.compileShader(
				GLES20.GL_VERTEX_SHADER, vertexShaderCode);
		int fragmentShaderHandle = ShaderHelper.compileShader(
				GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
			
		int mProgram;
		
		mProgram = ShaderHelper.createAndLinkProgram(vertexShaderHandle,fragmentShaderHandle);

		return mProgram;
	}

	
}
