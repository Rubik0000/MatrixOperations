
package matrixoperations;


/**
 *
 */
public abstract class Matrix implements IMatrix {

  /**The number of rows*/
  private int _rows;
  
  /**The number of columns*/
  private int _cols;
  
  /**The values of the matrix*/
  private Number[][] _matrix; 
  
  protected Matrix(Number[][] arr) throws InvalidSizeException {
    int cols = arr[0].length;
    for (int i = 1; i < arr.length; ++i) {
      if (arr[i].length != cols) {
        throw new InvalidSizeException("The number of columns in " +
           i + "th row does not equal to the initial size " + cols);
      }
    }
    _rows = arr.length;
    _cols = cols;
    _matrix = arr;
  }
  
  protected abstract IMatrix getInstance(Number[][] arr)
      throws InvalidSizeException;
  
  protected abstract Number sum(Number a, Number b);
  
  protected abstract Number mult(Number a, Number b);
  
  
  @Override
  final public IMatrix multiply(IMatrix matr) 
      throws MultiplyImposibleException {
    
    if (this.GetColumns() != matr.GetRows()) {
      throw new MultiplyImposibleException("The number of columns"
          + "in the left matrix does not equal to the number of rows in the right one");
    }    
    var newMatr = new Number[this.GetRows()][matr.GetColumns()];        
    try {
      for (int i = 0; i < this.GetRows(); ++i) {
        for (int j = 0; j < matr.GetColumns(); ++j) {          
          newMatr[i][j] = 0;
          for (int k = 0; k < this.GetColumns(); ++k) {
            newMatr[i][j] = sum(newMatr[i][j], mult(this.GetValue(i, k),
                matr.GetValue(k, j)));
          }
        }
      }
      return getInstance(newMatr);
    }
    catch (Exception ex) {
      return null;
    }    
  }

  @Override
  final public int GetRows() {
    return _rows;
  }

  @Override
  final public int GetColumns() {
    return _cols;
  }

  @Override
  public Number GetValue(int row, int col) throws InvalidIndexException{
    if (row < 0 || row >= GetRows() || col < 0 || col >= GetColumns()) {
      throw new InvalidIndexException("Invalid indexes");
    }
    return _matrix[row][col];
  }

}
