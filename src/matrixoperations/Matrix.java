
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
  
  protected abstract Number sub(Number a, Number b);
  
  protected abstract Number mult(Number a, Number b);
  
  protected abstract Number devide(Number a, Number b);
    
  
  
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
  
  private void changeRows(Number[][] matr, int row1, int row2) {
    Number tmp = null;
    for (int i = 0; i < matr[0].length; ++i) {
      tmp = matr[row1][i];
      matr[row1][i] = matr[row2][i];
      matr[row2][i] = tmp;      
    }
  }
  
  @Override
  final public IMatrix reverseMatrix() {
    int m = GetRows();
    int n = GetColumns() * 2;
    var enhancedMatr = new Number[m][n];
    try {
      for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
          if (j < n /2) {
            enhancedMatr[i][j] = GetValue(i, j);
          }
          else if (i == j - n / 2) {
            enhancedMatr[i][j] = 1;
          }
          else {
            enhancedMatr[i][j] = 0;
          }
        }
      }
      
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
          //for (int k = 0; k < i; ++k) {
          //  enhancedMatr[j][k] = sub(enhancedMatr[j][k], mult(tmp, enhancedMatr[i][k]));
          //}
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
      return null;
    } 
    catch (InvalidSizeException e) {
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
  public Number GetValue(int row, int col) throws InvalidIndexException {
    if (row < 0 || row >= GetRows() || col < 0 || col >= GetColumns()) {
      throw new InvalidIndexException("Invalid indexes");
    }
    return _matrix[row][col];
  }

}
