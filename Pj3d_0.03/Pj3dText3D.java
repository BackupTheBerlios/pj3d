import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Text2D;
import java.awt.Font;

///
/// class for 3d text in pj3d
///
public class Pj3dText3D extends Pj3dToolbox
{
	private Pj3d parent;
	private BranchGroup primitiveBranch;
	private Pj3dToolbox ptools = new Pj3dToolbox();
	private float xdim, ydim, zdim;
	public Pj3dTransform transform;								///< reference to the transform object
	public Pj3dShader shader;											///< reference to the shader object
	
	///
	/// constructor: set the object to the "null" point of the world
	///
	public Pj3dText3D(Pj3d parent, String text, String font)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.InitPrimitive(text, font);
	}
	
	///
	/// init the primitive
	///
	private void InitPrimitive(String text, String font)
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
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		
		Font3D font3d = new Font3D(new Font(font, Font.PLAIN, 1), new FontExtrusion());
		Text3D textGeom = new Text3D(font3d, new String(text), new Point3f(0.0f, 0.0f, 0.0f));
		Shape3D textShape = new Shape3D(textGeom, shader.appearance);

		textShape.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
		textShape.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
		
		transform.transformgroup.addChild( textShape );
		primitiveBranch.addChild(transform.transformgroup);
		parent.AddModel(primitiveBranch);
		//parent.pickables.add(box);
		//System.out.println(box.getShape(0).toString());
	}
}
