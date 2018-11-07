
package matrixoperations;

/**
 * @author Oleg
 *
 */
public class Serializer implements IStrUtils {
  
  public String getStringRepresentation(IMatrix matr) {
    var strBuff = new StringBuffer();
    strBuff.append(matr.GetRows() + " " + matr.GetColumns() + LINE_SEP);
    try {
      for (int i = 0; i < matr.GetRows(); ++i) {
        for (int j = 0; j < matr.GetColumns(); ++j) {
          strBuff.append(matr.GetValue(i, j).toString() + " ");
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
