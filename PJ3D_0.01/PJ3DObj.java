import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;

public class PJ3DObj extends PJ3DTransform 
{
	public BranchGroup mMainBranch, primitiveBranch;
	public float mColorAR, mColorAG, mColorAB, mColorAA;
	public String file;
	
	public PJ3DObj(BranchGroup branch, String fileLocation, Vector3f transformVec)
	{
		this.mMainBranch = branch;
		this.file = fileLocation;
		this.InitPrimitive(transformVec);
	}
	
	public void InitPrimitive(Vector3f transformVec)
	{
		Scene s = null;
		primitiveBranch = new BranchGroup();
		t3d = new Transform3D();
		tVec3f = transformVec;
		t3d.set(tVec3f);
		tg = new TransformGroup(t3d);
		tg.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		
		ObjectFile f = new ObjectFile ();
        f.setFlags (ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
      	
        try
		{
        	s = f.load(file);
        }
        catch (Exception e)
		{
        	System.out.println(e);
        	//System.exit(1);
        }
        	  
		tg.addChild( s.getSceneGroup () );
		primitiveBranch.addChild(tg);
		mMainBranch.addChild(primitiveBranch);
	}
}
