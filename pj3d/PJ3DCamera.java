import javax.media.j3d.*;
import javax.vecmath.*;

///
/// Enthält aller nötigen Methoden um eine Kamera zu erzeugen und zu bewegen
///
public class PJ3DCamera 
{
	private Canvas3D 			canvas;
	private Locale 				locale;
	private Vector3f 			mCamVec;
	private MainBranch		mMb;
	private View 					view;
	public TransformGroup 	camGroup;
	public Transform3D 		camTrans;
	public ViewPlatform 		myViewPlatform;
	
	///
	/// Konstruktor 
	///
	public PJ3DCamera(Canvas3D canvas, Locale locale, MainBranch mb, Vector3f transformVec)
	{
		this.canvas = canvas;
		this.locale = locale;
		this.mCamVec = new Vector3f(transformVec.x, transformVec.y , transformVec.z);
		this.mMb = mb;
		InitCamera();
	}
	
	///
	/// Für view.setBackClipDistance und view.setFieldOfView sind default Werte vergeben.
	/// Kann man noch set Methoden schreiben
	///
	private void InitCamera()
	{
		BranchGroup camBranch = new BranchGroup();
		camBranch.setCapability(camBranch.ALLOW_CHILDREN_READ);
		camBranch.setCapability(camBranch.ALLOW_CHILDREN_WRITE);
        
		ViewPlatform platform = new ViewPlatform();
		Transform3D location = new Transform3D();
		PhysicalBody body = new PhysicalBody();
		body.setNominalEyeHeightFromGround(20.0);
		PhysicalEnvironment env = new PhysicalEnvironment();

		view = new View();
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
        camTrans.set(mCamVec);
        
        // transformgroup erstellen und rechte fuer setzen (modes auslesen, schreiben, etc)
        camGroup = new TransformGroup(camTrans);
        camGroup.setCapability(camGroup.ALLOW_TRANSFORM_READ);
        camGroup.setCapability(camGroup.ALLOW_TRANSFORM_WRITE);
        camGroup.setCapability(camGroup.ALLOW_CHILDREN_READ);
        camGroup.setCapability(camGroup.ALLOW_CHILDREN_WRITE);
        
        camGroup.addChild(myViewPlatform);
        camBranch.addChild(camGroup);
        
        mMb.getLocale().addBranchGraph(camBranch);
	}
	
	///
	/// get Methode fuer Locale
	///
	public Locale getLocale()
	{
		return locale;
	}
	
	///
	/// Positionierung der Kamera
	///
    public void setCamerapos(float x, float y, float z)
    {
	    // neuen Vector3f mit xyz position erstellen
    	mCamVec.x = x;
    	mCamVec.y = y;
    	mCamVec.z = z;
	    
	    // in Transform3D schreiben
	    camTrans.setTranslation(mCamVec);
	    camGroup.setTransform(camTrans);
    }
    
	///
	/// Rotiert die Kamera
	///
    public void RotateCamera(double x, double y, double z)
    {
	    Transform3D newTransX = new Transform3D();
	    Transform3D newTransY = new Transform3D();
	    Transform3D newTransZ = new Transform3D();
	    //newTrans.add(camTrans);
	    newTransX.rotX(x);
	    newTransY.rotY(y);
	    newTransZ.rotZ(z);
	
	    camTrans.mul(newTransX);
	    camTrans.mul(newTransY);
	    camTrans.mul(newTransZ);

	    camGroup.setTransform(camTrans);
    }
}
