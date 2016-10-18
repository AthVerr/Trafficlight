package swing;

class TranslatorWE extends Translator {
	  TranslatorWE(double tX, double tY, double tWidth, double tHeight, double tScaleFactor) {
	    super(tX, tY, tWidth, tHeight, tScaleFactor);
	  }
	  int getX(double x, double y, double width, double height) { return scale(_tX+x); }
	  int getY(double x, double y, double width, double height) { return scale(_tY+y); }
	  int getWidth(double width, double height) { return scale(width); }
	  int getHeight(double width, double height)  { return scale(height); }
	}