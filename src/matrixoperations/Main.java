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
      
      var ser = new Serializer();      
      var des = new Deserializer();
      
      var m2 = new FloatMatrix(new Float[][] {
        {-7f, 5f},
        {2f, -1f},
        {4f, 3f}
      });
      var m3 = m1.multiply(m2);
      
      var f = new FileOperations();
      f.writeFileContent("test.txt", 
          ser.getStringRepresentation(m1),
          ser.getStringRepresentation(m2),
          ser.getStringRepresentation(m3));
      
      /*f.writeFileContent("test.txt", new String[] {
          "str1", "str2", "str3", "\r\n", "str4"
      });*/
      var s = f.readFileContent("test.txt");
      des.getMatrix(s[0]);
      /*for (var st : s)
        System.out.println(st);
        */
    } 
    catch (Exception e) {
      
      e.printStackTrace();
    }
    
  }

}
