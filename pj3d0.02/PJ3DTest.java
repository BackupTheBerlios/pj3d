import java.applet.*;


// das ist eine beispieldatei, damit man nicht immer erst alle klassen in processing werfen muss..
// es gibt nun zwei arten ein objekt aufzurufen:
// ueber PJ3DCommands oder das Objekt selbst mit der uebergabe des parent objekts (p)

public class PJ3DTest extends Applet{

	public void init()
	{
		PJ3D p = new PJ3D(640, 480);
		p.background(90, 90, 90);
		//p.background("eMario_02.jpg");
		//PJ3DCamera cam = p.PJ3DCamera();
		PJ3DCamera cam = new PJ3DCamera(p);
		//cam.transform.rotate(0, 0.2, 0);
		//cam.transform.translate(0, 0, 30);
		
		PJ3DLight light = new PJ3DLight(p);
		
		//p.PJ3DBox(20, 20, 20);
		PJ3DBox b = new PJ3DBox(p, 10);
		b.transform.position(0, 0, -120);
		b.transform.rotate(0, -0.5, 0);
		//b.transform.translate(20, 40, 0);
		//b.color.alpha(100);
		b.color.shininess(0);
		b.color.fill(0, 255, 0);
	}
}
