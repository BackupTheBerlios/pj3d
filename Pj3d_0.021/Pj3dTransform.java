import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.*;

public class Pj3dTransform {

	public TransformGroup transformgroup;
	public Transform3D transform3D;
	public Vector3f transformVector;
	private Pj3dToolbox ptools = new Pj3dToolbox();
	
	///
	/// default konstruktor
	///
	public Pj3dTransform() {}

	///
	/// sichtbare methode in p5 um ein objekt zu manipulieren: translateObj o. translate
	///
	public void translate(int x, int y, int z)
   	{	
		transformVector.x += ptools.Int2Float(x);
		transformVector.y += ptools.Int2Float(y);
		transformVector.z += ptools.Int2Float(z);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// sichtbare methode in p5 um ein objekt zu manipulieren: translateObj o. position
	///
	public void position(int x, int y, int z)
   	{	
		transformVector.x = ptools.Int2Float(x);
		transformVector.y = ptools.Int2Float(y);
		transformVector.z = ptools.Int2Float(z);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// sichtbare methode in p5 um ein objekt zu manipulieren: translateObj o. rotate
	///
	public void rotate(double x, double y, double z)
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
	
	///
	/// sichtbare methode in p5 um ein objekt zu manipulieren: translateObj o. rotation
	///
	public void rotation(double x, double y, double z)
	{
		transform3D.rotX(x);
		transform3D.rotY(y);
		transform3D.rotZ(z);

		transformgroup.setTransform(transform3D);
	}
	
	///
	/// sichtbare methode in p5 um ein objekt zu manipulieren: translateObj o. scale
	///
	public void scale(int s)
	{
		transform3D.setScale(ptools.Int2Double(s));
		transformgroup.setTransform(transform3D);
	}
}
