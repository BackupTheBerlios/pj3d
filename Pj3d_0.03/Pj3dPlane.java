import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Box;

///
/// draws a plane 
///
public class Pj3dPlane extends Pj3dToolbox
{
	private Pj3d parent;
	private BranchGroup primitiveBranch;
	private Pj3dToolbox ptools = new Pj3dToolbox();
	private float xdim, ydim, zdim;
	public Pj3dTransform transform;								///< reference to the transform object
	public Pj3dShader shader;											///< reference to the shader object

	///
	/// constructor, need the size of the plane as integers
	///	
	public Pj3dPlane(Pj3d parent, int x, int z)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.xdim = Int2Float(x);
		// fake, damit ich keine plane selbst schreiben muss..
		this.ydim = 0.0001f;
		this.zdim = Int2Float(z);
		this.InitPrimitive(xdim, ydim, zdim);
	}
	
	///
	/// constructor, need the size of the plane as floats
	///	
	public Pj3dPlane(Pj3d parent, float x, float z)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.xdim = x;
		// fake, damit ich keine plane selbst schreiben muss..
		this.ydim = 0.0001f;
		this.zdim = z;
		this.InitPrimitive(xdim, ydim, zdim);
	}
	
	///
	/// init the plane
	///
	private void InitPrimitive(float xdim, float ydim, float zdim)
	{
		primitiveBranch = new BranchGroup();
		transform.transform3D = new Transform3D();
		transform.transformVector = new Vector3f(0f, 0f, 0f);
		transform.transform3D.set(transform.transformVector);
		transform.transformgroup = new TransformGroup(transform.transform3D);
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
		
		shader.appearance = shader.CreateAppearance(parent.ambientColor, parent.diffuseColor, parent.emissiveColor, parent.specularColor, parent.shininess, parent.alpha);
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		Box box = new Box(xdim, ydim, zdim, primflags, shader.appearance);
		transform.transformgroup.addChild( box );
		primitiveBranch.addChild(transform.transformgroup);
		parent.AddModel(primitiveBranch);
	}
}
