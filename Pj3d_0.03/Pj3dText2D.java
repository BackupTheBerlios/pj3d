import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Text2D;
import java.awt.Font;

///
/// class for 2d text in pj3d
///
public class Pj3dText2D extends Pj3dToolbox
{
	private Pj3d parent;
	private BranchGroup primitiveBranch;
	private Pj3dToolbox ptools = new Pj3dToolbox();
	private float xdim, ydim, zdim;	
	public Pj3dTransform transform;									///< reference to the transform object
	public Pj3dColor color;													///< reference to the color
	
	///
	/// constructor: set the object to the "null" point of the world
	///
	public Pj3dText2D(Pj3d parent, String text, String font, int size)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.color = new Pj3dColor();
		this.InitPrimitive(text, font, size);
	}
	
	///
	/// init the primitive
	///
	private void InitPrimitive(String text, String font, int size)
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
		
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		
		Text2D text2d = new Text2D(text, color.color2D, font, size, java.awt.Font.PLAIN);

		text2d.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
		text2d.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
		
		transform.transformgroup.addChild( text2d );
		primitiveBranch.addChild(transform.transformgroup);
		parent.AddModel(primitiveBranch);
	}
}
