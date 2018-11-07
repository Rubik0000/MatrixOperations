/**
 * 
 */
package matrixoperations;

import java.util.regex.*;

/**
 * @author Oleg
 *
 */
public class Deserializer implements IStrUtils {

  public IMatrix getMatrix(String str) 
      throws InvalidFormatException {
    
    var lines = str.trim().split(LINE_SEP);
    var pattern = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
    var matcher = pattern.matcher(lines[0]);
    int rows;
    int cols;
    if (matcher.find()) {
      rows = Integer.parseInt(matcher.group(1));
      cols = Integer.parseInt(matcher.group(2));
    }
    else {
      throw new InvalidFormatException("Invalid format");
    }
    //if (Pattern.matches("\\d+", lines[1])) {
      var matr = new Float[rows][cols];
      pattern = Pattern.compile("(-?\\d+\\s+|-?\\d+$|-?\\d+\\.\\d+)");      
      for (int i = 0; i < rows; ++i) {
        matcher = pattern.matcher(lines[i + 1]);
        for (int j = 0; j < cols; ++j) {
          if (matcher.find()) {
            matr[i][j] = Float.parseFloat(matcher.group(1));
          }
          else {
            throw new InvalidFormatException("Invalid format");
          }
        }                
      }
      try {
        return new FloatMatrix(matr);
      } 
      catch (InvalidSizeException e) {        
        e.printStackTrace();
      }
    //}            
    return null;
  }
}
