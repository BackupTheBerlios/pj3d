import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Sphere;

public class PJ3DSphere extends PJ3DTransform 
{
	public BranchGroup mMainBranch, primitiveBranch;
	public float mColorAR, mColorAG, mColorAB, mColorAA;
	
	public PJ3DSphere(BranchGroup branch, float xdim, Vector3f transformVec, Color3f ambientColor, Color3f diffuseColor, Color3f emissiveColor, Color3f specularColor, float shininess, float alpha)
	{
		this.mMainBranch = branch;
		this.InitPrimitive(xdim, transformVec, ambientColor, diffuseColor, emissiveColor, specularColor, shininess, alpha);
	}
	
	public void InitPrimitive(float xdim, Vector3f transformVec, Color3f ambientColor, Color3f diffuseColor, Color3f emissiveColor, Color3f specularColor, float shininess, float alpha)
	{
		primitiveBranch = new BranchGroup();
		t3d = new Transform3D();
		tVec3f = transformVec;
		t3d.set(tVec3f);
		tg = new TransformGroup(t3d);
		tg.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		
		appearance = CreateAppearance(ambientColor, diffuseColor, emissiveColor, specularColor, shininess, alpha);
		Sphere sphere = new Sphere(xdim, appearance);
		tg.addChild(sphere);
		primitiveBranch.addChild(tg);
		mMainBranch.addChild(primitiveBranch);
	}
}
