import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;

public class PJ3DColor
{
	public Appearance appearance;
	private PJ3DToolbox ptools;
	
	public Appearance CreateAppearance(Color3f ambientColor, Color3f diffuseColor, Color3f emissiveColor, Color3f specularColor, float shininess, float alpha)
	{
		ptools = new PJ3DToolbox();
		
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
	
	public void ambient(int r, int g, int b)
	{
		appearance.getMaterial().setAmbientColor(new Color3f(ptools.colorInt2Float(r), ptools.colorInt2Float(g), ptools.colorInt2Float(b)));
	}
	
	public void diffuse(int r, int g, int b)
	{
		appearance.getMaterial().setDiffuseColor(new Color3f(ptools.colorInt2Float(r), ptools.colorInt2Float(g), ptools.colorInt2Float(b)));
	}
	
	public void specular(int r, int g, int b)
	{
		appearance.getMaterial().setSpecularColor(new Color3f(ptools.colorInt2Float(r), ptools.colorInt2Float(g), ptools.colorInt2Float(b)));
	}
	
	public void emissive(int r, int g, int b)
	{
		appearance.getMaterial().setEmissiveColor(new Color3f(ptools.colorInt2Float(r), ptools.colorInt2Float(g), ptools.colorInt2Float(b)));
	}
	
	public void shininess(int s)
	{
		appearance.getMaterial().setShininess(ptools.colorInt2Float(s));
	}
	
	public void alpha(int alpha)
	{
		appearance.getTransparencyAttributes().setTransparency(ptools.colorInt2Float(alpha));
	}
	
	// einfacher weg um die farbe zu setzen
	// eigentlich duerfte specular nur das highlight definieren. da liegt noch ein fehler
	public void fill(int r, int g, int b)
	{
		appearance.getMaterial().setSpecularColor(new Color3f(ptools.colorInt2Float(r), ptools.colorInt2Float(g), ptools.colorInt2Float(b)));
	}
}
