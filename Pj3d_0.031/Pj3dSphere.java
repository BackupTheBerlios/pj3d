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
	private int divisions = -1;
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
		this.xdim = (float)x;
		this.InitPrimitive(xdim);
	}

	///
	/// constructor: set the object to the given values 
	///	
	public Pj3dSphere(Pj3d parent, float x)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.xdim = x;
		this.InitPrimitive(xdim);
	}
	
	///
	/// constructor: set the object to the given values 
	///	
	public Pj3dSphere(Pj3d parent, float x, int divisions)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.xdim = x;
		this.divisions = divisions;
		this.InitPrimitive(xdim);
	}
	
	///
	/// init the primitive
	///
	private void InitPrimitive(float xdim)
	{
		primitiveBranch = new BranchGroup();
		primitiveBranch.setCapability(BranchGroup.ENABLE_PICK_REPORTING);
		primitiveBranch.setCapability(BranchGroup.ALLOW_DETACH);
		primitiveBranch.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		primitiveBranch.setCapability(BranchGroup.ALLOW_PICKABLE_READ);
		
		transform.transform3D = new Transform3D();
		transform.transformVector = new Vector3f(0f, 0f, 0f);
		transform.transform3D.set(transform.transformVector);
		transform.transformgroup = new TransformGroup(transform.transform3D);
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
		
		shader.appearance = shader.CreateAppearance(parent.ambientColor, parent.diffuseColor, parent.emissiveColor, parent.specularColor, parent.shininess, parent.alpha);
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		Sphere sphere = null;
		
		if (divisions == -1)
			sphere = new Sphere(xdim, primflags, shader.appearance);
		else
			sphere = new Sphere(xdim, primflags, divisions, shader.appearance);
			
		sphere.setCapability(Sphere.ALLOW_CHILDREN_READ);
		
		for (int i=0; i<sphere.numChildren(); i++)
		{
			sphere.getShape(i).setCapability(Shape3D.ALLOW_GEOMETRY_READ);
			sphere.getShape(i).setCapability(Shape3D.ALLOW_APPEARANCE_READ);
			sphere.getShape(i).setCapability(Shape3D.ENABLE_PICK_REPORTING);
		}
		
		transform.transformgroup.addChild( sphere );
		primitiveBranch.addChild(transform.transformgroup);
		parent.AddModel(primitiveBranch);
	}
	
	public void delete()
	{
		primitiveBranch.detach();
	}
}
