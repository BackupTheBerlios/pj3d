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
import java.util.ArrayList;


///
/// contain all subobjekts that processing needs for later use.
/// the subobjekts: e.g. PJ3DBox or PJ3DSphere... will be referenced by the first
/// initialization of PJ3D automaticly.
///

public class Pj3d extends Applet implements KeyListener, MouseListener, MouseMotionListener
{
	private int mWinHeight, mWinWidth; 
	private Pj3dScene mb;
	private BranchGroup branch;
	private Canvas3D canvas;
	private Pj3dToolbox toolbox;																				// private Background bg; ist nun in PJ3DCommands
	private float mColorBgWorldR, mColorBgWorldG, mColorBgWorldB;
	private Vector3f transformVec = new Vector3f(0, 0, 0); 								// em05: wenn der default transform vector erst spaeter definiert wird, wuerde ein setTransform vor der inizialisierung der camera nichts bewirken
	public final float DEFAULTCOLOR = 0.5f; 														///< appointment of the  default color
	public int width, height;																						///< width and height of the window. is needed to represent the processing sytax
	public Pj3dToolbox ptools = new Pj3dToolbox();											///< referenz to the Pj3DToolbox
	public Background bg;																							///< the backround objekt
	public Color3f backgroundColor, 																		///< backround of the 3D world
	ambientColor, 																										///< appointment of the Ambient color
	diffuseColor, 																										///< appointment of the Diffuse color
	emissiveColor,																										///< appointment of the Emissive color
	specularColor, 																										///< appointment of the Specular color
	textColor;																												///< appointment of the Text2D color
	public float shininess, 																						///< appointment of the Shininess color
	alpha;																														///< appointment of the alpha value
	public Applet parent;																							///< referenz to applet
	
	public int key;																										///< the key code
	public boolean keyPressed;																				///< is a key pressed?
	public boolean keyReleased;																				///< is a key released?
	public int mouseX;																								///< mouse X position
	public int mouseY;																								///< mouse Y position
	public boolean mousePressed;																			///< is mouse pressed?
	public boolean mouseReleased;																		///<  is mouse released?
	
	public ArrayList pickables = new ArrayList();													///< picking stuff: list of pickable objekts
	public BranchGroup pickedObject;														///< picking stuff: picked objekt
	public boolean objPicked;																					///< picking stuff: is objekt picked?
	
	///
	/// default constructor
	///
	public Pj3d(Applet parent)
	{
		// em05: habe reihenfolge vertauscht und default aufloesung heruntergesetzt
		this.mWinWidth = 640;
		this.mWinHeight = 480;
		
		InitPj3d(mWinWidth, mWinHeight);
	}

	///
	/// overloaded constructor: need the desired values for the window size.
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
	/// initialization of the outer branchGroup. the "normal" way.
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
	    diffuseColor = new Color3f(0.8f, 0.8f, 0.8f);
	    emissiveColor = new Color3f(0.5f, 0.5f, 0.5f);
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
	    canvas.addKeyListener(this);
	    canvas.addMouseListener(this);
	    canvas.addMouseMotionListener(this);
	    System.out.println(canvas.getBounds());
	    f.add( "Center", canvas );
	    
