
package matrixoperations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 *
 */
public class FileOperations {
  
  //static private String lineSep = "\r\n";
  static private String lineSep = System.getProperty("line.separator");

  public String[] readFileContent(String fileName) 
      throws IOException {
    
    try (var buffReader = new BufferedReader(
        new FileReader(fileName))){
      
      var result = new ArrayList<String>();
      var strBuff = new StringBuffer();
      String s = null;
      while ((s = buffReader.readLine()) != null) {
        if (s.equals("")) {
          if (strBuff.length() != 0) {
            result.add(strBuff.toString());
            strBuff = new StringBuffer();
          }          
        }
        else {
          strBuff.append(s);
        }
      }// while
      if (strBuff.length() != 0) {
        result.add(strBuff.toString());
      }
      return result.toArray(new String[1]);
    }// try
    catch (IOException ex) {
      throw ex;
    }
  }
  
  public void writeFileContent(String fileName, String ...content) 
      throws IOException {
    try (var buffWriter = new BufferedWriter(
        new OutputStreamWriter(
            new FileOutputStream(fileName)))) {
      
      for (var str : content) {
        buffWriter.write(str);
        //buffWriter.write(lineSep);
        buffWriter.write(lineSep);                
      }
    }
    catch (IOException ex) {
      throw ex;
    }
  }  
}
