import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Box;
import java.util.ArrayList;

public class Pj3dBox extends Pj3dToolbox
{
	private Pj3d parent;
	private BranchGroup primitiveBranch;
	private Pj3dToolbox ptools = new Pj3dToolbox();
	private float xdim, ydim, zdim;
	public Pj3dTransform transform;
	public Pj3dShader shader;
	
	///
	/// konstruktor 1: setzt das objekt auf den "null" punkt
	///
	public Pj3dBox(Pj3d parent, int x)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.xdim = Int2Float(x);
		this.InitPrimitive(xdim, xdim, xdim);
	}

	///
	/// konstruktor : setzt das objekt auf die gegebenen koordinatent
	///	
	public Pj3dBox(Pj3d parent, int x, int y, int z)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.xdim = Int2Float(x);
		this.ydim = Int2Float(y);
		this.zdim = Int2Float(z);
		this.InitPrimitive(xdim, ydim, zdim);
	}
	
	private void InitPrimitive(float xdim, float ydim, float zdim)
	{
		primitiveBranch = new BranchGroup();
		primitiveBranch.setPickable(true);
		primitiveBranch.setCapability(BranchGroup.ENABLE_PICK_REPORTING);
		primitiveBranch.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		transform.transform3D = new Transform3D();
		transform.transformVector = new Vector3f(0f, 0f, 0f);
		transform.transform3D.set(transform.transformVector);
		transform.transformgroup = new TransformGroup(transform.transform3D);
		transform.transformgroup.setCapability( TransformGroup.ALLOW_CHILDREN_READ );
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
		
		shader.appearance = shader.CreateAppearance(parent.ambientColor, parent.diffuseColor, parent.emissiveColor, parent.specularColor, parent.shininess, parent.alpha);
		//Box box = new Box(xdim, ydim, zdim, shader.appearance);
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS; 
		Box box = new Box(xdim, ydim, zdim, primflags, shader.appearance);
		box.setCapability(Box.ALLOW_CHILDREN_READ);
		box.getShape(0).setCapability(Shape3D.ALLOW_GEOMETRY_READ);
		box.getShape(0).setCapability(Shape3D.ALLOW_APPEARANCE_READ);
		//box.setCapability(Box.);
		
		transform.transformgroup.addChild( box );
		primitiveBranch.addChild(transform.transformgroup);
		parent.AddModel(primitiveBranch);
		//parent.pickables.add(box);
		//System.out.println(box.getShape(0).toString());
	}
}
