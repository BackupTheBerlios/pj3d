import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.geometry.Box;

public class Pj3dObj extends Pj3dToolbox 
{
	private Pj3d parent;
	private BranchGroup primitiveBranch;
	private Pj3dToolbox ptools = new Pj3dToolbox();
	private float xdim, ydim, zdim;
	public Pj3dTransform transform;
	public Pj3dShader shader;
	public String file;
	
	///
	/// konstruktor 1: setzt das objekt auf den "null" punkt
	///
	public Pj3dObj(Pj3d parent, String fileLocation)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.file = fileLocation;
		this.InitPrimitive(file);
	}
	
	
	public void InitPrimitive(String file)
	{
		Scene s = null;
		primitiveBranch = new BranchGroup();
		transform.transform3D = new Transform3D();
		transform.transformVector = new Vector3f(0f, 0f, 0f);
		transform.transform3D.set(transform.transformVector);
		transform.transformgroup = new TransformGroup(transform.transform3D);
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
		
		ObjectFile f = new ObjectFile ();
        f.setFlags (ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
      	
        try
		{
        	s = f.load(file);
        }
        catch (Exception e)
		{
        	System.out.println(e);
        }
        
		transform.transformgroup.addChild( s.getSceneGroup () );
		primitiveBranch.addChild(transform.transformgroup);
		parent.AddModel(primitiveBranch);
	}
}