import javax.media.j3d.*;
import javax.vecmath.*;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.*;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;

public class MainBranch
{
	private Frame mFrame;
	private Color3f bgColor;
	public Background background;
	//private float mColorBgWorldR, mColorBgWorldG, mColorBgWorldB;
	
	public MainBranch(){}
	
	public MainBranch (Frame f)
	{
		this.mFrame = f;
	}
	
	public MainBranch (Frame f, Color3f backgroundColor)
	{
		this.mFrame = f;
		this.bgColor = backgroundColor;
	}
	
	public BranchGroup InitBranch()
	{
		Canvas3D canvas = new Canvas3D( null );
		mFrame.add( "Center", canvas );
	    
	    VirtualUniverse univ = new VirtualUniverse();
	    Locale locale = new Locale(univ);
	    //SimpleUniverse u = new SimpleUniverse(canvas);
	    //u.getViewingPlatform().setNominalViewingTransform();
	    
	    PJ3DCamera p = new PJ3DCamera(canvas, locale);
	    BranchGroup cam = p.initCamera(0,0,0);
	    locale.addBranchGraph(cam);
	    
	    BranchGroup branch = new BranchGroup();
	    branch.setCapability( BranchGroup.ALLOW_CHILDREN_EXTEND );
	    branch.setCapability( BranchGroup.ALLOW_CHILDREN_WRITE );
	    branch.setCapability( BranchGroup.ALLOW_CHILDREN_READ );
	    branch.setCapability( BranchGroup.ALLOW_DETACH );
	
	    BoundingSphere worldBounds = new BoundingSphere(
	    new Point3d( 0.0, 0.0, 0.0 ),  // Center
	    1000.0 );                      // Extent
	
	    // Set the background color and its application bounds
	    background = new Background( );
	    background.setColor( bgColor );
	    background.setCapability( Background.ALLOW_COLOR_WRITE );
	    background.setApplicationBounds( worldBounds );
	    branch.addChild( background );
	    
//	  licht
		Color3f light1Color = new Color3f(0.3f, 0.3f, 0.3f);
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 200.0);
		Vector3f light1Direction = new Vector3f(0.0f, -7.0f, -12.0f);
		DirectionalLight light2	= new DirectionalLight(light1Color, light1Direction);
		AmbientLight light1 = new AmbientLight(light1Color);
		light1.setInfluencingBounds(bounds);
		light2.setInfluencingBounds(bounds);
		branch.addChild(light2);
	    
	    //Box b2 = new Box(0.5f, 0.5f, 0.5f, new Appearance());
		//branch.addChild( b2 );
	
		//branch.addChild(new ColorCube(0.4));
		//branch.compile();
		
	    locale.addBranchGraph( branch );
	    return branch;
	}
}
