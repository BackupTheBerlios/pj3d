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
	
	public void translateX(int x)
   	{	
		transformVector.x += ptools.Int2Float(x);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	public void translateY(int y)
   	{	
		transformVector.y += ptools.Int2Float(y);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	public void translateZ(int z)
   	{	
		transformVector.z += ptools.Int2Float(z);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// sichtbare methode in p5 um ein objekt zu manipulieren: translateObj o. position
	///
	public void setPosition(int x, int y, int z)
   	{	
		transformVector.x = ptools.Int2Float(x);
		transformVector.y = ptools.Int2Float(y);
		transformVector.z = ptools.Int2Float(z);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	public void setPositionX(int x)
   	{	
		transformVector.x = ptools.Int2Float(x);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	public void setPositionY(int y)
   	{	
		transformVector.y = ptools.Int2Float(y);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	public void setPositionZ(int z)
   	{	
		transformVector.z = ptools.Int2Float(z);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	public int getPositionX()
   	{	
		int x = ptools.Float2Int(transformVector.x);
		return x;
   	}
	
	public int getPositionY()
   	{	
		int y = ptools.Float2Int(transformVector.y);
		return y;
   	}
	
	public int getPositionZ()
   	{	
		int z = ptools.Float2Int(transformVector.z);
		return z;
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
	public void setRotation(double x, double y, double z)
	{
		transform3D.rotX(x);
		transform3D.rotY(y);
		transform3D.rotZ(z);

		transformgroup.setTransform(transform3D);
	}
	
	///
	/// sichtbare methode in p5 um ein objekt zu manipulieren: translateObj o. scale
	///
	public void setScale(int s)
	{
		transform3D.setScale(ptools.Int2Double(s));
		transformgroup.setTransform(transform3D);
	}
}
