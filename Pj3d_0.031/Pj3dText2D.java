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
	public Pj3dText2D(Pj3d parent, String text, String font, int size, int r, int g, int b)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.color = new Pj3dColor();
		Color3f textColor = new Color3f(ColorInt2Float(r), ColorInt2Float(g), ColorInt2Float(b));
		this.InitPrimitive(text, font, size, textColor);
	}
	
//	/
	/// constructor: set the object to the "null" point of the world
	///
	public Pj3dText2D(Pj3d parent, String text, String font, int size, int pcolor)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.color = new Pj3dColor();
		Color3f textColor = new Color3f(color2Color3f(pcolor));
		this.InitPrimitive(text, font, size, textColor);
	}
	
	///
	/// init the primitive
	///
	private void InitPrimitive(String text, String font, int size, Color3f textColor)
	{
		primitiveBranch = new BranchGroup();
		primitiveBranch.setCapability(BranchGroup.ENABLE_PICK_REPORTING);
		primitiveBranch.setCapability(BranchGroup.ALLOW_DETACH);
		primitiveBranch.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		primitiveBranch.setCapability(BranchGroup.ALLOW_PICKABLE_READ);
		primitiveBranch.setPickable(true);

		transform.transform3D = new Transform3D();
		transform.transformVector = new Vector3f(0f, 0f, 0f);
		transform.transform3D.set(transform.transformVector);
		transform.transformgroup = new TransformGroup(transform.transform3D);
		transform.transformgroup.setCapability( TransformGroup.ALLOW_CHILDREN_READ );
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		transform.transformgroup.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
		
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		
		Text2D text2d = new Text2D(text, textColor, font, size, java.awt.Font.PLAIN);
		text2d.setRectangleScaleFactor(0.8f);

		text2d.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
		text2d.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
		
		transform.transformgroup.addChild( text2d );
		primitiveBranch.addChild(transform.transformgroup);
		parent.AddModel(primitiveBranch);
	}
}
