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

public class PJ3DLight
{
	private Color3f bcolor;
	private Color3f lColor1;
	private Color3f alColor;
	private Vector3f ldir1;
	private AmbientLight al;
	private DirectionalLight light1;
	private PointLight light;
	private ExponentialFog fog;
	private Background background;
	private BoundingSphere bounds;
	
	private MainBranch		mMb;
	
	///
	/// Default Konstruktor
	///
	public PJ3DLight(MainBranch mb)
	{
		this.bcolor =     	new Color3f((50f/255f),(50f/255f),(154f/255f));
		this.lColor1 =  		new Color3f(0.75F, 0.75F, 0.75F);
		this.alColor =   	new Color3f(0.6F, 0.6F, 0.6F);
		this.ldir1 =     	new Vector3f( 0.0F, -1.0F, -1F);
		this.bounds =    	new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 20000D);
		this.background =	new Background(bcolor);
		this.al =        	new AmbientLight(alColor);
		this.light = 		new PointLight( );
		this.fog = 			new ExponentialFog(0.5f,0.5f,0.5f);
		this.light.setEnable( true );
		this.light.setColor( new Color3f( 1.0f, 1.0f, 1.0f ) );
		this.light.setPosition( new Point3f( 0.0f, 1.0f, 0.0f ) );
		this.light.setAttenuation( new Point3f( 1.0f, 0.0f, 0.0f ) );
		this.light1 =     			new DirectionalLight(lColor1, ldir1);
		this.background. setApplicationBounds(bounds);
		this.al.        	setInfluencingBounds(bounds);
		this.light1.    	setInfluencingBounds(bounds);
		this.light.     	setInfluencingBounds(bounds);
		this.fog.		setDensity(0.005f);
		this.fog. 		setCapability( Fog.ALLOW_COLOR_WRITE );
		this.fog. 		setCapability( ExponentialFog.ALLOW_DENSITY_WRITE );
		this.fog. 		setInfluencingBounds(bounds);
		
		this.mMb = mb;
		
		
		InitLight();
	}
	
	///
	/// Initialisierung des Lichtes
	///
	private void InitLight()
	{
		BranchGroup lightBranch = new BranchGroup();
		lightBranch.addChild(light1);
		mMb.getLocale().addBranchGraph(lightBranch);
	}
}
