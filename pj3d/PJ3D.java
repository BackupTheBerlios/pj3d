import java.applet.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.*;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;

///
/// Enthält alle Unterobjekte die zum späteren Gebrauch in Processing benötigt werden.
/// Die Unterobjekte: z.B. PJ3DBox oder  PJ3DSphere... werden bei der ersten 
/// Initialisierung mit Referenziert. Blah...
///
public class PJ3D extends Applet
{
	private int mWinHeight, mWinWidth; 
	private MainBranch mb;
	private BranchGroup branch;
	private float mColorBgWorldR, mColorBgWorldG, mColorBgWorldB;
	/// Hier kann eine Erklärung der eizelnen Werte rein, was sicherlich nicht schlecht ist fuer eine Processing user.
	public Color3f backgroundColor, ambientColor, diffuseColor, emissiveColor, specularColor;	
	/// Hier kann eine Erklärung der eizelnen Werte rein, was sicherlich nicht schlecht ist fuer eine Processing user. 
	public float shininess, alpha;
	/// Jaja, die Default Color
	public final float DEFAULTCOLOR = 0.5f;
	
	private Vector3f transformVec;
	
	///
	/// Standart Konstruktor.
	///
	public PJ3D(){}
	
	///
	/// Benötigt als Werte die gewünschte Fenstergrösse.
	///
	public PJ3D(int windowWidth, int windowHeight)
	{
		this.mWinHeight = windowHeight;
		this.mWinWidth = windowWidth;
		
		InitPJ3D(mWinWidth, mWinHeight);
	}
	
	///
	/// Initialisierung der äußeren BranchGroup.
	///
	public BranchGroup InitPJ3D(int mWinWidth, int mWinHeight)
	{		
		setLayout( new BorderLayout( ) );
		Frame f = new Frame("PJ3D");
	    f.pack();
	    f.show();
	    f.setSize(mWinWidth, mWinHeight);
	    
	    // default colors
	    backgroundColor = new Color3f(1.0f, 1.0f, 1.0f);
	    ambientColor = new Color3f(0.2f, 0.2f, 0.2f);
	    diffuseColor = new Color3f(1.0f, 1.0f, 1.0f);
	    emissiveColor = new Color3f(0.0f, 0.0f, 0.0f);
	    specularColor = new Color3f(1.0f, 1.0f, 1.0f);
	    shininess = DEFAULTCOLOR;
	    alpha = 0.0f;
	    
	    mb = new MainBranch(f, backgroundColor);
	    branch = mb.InitBranch();
	    
	    // default transform vector
	    transformVec = new Vector3f(0, 0, 0);
	    
	    f.show( );
	    return branch;
	}
	
	///
	/// Erstellung eines Quadrates. Benötigt als Werte die Länge, Höhe und Breite des 
	/// gewünschten Quadrates.
	///
	public PJ3DBox PJ3DBox( float xdim, float ydim, float zdim )
	{
		PJ3DBox s = new PJ3DBox (branch, xdim, ydim, zdim, transformVec, ambientColor, diffuseColor, emissiveColor, specularColor, shininess, alpha);
		return s;
	}
	
	///
	/// Erstellt einen Würfel.
	///
	public PJ3DBox PJ3DBox( float xdim )
	{
		PJ3DBox s = new PJ3DBox (branch, xdim, xdim, xdim, transformVec, ambientColor, diffuseColor, emissiveColor, specularColor, shininess, alpha);
		return s;
	}
	
	///
	/// Erstellt eine Sphere.
	///
	public PJ3DSphere PJ3DSphere( float xdim )
	{
		PJ3DSphere s = new PJ3DSphere (branch, xdim, transformVec, ambientColor, diffuseColor, emissiveColor, specularColor, shininess, alpha);
		return s;
	}
	
	///
	/// Erstellt ein .x Objekt?? Welche Formate kann man da angeben?
	///
	public PJ3DObj PJ3DObj( String fileLocation )
	{
		PJ3DObj s = new PJ3DObj (branch, fileLocation, transformVec);
		return s;
	}
	
	///
	/// Bestimmung der Hintergrundfarbe
	///
	public void setBackgroundColor(float r, float g, float b)
	{
		backgroundColor.x = r;
		backgroundColor.y = g;
		backgroundColor.z = b;
		mb.background.setColor(r, g, b);
	}
	
	///
	/// Bestimmung der Emissivefarbe
	///		
	public void setAmbientColor(float r, float g, float b)
	{
		ambientColor.x = r;
		ambientColor.y = g;
		ambientColor.z = b;
	}
	
	///
	/// Bestimmung der Diffusefarbe
	///		
	public void setDiffuseColor(float r, float g, float b)
	{
		diffuseColor.x = r;
		diffuseColor.y = g;
		diffuseColor.z = b;
	}

	///
	/// Bestimmung der Emissivefarbe
	///	
	public void setEmissiveColor(float r, float g, float b)
	{
		emissiveColor.x = r;
		emissiveColor.y = g;
		emissiveColor.z = b;
	}
	
	///
	/// Bestimmung der Emissivefarbe
	///		
	public void setSpecularColor(float r, float g, float b)
	{
		specularColor.x = r;
		specularColor.y = g;
		specularColor.z = b;
	}
	
	///
	/// Bestimmung der Shininess?
	///		
	public void setShininess(float s)
	{
		shininess = s;
	}

	///
	/// Bestimmung der Alphakanal stärke.
	///	
	public void setAlpha(float a)
	{
		alpha = a;
	}
	
	///
	/// Bestimmung der Emissivefarbe
	///	
	public void setTransform(float x, float y, float z)
	{
		transformVec.x = x;
		transformVec.y = y;
		transformVec.z = z;
	}
}
