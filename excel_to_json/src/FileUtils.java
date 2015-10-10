import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {

	public static List<String> getFileLines(String path)
	{

        String s;
        List lst = new ArrayList<String>() ;

        BufferedReader bfrd = null;
		try {
			bfrd = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(path),
                    "utf-8"));     
	        while ((s = bfrd.readLine())!= null){
	            lst.add(s);
	        }
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(bfrd != null)
			{
		        try {
					bfrd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
            
       
        return lst;
	}
	

	public static String getFileContent(String path)
	{
		String ret = "";
        String s;

        BufferedReader bfrd = null;
		try {
			bfrd = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(path),
                    "utf-8"));     
	        while ((s = bfrd.readLine())!= null){
	        	ret += s;
	        }
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(bfrd != null)
			{
		        try {
					bfrd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
            
       
        return ret;
	}
	
    public static void writeFile(String filePath, String sets)  {  
    	BufferedWriter writer = null;
  	  try {
  		   File f = new File(filePath);
  		   if (!f.exists()) {
  		    f.createNewFile();
  		   }
  		   OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
  		   writer=new BufferedWriter(write);   
  		   //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePathAndName)));
  		   //PrintWriter writer = new PrintWriter(new FileWriter(filePathAndName));
  		   writer.write(sets);
  		   writer.close();
  		  } catch (Exception e) {
  		   e.printStackTrace();
  		  }finally
			{
				if(writer != null)
				{
			        try {
			        	writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
			} 
  } 
}
