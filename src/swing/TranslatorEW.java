package swing;

class TranslatorEW extends Translator {
	  TranslatorEW(double tX, double tY, double tWidth, double tHeight, double tScaleFactor) {
	    super(tX, tY, tWidth, tHeight, tScaleFactor);
	  }
	  int getX(double x, double y, double width, double height) { return scale(_tX+_tWidth-x-width); }
	  int getY(double x, double y, double width, double height) { return scale(_tY+_tHeight-y-height); }
	  int getWidth(double width, double height) { return scale(width); }
	  int getHeight(double width, double height)  { return scale(height); }
	}