import java.applet.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.Enumeration;
import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;

public class PJ3DCamera {
	
	public Canvas3D canvas;
	public Locale locale;
	public TransformGroup camGroup;
	public Transform3D camTrans;
	public Vector3f camVec;
	public ViewPlatform myViewPlatform;
	
	public PJ3DCamera(Canvas3D canvas, Locale locale)
	{
		this.canvas = canvas;
		this.locale = locale;
		// camera initialisieren auf 0,0,0 (oder wollen wir ne camera auf nen bestimmten punkt inizialisieren?)
		//this.initCamera(0, 0, 0);
	}
	
	public BranchGroup initCamera(float x, float y, float z)
	{
		BranchGroup cam = new BranchGroup();
		cam.setCapability(cam.ALLOW_CHILDREN_READ);
		cam.setCapability(cam.ALLOW_CHILDREN_WRITE);
        
		ViewPlatform platform = new ViewPlatform();
		Transform3D location = new Transform3D();
		PhysicalBody body = new PhysicalBody();
		body.setNominalEyeHeightFromGround(20.0);
		PhysicalEnvironment env = new PhysicalEnvironment();

		View view = new View();
		view.addCanvas3D(canvas);
	    view.setBackClipDistance(1000);
	    view.setFieldOfView(1.5);
	    view.startView();
	    view.setPhysicalBody(body);
	    view.setPhysicalEnvironment(env);
	    view.attachViewPlatform(platform);
	    
	    myViewPlatform = new ViewPlatform();
	    view.attachViewPlatform(myViewPlatform);
	    
	    // camera auf vector 0,0,0 setzen
        camTrans = new Transform3D();
        camVec = new Vector3f(x, y , z);
        camTrans.set(camVec);
        
        // transformgroup erstellen und rechte fuer setzen (modes auslesen, schreiben, etc)
        camGroup = new TransformGroup(camTrans);
        camGroup.setCapability(camGroup.ALLOW_TRANSFORM_READ);
        camGroup.setCapability(camGroup.ALLOW_TRANSFORM_WRITE);
        camGroup.setCapability(camGroup.ALLOW_CHILDREN_READ);
        camGroup.setCapability(camGroup.ALLOW_CHILDREN_WRITE);
        
        camGroup.addChild(myViewPlatform);
	    cam.addChild(camGroup);
	    
	    //KeyNavigatorBehavior myKeyNavigationBehavior = new KeyNavigatorBehavior (camGroup);
		//cam.addChild(myKeyNavigationBehavior);
		
	    // camera zu locale hinzufuegen
	    //locale.addBranchGraph(cam);
	    return cam;
	}
}
