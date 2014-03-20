attribute vec4 aPosition;
attribute vec2 aTexCord;
attribute vec3 normal;
attribute vec3 tangent;
uniform mat4 projection;
uniform mat4 view;
uniform mat4 model;

varying vec2 vTexCord;
varying vec3 eyeDir;
varying vec3 lightDir;

void main() {

	vec3 light_pos = vec3(1.0,0.0,4.0);
	mat4 mvMatrix = view*model;
	vec4 viewPosition = mvMatrix*aPosition;
	
	vec3 normalizednormal = normalize(mat3(mvMatrix)*normal);
	vec3 normalizedtangent = normalize(mat3(mvMatrix)* tangent);
	
	vec3 bittangent = cross(normalizednormal,normalizedtangent);
	
	//view vector
	vec3 v = -viewPosition.xyz;
	eyeDir = normalize(vec3(dot(v,normalizedtangent),dot(v,bittangent),dot(v,normalizedtangent)));
	
	//vector from point to light source
	vec3 source = light_pos - viewPosition.xyz;
	lightDir = normalize(vec3(dot(source,normalizedtangent),dot(source,bittangent),dot(source,normalizedtangent)));
	
	vTexCord = aTexCord;
	gl_Position = projection*view*model*aPosition;
}