import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;

///
/// PmColor3D: die get methoden sind vorerst private
///
public class Pj3dColor3D extends Pj3dToolbox
{
	private Color3f background = new Color3f();
	private Color3f ambient = new Color3f();
	private Color3f diffuse = new Color3f();
	private Color3f emissive = new Color3f();
	private Color3f specular = new Color3f();
	private Color3f text3D = new Color3f();
	private float shininess = 0.0f;
	private float alpha = 0.0f;
	
	public Appearance appearance;
	
	///
	/// default konstruktor
	///
	public Pj3dColor3D() {}
	
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
	/// get methode fuer background
	///
	private Color3f getBackground()
	{
		return background;
	}
	
	///
	/// set methode fuer background
	///
	public void setBackground(int r, int g, int b)
	{
		background.x = Int2Float(r);
		background.y = Int2Float(g);
		background.z = Int2Float(b);
	}
	
	///
	/// get methode fuer ambient
	///
	private Color3f getAmbient()
	{
		return ambient;
	}
	
	///
	/// set methode fuer ambient
	///
	public void setAmbient(int r, int g, int b)
	{
		ambient.x = Int2Float(r);
		ambient.y = Int2Float(g);
		ambient.z = Int2Float(b);
		
		appearance.getMaterial().setAmbientColor(new Color3f(ambient.x, ambient.y, ambient.z));
	}
	
	///
	/// get methode fuer diffuse
	///
	private Color3f getDiffuse()
	{
		return diffuse;
	}
	
	///
	/// set methode fuer diffuse
	///
	public void setDiffuse(int r, int g, int b)
	{
		diffuse.x = Int2Float(r);
		diffuse.y = Int2Float(g);
		diffuse.z = Int2Float(b);
		
		appearance.getMaterial().setDiffuseColor(new Color3f(diffuse.x, diffuse.y, diffuse.z));
	}
	
	///
	/// get methode fuer emissive
	///
	private Color3f getEmissiveColor()
	{
		return emissive;
	}
	
	///
	/// set methode fuer emissive
	///
	public void setEmissive(int r, int g, int b)
	{
		diffuse.x = Int2Float(r);
		diffuse.y = Int2Float(g);
		diffuse.z = Int2Float(b);
		
		appearance.getMaterial().setEmissiveColor(new Color3f(diffuse.x, diffuse.y, diffuse.z));
	}
	
	///
	/// get methode fuer specular
	///
	private Color3f getSpecular()
	{
		return specular;
	}
	
	///
	/// set methode fuer specular
	///
	public void setSpecular(int r, int g, int b)
	{
		specular.x = Int2Float(r);
		specular.y = Int2Float(g);
		specular.z = Int2Float(b);
		
		appearance.getMaterial().setSpecularColor(new Color3f(specular.x, specular.y, specular.z));
	}
	
	///
	/// get methode fuer text3D
	///
	private Color3f getText3D()
	{
		return text3D;
	}
	
	///
	/// set methode fuer text3D
	///
	public void setText3D(int r, int g, int b)
	{
		text3D.x = Int2Float(r);
		text3D.y = Int2Float(g);
		text3D.z = Int2Float(b);
		
		appearance.getMaterial().setSpecularColor(new Color3f(text3D.x, text3D.y, text3D.z));
	}
	
	///
	/// get methode fuer shininess
	///
	private float getShininess()
	{
		return shininess;
	}
	
	///
	/// set methode fuer shininess
	///
	public void setShininess(int s)
	{
		shininess = ColorInt2Float(s);
		appearance.getMaterial().setShininess(shininess);
	}
	
	///
	/// get methode fuer alpha
	///
	private float getAlpha()
	{
		return alpha;
	}
	
	///
	/// set methode fuer alpha
	///
	public void setAlpha(int s)
	{
		alpha = ColorInt2Float(s);
		appearance.getTransparencyAttributes().setTransparency(alpha);
	}
}
