import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;

public class Pj3dColor extends Pj3dToolbox
{
	public Color3f color2D = new Color3f();
	
	///
	/// default konstruktor
	///
	public Pj3dColor() {}
	
	///
	/// set methode fuer text2D
	///
	public void setColor(int r, int g, int b)
	{
		color2D.x = Int2Float(r);
		color2D.y = Int2Float(g);
		color2D.z = Int2Float(b);
	}
}
