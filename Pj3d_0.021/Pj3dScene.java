import java.applet.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;

///
/// all created objects goes in this mainbranch
///
public class Pj3dScene
{
	private Frame 		mFrame;
	private Color3f 		mBgColor;
	private Locale		locale;
	private Applet parent;
	private Canvas3D canvas;
	public BranchGroup branch = new BranchGroup();	///< the masterbranch
	public Background 	background;									///< the backroound object

	///
	/// default constructor
	///
	public Pj3dScene(){}

	///
	/// constructor: needs a frame
	///
	public Pj3dScene (Frame f)
	{
		this.mFrame = f;
	}
	
	///
	/// constructor: need a created frame and a color3f object for the bg color
	///
	public Pj3dScene (Frame f, GraphicsConfiguration gc, Background bg)
	{
		this.mFrame = f;
		//this.parent = parent;
		this.background = bg;
	 	this.canvas = new Canvas3D( gc );
	}
	
	///
	/// initialisation of the mainbranch: position and size are default values, yet. maybe need a bunch of geter and seter methods
	///
	public BranchGroup InitBranch()
	{
		//parent.add( "Center", canvas );
	    
	    VirtualUniverse univ = new VirtualUniverse();
	    
	    locale = new Locale(univ);
	    
	    branch.setCapability( BranchGroup.ALLOW_CHILDREN_EXTEND );
	    branch.setCapability( BranchGroup.ALLOW_CHILDREN_WRITE );
	    branch.setCapability( BranchGroup.ALLOW_CHILDREN_READ );
	    branch.setCapability( BranchGroup.ALLOW_DETACH );
	
	    branch.addChild( background );
		
		locale.addBranchGraph( branch );
	    
	    return branch;
	}
	
	///
	/// get Methode for Locale
	///
	public Locale getLocale()
	{
		return locale;
	}
	
	///
	/// get Methode for Canvas3D
	///
	public Canvas3D getMBCanvas3D()
	{
		return canvas;
	}
}
