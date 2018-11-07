/**
 * 
 */
package matrixoperations;

/**
 * @author Oleg
 *
 */
public class Serializer {

  static private String lineSep = System.getProperty("line.separator");
  
  public String getStringRepresentation(IMatrix matr) {
    var strBuff = new StringBuffer();
    strBuff.append(matr.GetRows() + " " + matr.GetColumns() + lineSep);
    try {
      for (int i = 0; i < matr.GetRows(); ++i) {
        for (int j = 0; j < matr.GetColumns(); ++j) {
          strBuff.append(matr.GetValue(i, j).toString() + " ");
        }
        strBuff.append(lineSep);
      }
      return strBuff.toString();
    }
    catch (InvalidIndexException ex) {
      return null;      
    }
  }
}
