import java.applet.*;
import java.io.*;
import java.awt.image.*;
import javax.media.j3d.*;
import com.sun.image.codec.jpeg.*;

import javax.vecmath.Color3f;

// wird von der PJ3D main geerbt um die commands zentral zu verwalten

public class PJ3DCommands extends Applet
{
	public PJ3DToolbox ptools = new PJ3DToolbox();
	public Background bg;
	//private PJ3D parent;
	
	public Color3f backgroundColor, 					///< Hintergrundfarbe der 3D Welt
	ambientColor, 					///< Beschreibung der Ambientfarbe
	diffuseColor, 						///< Beschreibung der Diffusefarbe
	emissiveColor,					///< Beschreibung der Emissivefarbe
	specularColor, 					///< Beschreibung der Specularfarbe
	textColor;						///< Beschreibung der Text2Dfarbe
	public float shininess, 								///< Beschreibung der Shininessfarbe
	alpha;										///< Beschreibung der Alphafarbe
	
	public PJ3DBox PJ3DBox(int x, int y, int z)
	{
		PJ3DBox b = new PJ3DBox((PJ3D)this, x, y, z);
		return b;
	}
	
	public PJ3DCamera PJ3DCamera()
	{
		PJ3DCamera cam = new PJ3DCamera((PJ3D)this);
		return cam;
	}
	
	public PJ3DLight PJ3DLight()
	{
		PJ3DLight light = new PJ3DLight((PJ3D)this);
		return light;
	}
	
    public void background(int r, int g, int b)
    {
    	bg.setColor(new Color3f(ptools.colorInt2Float(r), ptools.colorInt2Float(g), ptools.colorInt2Float(b)));
    }
    
    public void background(String texture)
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
}
