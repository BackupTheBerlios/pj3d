import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Text2D;

public class PJ3DText2D extends PJ3DTransform 
{
	public BranchGroup mMainBranch, primitiveBranch;
	public float mColorAR, mColorAG, mColorAB, mColorAA;
	
	public PJ3DText2D(BranchGroup branch, String text, String font, int textSize, Vector3f transformVec, Color3f textColor)
	{
		this.mMainBranch = branch;
		this.InitPrimitive(text, font, textSize, transformVec, textColor);
	}
	
	public void InitPrimitive(String text, String font, int textSize, Vector3f transformVec, Color3f textColor)
	{
		primitiveBranch = new BranchGroup();
		t3d = new Transform3D();
		tVec3f = transformVec;
		t3d.set(tVec3f);
		tg = new TransformGroup(t3d);
		tg.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		
		Text2D txt2d = new Text2D(text, textColor, font, textSize, java.awt.Font.PLAIN);
		tg.addChild( txt2d );
		primitiveBranch.addChild(tg);
		mMainBranch.addChild(primitiveBranch);
	}
}
