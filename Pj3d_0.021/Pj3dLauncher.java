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
		light.setColor(20, 20, 20);
		/*
		// more attributes
		light.setDirection(x, y, z);
		*/

		// create a box - position, rotation and standard color (grey)
		// you have to reference the Pj3d object (here: p) for all primitives
		//Pj3dBox b = new Pj3dBox(p, 100);
		//b.transform.position(0, -100, -820);
		//b.transform.rotate(0, -0.8, 0);
		
		for (int i=0; i<7; i++)
		{
			int height = (int)((Math.random()*10));
			int vorzeichen = 1;
			if (Math.random()>0.5f)
				vorzeichen = -1;
			int posX = vorzeichen*(int)((Math.random()*1000));
			int posY = (height/2);
			int posZ = -(int)((Math.random()*1000));
			Pj3dBox b = new Pj3dBox(p, 20, height, 20);
			b.transform.setPosition(posX, 0, posZ);
			System.out.println(posX+" "+posY+" "+posZ+" "+height);
			// texture ist natuerlich langsam, wenn sie jedesmal neu geladen wird. -> texture objekt?
			b.shader.setTexture("luzi_in_snow.jpg");
		}
		
		/*
		// more attributes
		b.transform.translate(0, 0, 10);
		b.transform.scale(10);
		*/
		
		// set color attributes of the box
		//b.shader.setAmbient(255, 0, 0);
		//b.shader.setDiffuse(255, 0, 0);
		//b.shader.setSpecular(0, 0, 0);
		//b.shader.setEmissive(0, 0, 255);
		//b.shader.setTexture("luzi_in_snow.jpg");
		//b.shader.setShininess(255);
		//b.shader.setAlpha(0);
		/*
		// more attributes
		b.color.setDiffuse(255, 255, 255);
		b.color.setSpecular(255, 255, 255);
		b.color.setEmissive(255, 255, 255);
		b.color.setShininess(255);
		b.color.setAlpha(255);
		*/
		
		// create a plane
		//Pj3dPlane plane = new Pj3dPlane(p, 150, 150);
		//plane.transform.position(0, -10, 0);
		//plane.shader.setTexture("luzi_in_snow2.jpg");
		// create a sphere
		//Pj3dSphere sphere = new Pj3dSphere(p, 20);
		//sphere.transform.position(0, 0, -30);
		//sphere.shader.setTexture("rainbow.jpg");
		//sphere.shader.setTexture("orange.jpg");
		//sphere.transform.scale(10);
		
	}
}
