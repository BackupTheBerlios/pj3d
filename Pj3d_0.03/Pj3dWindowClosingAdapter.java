/*
 * Created on Mar 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author dk
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.awt.*;
import java.awt.event.*;

public class Pj3dWindowClosingAdapter extends WindowAdapter
{
	private boolean exitSystem;
	
	public Pj3dWindowClosingAdapter(boolean exitSystem)
	{
		this.exitSystem = exitSystem;
	}

	public Pj3dWindowClosingAdapter()
	{
		this(false);
	}
	
	public void windowClosing(WindowEvent event)
	{
	     event.getWindow().setVisible(false);
	     event.getWindow().dispose();
	     if (exitSystem) 
	     {
	     	System.exit(0);
	     }
	}
}
