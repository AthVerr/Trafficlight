package swing;

/**
 * A translator from relative model space to screen graphics.
 */
abstract class Translator {
  double _tX;
  double _tY;
  double _tWidth;
  double _tHeight;
  double _tScaleFactor;
  Translator(double tX, double tY, double tWidth, double tHeight, double tScaleFactor) {
    _tX = tX;
    _tY = tY;
    _tWidth = tWidth;
    _tHeight = tHeight;
    _tScaleFactor = tScaleFactor;
  }
  int scale(double arg) {
    return (int) Math.ceil(arg * _tScaleFactor); 
  }
  abstract int getX(double x, double y, double width, double height);
  abstract int getY(double x, double y, double width, double height);
  abstract int getWidth(double width, double height);
  abstract int getHeight(double width, double height);
}
