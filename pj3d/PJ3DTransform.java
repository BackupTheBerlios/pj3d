import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.*;

public class PJ3DTransform {

	public TransformGroup tg;
	public Transform3D t3d;
	public Vector3f tVec3f;
	public Appearance appearance;
	
	public PJ3DTransform()
	{
		/*
		this.t3d = t3d;
		this.tg = tg;
		this.tVec3f = tVec3f;
	*/
	}
	
	public void MoveObj(float x, float y, float z)
   	{	
		tVec3f.x += x;
		tVec3f.y += y;
		tVec3f.z += z;
		
		// in Transform3D schreiben
		t3d.setTranslation(tVec3f);
		tg.setTransform(t3d);
   	}
	
	public void SetObj(float x, float y, float z)
   	{	
		tVec3f.x += x;
		tVec3f.y += y;
		tVec3f.z += z;
		
		// in Transform3D schreiben
		t3d.setTranslation(tVec3f);
		tg.setTransform(t3d);
   	}
	
	public void RotateObj(double x, double y, double z)
	{
		Transform3D newTransX = new Transform3D();
		Transform3D newTransY = new Transform3D();
		Transform3D newTransZ = new Transform3D();

		newTransX.rotX(x);
		newTransY.rotY(y);
		newTransZ.rotZ(z);

		t3d.mul(newTransX);
		t3d.mul(newTransY);
		t3d.mul(newTransZ);

		tg.setTransform(t3d);
	}
	
	public Appearance CreateAppearance(Color3f ambientColor, Color3f diffuseColor, Color3f emissiveColor, Color3f specularColor, float shininess, float alpha) {
		//this is an easy way to convert a jpg file to the texture object we need.
		//TextureLoader loader = new TextureLoader("/home/rfroese/java/reedtalk/applets/bixle_door.jpg", new String("RGB"), comp);
		//Texture texture = loader.getTexture();
		Appearance appearance = new Appearance();
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
		ta.setTransparencyMode (ta.BLEND_ONE);
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
	
	public void setAmbientColor(float r, float g, float b)
	{
		appearance.getMaterial().setAmbientColor(new Color3f(r, g, b));
	}
	
	public void setDiffuseColor(float r, float g, float b)
	{
		appearance.getMaterial().setDiffuseColor(new Color3f(r, g, b));
	}
	
	public void setSpecularColor(float r, float g, float b)
	{
		appearance.getMaterial().setSpecularColor(new Color3f(r, g, b));
	}
	
	public void setEmissiveColor(float r, float g, float b)
	{
		appearance.getMaterial().setEmissiveColor(new Color3f(r, g, b));
	}
	
	public void setShininess(float s)
	{
		appearance.getMaterial().setShininess(s);
	}
	
	public void setAlpha(float alpha)
	{
		appearance.getTransparencyAttributes().setTransparency(alpha);
	}
}
