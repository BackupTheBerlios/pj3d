import java.applet.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import java.awt.*;
import java.awt.event.*;

///
/// Enthält alle Unterobjekte die zum späteren Gebrauch in Processing benötigt werden.
/// Die Unterobjekte: z.B. PJ3DBox oder  PJ3DSphere... werden bei der ersten 
/// Initialisierung mit Referenziert. 
///
public class PJ3D extends Applet implements KeyListener, MouseListener, MouseMotionListener
{
	private int mWinHeight, mWinWidth;
	public int width, height;
	public int mouseX, mouseY, key;							// public mouse and key values for processing
	public boolean mousePressed, keyPressed, mouseReleased, keyReleased;	// boolean mouse and keyboard states
	private MainBranch 	mb;
	private BranchGroup 	branch;
	private float mColorBgWorldR, mColorBgWorldG, mColorBgWorldB;
	public Color3f backgroundColor, 					///< Hintergrundfarbe der 3D Welt
							ambientColor, 					///< Beschreibung der Ambientfarbe
							diffuseColor, 						///< Beschreibung der Diffusefarbe
							emissiveColor,					///< Beschreibung der Emissivefarbe
							specularColor, 					///< Beschreibung der Specularfarbe
							textColor;						///< Beschreibung der Text2Dfarbe
	public float shininess, 								///< Beschreibung der Shininessfarbe
					 alpha; 										///< Beschreibung der Alphafarbe
	public final float DEFAULTCOLOR = 0.5f; 	///< Bestimmung der defaultfarbe
	
	// em05: wenn der default treanform vector erst spaeter definiert wird, wuerde ein setTransform vor der inizialisierung der camera nichts bewirken
	private Vector3f transformVec = new Vector3f(0, 0, 0); /// Default Transform Vector

	///
	/// Standart Konstruktor.
	///
	public PJ3D()
	{
		// em05: habe reihenfolge vertauscht und default aufloesung heruntergesetzt
		this.mWinWidth = 640;
		this.mWinHeight = 480;
		
		InitPJ3DSimple(mWinWidth, mWinHeight);	
	}

	///
	/// Benötigt als Werte die gewünschte Fenstergrösse.
	///
	public PJ3D(int windowWidth, int windowHeight)
	{
		this.mWinWidth = windowWidth;
		this.mWinHeight = windowHeight;
		// um mit processings syntax zu arbeiten...
		this.width = windowWidth;
		this.height = windowHeight;
		
		InitPJ3D(mWinWidth, mWinHeight);
	}
	
	///
	/// Initialisierung der äußeren BranchGroup. Der "normale" weg
	///
	public BranchGroup InitPJ3D(int mWinWidth, int mWinHeight)
	{		
		setLayout( new BorderLayout( ) );
		Frame f = new Frame("PJ3D");
	    f.pack();
	    f.show();
	    f.setSize(mWinWidth, mWinHeight);
	    // em05: damits fenster wieder zugeht, will aber nicht
	    /*
	    f.addWindowListener(new WindowAdapter()
	    					{
	    		       			public void windowClosing(WindowEvent e)
	    		       			{
	    		       				System.exit(0);
	    		       			}
	    		       			
	    		       			public void windowOpened(WindowEvent e)
	    		       			{
	    		       				System.out.println("opening window..");
	    		       			}
	    					});
	    					*/
	    
	    // default colors
	    backgroundColor = new Color3f(1.0f, 1.0f, 1.0f);
	    ambientColor = new Color3f(0.2f, 0.2f, 0.2f);
	    diffuseColor = new Color3f(1.0f, 1.0f, 1.0f);
	    emissiveColor = new Color3f(0.0f, 0.0f, 0.0f);
	    specularColor = new Color3f(1.0f, 1.0f, 1.0f);
	    textColor = new Color3f(0.5f, 0.5f, 0.5f);
	    shininess = DEFAULTCOLOR;
	    alpha = 0.0f;
	    
	    mb = new MainBranch(f, backgroundColor);
	    branch = mb.InitBranch();
	    
	    // get the canvas3d to add the mouse and keylisteners
	    Canvas3D mbCanvas = mb.getCanvas3D();
	    mbCanvas.addKeyListener(this);
	    mbCanvas.addMouseListener(this);
	    mbCanvas.addMouseMotionListener(this);
	    
	    // default transform vector
	    //transformVec = DEFAULTVECTOR;
	    
	    f.show( );
	    return branch;
	}
	
