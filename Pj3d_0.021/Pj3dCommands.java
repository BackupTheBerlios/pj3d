import java.applet.*;
import java.io.*;
import java.awt.image.*;
import javax.media.j3d.*;
import com.sun.image.codec.jpeg.*;

import javax.vecmath.Color3f;

// wird von der PJ3D main geerbt um die commands zentral zu verwalten

public class Pj3dCommands
{
	/*
	private Pj3dToolbox ptools;
	
	public Pj3dCommands(Pj3d parent)
	{
		ptools = new Pj3dToolbox();
	}
	
	public Pj3dBox PmBox(Pj3d parent, int x, int y, int z)
	{
		Pj3dBox b = new Pj3dBox(parent, x, y, z);
		return b;
	}
	
	public Pj3dCamera PmCamera(Pj3d parent)
	{
		Pj3dCamera cam = new Pj3dCamera(parent);
		return cam;
	}
	
	public Pj3dLight PmLight(Pj3d parent)
	{
		Pj3dLight light = new Pj3dLight(parent);
		return light;
	}
	
    public void setBackground(Pj3d parent, int r, int g, int b)
    {
    	bg.setColor(new Color3f(ptools.ColorInt2Float(r), ptools.ColorInt2Float(g), ptools.ColorInt2Float(b)));
    }
    
    public void setBackground(Pj3d parent, String texture)
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
    */
}
