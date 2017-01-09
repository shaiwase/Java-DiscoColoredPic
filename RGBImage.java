/**
 * 
 * @author karine Or TZ : 310261243 Course 20441
 * @category MMN13
 * This class represents a colored picture represented by a Multidimentional array of object from RGBColor class
 * Each point in the picture is  a pixel that represents the color on coordinates.
 * 
 */

public class RGBImage {
	
private RGBColor[][] _pixels;

/**
 * This is the default constructor. It creates a Black image  with a height ='rows' and width = 'cols'
 * @param rows Sets the number of rows in the 2D array.
 * @param cols Sets the number of columns in the 2D array.
 */
public RGBImage(int rows, int cols) 
{
	_pixels = setArrayBlack(new RGBColor[rows][cols]); //Calls the private method setArrayBlack() who creates black image Array.
													   //  RGBColor[][] blackArray = new RGBColor[pixelArray.length][pixelArray[0].length]; 
} 
 
/**
 * This constructor creates a new RGBImage cloned of the array of pixels given in parameters  and initializes it with 'pixels' array.
 * @param pixels
 */
public RGBImage(RGBColor[][] pixels)
{
 	_pixels = new RGBColor[pixels.length][pixels[0].length];

	for (int r = 0; r < pixels.length ; r++){ //go over the source array to copy pixel by pixel. instead of pointing to the external  array (which is bad)
		for (int c = 0; c < _pixels[r].length; c++){

			_pixels[r][c] = new RGBColor (pixels[r][c]); //create the object for a color instead of just doing =, to avoid pointing to an external color (cell)
		}
	}
}

/**
 * This constructor creates a new RGBImage identical to 'other'.
 * @param other
 */
public RGBImage(RGBImage other)
{
	RGBColor[][] tempArray = other._pixels; //Create a new temporary Array and copy the other array inside.
	_pixels = new RGBColor[tempArray.length][tempArray[0].length]; //Create a new array with the lenghts of the other array copied into temp array.

	for (int r = 0; r < tempArray.length; r++)
	{ //Iterate over the tempArray to copy pixel by pixel 
		for (int c = 0; c < tempArray[r].length; c++)
		{
			_pixels[r][c] = new RGBColor(tempArray[r][c]);  //Copy the tempArray into the new created image. 
		}
	}
}

/**
 * @return The height of this array.
 */
public int getHeight()  //returns the number of rows
{
	return _pixels.length;
} 

/**
 * @return The width of this array.
 */
public int getWidth() //returns the number of columns
{
	return _pixels[0].length;
}

/**
 * This method returns the pixel located at [row][col]
 * @param row
 *            The row in which the pixel is located at
 * @param col
 *            The column in which the pixel is located at
 * @return The pixel located at [height][width]
 */
public RGBColor getPixel(int row, int col) 
{
	if (row > getHeight() || col > getWidth())
	{
		return new RGBColor();
	} else	{
		return new RGBColor(_pixels[row][col]);
	}
} 

/**
 * This method sets 'pixel' in the location [row][column].
 * @param row
 *            The row in which 'pixel' is to be set at.
 * @param col
 *            The column in which 'pixel' is to be set at.
 * @param pixel
 *            The pixel to set at [row][column].
 */
public void setPixel(int row, int col, RGBColor pixel) 
{
	if (row > getHeight() || col > getWidth())
	{
		return;  //Check if outside of bound and if yes does nothing.
	} else {
		_pixels[row][col] = new RGBColor(pixel);  //Set the pixel to be as the coordinates passed in the method.
	}
}

/**
 * This method check whether two RGBImages are identical.
 * 
 * @param other
 *            The RGBImage to be compared with.
 * @return True if images are the same and False if they are not
 */
public boolean equals(RGBImage other)
{
	if (!(getHeight() == other.getHeight())	|| !(getWidth() == other.getWidth()))//Check if Height and Width of the compared pictures are different. If so return false 
	{
		return false;
	} 
	for (int r = 0; r < getHeight(); r++) 
	{
		for (int c = 0; c < getWidth(); c++)
		{
			if (!_pixels[r][c].equals(other.getPixel(r, c)))
			{
				return false;
			} 
		} 
	} 
	return true;
} 

/**
 * Flips the image on a horizontal axis.
**/
public void flipHorizontal()
{
  rotateClockwise();
  flipVertical();
  rotateCounterClockwise();
}
//Alternative method for flipHorizontal which is a lot longer...
//	for(int r = 0; r < _pixels.length; r++) //Iterate on the rows
//	{
//		for(int c = 0; c < _pixels[r].length / 2; c++) //Iterate on the columns. Calculate the middle of the length to know where to stop the iteration as all the numbers will have been flipped.
//		{
//			RGBColor _swap = _pixels[r][c]; //Store the left pixel reference in a swap variable.
//			_pixels[r][c] =_pixels[r][_pixels[r].length - 1 - c]; //Override the left pixel reference with the right pixel reference
//			_pixels[r][_pixels[r].length - 1 - c] = _swap; //Override the right pixel reference with the reference stored in swap (left reference)
//		}
//	}



/**
 * This method flips the image on it's vertical axis.
 */
public void flipVertical()
{
	for(int r = 0; r < _pixels.length / 2 ; r++)
	{
	 	RGBColor[] _swap = _pixels[r]; //i can use this syntax because it is multidimentional _pixel[r]
	 	
		_pixels[r] = _pixels[_pixels.length - 1 - r];
		_pixels[_pixels.length - 1 - r] = _swap;
	}
}


/**
 * This method inverts each pixel in the Array.
 */
public void invertColors()
{
	for (int r = 0; r < getHeight(); r++) {
		for (int c = 0; c < getWidth(); c++) {
			_pixels[r][c].invert(); //Calls invert() method from RGBColor class
		} 
	} 
}

/**
 * This method copies an array.
 * @return A copy of this RGBImage array.
 */
public RGBColor[][] toRGBColorArray() {
	return copyArray(_pixels);
} 

/**
 * This method rotates the image 90 degrees to the right.
 */
public void rotateClockwise()
{
	RGBColor[][] rotatedArray = new RGBColor[getWidth()][getHeight()]; //Create new array

	for (int r = 0; r < rotatedArray.length; r++) 
	{
		for (int c = 0; c < rotatedArray[0].length; c++)
		{
		rotatedArray[r][c] = toRGBColorArray()[getHeight() - 1 - c][r];
	    }
	}
	setPixelArray(rotatedArray);
}

/**
 * This method rotates the image 90 degrees to the left.
 */
public void rotateCounterClockwise() 
{
	rotateClockwise(); //Turning 3 times clockwise return to turning 1 time against clockwise
	rotateClockwise();
	rotateClockwise();
} 

/**
 * This method shifts the 2D array to the right if offset > 0 and to the  left if offset < 0
 * If out of the bounds it does nothing.
 * @param offset
 *            The number of columns to shift the image.
 */
public void shiftCol(int offset)
{
	if (offset == getWidth() || offset == -getWidth()) 
	{
		setPixelArray(setArrayBlack(_pixels)); //If foset is 
    } 

	else if (offset > getWidth() - 1 || offset < -getWidth() + 1 || offset == 0) 
	{
		return; // if out of bounds it does nothing
	}

	else {

		RGBColor[][] blackSheet = setArrayBlack(_pixels);
		if (offset > 0) {
			for (int i = 0; i < blackSheet.length; i++) {
				for (int j = 0; j < blackSheet[0].length; j++) {
					if (!(j < offset)) {
						blackSheet[i][j] = new RGBColor(
								toRGBColorArray()[i][j - offset]);
					} 

				} 
			} 

			setPixelArray(blackSheet);
		}// if > 0

		else if (offset < 0) {
			
			offset = -offset;
			flipHorizontal();
			for (int i = 0; i < blackSheet.length; i++) {
				for (int j = 0; j < blackSheet[0].length; j++) {
					if (!(j < offset)) {
						blackSheet[i][j] = toRGBColorArray()[i][j - offset];
					} 
				} 
			} 
			setPixelArray(blackSheet);
			flipHorizontal();
		} 
	} 

}// shiftCol

/**
 * This method shifts the 2D array upwards if offset < 0 and downwards if * offset > 0.
 * @param offset
 *            The number of rows to shift the image.
 */
public void shiftRow(int offset)
{
	if (offset == getWidth() || offset == -getWidth())
	{
		 
		setPixelArray(setArrayBlack(_pixels));
	} 
	else {
		    if (offset > 0) 
		 {
			rotateCounterClockwise();
			shiftCol(offset);
			rotateClockwise();
		
		 }
	else if (offset < 0) 
		 {
			rotateClockwise();
			shiftCol(-offset);
			rotateCounterClockwise();
		}
	}// else 1
}



/**
 * @return A grayscale representation of the image.
 */
public double[][] toGrayscaleArray()
{
	double[][] grayScaledArray = new double[getHeight()][getWidth()];
	
	for (int r = 0; r < _pixels.length; r++) 
	{
		for (int c = 0; c< _pixels[0].length; c++)
		{

			grayScaledArray[r][c] = _pixels[r][c].convertToGrayscale();
		}
	}
	return grayScaledArray;
} 

/**
 * @returns the 2D array as A String for visualization.
 */
public String toString() 
{
	String output = "";
	
	for (int r = 0; r < _pixels.length; r++) 
	{
		for (int c = 0; c < _pixels[0].length; c++) 
		{
			if (c != _pixels[0].length - 1)
			{
				output += _pixels[r][c].toString() + " ";
			} else {
				output += _pixels[r][c].toString() + "\n";
			}
		}
	} 
	return output;
} 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                  **      PRIVATE METHODS      **                                                         /
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * This method copies an a given array.
 * 
 * @param pixels
 *            The array to copy.
 * @return A copy of 'pixels'.
 */
private RGBColor[][] copyArray(RGBColor[][] pixels) 
{
	RGBColor[][] copiedArray = new RGBColor[pixels.length][pixels[0].length];
	
	for (int r = 0; r < pixels.length; r++)
	{
		for (int c = 0; c < pixels[0].length; c++)
		{
			copiedArray[r][c] = new RGBColor(pixels[r][c]);
		} 
	} 
	return copiedArray;
} 

/**
 * This method sets 'pixelArray' as this RGBImage array.
 * @param pixelArray
 *        (the array to be set to this RGBImage).
 */
private void setPixelArray(RGBColor[][] pixelArray)
{
	this._pixels = copyArray(pixelArray); //calls private method copyArray()
} 

/**
 * This method converts a give Array into a black Array with the same dimensions as pixelArray 
 * @param pixelArray
 * @return A black image.
 */
private RGBColor[][] setArrayBlack(RGBColor[][] pixelArray)
{
	RGBColor[][] blackArray = new RGBColor[pixelArray.length][pixelArray[0].length]; //Create empty Array full of 0 on the heap.
	
	for (int r = 0; r < blackArray.length; r++)
	{
		for (int c = 0; c < blackArray[0].length; c++) 
		{
			blackArray[r][c] = new RGBColor();
		}
	}
	return blackArray;
} 

} //class close