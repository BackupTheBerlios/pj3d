public class PJ3DToolbox
{
	public int colorFloat2Int(float fVal)
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
	
	public float colorInt2Float(int iVal)
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
	public float int2Float(int iVal)
	{
		float fVal = (float)iVal / 255f;
		return fVal;
	}
	
	public double int2Double(int iVal)
	{
		double fVal = (double)iVal / 255f;
		return fVal;
	}
}
