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

public class PJ3D extends Applet
{
	private int mWinHeight, mWinWidth; 
	private MainBranch mb;
	private BranchGroup branch;
	private float mColorBgWorldR, mColorBgWorldG, mColorBgWorldB;
	
	public Color3f backgroundColor, ambientColor, diffuseColor, emissiveColor, specularColor;
	public float shininess, alpha;
	public final float DEFAULTCOLOR = 0.5f;
	
	private Vector3f transformVec;
	
	public PJ3D(){}
	
	public PJ3D(int windowWidth, int windowHeight)
	{
		this.mWinHeight = windowHeight;
		this.mWinWidth = windowWidth;
		
		InitPJ3D(mWinWidth, mWinHeight);
	}
	
	/*
	public PJ3D(int windowWidth, int windowHeight)
	{
		this.mWinHeight = windowHeight;
		this.mWinWidth = windowWidth;
		this.mColorBgWorldR = colorBgWorldR;
		this.mColorBgWorldG = colorBgWorldG;
		this.mColorBgWorldB = colorBgWorldB;
		
		InitPJ3D(mWinWidth, mWinHeight);
	}
	*/
	/*
	public void init()
	{
		System.out.println("huhu");
		BranchGroup hu = InitPJ3D(640, 480);
		SimpleBox b = simpleBox(0.3f, 0.0f, 0.0f, 0.0f);
		b.RotateObj(0.0, 0.3, 0.0);
	}
	*/
	
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
	
	public PJ3DBox PJ3DBox( float xdim, float ydim, float zdim )
	{
		PJ3DBox s = new PJ3DBox (branch, xdim, ydim, zdim, transformVec, ambientColor, diffuseColor, emissiveColor, specularColor, shininess, alpha);
		return s;
	}
	
	public PJ3DBox PJ3DBox( float xdim )
	{
		PJ3DBox s = new PJ3DBox (branch, xdim, xdim, xdim, transformVec, ambientColor, diffuseColor, emissiveColor, specularColor, shininess, alpha);
		return s;
	}
	
	public PJ3DSphere PJ3DSphere( float xdim )
	{
		PJ3DSphere s = new PJ3DSphere (branch, xdim, transformVec, ambientColor, diffuseColor, emissiveColor, specularColor, shininess, alpha);
		return s;
	}
	
	public PJ3DObj PJ3DObj( String fileLocation )
	{
		PJ3DObj s = new PJ3DObj (branch, fileLocation, transformVec);
		return s;
	}
	
	/*
	public PJ3DBox PJ3DBox( float xdim, float ydim, float zdim, float xpos, float ypos, float zpos )
	{
		PJ3DBox s = new PJ3DBox (branch, xdim, ydim, zdim, xpos, ypos, zpos);
		return s;
	}

	public PJ3DBox PJ3DBox( float xdim, float ydim, float zdim, float xpos, float ypos, float zpos, float r, float g, float b )
	{
		PJ3DBox s = new PJ3DBox (branch, xdim, ydim, zdim, xpos, ypos, zpos, r, g, b);
		return s;
	}
	
	public PJ3DBox PJ3DBox( float xdim, float ydim, float zdim, float xpos, float ypos, float zpos, float r, float g, float b, float a )
	{
		PJ3DBox s = new PJ3DBox (branch, xdim, ydim, zdim, xpos, ypos, zpos, r, g, b, a);
		return s;
	}
	*/
	
	public void setBackgroundColor(float r, float g, float b)
	{
		backgroundColor.x = r;
		backgroundColor.y = g;
		backgroundColor.z = b;
		mb.background.setColor(r, g, b);
	}
	
	public void setAmbientColor(float r, float g, float b)
	{
		ambientColor.x = r;
		ambientColor.y = g;
		ambientColor.z = b;
	}
	
	public void setDiffuseColor(float r, float g, float b)
	{
		diffuseColor.x = r;
		diffuseColor.y = g;
		diffuseColor.z = b;
	}
	
	public void setEmissiveColor(float r, float g, float b)
	{
		emissiveColor.x = r;
		emissiveColor.y = g;
		emissiveColor.z = b;
	}
	
	public void setSpecularColor(float r, float g, float b)
	{
		specularColor.x = r;
		specularColor.y = g;
		specularColor.z = b;
	}
	
	public void setShininess(float s)
	{
		shininess = s;
	}
	
	public void setAlpha(float a)
	{
		alpha = a;
	}
	
	public void setTransform(float x, float y, float z)
	{
		transformVec.x = x;
		transformVec.y = y;
		transformVec.z = z;
	}
}
