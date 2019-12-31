import java.util.Map;
import java.util.Arrays;
import java.util.*;
import java.util.Set;
import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.io.File;
import java.io.FileOutputStream;


public class repeat{
    public static void DOWriteTxt( String txt, FileOutputStream os) {
      try {

       os.write((txt + "\n").getBytes());


      } catch (Exception e) {
       e.printStackTrace();
      }
    }



        public static void Read_Print(String strFile,String txt){
            try {
                File file = new File(strFile);
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String strLine = null;
                FileOutputStream os = new FileOutputStream(new File(txt), true);
                while(null != (strLine = bufferedReader.readLine())){
                    DOWriteTxt(strLine,os);
                }
                os.close();
            }catch(Exception e){
                e.printStackTrace();
            }
	    }
    public static void main(String[] arg)throws IOException{
	 try{
        for(int j=0;j<1000;j++){
            for (int i =0;i<1000;i++){
            Read_Print(arg[0],arg[1]);
        }
        }
         }catch(Exception e){
		e.printStackTrace();
		System.out.println("error");
	}
    }
    }


