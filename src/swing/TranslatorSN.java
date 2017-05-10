package swing;


class TranslatorSN extends Translator {
  TranslatorSN(double tX, double tY, double tWidth, double tHeight, double tScaleFactor) {
    super(tX, tY, tWidth, tHeight, tScaleFactor);
  }
  int getX(double x, double y, double width, double height) { return scale(_tX+_tHeight-y-height); }
  int getY(double x, double y, double width, double height) { return scale(_tY+_tWidth-x-width); }
  int getWidth(double width, double height) { return scale(height); }
  int getHeight(double width, double height)  { return scale(width); }
}