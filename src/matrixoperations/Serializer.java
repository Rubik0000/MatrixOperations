
package matrixoperations;

/**
 * @author Oleg
 *
 */
public class Serializer implements IStrUtils {
  
  public String getStringRepresentation(IMatrix matr) {
    var strBuff = new StringBuffer();
    strBuff.append(matr.getRows() + " " + matr.getColumns() + LINE_SEP);
    try {
      for (int i = 0; i < matr.getRows(); ++i) {
        for (int j = 0; j < matr.getColumns(); ++j) {
          strBuff.append(matr.getValue(i, j).toString() + " ");
        }
        strBuff.append(LINE_SEP);
      }
      return strBuff.toString();
    }
    catch (InvalidIndexException ex) {
      return null;      
    }
  }
}