	    f.show( );
	    return branch;
	}
	
	///
    /// create background at setup with default color
	///
    private Background InitBackground()
    {
    	BoundingSphere worldBounds = new BoundingSphere(
    		    new Point3d( 0.0, 0.0, 0.0 ),  // Center
    		    1000000000000000000000.0 );                      // Extent
    		
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
    /// makes a box with the committed x, y, z values
    /// the method is aviable for the processing user in the processing syntax
    ///
    public Pj3dBox Box(int x, int y, int z)
	{
		Pj3dBox b = new Pj3dBox(this, x, y, z);
		return b;
	}

    ///
    /// makes a box with the committed x values
    /// the method is aviable for the processing user in the processing syntax
    ///
    public Pj3dBox Box(int x)
	{
		Pj3dBox b = new Pj3dBox(this, x);
		return b;
	}
    
    ///
    /// makes a sphere with the committed x values
    /// the method is aviable for the processing user in the processing syntax
    ///
    public Pj3dSphere Sphere(int x)
	{
    	Pj3dSphere s = new Pj3dSphere(this, x);
		return s;
	}
    
    ///
    /// makes a plane with the committed x, z values
    /// the method is aviable for the processing user in the processing syntax
    ///
    public Pj3dPlane Pj3dPlane(int x, int z)
	{
    	Pj3dPlane plane = new Pj3dPlane(this, x, z);
		return plane;
	}
    
    ///
    /// load a 3D objekt with the committed path to the file
    /// the method is aviable for the processing user in the processing syntax
    ///
    public Pj3dObj Pj3dObj(String file)
	{
    	Pj3dObj obj = new Pj3dObj(this, file);
		return obj;
	}
	
    ///
    /// creates a camera objekt
    /// the method is aviable for the processing user in the processing syntax
    ///
	public Pj3dCamera Camera()
	{
		Pj3dCamera cam = new Pj3dCamera(this);
		return cam;
	}

    ///
    /// creates a light objekt
    /// the method is aviable for the processing user in the processing syntax
    ///
	public Pj3dLight Light()
	{
		Pj3dLight light = new Pj3dLight(this);
		return light;
	}
	
    ///
    ///set the background color wirh the committed values
    /// the method is aviable for the processing user in the processing syntax
    ///
    public void setBackground(int r, int g, int b)
    {
    	bg.setColor(new Color3f(ptools.ColorInt2Float(r), ptools.ColorInt2Float(g), ptools.ColorInt2Float(b)));
    }

    ///
    ///set the background color wirh the committed values
    /// the method is aviable for the processing user in the processing syntax
    ///
    public void setBackground(int x)
    { 	
    	bg.setColor(ptools.color2Color3f(x));
    }
    
    ///
    /// creates a texture in the background
    /// the method is aviable for the processing user in the processing syntax
    ///
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

    ///
    /// not implemented
    ///
    public void keyTyped (KeyEvent e) { }

    ///
    /// call PJ3D that a key is pressed 
    /// the method is aviable for the processing user in the processing syntax
    ///
    public void keyPressed (KeyEvent e)
    {
    	keyPressed = true;
    	keyReleased = false;
    	key = e.getKeyCode();
    }

    ///
    /// call PJ3D that a key is released 
    /// the method is aviable for the processing user in the processing syntax
    ///
    public void keyReleased (KeyEvent e)
    {
    	keyPressed = false;
    	keyReleased = true;
    	key = 0;
    }
    
    ///
    /// call PJ3D that a the mouse is pressed. calls pickObj(x, y) 
    /// the method is aviable for the processing user in the processing syntax
    ///
    public void mousePressed (MouseEvent m)
    {
    	mousePressed = true;
    	mouseReleased = false;
    	mouseX = m.getX();
    	mouseY = m.getY();
    	pickObject(mouseX, mouseY);
    	//System.out.println("mousePressed");
    }
    
    ///
    /// not implemented
    ///
    public void mouseExited (MouseEvent m) { }
    
    ///
    /// not implemented
    ///
    public void mouseEntered (MouseEvent m) { }
    
    ///
    /// not implemented
    ///
    public void mouseClicked (MouseEvent m) {
    	//System.out.println("mouseClicked..");	
    }
    
    ///
    /// call PJ3D that a the mouse is released and sets the objPicked variable to false
    /// the method is aviable for the processing user in the processing syntax
    ///
    public void mouseReleased (MouseEvent m)
    {
    	mousePressed = false;
    	mouseReleased = true;
    	objPicked = false;
    	//System.out.println("mouseReleased..");
    }
    
    ///
    /// set the mouseX and mouseY variable
    /// the method is aviable for the processing user in the processing syntax
    ///
    public void mouseMoved (MouseEvent n)
    {
    	mouseX = n.getX();
    	mouseY = n.getY();
    }
    
    ///
    /// set the mouseX and mouseY variable
    /// the method is aviable for the processing user in the processing syntax
    /// 
    public void mouseDragged (MouseEvent n)
    {
    	mouseX = n.getX();
    	mouseY = n.getY();
    }
    
    ///
    /// picks a objekt on the scene
    /// the method is available for the processing user in the processing syntax
    ///
    public void pickObject(int mouseX, int mouseY)
    {
    	System.out.println("pick!");
    	try
		{                      
	    	//System.out.println("1");
	    	Point3d mousePoint = new Point3d();
	    	Point3d eyePoint = new Point3d();
	    	//System.out.println("2");
	    	canvas.getPixelLocationInImagePlate(mouseX, mouseY, mousePoint);
	    	canvas.getCenterEyeInImagePlate(eyePoint);
	    	//System.out.println(mousePoint.x);
	    	Transform3D vt = new Transform3D();
	    	canvas.getImagePlateToVworld(vt);
	    	//System.out.println(vt.toString());
	    	vt.transform(mousePoint);
	    	vt.transform(eyePoint);
	    	//System.out.println(vt.toString());
	    	//System.out.println("5");
	    	Vector3d rayVector;
	    	//System.out.println("6");
	    	rayVector = new Vector3d();
	    	rayVector.sub(mousePoint,eyePoint);
	    	rayVector.normalize();
	    	//System.out.println("rayVector "+rayVector.toString());
	    	//System.out.println("7");
	    	PickRay pickRay = new PickRay(mousePoint,rayVector);
	    	System.out.println("pickRay: "+pickRay);
	    	try{
	    	System.out.println("getLocale: "+getMBLocale().pickClosest(pickRay));
	    	SceneGraphPath sceneGraphPath = getMBLocale().pickClosest(pickRay);
	    	System.out.println("9");
	    	//System.out.println(sceneGraphPath.toString());
	    	//pickedObject = (BranchGroup)sceneGraphPath.getNode(0);
	    	}
	    	catch (Exception e) { System.out.println("catch scenegraphpath: "+e); }
	    	objPicked = true;
	    	
	    	System.out.println(objPicked);
		}
    	catch (Exception e)
		{
    		pickedObject = null;
    		objPicked = false;
    		System.out.println("nothing picked!");
    		System.out.println(e);
		}
    }
    
    ///
    /// get the picked objekt on the scene
    /// the method is aviable for the processing user in the processing syntax
    ///
    public Pj3dPickable getPickedObj()
    {
    	Pj3dPickable picked = new Pj3dPickable(this, pickedObject);
    	return picked;
    }
}
