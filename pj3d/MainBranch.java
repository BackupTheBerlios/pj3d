import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;

///
/// in diesen Mainbranch werden aller erzeugten Objekte hinzugefügt.
///
public class MainBranch
{
	private Frame 		mFrame;
	private Color3f 		mBgColor;
	private Locale		locale;
	private Canvas3D 	canvas = new Canvas3D( null );
	public BranchGroup branch = new BranchGroup();
	public Background 	background;

	///
	/// Default Konstruktor
	///
	public MainBranch(){}

	///
	/// Konstruktor: benötigt einen erzeugten Frame
	///
	public MainBranch (Frame f)
	{
		this.mFrame = f;
	}
	
	///
	/// Konstruktor: benötigt einen erzeugten Frame und ein Color3f Objekt fuer die Hintergrundfarbe
	///
	public MainBranch (Frame f, Color3f backgroundColor)
	{
		this.mFrame = f;
		this.mBgColor = backgroundColor;
	}
	
	///
	/// Initialisierung des Hauptbranches: position und Groesse des branches werden mit default  Werten gesetzt.
	/// Evtl. set Methoden?
	///
	public BranchGroup InitBranch()
	{
		mFrame.add( "Center", canvas );
	    
	    VirtualUniverse univ = new VirtualUniverse();
	    locale = new Locale(univ);
	    
	    branch.setCapability( BranchGroup.ALLOW_CHILDREN_EXTEND );
	    branch.setCapability( BranchGroup.ALLOW_CHILDREN_WRITE );
	    branch.setCapability( BranchGroup.ALLOW_CHILDREN_READ );
	    branch.setCapability( BranchGroup.ALLOW_DETACH );
	
	    BoundingSphere worldBounds = new BoundingSphere(
	    new Point3d( 0.0, 0.0, 0.0 ),  // Center
	    1000.0 );                      // Extent
	
	    // Set the background color and its application bounds
	    background = new Background( );
	    background.setColor( mBgColor );
	    background.setCapability( Background.ALLOW_COLOR_WRITE );
	    background.setApplicationBounds( worldBounds );
	    branch.addChild( background );
		
		locale.addBranchGraph( branch );
	    
	    return branch;
	}
	
	///
	/// get Methode fuer Locale
	///
	public Locale getLocale()
	{
		return locale;
	}
	
	///
	/// get Methode fuer Canvas3D
	///
	public Canvas3D getCanvas3D()
	{
		return canvas;
	}
}
