/*
 * Created on Mar 23, 2005
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
import com.sun.j3d.utils.behaviors.picking.*;

public class Pj3dPickableCallback extends Object implements PickingCallback
{
	public Pj3dPickableCallback()
	{
		//System.out.println("Pj3dPickableCallback is init");
	}
	public void transformChanged(int type, javax.media.j3d.TransformGroup tg)
	{
		System.out.println("pick via callback");
	}

}