	///
	/// Initialisierung der äußeren BranchGroup. Der "simple" weg
	///
	// em05: easy in simple umgetauft 
	public BranchGroup InitPJ3DSimple(int mWinWidth, int mWinHeight)
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
	    //transformVec = DEFAULTVECTOR;
	    
	    f.show( );
	    
	    PJ3DLight();
	    PJ3DCamera();
	    
	    return branch;
	}
	
	///
	/// Erstellung eines Lichtes.
	///
	public PJ3DLight PJ3DLight()
	{
		PJ3DLight s = new PJ3DLight (mb);
		return s;
	}
	
	///
	/// Erstellung einer Kamera. Benötigt als Werte die xpos, ypos und zpos der 
	/// Kamera.
	///
	public PJ3DCamera PJ3DCamera()
	{
		PJ3DCamera s = new PJ3DCamera (mb.getCanvas3D(), mb.getLocale(), mb,  transformVec);
		return s;
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
	/// Erstellt ein .obj Objekt?? Welche Formate kann man da angeben? -> im moment nur obj. andere importer habe ich noch nicht eingebunden
	///
	public PJ3DObj PJ3DObj( String fileLocation )
	{
		PJ3DObj s = new PJ3DObj (branch, fileLocation, transformVec);
		return s;
	}
	
	///
	/// Erstellt Text2D
	///
	public PJ3DText2D PJ3DText2D(String text, String font, int textSize)
	{
		PJ3DText2D t = new PJ3DText2D(branch, text, font, textSize, transformVec, textColor);
		return t;
	}
	
	///
	/// Erstellt Text3D
	///
	public PJ3DText3D PJ3DText3D(String text, String font)
	{
		PJ3DText3D t = new PJ3DText3D(branch, text, font, transformVec, ambientColor, diffuseColor, emissiveColor, specularColor, shininess, alpha);
		return t;
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
	/// Bestimmung der Specularfarbe
	///		
	public void setSpecularColor(float r, float g, float b)
	{
		specularColor.x = r;
		specularColor.y = g;
		specularColor.z = b;
	}
	
	///
	/// Bestimmung der TextFarbe fuer Text2D
	///
	public void setTextColor(float r, float g, float b)
	{
		textColor.x = r;
		textColor.y = g;
		textColor.z = b;
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
	/// Bestimmung der Transform
	///	
	public void setTransform(float x, float y, float z)
	{
		transformVec.x = x;
		transformVec.y = y;
		transformVec.z = z;
	}
	

	// keyboardlisteners
	
	public void keyTyped (KeyEvent e) { }

    public void keyPressed (KeyEvent e)
    {
    	keyPressed = true;
    	keyReleased = false;
    	key = e.getKeyCode();
    }

    public void keyReleased (KeyEvent e)
    {
    	keyPressed = false;
    	keyReleased = true;
    	key = 0;
    }
    
    // mouseListeners
    public void mousePressed (MouseEvent m)
    {
    	mousePressed = true;
    	mouseReleased = false;
    	mouseX = m.getX();
    	mouseY = m.getY();
    }
    
    public void mouseExited (MouseEvent m) { }
    
    public void mouseEntered (MouseEvent m) { }
    
    public void mouseClicked (MouseEvent m) { }
    
    public void mouseReleased (MouseEvent m)
    {
    	mousePressed = false;
    	mouseReleased = true;
    }
    
    // mousemotionlistener
    public void mouseMoved (MouseEvent n)
    {
    	mouseX = n.getX();
    	mouseY = n.getY();
    }
    
    public void mouseDragged (MouseEvent n)
    {
    	mouseX = n.getX();
    	mouseY = n.getY();
    }
}
