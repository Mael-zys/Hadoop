import java.util.Map;
import java.util.Arrays;
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

public class Base{
    private static Map<String, Integer> wordMap=new HashMap<String, Integer>();


    public static void readFileLine(String strFile){
        try {
            File file = new File(strFile);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String strLine = null;
            //int lineCount = 1;
            while(null != (strLine = bufferedReader.readLine())){
                mapLine(strLine);
				/* System.out.println(strLine);
				String[] command = strLine.split(" ");
				System.out.println("line "+command[0]);  */
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void mapLine(String line){
		//line=line.replaceAll("\\pP","");
		line=line.replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5\'-]+"," ");
//		line = line.replaceAll("[,.;?!:()]", "");
		line = line.replaceAll("^$", " ");
//		line = line.replaceAll("[\t]", " ");
//		line = line.replaceAll("[\r]", " ");
//		line = line.replaceAll("[\n]", " ");
        String[] command = line.split(" ");
		//command=command.split(" ");
		//this.wordMap=new HashMap<String, Integer>();
                for(int i=0;i<command.length;i++){
                        String word=command[i];
                        //System.out.println("word : "+word);
                        if(!wordMap.containsKey(word))
                            wordMap.put(word,1);
                        else {
                            int nombre=wordMap.get(word);
                            nombre++;
                            wordMap.put(word,nombre);
                        }
                }
    }

    public static void printUsers() {
		for (String key : wordMap.keySet()) {
			System.out.println(key+" "+wordMap.get(key));
			//System.out.println("Value = " + wordMap.get(key));
		}
	}

    public static void sort(){
        Set set=wordMap.keySet();
        Object[] arr=set.toArray();
        Arrays.sort(arr);
        int length=arr.length;
        for(int i=0;i<length;i++){
            for(int j=0;j<length-1-i;j++)
            {
                if(wordMap.get(arr[j])<wordMap.get(arr[j+1]))
                {
                    Object temp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        for (Object key:arr) {
			System.out.println(key+" "+wordMap.get(key));
		}
    }

    public static void main(String[] arg){
         double t0=System.currentTimeMillis();
         readFileLine("./Jane Eyre.txt");
         sort();
         double t1=System.currentTimeMillis();
         System.out.println("total time: "+(t1-t0)/1000+"s");
         System.out.println(wordMap.size());
         //printUsers();
    }




}

