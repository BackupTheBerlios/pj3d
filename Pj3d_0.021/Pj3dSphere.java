import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

///
/// creates a sphere primitive
///
public class Pj3dSphere extends Pj3dToolbox
{
	private Pj3d parent;
	private BranchGroup primitiveBranch;
	private Pj3dToolbox ptools = new Pj3dToolbox();
	private float xdim, ydim, zdim;
	public Pj3dTransform transform;								///< reference to the transform object
	public Pj3dShader shader;											///< reference to the shader object
	
	///
	/// constructor: set the object to the "null" point of the world
	///
	public Pj3dSphere(Pj3d parent, int x)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.xdim = Int2Float(x);
		this.InitPrimitive(xdim);
	}

	///
	/// constructor: set the object to the given values 
	///	
	public Pj3dSphere(Pj3d parent, int x, int y, int z)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.xdim = Int2Float(x);
		this.InitPrimitive(xdim);
	}
	
	///
	/// init the primitive
	///
	private void InitPrimitive(float xdim)
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
		Sphere sphere = new Sphere(xdim, primflags, shader.appearance);
		transform.transformgroup.addChild( sphere );
		primitiveBranch.addChild(transform.transformgroup);
		parent.AddModel(primitiveBranch);
	}
}
