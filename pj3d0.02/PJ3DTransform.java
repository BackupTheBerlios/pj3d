import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.*;

public class PJ3DTransform {

	public TransformGroup transformgroup;
	public Transform3D transform3D;
	public Vector3f transformVector;
	private PJ3DToolbox ptools = new PJ3DToolbox();
	
	public PJ3DTransform()
	{
		
	}
	
	public void translate(int x, int y, int z) // translateObj o. translate
   	{	
		transformVector.x += ptools.int2Float(x);
		transformVector.y += ptools.int2Float(y);
		transformVector.z += ptools.int2Float(z);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	public void position(int x, int y, int z) // setObj o. set
   	{	
		transformVector.x = ptools.int2Float(x);
		transformVector.y = ptools.int2Float(y);
		transformVector.z = ptools.int2Float(z);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	public void rotate(double x, double y, double z) // rotateObj o. rotate
	{
		Transform3D newTransX = new Transform3D();
		Transform3D newTransY = new Transform3D();
		Transform3D newTransZ = new Transform3D();

		newTransX.rotX(x);
		newTransY.rotY(y);
		newTransZ.rotZ(z);

		transform3D.mul(newTransX);
		transform3D.mul(newTransY);
		transform3D.mul(newTransZ);

		transformgroup.setTransform(transform3D);
	}
	
	public void rotation(double x, double y, double z) // rotateObj o. rotate
	{
		transform3D.rotX(x);
		transform3D.rotY(y);
		transform3D.rotZ(z);

		transformgroup.setTransform(transform3D);
	}
	
	public void scale(int s)
	{
		transform3D.setScale(ptools.int2Double(s));
		transformgroup.setTransform(transform3D);
	}
}
