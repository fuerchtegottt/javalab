package de.g3ll3rt.StringOperator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StringOperationFileSystem {
	public static String readFile(String path) throws Exception{
		File file = new File(path);
		StringBuffer content = new StringBuffer();
		BufferedReader reader = null;

		 try {
		  reader = new BufferedReader(new FileReader(file));
		  String s = null;
		  while ((s = reader.readLine()) != null) {
		   content.append(s).append(System.getProperty("line.separator"));
		  }

		 } catch (FileNotFoundException e) { 
			 throw e;
		 } catch (IOException e) {
			 throw e;
		 } finally {
		  try {
		   if (reader != null) {
		    reader.close();
		   }
		  } catch (IOException e) {
			  throw e;
		  }
		 }
		 return content.toString();
	}
	
	public static void writeFile(String file, String path) throws Exception{
        BufferedWriter writer = null;
        try
        {
                writer = new BufferedWriter( new FileWriter( path));
                writer.write( file);
        }
        catch ( IOException e)
        {
        	throw e;
        }
        finally
        {
                try
                {
                        if ( writer != null)
                                writer.close( );
                }
                catch ( IOException e)
                {
                	throw e;
                }
     }
	}
}
