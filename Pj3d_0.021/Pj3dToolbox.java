import javax.vecmath.Color3f;

public class Pj3dToolbox 
{
	public int ColorFloat2Int(float fVal)
	{
		int iVal = 0;
		if (fVal <= 1f & fVal >= 0f)
			iVal = (int)(fVal * 255f);
		else if (fVal < 0f)
			iVal = 0;
		else if (fVal > 1f)
			iVal = 255;
		return iVal;
	}
	
	public float ColorInt2Float(int iVal)
	{
		float fVal = 0f;
		if (iVal <= 255 & iVal >= 0)
			fVal = (float)iVal / 255f;
		else if (iVal < 0f)
			fVal = 0f;
		else if (iVal > 255)
			fVal = 1f;
		return fVal;
	}
	
	// hier muss noch ein richtiges groessenverhaeltnis gefunden werden..
	public float Int2Float(int iVal)
	{
		float fVal = (float)iVal / 255f;
		return fVal;
	}
	
	public double Int2Double(int iVal)
	{
		double fVal = (double)iVal / 255f;
		return fVal;
	}
	
	public Color3f color2Color3f(int pcolor)
	{
		float faktor = 1.0f / 256.0f;
		float x = (((pcolor >> 16) & 0xff) + 1) * faktor;
		float y = (((pcolor >> 8) & 0xff) + 1) * faktor;
		float z = ((pcolor  & 0xff) + 1) * faktor;
		
		Color3f c3f = new Color3f(x, y, z);
		return c3f;
	}
	
	public int color3f2Color(Color3f c3f)
	{
		int a = 255;
		int x = (int)(c3f.x * 256.0f);
		int y = (int)(c3f.y * 256.0f);
		int z = (int)(c3f.z * 256.0f);

		int pcolor = (a << 24) | (x << 16) | (y << 8) | z;
		return pcolor;
	}
}
