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
		
		int lines = 30;
		float lastX = 0;
		float lastY = 0;
		float lastZ = 0;
		//float[] points = new float[lines*3];
		
		/*
		for (int i=0; i<lines/2; i++)
		{
			float lineX = (float)Math.random()*100;
			float lineY = (float)Math.random()*100;
			float lineZ = (float)Math.random()*100;
			
		}
		*/
		float[] points = {0,0,0, 20,0,10};
		Pj3dLine l = new Pj3dLine(p, points);
		//l.transform.translate(0,0,-220);
		
		float newPoint[] = {20,20,0};
		l.addPoint(newPoint);
		
		for (int i=0; i<20; i++)
		{
			newPoint[0] = (float)Math.random()*50f;
			newPoint[1] = (float)Math.random()*50f;
			newPoint[2] = (float)Math.random()*50f;
			l.addPoint(newPoint);
		}
			
			
		// create a box - position, rotation and standard color (grey)
		// you have to reference the Pj3d object (here: p) for all primitives
		/*
		Pj3dSphere b = new Pj3dSphere(p, 30);
		//b.transform.setPosition(0.0f, 0.0f, -20.2f);
		b.transform.translate(30.0f, 0.0f, 50f);
		b.transform.rotate(0, -0.8, 0.5);
		b.transform.setScale(1);
		b.shader.setShininess(0);
		b.shader.setSpecular(255, 100, 0);
		b.shader.setEmissive(100, 100, 0);
		*/
		
		for (int i=0; i<5; i++)
		{
			Pj3dBox box = new Pj3dBox(p, 20);
			//Pj3dSphere box = new Pj3dSphere(p, 20);
			//box.transform.translateZ(-(i+1)*100);
			box.transform.setPosition(0f, 0f, -(i+1f)*100f);
			box.transform.rotate(0d, Math.random(), 0d);
			box.shader.setAlpha(200);
		}
		
		/*
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
		
		*/
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
