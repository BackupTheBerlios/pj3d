import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.loaders.*;
import com.realvue.sim.ui.loader.java3d.max3ds.*;

///
/// class for loading a 3d object in the world
///
public class Pj3d3DS extends Pj3dToolbox 
{
	private Pj3d parent;
	private BranchGroup primitiveBranch;
	private Pj3dToolbox ptools = new Pj3dToolbox();
	private float xdim, ydim, zdim;
	public Pj3dTransform transform;								///< instance to the transform object
	public Pj3dShader shader;											///< instance to the shader object
	public String file;															///< file is public??
	
	///
	/// constructor: set the object to the "null" point of the world
	///
	public Pj3d3DS(Pj3d parent, String fileLocation)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.file = fileLocation;
		this.InitPrimitive(file);
	}
	
	///
	/// init the 3d object to the world
	///
	public void InitPrimitive(String file)
	{
		Scene s = null;
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
		
		Loader3DS hm = new Loader3DS();
		try
		{
			s = hm.load(file);
			transform.transformgroup.addChild( s.getSceneGroup () );
		}
		catch (Exception e) {}
		
		primitiveBranch.addChild(transform.transformgroup);
		parent.AddModel(primitiveBranch);
	}
	
	public void delete()
	{
		primitiveBranch.detach();
	}
}
