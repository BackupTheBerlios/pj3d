import java.applet.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

//import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.awt.event.*;

///
/// Enthält alle Unterobjekte die zum späteren Gebrauch in Processing benötigt werden.
/// Die Unterobjekte: z.B. PJ3DBox oder  PJ3DSphere... werden bei der ersten 
/// Initialisierung mit Referenziert. 
///

public class Pj3d extends Applet implements KeyListener, MouseListener, MouseMotionListener
{
	private int mWinHeight, mWinWidth;
	private Pj3dScene mb;
	private BranchGroup branch;
	private Canvas3D canvas;
	private Pj3dToolbox toolbox;	///< private Background bg; ist nun in PJ3DCommands
	private float mColorBgWorldR, mColorBgWorldG, mColorBgWorldB;
	private Vector3f transformVec = new Vector3f(0, 0, 0); ///< em05: wenn der default transform vector erst spaeter definiert wird, wuerde ein setTransform vor der inizialisierung der camera nichts bewirken
	public final float DEFAULTCOLOR = 0.5f; 	///< Bestimmung der defaultfarbe
	public int width, height;
	public Pj3dToolbox ptools = new Pj3dToolbox();
	public Background bg;	
	public Color3f backgroundColor, 					///< Hintergrundfarbe der 3D Welt
	ambientColor, 											///< Beschreibung der Ambientfarbe
	diffuseColor, 												///< Beschreibung der Diffusefarbe
	emissiveColor,											///< Beschreibung der Emissivefarbe
	specularColor, 											///< Beschreibung der Specularfarbe
	textColor;													///< Beschreibung der Text2Dfarbe
	public float shininess, 								///< Beschreibung der Shininessfarbe
	alpha;	
	public Applet parent;
	
	// events
	public int key;
	public boolean keyPressed;
	public boolean keyReleased;
	public int mouseX;
	public int mouseY;
	public boolean mousePressed;
	public boolean mouseReleased;
	
	///
	/// Standart Konstruktor.
	///
	public Pj3d(Applet parent)
	{
		// em05: habe reihenfolge vertauscht und default aufloesung heruntergesetzt
		this.mWinWidth = 640;
		this.mWinHeight = 480;
		
		InitPj3d(mWinWidth, mWinHeight);
	}

	///
	/// Benötigt als Werte die gewünschte Fenstergrösse.
	///
	public Pj3d(Applet parent, int windowWidth, int windowHeight)
	{
		this.parent = (Applet)parent;
		this.mWinWidth = windowWidth;
		this.mWinHeight = windowHeight;
		// um mit processings syntax zu arbeiten...
		this.width = windowWidth;
		this.height = windowHeight;
		
		InitPj3d(mWinWidth, mWinHeight);
	}
	
	///
	/// Initialisierung der äußeren BranchGroup. Der "normale" weg
	///
	public BranchGroup InitPj3d(int mWinWidth, int mWinHeight)
	{	
		//toolbox = new PJ3DToolbox();
		setLayout( new BorderLayout( ) );
		//parent.width = 640;
		//parent.resize(640, 480);

		Frame f = new Frame("pj3d");
	    f.pack();
	    f.show();
	    f.setSize(mWinWidth, mWinHeight);

	    GraphicsConfiguration gc = parent.getGraphicsConfiguration();
	    
	    // default colors
	    backgroundColor = new Color3f(0f, 0f, 0f);
	    ambientColor = new Color3f(0.2f, 0.2f, 0.2f);
	    diffuseColor = new Color3f(1.0f, 1.0f, 1.0f);
	    emissiveColor = new Color3f(0.0f, 0.0f, 0.0f);
	    specularColor = new Color3f(1.0f, 1.0f, 1.0f);
	    textColor = new Color3f(0.5f, 0.5f, 0.5f);
	    shininess = DEFAULTCOLOR;
	    alpha = 0.0f;
	    
	    // default background um spaeter drauf zugreifen zu koennen
	    //bg = new Background();
	    bg = InitBackground();
	    
	    mb = new Pj3dScene(f, gc, bg);
	    branch = mb.InitBranch();
	    
	    // get the canvas3d to add the mouse and keylisteners
	    canvas = mb.getMBCanvas3D();
	    f.add( "Center", canvas );
	    
	    f.show( );
	    return branch;
	}
	
    // create background at setup with default color
    private Background InitBackground()
    {
    	BoundingSphere worldBounds = new BoundingSphere(
    		    new Point3d( 0.0, 0.0, 0.0 ),  // Center
    		    1000.0 );                      // Extent
    		
    	// Set the background color and its application bounds
    	bg = new Background( );
    	bg.setColor( backgroundColor );
    	bg.setCapability( Background.ALLOW_COLOR_WRITE );
    	bg.setCapability( Background.ALLOW_COLOR_READ );
    	bg.setCapability( Background.ALLOW_IMAGE_READ );
    	bg.setCapability( Background.ALLOW_IMAGE_WRITE );
    	bg.setApplicationBounds( worldBounds );
    	
    	return bg;
    }
    
	///
	/// get Canvas
	///
    public Canvas3D getMBCanvas()
    {
    	Canvas3D c = mb.getMBCanvas3D();
    	return c;
    }
    
	///
	/// get locale
	///
    public Locale getMBLocale()
    {
    	Locale l = mb.getLocale();
    	return l;
    }
    
	///
	/// add model to branch
	///
    public void AddModel(BranchGroup child)
    {
    	branch.addChild(child);
    }
    
    ///
    /// commands
    ///
    
    public Pj3dBox Box(int x, int y, int z)
	{
		Pj3dBox b = new Pj3dBox(this, x, y, z);
		return b;
	}
    
    public Pj3dBox Box(int x)
	{
		Pj3dBox b = new Pj3dBox(this, x);
		return b;
	}
    
    public Pj3dSphere Sphere(int x)
	{
    	Pj3dSphere s = new Pj3dSphere(this, x);
		return s;
	}
    
    public Pj3dPlane Pj3dPlane(int x, int z)
	{
    	Pj3dPlane plane = new Pj3dPlane(this, x, z);
		return plane;
	}
    
    public Pj3dObj Pj3dObj(String file)
	{
    	Pj3dObj obj = new Pj3dObj(this, file);
		return obj;
	}
	
	public Pj3dCamera Camera()
	{
		Pj3dCamera cam = new Pj3dCamera(this);
		return cam;
	}
	
	public Pj3dLight Light()
	{
		Pj3dLight light = new Pj3dLight(this);
		return light;
	}
	
    public void setBackground(int r, int g, int b)
    {
    	bg.setColor(new Color3f(ptools.ColorInt2Float(r), ptools.ColorInt2Float(g), ptools.ColorInt2Float(b)));
    }
    
    public void setBackground(int x)
    { 	
    	bg.setColor(ptools.color2Color3f(x));
    }
    
    public void setBackground(String texture)
    {
    	FileInputStream inFile = null;
   	    try
   	    {
   	        inFile = new FileInputStream(texture);
   	        JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(inFile);
   	        BufferedImage bgBuffImg = decoder.decodeAsBufferedImage();
   	        ImageComponent2D bgImange = new ImageComponent2D(BufferedImage.TYPE_INT_RGB, bgBuffImg);
   	        bg.setImage(bgImange);
   	        inFile.close();
   	    }
   	    catch (Exception e)
		{
   	    	System.out.println(e + "\n" + "Texture Import failed!");
   	    }
    }
    
    
    // keyboard and mouse events
    // alle variablen public oder halt get/set
	
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
