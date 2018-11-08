
package matrixoperations;


/**
 * An abstract matrix with "Number" type of elements
 */
public abstract class Matrix implements IMatrix {

  /**The number of rows*/
  private int _rows;
  
  /**The number of columns*/
  private int _cols;
  
  /**The values of the matrix*/
  private Number[][] _matrix; 
  
  /**
   * Constructor creates a new matrix from a given 2D array
   * 
   * @param arr
   * @throws InvalidSizeException if the array is not simetric
   */
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
  
  /**
   * Creates a 2D array from a given matrix and
   * enhances it with a identity matrix 
   * 
   * @param matr
   * @return
   * @throws InvalidIndexException
   */
  static private Number[][] getEnhancedMatixInternals(IMatrix matr) 
      throws InvalidIndexException {
    
    int m = matr.getRows();
    int n = matr.getColumns() * 2;
    var enhancedMatr = new Number[m][n];
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (j < n /2) {
          enhancedMatr[i][j] = matr.getValue(i, j);
        }
        else if (i == j - n / 2) {
          enhancedMatr[i][j] = 1;
        }
        else {
          enhancedMatr[i][j] = 0;
        }
      }
    }
    return enhancedMatr;
  }
  
  
  /**
   * Changes two rows in a given array
   * 
   * @param matr where it needs to change 
   * @param row1 the number of the first row
   * @param row2 the number of the second row
   */
  static private void changeRows(Number[][] matr, int row1, int row2) {
    Number tmp = null;
    for (int i = 0; i < matr[0].length; ++i) {
      tmp = matr[row1][i];
      matr[row1][i] = matr[row2][i];
      matr[row2][i] = tmp;      
    }
  }
  
  /**
   * Creates a new instance of a class with the same type
   * as invoked object
   * 
   * @param arr constructor arguments
   * @return new instance
   * @throws InvalidSizeException
   */
  protected abstract IMatrix getInstance(Number[][] arr)
      throws InvalidSizeException;
  
  // Overloaded arithmetic operations for different types
    
  protected abstract Number sum(Number a, Number b);
    
  protected abstract Number sub(Number a, Number b);
  
  protected abstract Number mult(Number a, Number b);
  
  protected abstract Number devide(Number a, Number b);
      
  
  @Override
  final public IMatrix multiply(IMatrix matr) 
      throws MultiplyImposibleException {
    
    if (this.getColumns() != matr.getRows()) {
      throw new MultiplyImposibleException("The number of columns"
          + "in the left matrix does not equal to the number of rows in the right one");
    }    
    var newMatr = new Number[this.getRows()][matr.getColumns()];        
    try {
      for (int i = 0; i < this.getRows(); ++i) {
        for (int j = 0; j < matr.getColumns(); ++j) {          
          newMatr[i][j] = 0;
          for (int k = 0; k < this.getColumns(); ++k) {
            newMatr[i][j] = sum(newMatr[i][j], mult(this.getValue(i, k),
                matr.getValue(k, j)));
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
  final public IMatrix invertMatrix() 
      throws InvertingImpossibleException, MatrixException {       
    if (getRows() != getColumns()) {
      throw new InvertingImpossibleException("Matrix is not square");
    }
    try {
      var enhancedMatr = getEnhancedMatixInternals(this);
      int m = enhancedMatr.length;
      int n = enhancedMatr[0].length;
      
      for (int i = 0; i < m; ++i) {
        if (enhancedMatr[i][i].equals(0)) {
          int row = i + 1;
          while (enhancedMatr[row++][i].equals(0));
          changeRows(enhancedMatr, i, row);
        }
        for (int j = n - 1; j >= i; --j) {
          enhancedMatr[i][j] = devide(enhancedMatr[i][j], enhancedMatr[i][i]);  
        }
        for (int j = 0; j < m; ++j) {
          if (j == i) {
            continue;
          }
          var tmp = enhancedMatr[j][i];
          for (int k = n - 1; k >= i; --k) {
            enhancedMatr[j][k] = sub(enhancedMatr[j][k], mult(tmp, enhancedMatr[i][k]));
          }          
        }
      }
      var revMatr = new Number[m][n / 2];
      for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n / 2; ++j) {
          revMatr[i][j] = enhancedMatr[i][j + n / 2];
        }
      }      
      return this.getInstance(revMatr);
    }
    catch (InvalidIndexException ex) {
      throw new MatrixException("Something goes wrong with internal array");
    } 
    catch (InvalidSizeException e) {
      throw new MatrixException("Something goes wrong with matrix initialization");
    }    
  }

  @Override
  final public int getRows() {
    return _rows;
  }

  @Override
  final public int getColumns() {
    return _cols;
  }

  @Override
  public Number getValue(int row, int col) throws InvalidIndexException {
    if (row < 0 || row >= getRows() || col < 0 || col >= getColumns()) {
      throw new InvalidIndexException("Invalid indexes");
    }
    return _matrix[row][col];
  }

}
