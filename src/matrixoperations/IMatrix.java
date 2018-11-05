
package matrixoperations;

/**
 *
 */
public interface IMatrix {
  
  /**
   * Multiplies the current matrix with a given one
   * 
   * @param matr
   * @return new matrix
   */
  IMatrix multiply(IMatrix matr) throws MultiplyImposibleException;
  
  int GetRows();
  
  int GetColumns();
  
  Number GetValue(int row, int col) throws InvalidIndexException;
}
