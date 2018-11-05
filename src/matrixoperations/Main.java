/**
 * 
 */
package matrixoperations;

/**
 * @author Oleg
 *
 */
public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      var m1 = new FloatMatrix(new Float[][] {
        {2f, -3f, 1f},
        {5f, 4f, -2f}
      });
      
      var m2 = new FloatMatrix(new Float[][] {
        {-7f, 5f},
        {2f, -1f},
        {4f, 3f}
      });
      var m3 = m1.multiply(m2);
    } 
    catch (Exception e) {
      
      e.printStackTrace();
    }
    
  }

}
