import java.awt.Font;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Text2D;

public class PJ3DText3D extends PJ3DTransform 
{
	public BranchGroup mMainBranch, primitiveBranch;
	public float mColorAR, mColorAG, mColorAB, mColorAA;
	
	public PJ3DText3D(BranchGroup branch, String text, String font, Vector3f transformVec, Color3f ambientColor, Color3f diffuseColor, Color3f emissiveColor, Color3f specularColor, float shininess, float alpha)
	{
		this.mMainBranch = branch;
		this.InitPrimitive(text, font, transformVec, ambientColor, diffuseColor, emissiveColor, specularColor, shininess, alpha);
	}
	
	public void InitPrimitive(String text, String font, Vector3f transformVec, Color3f ambientColor, Color3f diffuseColor, Color3f emissiveColor, Color3f specularColor, float shininess, float alpha)
	{
		primitiveBranch = new BranchGroup();
		t3d = new Transform3D();
		tVec3f = transformVec;
		t3d.set(tVec3f);
		tg = new TransformGroup(t3d);
		tg.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		
		appearance = CreateAppearance(ambientColor, diffuseColor, emissiveColor, specularColor, shininess, alpha);
		Font3D font3d = new Font3D(new Font(font, Font.PLAIN, 1), new FontExtrusion());
		Text3D textGeom = new Text3D(font3d, new String(text), new Point3f(0.0f, 0.0f, 0.0f));
		Shape3D textShape = new Shape3D(textGeom, appearance);
		
		tg.addChild(textShape);
		primitiveBranch.addChild(tg);
		mMainBranch.addChild(primitiveBranch);
	}
}
