import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Cylinder;

///
///  creates a box primitve
///
//PickingCallback myCallback = new MyCallbackClass();
// Register the class callback to be called
//pickt.setupCallback(myCallback);
public class Pj3dCylinder extends Pj3dToolbox
{
	private Pj3d parent;
	private BranchGroup primitiveBranch;
	private Pj3dToolbox ptools = new Pj3dToolbox();
	private int xdivisions = -1;
	private int ydivisions = -1;
	public Pj3dTransform transform;									///< referenz to the PJ3dTransform objekt
	public Pj3dShader shader;												///< referenz to the PJ3dShader objekt
	
	///
	/// set the Box to the "zero" point of the world
	///
	public Pj3dCylinder(Pj3d parent, float radius, float height)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.InitPrimitive(radius, height, xdivisions, ydivisions);
	}
	
	///
	/// set the Box to the "zero" point of the world
	///
	public Pj3dCylinder(Pj3d parent, float radius, float height, int xdivisions, int ydivisions)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.InitPrimitive(radius, height, xdivisions, ydivisions);
	}

	///
	/// init the primitive
	///	
	private void InitPrimitive(float radius, float height, int xdivisions, int ydivisions)
	{
		primitiveBranch = new BranchGroup();
		primitiveBranch.setPickable(true);
		
		primitiveBranch.setCapability(BranchGroup.ENABLE_PICK_REPORTING);
		primitiveBranch.setCapability(BranchGroup.ALLOW_DETACH);
		primitiveBranch.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		primitiveBranch.setCapability(BranchGroup.ALLOW_PICKABLE_READ);

		transform.transform3D = new Transform3D();
		transform.transformVector = new Vector3f(0f, 0f, 0f);
		transform.transform3D.set(transform.transformVector);
		transform.transformgroup = new TransformGroup(transform.transform3D);
		
		transform.transformgroup.setCapability( TransformGroup.ALLOW_CHILDREN_READ );
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );		
		transform.transformgroup.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
        
		shader.appearance = shader.CreateAppearance(parent.ambientColor, parent.diffuseColor, parent.emissiveColor, parent.specularColor, parent.shininess, parent.alpha);

		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		
		Cylinder cylinder = null;
		if (xdivisions == -1)
			cylinder = new Cylinder(radius, height, primflags, shader.appearance);
		else
			cylinder = new Cylinder(radius, height, primflags, xdivisions, ydivisions, shader.appearance);
		
		cylinder.setCapability(Cylinder.ALLOW_CHILDREN_READ);
		
		for (int i=0; i<cylinder.numChildren(); i++)
		{
			cylinder.getShape(i).setCapability(Shape3D.ALLOW_GEOMETRY_READ);
			cylinder.getShape(i).setCapability(Shape3D.ALLOW_APPEARANCE_READ);
			cylinder.getShape(i).setCapability(Shape3D.ENABLE_PICK_REPORTING);
		}
		
		transform.transformgroup.addChild( cylinder );
		primitiveBranch.addChild(transform.transformgroup);
		parent.AddModel(primitiveBranch);
	}
	
	public void delete()
	{
		primitiveBranch.detach();
	}
}
