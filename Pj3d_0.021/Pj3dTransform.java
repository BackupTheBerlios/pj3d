import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.*;

///
/// class for the movement and resizing of the aviable objets in the mainbranch
///
public class Pj3dTransform {

	public TransformGroup transformgroup;						///< reference to the transform object
	public Transform3D transform3D;									///< reference to the transform3d object
	public Vector3f transformVector;									///< the transformvector
	private Pj3dToolbox ptools = new Pj3dToolbox();
	
	///
	/// default constructor
	///
	public Pj3dTransform() {}

	///
	/// the visible translate object. this is visible for the processing users
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
	/// the visible translate object. this is visible for the processing users
	///
	public void translate(float x, float y, float z)
   	{	
		transformVector.x += x;
		transformVector.y += y;
		transformVector.z += z;
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// manipulates the x axis need a integer
	///
	public void translateX(int x)
   	{	
		transformVector.x += ptools.Int2Float(x);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// manipulates the x axis need a float
	///
	public void translateX(float x)
   	{	
		transformVector.x += x;
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// manipulates the y axis need a integer
	///
	public void translateY(int y)
   	{	
		transformVector.y += ptools.Int2Float(y);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// manipulates the y axis need a floaf
	///
	public void translateY(float y)
   	{	
		transformVector.y += y;
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// manipulates the z axis need a integer
	///
	public void translateZ(int z)
   	{	
		transformVector.z += ptools.Int2Float(z);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// manipulates the z axis need a float
	///
	public void translateZ(float z)
   	{	
		transformVector.z += z;
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	/// 
	/// visible methode in p5 to manipulate a object: like translateObj	to.position. need integer
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
	
	/// 
	/// visible methode in p5 to manipulate a object: like translateObj	to.position. need float
	///
	public void setPosition(float x, float y, float z)
   	{	
		transformVector.x = x;
		transformVector.y = y;
		transformVector.z = x;
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// set the x position of the object. need integer
	///
	public void setPositionX(int x)
   	{	
		transformVector.x = ptools.Int2Float(x);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// set the x position of the object. need float
	///
	public void setPositionX(float x)
   	{	
		transformVector.x = x;
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// set the y position of the object. need integer
	///
	public void setPositionY(int y)
   	{	
		transformVector.y = ptools.Int2Float(y);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// set the y position of the object. need float
	///
	public void setPositionY(float y)
   	{	
		transformVector.y = y;
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}

	///
	/// set the z position of the object. need integer
	///
	public void setPositionZ(int z)
   	{	
		transformVector.z = ptools.Int2Float(z);
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// set the z position of the object. need float
	///
	public void setPositionZ(float z)
   	{	
		transformVector.z = z;
		
		// in Transform3D schreiben
		transform3D.setTranslation(transformVector);
		transformgroup.setTransform(transform3D);
   	}
	
	///
	/// returns the x position of the object
	///
	public float getPositionX()
   	{	
		return transformVector.x;
   	}

	///
	/// returns the y position of the object
	///
	public float getPositionY()
   	{	
		return transformVector.y;
   	}
	
	///
	/// returns the z position of the object
	///
	public float getPositionZ()
   	{	
		return transformVector.z;
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
