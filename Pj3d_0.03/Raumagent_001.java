import java.applet.*;
import java.awt.image.BufferedImage;
import javax.media.j3d.ImageComponent2D;

public class Raumagent_001 extends Applet implements Runnable
{
	
	private Thread t;
	private int sleep = 10;
	private Pj3d p;
	private Pj3dCamera cam;
	private Pj3dLine l, bgl, bgl2;
	private Pj3dBox player;
	private float counter, counter2;
	private float dist01, dist02;
	private float playerPosY, playerPosZ;
	private float lastMouseX, lastMouseY;
	public boolean irritation = false;
	//private FrameGrabber fg;
	public ImageComponent2D ic = null;
	
	public void init()
	{
		p = new Pj3d(this, 800, 600);
		p.setBackground(90, 90, 90);
		
		// standard camera
		cam = new Pj3dCamera(p);
		cam.transform.rotate(0d, Math.PI/3d, 0);
		cam.transform.translateX(50);
		System.out.println(cam.transform.getPositionZ());
		
		dist01 = -10f;
		dist02 = -50f;
		
		for (int i=0; i<100; i++)
		{
			float size = (float)Math.random()*10f;
			float posY = 15f-(float)Math.random()*30f;
			float posZ = -(i+1)*100f;
			Pj3dBox b = new Pj3dBox(p, size);
			//b.transform.setPosition(0, 0, -100);
			//System.out.println("box");
			b.transform.setPosition(0, posY, posZ);
			b.shader.setAlpha(200);
			//b = null;
		}
		
		// standard directional and diffuse light
		//Pj3dLight light = new Pj3dLight(p);
		//light.setColor(100, 100, 100);
		
		float[] points = {0,-10,0, 0,-10,-1000000000};
		l = new Pj3dLine(p, points);
		//l.transform.translate(0,0,-220);
		
		float[] points2 = {dist01,0,0, dist01,0,10};
		bgl = new Pj3dLine(p, points2);
		bgl.shader.setColor(255, 0, 100);
		
		float[] points3 = {dist02,0,0, dist02,0,10};
		bgl2 = new Pj3dLine(p, points3);
		bgl2.shader.setColor(100, 0, 20);
		counter = 0f;
		counter2 = 0f;
		
		createSinBg();
		
		// player
		player = new Pj3dBox(p, 2);
		player.shader.setColor(255, 0, 100);
		player.shader.setEmissive(255, 0, 100);
		//player.shader.setTexture("H:\\Projekte\\Quellcode\\Java\\Pj3d_0.021\\silhuette.gif");
		playerPosY = 0f;
		playerPosZ = 0f;
		lastMouseX = -1f;
		lastMouseY = -1f;
		
		/*
		try
		{
	    	fg = new FrameGrabber();
	    	fg.init();
		} catch (Exception e) { System.out.println(e); };
		*/
		t = new Thread(this);
	    t.start();
		
	}
	
	public void run()
	{
		try
		{
			while(Thread.currentThread() == t)
			{
				this.draw();
				t.sleep(sleep);
			}
		}
		catch (Exception e) { System.out.println(e); }
	}
	
	public void createSinBg()
	{
		for (int i=0; i<10000; i+=10)
		{
			//float newPoint[] = {0, p.height/2-p.mouseY, cam.transform.getPositionZ()-10000f};
			//float newPoint[] = {0, (float)Math.random()*100f, cam.transform.getPositionZ()-1000f};
			float newPoint2[] = {dist01, (float)Math.sin(i/100f)*10f, -i};
			float newPoint3[] = {dist02, (float)Math.sin(i/100f)*50f, -i};
			//l.addPoint(newPoint);
			bgl.addPoint(newPoint2);
			bgl2.addPoint(newPoint3);
		}
	}
	
	public void draw()
	{
		/*
		if (counter2 == 10)
		{
			//float newPoint[] = {0, p.height/2-p.mouseY, cam.transform.getPositionZ()-10000f};
			//float newPoint[] = {0, (float)Math.random()*100f, cam.transform.getPositionZ()-1000f};
			float newPoint2[] = {dist01, (float)Math.sin(counter*10)*10f, cam.transform.getPositionZ()-300f};
			float newPoint3[] = {dist02, (float)Math.sin(counter*2)*50f, cam.transform.getPositionZ()-300f};
			//l.addPoint(newPoint);
			bgl.addPoint(newPoint2);
			bgl2.addPoint(newPoint3);
			counter2 = 0;
		}
		*/
		counter2++;
		//l.transform.rotate(0.0, 0.001, 0.0);
		float camPos = (float)Math.sin(counter);
		//cam.transform.translateX(camPos); //setPositionX(camPos);
		//cam.transform.setRotation(0d, (double)camPos, 0d);
		
		if(p.keyPressed)
		{
			//System.out.println((char)p.key);
			switch((char)p.key)
			{
				case 'W':
					player.transform.translateY(0.1f);
					break;
				case 'S':
					player.transform.translateY(-0.1f);
					break;
				case 'A':
					playerPosZ += 0.1f;
					break;
				case 'D':
					playerPosZ -= 0.1f;
					break;
				case ' ':
					if (irritation == true)
						irritation = false;
					else
						irritation = true;
					System.out.println("irritation: "+irritation);
					break;
			}
		}
		
		if (p.mousePressed)
		{
			cam.transform.rotate(0d, ((p.width/2)-(p.mouseX-lastMouseX))/10000d, 0d);
		}
		else
		{
			cam.transform.rotate(0d, (double)camPos/800d, 0d);
		}
		cam.transform.translateZ(-0.5f);
		// irritation
		if (irritation)
		{
			double move = (0.5-Math.random())/100d;
			cam.transform.rotate(move, move, 0d);
			//System.out.println(move);
			//p.getMBCanvas().getOffScreenBuffer();
		}
			
		
		player.transform.setPositionZ(cam.transform.getPositionZ()-10f+playerPosZ);
		//System.out.println(cam.transform.getPositionZ());
		//cam.transform.setPositionZ(-counter*100);
		
		//bgl2.shader.setColor(10, 20, 10);
		/*
		try {
			fg.getCamFrame();
			} catch (Exception e) {};
		if (fg.img != null)
		{
			BufferedImage bi = new BufferedImage(fg.img.getWidth(null), fg.img.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
			if (ic == null)
			{
				ic = new ImageComponent2D(ImageComponent2D.FORMAT_RGB, bi);
				ic.setCapability(ImageComponent2D.ALLOW_IMAGE_WRITE);
			}
			else
				ic.setSubImage(bi, 640, 480, 0, 0, 0, 0);
			p.bg.setImage(ic);
		}
		*/
		counter += 0.005;
		//counterBg +=
	}
}
