precision mediump float;
uniform sampler2D uTexture;
uniform sampler2D nTexture;
varying vec2 vTexCord;
varying vec3 eyeDir;
varying vec3 lightDir;
void main() {
	vec2 flipped_texcoord = vec2(vTexCord.x, 1.0 - vTexCord.y);

	vec3 v = normalize(eyeDir);
	vec3 l = normalize(lightDir);
	vec3 N = normalize(texture2D(nTexture, flipped_texcoord).rgb *2.0 - vec3(1.0));
	
	vec3 R = reflect(-l,N);
	
	vec3 diffuse = max(dot(N,l),0.0)*texture2D(uTexture, flipped_texcoord).rgb;
	vec3 specular = max(pow(dot(R,v),5.0),0.0)*vec3(1.0);
	gl_FragColor =vec4(diffuse+specular,1.0);
}