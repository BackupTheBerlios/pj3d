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
	private Pj3dToolbox ptools = new Pj3dToolbox();
	
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
	/// get methode fuer Ambient als processing color (int)
	///
	private int getAmbient()
	{
		return ptools.color3f2Color(ambient);
	}
	
	///
	/// get methode fuer Ambient red als processing color (int)
	///
	private int getAmbientR()
	{
		return Float2Int(ambient.x);
	}

	///
	/// get methode fuer Ambient green als processing color (int)
	///
	private int getAmbientG()
	{
		return Float2Int(ambient.y);
	}
	
	///
	/// get methode fuer Ambient blue als processing color (int)
	///
	private int getAmbientB()
	{
		return Float2Int(ambient.z);
	}
	
	///
	/// set methode fuer Ambient als int r, g, b
	///
	public void setAmbient(int r, int g, int b)
	{
		ambient.x = Int2Float(r);
		ambient.y = Int2Float(g);
		ambient.z = Int2Float(b);
		
		appearance.getMaterial().setAmbientColor(new Color3f(ambient.x, ambient.y, ambient.z));
	}
	
	///
	/// set methode fuer Ambient als processing color (int)
	///
	public void setAmbient(int color)
	{
		Color3f c3f = ptools.color2Color3f(color);
		
		appearance.getMaterial().setAmbientColor(c3f);
	}
	
	///
	/// get methode fuer Diffuse als processing color (int)
	///
	private int getDiffuse()
	{
		return ptools.color3f2Color(diffuse);
	}
	
	///
	/// get methode fuer Diffuse red als processing color (int)
	///
	private int getDiffuseR()
	{
		return Float2Int(diffuse.x);
	}

	///
	/// get methode fuer Diffuse green als processing color (int)
	///
	private int getDiffuseG()
	{
		return Float2Int(diffuse.y);
	}
	
	///
	/// get methode fuer Diffuse blue als processing color (int)
	///
	private int getDiffuseB()
	{
		return Float2Int(diffuse.z);
	}
	
	///
	/// set methode fuer Diffuse als int r, g, b
	///
	public void setDiffuse(int r, int g, int b)
	{
		diffuse.x = Int2Float(r);
		diffuse.y = Int2Float(g);
		diffuse.z = Int2Float(b);
		
		appearance.getMaterial().setDiffuseColor(new Color3f(diffuse.x, diffuse.y, diffuse.z));
	}
	
	///
	/// set methode fuer Diffuse als processing color (int)
	///
	public void setDiffuse(int color)
	{
		Color3f c3f = ptools.color2Color3f(color);
		
		appearance.getMaterial().setDiffuseColor(c3f);
	}
	
	///
	/// get methode fuer emissive als processing color (int)
	///
	private int getEmissive()
	{
		return ptools.color3f2Color(emissive);
	}
	
	///
	/// get methode fuer emissive red als processing color (int)
	///
	private int getEmissiveR()
	{
		return Float2Int(emissive.x);
	}

	///
	/// get methode fuer emissive green als processing color (int)
	///
	private int getEmissiveG()
	{
		return Float2Int(emissive.y);
	}
	
	///
	/// get methode fuer emissive blue als processing color (int)
	///
	private int getEmissiveB()
	{
		return Float2Int(emissive.z);
	}
	
	///
	/// set methode fuer emissive als int r, g, b
	///
	public void setEmissive(int r, int g, int b)
	{
		emissive.x = Int2Float(r);
		emissive.y = Int2Float(g);
		emissive.z = Int2Float(b);
		
		appearance.getMaterial().setEmissiveColor(new Color3f(emissive.x, emissive.y, emissive.z));
	}
	
	///
	/// set methode fuer ambient als processing color (int)
	///
	public void setEmissive(int color)
	{
		Color3f c3f = ptools.color2Color3f(color);
		
		appearance.getMaterial().setEmissiveColor(c3f);
	}
	
	///
	/// get methode fuer specular als processing color (int)
	///
	private int getSpecular()
	{
		return ptools.color3f2Color(specular);
	}
	
	///
	/// get methode fuer specular red als processing color (int)
	///
	private int getSpecularR()
	{
		return Float2Int(specular.x);
	}

	///
	/// get methode fuer specular green als processing color (int)
	///
	private int getSpecularG()
	{
		return Float2Int(specular.y);
	}
	
	///
	/// get methode fuer specular blue als processing color (int)
	///
	private int getSpecularB()
	{
		return Float2Int(specular.z);
	}
	
	///
	/// set methode fuer emissive als int r, g, b
	///
	public void setSpecular(int r, int g, int b)
	{
		specular.x = Int2Float(r);
		specular.y = Int2Float(g);
		specular.z = Int2Float(b);
		
		appearance.getMaterial().setSpecularColor(new Color3f(specular.x, specular.y, specular.z));
	}
	
	///
	/// set methode fuer specular als processing color (int)
	///
	public void setSpecular(int color)
	{
		Color3f c3f = ptools.color2Color3f(color);
		
		appearance.getMaterial().setSpecularColor(c3f);
	}
	
	///
	/// get methode fuer text3D
	///
	private Color3f getText3D()
	{
		return text3D;
	}
	
	///
	/// set methode fuer text3D als int r, g, b
	///
	public void setText3D(int r, int g, int b)
	{
		text3D.x = Int2Float(r);
		text3D.y = Int2Float(g);
		text3D.z = Int2Float(b);
		
		appearance.getMaterial().setSpecularColor(new Color3f(text3D.x, text3D.y, text3D.z));
	}
	
	///
	/// set methode fuer text3D als processing color
	///
	public void setText3D(int color)
	{
		Color3f c3f = ptools.color2Color3f(color);
		appearance.getMaterial().setSpecularColor(c3f);
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
