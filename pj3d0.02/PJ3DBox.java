import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Box;

public class PJ3DBox
{
	private PJ3D parent;
	private BranchGroup primitiveBranch;
	public PJ3DTransform transform = new PJ3DTransform();
	public PJ3DColor color = new PJ3DColor();
	private PJ3DToolbox ptools = new PJ3DToolbox();
	private float xdim, ydim, zdim;
	
	public PJ3DBox(PJ3D parent, int x)
	{
		this.parent = parent;
		this.xdim = ptools.int2Float(x);
		this.InitPrimitive(xdim, xdim, xdim);
	}
	
	public PJ3DBox(PJ3D parent, int x, int y, int z)
	{
		this.parent = parent;
		this.xdim = ptools.int2Float(x);
		this.ydim = ptools.int2Float(y);
		this.zdim = ptools.int2Float(z);
		this.InitPrimitive(xdim, ydim, zdim);
	}
	
	public void InitPrimitive(float xdim, float ydim, float zdim)
	{
		primitiveBranch = new BranchGroup();
		transform.transform3D = new Transform3D();
		transform.transformVector = new Vector3f(0f, 0f, 0f);
		transform.transform3D.set(transform.transformVector);
		transform.transformgroup = new TransformGroup(transform.transform3D);
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
		
		color.appearance = color.CreateAppearance(parent.ambientColor, parent.diffuseColor, parent.emissiveColor, parent.specularColor, parent.shininess, parent.alpha);
		Box box = new Box(xdim, ydim, zdim, color.appearance);
		transform.transformgroup.addChild( box );
		primitiveBranch.addChild(transform.transformgroup);
		parent.addModelToBranch(primitiveBranch);
	}
}
