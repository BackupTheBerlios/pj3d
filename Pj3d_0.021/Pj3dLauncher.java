import java.applet.*;


//das ist eine beispieldatei, damit man nicht immer erst alle klassen in processing werfen muss..
//es gibt nun zwei arten ein objekt aufzurufen:
//ueber PJ3DCommands oder das Objekt selbst mit der uebergabe des parent objekts (p)

public class Pj3dLauncher extends Applet
{
	public void init()
	{
		Pj3d p = new Pj3d(this, 640, 480);
		p.setBackground(90, 90, 90);
		
		// standard camera
		Pj3dCamera cam = new Pj3dCamera(p);
		
		// standard directional and diffuse light
		Pj3dLight light = new Pj3dLight(p);
		light.setColor(100, 100, 100);
		/*
		// more attributes
		light.setDirection(x, y, z);
		*/

		// create a box - position, rotation and standard color (grey)
		// you have to reference the Pj3d object (here: p) for all primitives
		Pj3dBox b = new Pj3dBox(p, 10);
		b.transform.position(0, 0, -320);
		b.transform.rotate(0, -0.5, 0);
		/*
		// more attributes
		b.transform.translate(0, 0, 10);
		b.transform.scale(10);
		*/
		
		// set color attributes of the box
		b.color.setAmbient(255, 0, 0);
		/*
		// more attributes
		b.color.setDiffuse(255, 255, 255);
		b.color.setSpecular(255, 255, 255);
		b.color.setEmissive(255, 255, 255);
		b.color.setShininess(255);
		b.color.setAlpha(255);
		*/
		
		// create a plane
		Pj3dPlane plane = new Pj3dPlane(p, 150, 150);
		plane.transform.position(0, -10, 0);
		
		// create a sphere
		Pj3dSphere sphere = new Pj3dSphere(p, 5);
		sphere.transform.position(-20, 0, -100);
	}
}
