import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Box;
import java.util.ArrayList;
///
/// the line drawing class
///
public class Pj3dLine extends Pj3dToolbox
{
	private Pj3d parent;
	private BranchGroup primitiveBranch;
	private Pj3dToolbox ptools = new Pj3dToolbox();
	private float xdim, ydim, zdim;
	private LineArray la;
	private Shape3D pointShape;
	private Point3f[] pts;
	public Pj3dTransform transform;									///< instance to the transform object
	public Pj3dShader shader;												///< instance to the shader object

	///
	/// default constructor
	///	
	public Pj3dLine(Pj3d parent, float [] points)
	{
		this.parent = parent;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.InitPrimitive(points);
	}
	
	private void InitPrimitive(float [] points)
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
		
		pts = new Point3f[points.length/3];
		int counter = 0;
		for (int i=0; i<points.length; i+=3)
		{
			pts[counter] = new Point3f(points[i], points[i+1], points[i+2]);
			
			counter ++;
		}
		
		shader.appearance = shader.CreateAppearance(parent.ambientColor, parent.diffuseColor, parent.emissiveColor, parent.specularColor, parent.shininess, parent.alpha);
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		
		la = new LineArray(pts.length, LineArray.COORDINATES);
		la.setCoordinates(0, pts);
		la.setCapability(LineArray.ALLOW_COUNT_READ);
		la.setCapability(LineArray.ALLOW_COORDINATE_READ);
		la.setCapability(LineArray.ALLOW_COORDINATE_WRITE);
		

		pointShape = new Shape3D(la, shader.appearance);
		pointShape.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
		pointShape.setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);
		pointShape.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
		pointShape.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
		
		transform.transformgroup.addChild( pointShape );
		primitiveBranch.addChild(transform.transformgroup);
		parent.AddModel(primitiveBranch);
	}
	
	public void addPoint(float [] points)
	{	
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		
		Point3f[] ptsCopy = new Point3f[pts.length+2];
		System.arraycopy(pts, 0, ptsCopy, 0, pts.length);

		ptsCopy[ptsCopy.length-2] = pts[pts.length-1];
		ptsCopy[ptsCopy.length-1] = new Point3f(points[0], points[1], points[2]);

		LineArray la2 = new LineArray(ptsCopy.length, LineArray.COORDINATES);
		la2.setCapability(LineArray.ALLOW_COUNT_READ);
		la2.setCapability(LineArray.ALLOW_COORDINATE_READ);
		la2.setCapability(LineArray.ALLOW_COORDINATE_WRITE);
		
		la2.setCoordinates(0, ptsCopy);
		pointShape.setGeometry(la2);
		
		pts = ptsCopy;
	}
}
