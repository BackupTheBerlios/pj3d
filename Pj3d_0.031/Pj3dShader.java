import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Transform3D;
import com.sun.j3d.utils.image.*;
import javax.vecmath.Color3f;
import java.applet.*;
import java.awt.Toolkit;

///
/// PmColor3D: the get methods are private for the time being
///
public class Pj3dShader extends Pj3dToolbox
{
	private Color3f background = new Color3f();
	private Color3f ambient = new Color3f();
	private Color3f diffuse = new Color3f();
	private Color3f emissive = new Color3f();
	private Color3f specular = new Color3f();
	private Color3f color = new Color3f();
	private float shininess = 0.0f;
	private float alpha = 0.0f;
	private Pj3dToolbox ptools = new Pj3dToolbox();
	private Applet parent;
	private ColoringAttributes ca;
	
	public Appearance appearance;
	
	///
	/// default constructor
	///
	public Pj3dShader(Applet parentApplet)
	{
		this.parent = parentApplet;
	}
	
	///
	/// crates a appearance
	///
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
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		appearance.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_READ);
		appearance.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
		appearance.setCapability(Appearance.ALLOW_TEXTURE_READ);
		appearance.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		// alpha
		TransparencyAttributes ta = new TransparencyAttributes();
		ta.setCapability(TransparencyAttributes.ALLOW_VALUE_READ);
		ta.setCapability(TransparencyAttributes.ALLOW_VALUE_WRITE);
		ta.setTransparencyMode (TransparencyAttributes.BLEND_ONE);
		ta.setTransparency (alpha);
		appearance.setTransparencyAttributes (ta);
		
		TextureAttributes tex = new TextureAttributes();
		tex.setCapability(TextureAttributes.ALLOW_MODE_READ);
		tex.setCapability(TextureAttributes.ALLOW_MODE_WRITE);
		appearance.setTextureAttributes(tex);
		
		ca = new ColoringAttributes();
		ca.setCapability(ColoringAttributes.ALLOW_COLOR_READ);
		ca.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		ca.setColor(diffuseColor);
		appearance.setColoringAttributes(ca);
		
		appearance.setMaterial(new Material(ambientColor, emissiveColor, diffuseColor, specularColor, shininess));
		appearance.getMaterial().setCapability(Material.ALLOW_COMPONENT_READ);
		appearance.getMaterial().setCapability(Material.ALLOW_COMPONENT_WRITE);
		
		//appearance.setTexture(texture);
		//appearance.setTextureAttributes(Texture.ALLOW_IMAGE_WRITE);

		return appearance;
	}
	
	///
	/// get methode for background
	///
	private Color3f getBackground()
	{
		return background;
	}
	
	///
	/// set methode for background need integer
	///
	public void setBackground(int r, int g, int b)
	{
		background.x = Int2Float(r);
		background.y = Int2Float(g);
		background.z = Int2Float(b);
	}
	
	///
	/// set methode for background need float
	///
	public void setBackground(float r, float g, float b)
	{
		background.x = r;
		background.y = g;
		background.z = b;
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
	/// set methode for Ambient as int r, g, b
	///
	public void setAmbient(int r, int g, int b)
	{
		ambient.x = Int2Float(r);
		ambient.y = Int2Float(g);
		ambient.z = Int2Float(b);
		
		appearance.getMaterial().setAmbientColor(new Color3f(ambient.x, ambient.y, ambient.z));
	}
	
	///
	/// set methode for Ambient as int r, g, b
	///
	public void setAmbient(float r, float g, float b)
	{
		ambient.x = r;
		ambient.y = g;
		ambient.z = b;
		
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
	/// set methode for Diffuse as int r, g, b
	///
	public void setDiffuse(int r, int g, int b)
	{
		diffuse.x = Int2Float(r);
		diffuse.y = Int2Float(g);
		diffuse.z = Int2Float(b);
		
		appearance.getMaterial().setDiffuseColor(new Color3f(diffuse.x, diffuse.y, diffuse.z));
	}
	
	///
	/// set methode for Diffuse as float r, g, b
	///
	public void setDiffuse(float r, float g, float b)
	{
		diffuse.x = r;
		diffuse.y = g;
		diffuse.z = b;
		
		appearance.getMaterial().setDiffuseColor(new Color3f(diffuse.x, diffuse.y, diffuse.z));
	}
	
	///
	/// set methode for Diffuse as processing color (int)
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
	/// set methode for emissive as int r, g, b
	///
	public void setEmissive(int r, int g, int b)
	{
		emissive.x = Int2Float(r);
		emissive.y = Int2Float(g);
		emissive.z = Int2Float(b);
		
		appearance.getMaterial().setEmissiveColor(new Color3f(emissive.x, emissive.y, emissive.z));
	}
	
	///
	/// set methode for emissive as float r, g, b
	///
	public void setEmissive(float r, float g, float b)
	{
		emissive.x = r;
		emissive.y = g;
		emissive.z = b;
		
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
	/// set methode for emissive as int r, g, b
	///
	public void setSpecular(int r, int g, int b)
	{
		specular.x = Int2Float(r);
		specular.y = Int2Float(g);
		specular.z = Int2Float(b);
		
		appearance.getMaterial().setSpecularColor(new Color3f(specular.x, specular.y, specular.z));
	}
	
	///
	/// set methode for emissive as float r, g, b
	///
	public void setSpecular(float r, float g, float b)
	{
		specular.x = r;
		specular.y = g;
		specular.z = b;
		
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
	/// get methode fuer Color
	///
	private Color3f getColor()
	{
		return color;
	}
	
	///
	/// set methode for Color as int r, g, b
	///
	public void setColor(int r, int g, int b)
	{
		color.x = r/256f;
		color.y = g;
		color.z = b;
		ca.setColor(r/256f, g/256f, b/256f);
		appearance.setColoringAttributes(ca);
	}

	///
	/// set methode for Color as float r, g, b
	///
	public void setColor(float r, float g, float b)
	{
		color.x = r;
		color.y = g;
		color.z = b;
		ca.setColor(r/256f, g/256f, b/256f);
		appearance.setColoringAttributes(ca);
	}
	
	///
	/// set methode fuer Color als processing color
	///
	public void setColor(int color)
	{
		Color3f c3f = ptools.color2Color3f(color);
		ca.setColor(c3f);
		appearance.setColoringAttributes(ca);
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
		//System.out.println(s);
		alpha = ColorInt2Float(s);
		System.out.println(alpha);
		appearance.getTransparencyAttributes().setTransparency(alpha);
	}
	
	///
	/// set methode fuer texture
	///
	
	// texture gibt es noch nicht, weil man parent ueberall uebergeben muesste. muss noch implementiert werden.
	public void setTexture(String fileLocation)
	{
		
		TextureLoader loader = new TextureLoader(fileLocation, new String("RGB"), parent);
		Texture texture = loader.getTexture();
		texture.setCapability(Texture.ALLOW_ENABLE_READ);
		texture.setCapability(Texture.ALLOW_ENABLE_WRITE);
		texture.setCapability(Texture.ALLOW_SIZE_READ);
		texture.setEnable(true);
		texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);

		appearance.getTextureAttributes().setTextureMode(TextureAttributes.REPLACE);
		
		// hier kann spaeter die texture mode eingestellt werden.
		/*
		String textureMode = null;
		
		if (textureMode.equals("replace"))
			appearance.getTextureAttributes().setTextureMode(TextureAttributes.REPLACE);
		else if (textureMode.equals("blend"))
			appearance.getTextureAttributes().setTextureMode(TextureAttributes.BLEND);
		else if (textureMode.equals("combine"))
			appearance.getTextureAttributes().setTextureMode(TextureAttributes.COMBINE);
		else if (textureMode.equals("add"))
			appearance.getTextureAttributes().setTextureMode(TextureAttributes.COMBINE_ADD);
		else if (textureMode.equals("kill"))
			appearance.setMaterial(null);
		*/
		
		// im moment muss das material geloescht werden, damit die textur richtig angezeigt werden kann
		appearance.setMaterial(null);
		appearance.setTexture(texture);
		/*
		TextureAttributes myTA = new TextureAttributes( );

		Transform3D myTrans = new Transform3D( );
		myTrans.rotZ( Math.PI/2.0 ); // 45 degrees
		myTrans.set(4);
		myTA.setTextureTransform( myTrans );
		myTA.setTextureMode(TextureAttributes.REPLACE);
		appearance.setTextureAttributes( myTA );

		System.out.println(texture.getHeight());
		System.out.println(texture.getEnable());
		*/
		/*
		TextureLoader loader = new TextureLoader( fileLocation, new String("RGB"), parent );
		ImageComponent2D image = loader.getImage( );
		Texture2D tex = new Texture2D( );
		tex.setCapability(Texture2D.ALLOW_IMAGE_WRITE);
		tex.setImage(0, image );
		tex.setEnable( true );
		tex.setBoundaryModeS( Texture.WRAP );
		tex.setBoundaryModeT( Texture.WRAP );
		appearance.setTexture( tex );
		*/
	}
	
}
