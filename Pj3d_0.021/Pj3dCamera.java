import javax.media.j3d.*;
import javax.vecmath.*;

///
/// Enthält alle nötigen Methoden um eine Kamera zu erzeugen und zu bewegen
///
public class Pj3dCamera
{
	private Canvas3D 			canvas;
	private Locale 				locale;
	private Vector3f 			mCamVec;
	private Pj3dScene	mMb;
	private View 					view;
	public ViewPlatform 		myViewPlatform;
	private Pj3d 					parent;
	public Pj3dTransform transform = new Pj3dTransform();
	
	///
	/// Konstruktor 
	///
	public Pj3dCamera(Pj3d parent)
	{
		this.parent = parent;
		this.canvas = parent.getMBCanvas();
		this.locale = parent.getMBLocale();
		InitCamera();
	}
	
	///
	/// Für view.setBackClipDistance und view.setFieldOfView sind default Werte vergeben.
	/// Kann man noch set Methoden schreiben
	///
	private void InitCamera()
	{
		BranchGroup camBranch = new BranchGroup();
		camBranch.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		camBranch.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        
		ViewPlatform platform = new ViewPlatform();
		Transform3D location = new Transform3D();
		PhysicalBody body = new PhysicalBody();
		body.setNominalEyeHeightFromGround(20.0);
		PhysicalEnvironment env = new PhysicalEnvironment();

		view = new View();
		view.addCanvas3D(canvas);
		view.setFrontClipDistance(0.01);
	    view.setBackClipDistance(10000);
	    view.setFieldOfView(1.5);
	    view.startView();
	    view.setPhysicalBody(body);
	    view.setPhysicalEnvironment(env);
	    view.attachViewPlatform(platform);

	    myViewPlatform = new ViewPlatform();
	    view.attachViewPlatform(myViewPlatform);
	    
	    // camera auf vector 0,0,0 setzen
	    transform.transformVector = new Vector3f(0f, 0f, 0f);
        transform.transform3D = new Transform3D();
        transform.transform3D.set(transform.transformVector);
        
        // transformgroup erstellen und rechte fuer setzen (modes auslesen, schreiben, etc)
        transform.transformgroup = new TransformGroup(transform.transform3D);
        transform.transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transform.transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transform.transformgroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        transform.transformgroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        
        transform.transformgroup.addChild(myViewPlatform);
        camBranch.addChild(transform.transformgroup);
        
        locale.addBranchGraph(camBranch);
	}
	
	///
	/// get Methode fuer Locale
	///
	public Locale getLocale()
	{
		return locale;
	}
	
    ///
	/// Setzt FOV
	///
    public void setFov(float fov)
    {
    	view.setFieldOfView(fov);
    }
}
