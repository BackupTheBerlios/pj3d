///
/// einfaches color objekt: wird zZt noch nicht verwendet
///
public class Pj3dColor extends Pj3dToolbox 
{
	private int r, g, b;
	
	///
	/// default konstruktor
	///
	public Pj3dColor() {}
	
	///
	/// benötigt r, g, b als int
	///
	public Pj3dColor(int red, int green, int blue)
	{
		this.r = red;
		this.g = green;
		this.b = blue;
	}
}
