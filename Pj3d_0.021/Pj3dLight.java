import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.ExponentialFog;
import javax.media.j3d.Fog;
import javax.media.j3d.PointLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

public class Pj3dLight
{
	private Color3f lightColor;
	private AmbientLight 		ambientLight;
	private DirectionalLight 	directionalLight;
	private BoundingSphere bounds;
	private Pj3d 					parent;
	private Color3f defaultColor = new Color3f(0.3f, 0.3f, 0.3f);
	private Pj3dToolbox ptools = new Pj3dToolbox();
	
	///
	/// Default Konstruktor
	///
	public Pj3dLight(Pj3d parent)
	{
		this.parent = parent;
		InitLightDefault(defaultColor);
	}
	///
	/// Konstruktor fuer RGB
	///
	public Pj3dLight(Pj3d parent, int r, int g, int b)
	{
		this.parent = parent;
		Color3f thisColor = ptools.RGB2Color3f(r, g, b);
		InitLightDefault(thisColor);
	}
	///
	/// Konstruktor fuer Processing color int
	///
	public Pj3dLight(Pj3d parent, int color)
	{
		this.parent = parent;
		Color3f thisColor = ptools.color2Color3f(color);
		InitLightDefault(thisColor);
	}
	
	///
	/// Initialisierung des Lichtes
	///
	private void InitLightDefault(Color3f lightcolor)
	{	
		// licht
		lightColor = lightcolor;
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 1000.0);
		Vector3f light1Direction = new Vector3f(0.0f, -7.0f, -12.0f);
		directionalLight	= new DirectionalLight(lightColor, light1Direction);
		directionalLight.setInfluencingBounds(bounds);
		directionalLight.setCapability(DirectionalLight.ALLOW_COLOR_WRITE);
		directionalLight.setCapability(DirectionalLight.ALLOW_COLOR_READ);
		ambientLight = new AmbientLight(lightColor);
		ambientLight.setInfluencingBounds(bounds);
		ambientLight.setCapability(AmbientLight.ALLOW_COLOR_WRITE);
		ambientLight.setCapability(AmbientLight.ALLOW_COLOR_READ);
	
		BranchGroup lightBranch = new BranchGroup();
		lightBranch.addChild(directionalLight);
		lightBranch.addChild(ambientLight);
		parent.getMBLocale().addBranchGraph(lightBranch);
	}
	
	public void setColor(int r, int g, int b)
	{
		lightColor = ptools.RGB2Color3f(r, g, b);
		ambientLight.setColor(lightColor);
		directionalLight.setColor(lightColor);
	}
	
	public void setColor(int color)
	{
		lightColor = ptools.color2Color3f(color);
		ambientLight.setColor(lightColor);
		directionalLight.setColor(lightColor);
	}
	
	///
	/// set the directional vector for the light
	///
	public void setDirection(float x, float y, float z)
	{
		directionalLight.setDirection(new Vector3f(x, y, z));
	}
	
	/*
	public float getDirectionX()
	{
		float x = directionalLight.g;
	}
	*/
}
