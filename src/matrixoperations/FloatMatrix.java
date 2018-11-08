
package matrixoperations;

/**
 *
 */
public class FloatMatrix extends Matrix {

  /**
   * @param arr
   * @throws InvalidSizeException
   */
  public FloatMatrix(Number[][] arr) throws InvalidSizeException {
    super(arr);
  }

  /* (non-Javadoc)
   * @see matrixoperations.IMatrix#GetValue(int, int)
   */
  @Override
  public Float getValue(int row, int col) throws InvalidIndexException {    
    return super.getValue(row, col).floatValue();
  }

  /* (non-Javadoc)
   * @see matrixoperations.Matrix#getInstance(java.lang.Number[][])
   */
  @Override
  protected IMatrix getInstance(Number[][] arr) throws InvalidSizeException {    
    return new FloatMatrix(arr);
  }

  /* (non-Javadoc)
   * @see matrixoperations.Matrix#sum(java.lang.Number, java.lang.Number)
   */
  @Override
  protected Number sum(Number a, Number b) {
    return a.floatValue() + b.floatValue();
  }

  /* (non-Javadoc)
   * @see matrixoperations.Matrix#mult(java.lang.Number, java.lang.Number)
   */
  @Override
  protected Number mult(Number a, Number b) {
    return a.floatValue() * b.floatValue();
  }

  @Override
  protected Number sub(Number a, Number b) {
    return a.floatValue() - b.floatValue();
  }

  @Override
  protected Number devide(Number a, Number b) {
    return a.floatValue() / b.floatValue();
  }

}
