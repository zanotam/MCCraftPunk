package common.craftpunk.core.util;

public class Color 
{
	public int primaryColor;
	
	public int red;	
	public int green;
	public int blue;
	
	public Color(int color)
	{
		this.primaryColor = color;
		//uses "& 255" to make sure that the number is less than or equal to 255.
		this.red = this.primaryColor >> 16 & 255;
		this.green  = this.primaryColor >> 8 & 255;
		this.blue = this.primaryColor & 255;
	}	
	
	public int getColorInt()
	{
		return this.primaryColor;
	}
	
	public int getRedInt()
	{
		return this.red;
	}
	
	public int getGreenInt()
	{
		return this.green;
	}
	
	public int getBlueInt()
	{
		return this.blue;
	}
	
	public boolean setRedInt(int redInt)
	{
		boolean valid = (0 <= redInt) && (256 > redInt);
		if (valid)
		{
			this.red = redInt;
			this.recalculatePrimaryColor();
		}
		return valid;
	}
	
	public boolean setBlueInt(int blueInt)
	{
		boolean valid = (0 <= blueInt) && (256 > blueInt);
		if (valid)
		{
			this.blue = blueInt;
			this.recalculatePrimaryColor();
		}
		return valid;
	}
	
	public boolean setGreenInt(int greenInt)
	{
		boolean valid = (0 <= greenInt) && (256 > greenInt);
		if (valid)
		{
			this.green = greenInt;
			this.recalculatePrimaryColor();
		}
		return valid;
	}
    
	public float getRed()
	{
		float redFloat = (float)(this.red) / 255.0F;
		return redFloat;
	}
	
	public float getBlue()
	{
		float blueFloat = (float) (this.blue) / 255.0F;
		return blueFloat;
	}
	
	public float getGreen()
	{
		float greenFloat = (float) (this.green) / 255.0F;
		return greenFloat;
	}
	
	public boolean recalculatePrimaryColor()
	{
		int colorInt = 0;
		colorInt += this.blue & 255;
		colorInt += (this.green & 255) << 8;
		colorInt += (this.red & 255) << 16;
		this.primaryColor = colorInt;
		return true;
	}

}
