
package matrixoperations;

/**
 * An interface provides operations of a matrix
 */
public interface IMatrix {
  
  /**
   * Multiplies the current matrix with a given one
   * 
   * @param matr
   * @return new matrix
   */
  IMatrix multiply(IMatrix matr) throws MultiplyImposibleException;
  
  
  /**
   * Returns a new invertible matrix
   * 
   * @return
   * @throws MatrixException
   */
  IMatrix invertMatrix() 
      throws InvertingImpossibleException, MatrixException;
  
  /**
   * Returns the count of rows
   * @return
   */
  int getRows();
  
  /**
   * Returns the count of columns
   * @return
   */
  int getColumns();
  
  /**
   * Returns the matrix value
   * 
   * @param row row index
   * @param col column index
   * @return
   * @throws InvalidIndexException
   */
  Number getValue(int row, int col) throws InvalidIndexException;
  
}
