import java.applet.*;


//das ist eine beispieldatei, damit man nicht immer erst alle klassen in processing werfen muss..
//es gibt nun zwei arten ein objekt aufzurufen:
//ueber PJ3DCommands oder das Objekt selbst mit der uebergabe des parent objekts (p)

public class Pj3dLauncher extends Applet
{
	public void init()
	{
		//Pj3d p = new Pj3d(640, 480);
		//p.setBackground(90, 90, 90);
		Pj3dToolbox t = new Pj3dToolbox();
		int r = 100;
		int g = 255;
		int b = 255;
		int a = 255;
		int c = (a << 24) | (r << 16) | (g << 8) | b;
		t.color2Color3f(c);
		/*
		Pj3dCamera cam = new Pj3dCamera(p);
		
		Pj3dLight light = new Pj3dLight(p);

		Pj3dBox b = new Pj3dBox(p, 10);
		b.transform.position(0, 0, -120);
		b.transform.rotate(0, -0.5, 0);
		
		b.color.setShininess(0);
		b.color.setSpecular(0, 255, 0);
		*/
	}
}
