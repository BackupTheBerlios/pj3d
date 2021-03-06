import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

///
/// in diesen Mainbranch werden aller erzeugten Objekte hinzugefügt.
///
public class PJ3DMainBranch
{
	private Frame 		mFrame;
	private Color3f 		mBgColor;
	private Locale		locale;
	private Canvas3D canvas;
	public BranchGroup branch = new BranchGroup();
	public Background 	background;

	///
	/// Default Konstruktor
	///
	public PJ3DMainBranch(){}

	///
	/// Konstruktor: benötigt einen erzeugten Frame
	///
	public PJ3DMainBranch (Frame f)
	{
		this.mFrame = f;
	}
	
	///
	/// Konstruktor: benötigt einen erzeugten Frame und ein Color3f Objekt fuer die Hintergrundfarbe
	///
	public PJ3DMainBranch (Frame f, GraphicsConfiguration gc, Background bg)
	{
		this.mFrame = f;
		this.background = bg;
	 	this.canvas = new Canvas3D( gc );
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
	public Canvas3D getMBCanvas3D()
	{
		return canvas;
	}
	
	public Locale getMBLocale()
	{
		return locale;
	}
	
	
}
