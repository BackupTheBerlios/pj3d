import javax.media.j3d.*;
import javax.vecmath.*;

import java.awt.*;
import java.awt.event.*;

///
/// Enthält alle Unterobjekte die zum späteren Gebrauch in Processing benötigt werden.
/// Die Unterobjekte: z.B. PJ3DBox oder  PJ3DSphere... werden bei der ersten 
/// Initialisierung mit Referenziert. 
///
public class PJ3D extends PJ3DCommands implements KeyListener, MouseListener, MouseMotionListener
{
	private int mWinHeight, mWinWidth;
	public int width, height;
	public int mouseX, mouseY, key;							// public mouse and key values for processing
	public boolean mousePressed, keyPressed, mouseReleased, keyReleased;	// boolean mouse and keyboard states
	private PJ3DMainBranch mb;
	private BranchGroup branch;
	private Canvas3D canvas;
	// private Background bg; ist nun in PJ3DCommands
	private PJ3DToolbox toolbox;
	private float mColorBgWorldR, mColorBgWorldG, mColorBgWorldB;
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
		
		InitPJ3D(mWinWidth, mWinHeight);
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
		toolbox = new PJ3DToolbox();
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
	    
	    // default GraphicsConfigureation
	    GraphicsConfiguration gc = this.getGraphicsConfiguration();
	    
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
	    bg = initBackground();
	    
	    mb = new PJ3DMainBranch(f, gc, bg);
	    branch = mb.InitBranch();
	    
	    // get the canvas3d to add the mouse and keylisteners
	    canvas = mb.getMBCanvas3D();
	    canvas.addKeyListener(this);
	    canvas.addMouseListener(this);
	    canvas.addMouseMotionListener(this);
	    
	    // default transform vector
	    //transformVec = DEFAULTVECTOR;
	    
	    f.show( );
	    return branch;
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
    
    
    // background functions
    
    // create background at setup with default color
    private Background initBackground()
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
    
    
    //get set fuer mainbranch
    public Canvas3D getMBCanvas()
    {
    	Canvas3D c = mb.getMBCanvas3D();
    	return c;
    }
    
    public Locale getMBLocale()
    {
    	Locale l = mb.getLocale();
    	return l;
    }
    
    
    // neue methode um branch private zu lassen.
    public void addModelToBranch(BranchGroup child)
    {
    	branch.addChild(child);
    }
}
