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

public class Slave{
    private static Map<String, Integer> wordMap=new HashMap<String, Integer>();
    private static ArrayList<String> wordList=new ArrayList<String>();
    private static int linecount =0;

    public static void readFileLine(String strFile){
        try {
            File file = new File(strFile);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String strLine = null;
            while(null != (strLine = bufferedReader.readLine())){
                mapLine(strLine);
                linecount++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void mapLine(String line){
		line=line.replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5\'-]+"," ");
		line = line.replaceAll("^$", " ");
        	String[] command = line.split(" ");
                for(int i=0;i<command.length;i++){
                        String word=command[i];
                        wordList.add(word);
                }
    }



    public static void DOWriteTxt(String file, String txt) {
      try {
       FileOutputStream os = new FileOutputStream(new File(file), true);
       os.write((txt + "\n").getBytes());
      } catch (Exception e) {
       e.printStackTrace();
      }
    }
    
    public static void mapping(String str){
        int i = str.lastIndexOf("S");
        String num=str.substring(i+1,str.length()-4);
        readFileLine(str);
        for(int j =0;j<wordList.size();j++){
                String word=wordList.get(j);
                DOWriteTxt("/tmp/ie/maps/UM"+ num+".txt", word+" 1");
        }
        //"./UM"+ num+".txt"
    }

    public static void shuffling(String[] arg){
        int len=arg.length;
        String word=arg[1];
        String txt=arg[2];
        for(int i=3;i<=len-1;i++){
            readFileLine(arg[i]);
        }
        for(int j=0;j<wordList.size();j++){
            if (word.equals(wordList.get(j))){
                DOWriteTxt(txt, word+" 1");
            }
        }

    }

    public static void reducing(String[] arg){
        String word=arg[1];
        String file=arg[2];
        String txt=arg[3];
        readFileLine(file);
        DOWriteTxt(txt, word+" "+linecount);
    }
    public static void main(String[] arg)throws IOException{
	 try{        
		if (arg[0].equals("0")){
		    mapping(arg[1]);
		    System.out.println("Map over");
		 }
		 if (arg[0].equals("1")){
		    shuffling(arg);
		    System.out.println("Shuffle over");
		 }
		 if (arg[0].equals("2")){
		    reducing(arg);
		    System.out.println("Reduce over");
		 }
         }catch(Exception e){
		e.printStackTrace();
		System.out.println("error");
	}
    }
}

