import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;

public class Pj3dColor2D extends Pj3dToolbox
{
	private Color3f text2D = new Color3f();
	public Appearance appearance;
	
	///
	/// default konstruktor
	///
	public Pj3dColor2D() {}
	
	public Appearance CreateAppearance(Color3f ambientColor, Color3f diffuseColor, Color3f emissiveColor, Color3f specularColor, float shininess, float alpha)
	{		
		//this is an easy way to convert a jpg file to the texture object we need.
		//TextureLoader loader = new TextureLoader("/home/rfroese/java/reedtalk/applets/bixle_door.jpg", new String("RGB"), comp);
		//Texture texture = loader.getTexture();
		appearance = new Appearance();
		appearance.setCapability(Appearance.ALLOW_MATERIAL_READ);
		appearance.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		appearance.setCapability(Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_READ);
		appearance.setCapability(Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_WRITE);
		// alpha
		TransparencyAttributes ta = new TransparencyAttributes();
		ta.setCapability(TransparencyAttributes.ALLOW_VALUE_READ);
		ta.setCapability(TransparencyAttributes.ALLOW_VALUE_WRITE);
		ta.setTransparencyMode (TransparencyAttributes.BLEND_ONE);
		ta.setTransparency (alpha);
		appearance.setTransparencyAttributes (ta);
		
		ColoringAttributes ca = new ColoringAttributes();
		ca.setColor(diffuseColor);
		
		appearance.setColoringAttributes(ca);
		appearance.setMaterial(new Material(ambientColor, emissiveColor, diffuseColor, specularColor, shininess));
		appearance.getMaterial().setCapability(Material.ALLOW_COMPONENT_READ);
		appearance.getMaterial().setCapability(Material.ALLOW_COMPONENT_WRITE);
		//appearance.setTexture(texture);

		return appearance;
	}
	
	///
	/// get methode fuer text2D
	///
	private Color3f getText2D()
	{
		return text2D;
	}
	
	///
	/// set methode fuer text2D
	///
	public void setText2D(int r, int g, int b)
	{
		text2D.x = Int2Float(r);
		text2D.y = Int2Float(g);
		text2D.z = Int2Float(b);
		
		appearance.getMaterial().setSpecularColor(new Color3f(text2D.x, text2D.y, text2D.z));
	}
}
