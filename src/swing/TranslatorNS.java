package swing;

class TranslatorNS extends Translator {
	  TranslatorNS(double tX, double tY, double tWidth, double tHeight, double tScaleFactor) {
	    super(tX, tY, tWidth, tHeight, tScaleFactor);
	  }
	  int getX(double x, double y, double width, double height) { return scale(_tX+y); }
	  int getY(double x, double y, double width, double height) { return scale(_tY+x); }
	  int getWidth(double width, double height) { return scale(height); }
	  int getHeight(double width, double height)  { return scale(width); }
	}