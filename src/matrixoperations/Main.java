
package matrixoperations;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 */
public class Main {

  static private final int INVALID_FORMAT = 1;
  static private final int MULT_IMPOSSIBLE = 2;
  static private final int INVERSE_IMPOSSIBLE = 3;
  static private final int FILE_ERROR = 4;
  static private final int UNKNOWN = 5;
  static private final int INVALID_ARGS = 6;
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    
    int code;
    if (args.length != 2) {
      System.out.println("Invalid arguments");
      code = INVALID_ARGS;
    }
    else {
      code =  showMenu(args[0], args[1]);
    }
    System.out.println("Exit code: " + code);
  }
  
  static private int showMenu(String sourceFile, String destFile) {
    try {
      var scanner = new Scanner(System.in);
      var files = new FileOperations();
      var ser = new Serializer();      
      var des = new Deserializer();
      int ans = 0;
      for (;;) {
        do {
          System.out.println("Choose operation:");
          System.out.println("1 - multiplying");
          System.out.println("2 - inverting");
          System.out.println("0 - exit");
          System.out.print(">> ");
          if (!scanner.hasNextInt()) {
            scanner.nextLine();
            continue;
          }
          ans = scanner.nextInt();
        } while ((ans < 0) || (ans > 2));
        
        switch (ans) {
          case 0:
            return 0;
            
          case 1:
            var srtMatrs = files.readFileContent(sourceFile);
            var matr1 = des.getMatrix(srtMatrs[0]);
            var matr2 = des.getMatrix(srtMatrs[1]);
            var matrMult = matr1.multiply(matr2);
            var strMatrMult = ser.getStringRepresentation(matrMult);
            files.writeFileContent(destFile, strMatrMult);
            return 0;
            
          case 2:
            var matrs = files.readFileContent(sourceFile);
            var m = des.getMatrix(matrs[0]);
            var inverted = m.invertMatrix();
            var strInverted = ser.getStringRepresentation(inverted);
            files.writeFileContent(destFile, strInverted);
            return 0;
        }
      }      
    }
    catch (InvalidFormatException ex) {
      System.out.println(ex.getMessage());
      return INVALID_FORMAT;
    }
    catch (MultiplyImposibleException ex) {
      System.out.println(ex.getMessage());
      return MULT_IMPOSSIBLE;
    }
    catch (InvertingImpossibleException ex) {
      System.out.println(ex.getMessage());
      return INVERSE_IMPOSSIBLE;
    }
    catch (IOException ex) {
      System.out.println(ex.getMessage());
      return FILE_ERROR;
    }
    catch (Exception ex) {
      System.out.println(ex.getMessage());
      return UNKNOWN;
    }
    //return 0;
  }

}
