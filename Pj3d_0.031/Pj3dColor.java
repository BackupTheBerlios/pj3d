import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;

///
/// the PJ3dColor objekt got just one methode for 
/// converting the given int values to floats
///
public class Pj3dColor extends Pj3dToolbox
{
	public Color3f color2D = new Color3f();	///< color 2d objekt
	
	///
	/// default constructor
	///
	public Pj3dColor() {}
	
	///
	/// set the color to the given value
	///
	public void setColor(int r, int g, int b)
	{
		color2D.x = Int2Float(r);
		color2D.y = Int2Float(g);
		color2D.z = Int2Float(b);
	}
	
	///
	/// set the color to the given value
	///
	public void setColor(float r, float g, float b)
	{
		color2D.x = r;
		color2D.y = g;
		color2D.z = b;
	}
}
