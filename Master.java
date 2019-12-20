import  java.lang.Object;
import java.lang.ProcessBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
public class Master{
	

	public static int slaveCount = 0;
	public static ArrayList<String> slaveList=new ArrayList<String>();
	

	private static Map<String, Integer> wordMap=new HashMap<String, Integer>();

	public static void readWord(String strFile){
		try {
		    File file = new File(strFile);
		    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		    String strLine = null;
		    while(null != (strLine = bufferedReader.readLine())){
		        mapLine(strLine);
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
		                wordMap.put(word,0);
		        }
            }
	    public static void readFileLine(String strFile, int s){
		try {
		    
		    File file0 = new File(strFile);
		    BufferedReader bufferedReader0 = new BufferedReader(new FileReader(file0));
		    String strLine0 = null;
		    double total_row_number=0;
		    while(null != (strLine0 = bufferedReader0.readLine())){
		        total_row_number=total_row_number+1;
		    }
		    
		    
		    File file = new File(strFile);
		    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		    String strLine = null;
		    double file_number = 1;
		    double row_number = 0;
		    //int num=0;
		    FileWriter fw = new FileWriter("S0.txt");
		    while ((strLine = bufferedReader.readLine()) != null) {
		        row_number=row_number+1;
		        fw.append(strLine + "\r\n");
		        if(row_number/total_row_number>=(file_number) /s){
		            fw.close();
		            if (file_number>=s) return;
		            fw = new FileWriter("S"+(int) file_number+".txt");
			    file_number ++ ;
		        }
			
		    }
		    fw.close();
		    
		}
		catch(Exception e){
		    e.printStackTrace();
		}
	    }	

	public static void slaveOn(String fileName) throws IOException {
		try {
		    File file = new File(fileName);
		    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		    String strLine = null; 
		    while(null != (strLine = bufferedReader.readLine())){
		        String[] sla = strLine.split(" ");
			ProcessBuilder pb0 = new ProcessBuilder("nmap",sla[1]);       
			Process process0 = pb0.start();
			process0.waitFor();
			InputStream isI=process0.getInputStream();
			byte[] re=new byte[1024];
			while(isI.read(re)!=-1){
				//System.out.println(new String(re));
				String read=new String(re);
				//String[] slave = read.split(" ");
				int index=read.indexOf("22/tcp");
				if(index!=-1) {
					slaveList.add(strLine);
					slaveCount++;
					break;
				}
			}
			InputStream IsE=process0.getErrorStream();
		 
		    }
		}catch(Exception e){
		    e.printStackTrace();
		}

		ProcessBuilder pb1 = new ProcessBuilder("rm","-rf","maps/");
			Process process1 = pb1.start();
			try {
				process1.waitFor();
				InputStream isI=process1.getInputStream();
				isI.read();
				InputStream IsE=process1.getErrorStream();
			} catch(Exception e) {
					e.printStackTrace();
			}
		
		pb1 = new ProcessBuilder("mkdir","/home/ie/Desktop/maps/");       
			process1 = pb1.start();
			try {
				process1.waitFor();
				InputStream isI=process1.getInputStream();
				isI.read();
				InputStream IsE=process1.getErrorStream();
			} catch(Exception e) {
					e.printStackTrace();
			}

		System.out.println(slaveCount);
		readFileLine("./README.txt", slaveCount);
		readWord("./README.txt");

		for(int ii=0;ii<slaveList.size();ii++){
			String[] slave=slaveList.get(ii).split(" ");
			String name=slave[0];
			String ip=slave[1];
			ProcessBuilder pb = new ProcessBuilder("ssh",name+"@"+ip,"rm","-rf","/tmp/ie/");       
			Process process = pb.start();
			try {
				process.waitFor();
				InputStream isI=process.getInputStream();
				isI.read();
				InputStream IsE=process.getErrorStream();
			} catch(Exception e) {
					e.printStackTrace();
			}

		  	pb = new ProcessBuilder("ssh",name+"@"+ip,"mkdir","/tmp/ie/");       
			process = pb.start();
			try {
				process.waitFor();
				InputStream isI=process.getInputStream();
				isI.read();
				InputStream IsE=process.getErrorStream();
			} catch(Exception e) {
					e.printStackTrace();
			}
			
			pb = new ProcessBuilder("scp","Slave.jar",name+"@"+ip+":/tmp/ie/");       
			process = pb.start();
			try {
				process.waitFor();
				InputStream isI=process.getInputStream();
				isI.read();
				InputStream IsE=process.getErrorStream();
			} catch(Exception e) {
					e.printStackTrace();
			}
			
			pb = new ProcessBuilder("ssh",name+"@"+ip,"mkdir","/tmp/ie/splits/");       
			process = pb.start();
			try {
				process.waitFor();
				InputStream isI=process.getInputStream();
				isI.read();
				InputStream IsE=process.getErrorStream();
			} catch(Exception e) {
					e.printStackTrace();
			}
			
			pb = new ProcessBuilder("scp","S"+ii+".txt",name+"@"+ip+":/tmp/ie/splits/");       
			process = pb.start();
			try {
				process.waitFor();
				InputStream isI=process.getInputStream();
				isI.read();
				InputStream IsE=process.getErrorStream();
			} catch(Exception e) {
					e.printStackTrace();
			}

			pb = new ProcessBuilder("ssh",name+"@"+ip,"mkdir","/tmp/ie/maps/");       
			process = pb.start();
			try {
				process.waitFor();
				InputStream isI=process.getInputStream();
				isI.read();
				InputStream IsE=process.getErrorStream();
			} catch(Exception e) {
					e.printStackTrace();
			}
			
			pb = new ProcessBuilder("ssh",name+"@"+ip,"mkdir","/tmp/ie/reduces/");       
			process = pb.start();
			try {
				process.waitFor();
				InputStream isI=process.getInputStream();
				isI.read();
				InputStream IsE=process.getErrorStream();
				byte[] re=new byte[1024];
				while(isI.read(re)!=-1){
					System.out.println(new String(re));
				}
			} catch(Exception e) {
					e.printStackTrace();
			}
		}
	}
	
	public static void maping(){
		ArrayList<ProcessBuilder> pb=new ArrayList<ProcessBuilder>();	
		ArrayList<Process> p=new ArrayList<Process>();	
		//ProcessBuilder[] pb[slaveCount];
		//Process[] process[slaveCount];
		for(int ii=0;ii<slaveList.size();ii++){
			String[] slave=slaveList.get(ii).split(" ");
			String name=slave[0];
			String ip=slave[1];
			pb.add(new ProcessBuilder("ssh",name+"@"+ip,"/usr/java/jdk1.8.0_231/bin/java","-jar","/tmp/ie/Slave.jar","0","/tmp/ie/splits/S"+ii+".txt"));       
			
			try {
				//process[ii].waitFor();
				p.add(pb.get(ii).start());
				InputStream isI=p.get(ii).getInputStream();
				isI.read();
				InputStream IsE=p.get(ii).getErrorStream();
			} catch(Exception e) {
					e.printStackTrace();
			}
		}
		for(int ii=0;ii<slaveList.size();ii++){
			try {
				p.get(ii).waitFor();
			} catch(Exception e) {
					e.printStackTrace();
			}
		}
		

		pb.clear();
		p.clear();
		for(int ii=0;ii<slaveList.size();ii++){
			String[] slave=slaveList.get(ii).split(" ");
			String name=slave[0];
			String ip=slave[1];
			//System.out.println("asf"+ii);
			pb.add(new ProcessBuilder("ssh",name+"@"+ip,"scp","/tmp/ie/maps/UM"+ii+".txt","ie@192.168.1.199:Desktop/maps/"));       
			
			try {
				p.add(pb.get(ii).start());
				//System.out.println("abc"+ii);
				InputStream isI=p.get(ii).getInputStream();
				isI.read();
				InputStream IsE=p.get(ii).getErrorStream();
			} catch(Exception e) {
					e.printStackTrace();
			}
		}
		for(int ii=0;ii<slaveList.size();ii++){
			try {
				p.get(ii).waitFor();
			} catch(Exception e) {
					e.printStackTrace();
			}
		}
			
	}
	

	public static void shuffling() throws IOException{
		ArrayList<ProcessBuilder> pb=new ArrayList<ProcessBuilder>();	
		ArrayList<Process> p=new ArrayList<Process>();		
		for(int ii=0;ii<slaveList.size();ii++){
			String[] slave=slaveList.get(ii).split(" ");
			String name=slave[0];
			String ip=slave[1];

			for(int jj=0;jj<slaveList.size();jj++){
				pb.add(new ProcessBuilder("scp","maps/UM"+jj+".txt",name+"@"+ip+":/tmp/ie/maps/"));       
				try {
					p.add(pb.get(jj).start());				
					p.get(jj).waitFor();
					InputStream isI=p.get(jj).getInputStream();
					isI.read();
					InputStream IsE=p.get(jj).getErrorStream();
				} catch(Exception e) {
						e.printStackTrace();
				}
			}	
		}
		for(int ii=0;ii<slaveList.size();ii++){
			try {
				p.get(ii).waitFor();
			} catch(Exception e) {
					e.printStackTrace();
			}
		}
		

		pb.clear();
		p.clear();
		

		Set set=wordMap.keySet();
		Object[] arr=set.toArray();
		for(int ii=0;ii<wordMap.size();ii++){
			String[] slave=slaveList.get(ii%slaveList.size()).split(" ");
			String name=slave[0];
			String ip=slave[1];
			wordMap.put(arr[ii].toString(),ii%slaveList.size());
			ArrayList<String> cmd=new ArrayList<String>();
			cmd.add("ssh");
			cmd.add(name+"@"+ip);
			cmd.add("/usr/java/jdk1.8.0_231/bin/java");
			cmd.add("-jar");
			cmd.add("/tmp/ie/Slave.jar");
			cmd.add("1");
			cmd.add(arr[ii].toString());
			cmd.add("/tmp/ie/maps/SM"+ii+".txt");
			for (int jj=0;jj<slaveList.size();jj++){
				
				cmd.add("/tmp/ie/maps/UM"+jj+".txt");
			}
					
			//pb.add(new ProcessBuilder("ssh",name+"@"+ip,"/usr/java/jdk1.8.0_231/bin/java","-jar","/tmp/ie/Slave.jar","1",arr[ii].toString(),cmd));       
			pb.add(new ProcessBuilder(cmd));
			try {
				//process[ii].waitFor();
				p.add(pb.get(ii).start());
				InputStream isI=p.get(ii).getInputStream();
				isI.read();
				InputStream IsE=p.get(ii).getErrorStream();
			} catch(Exception e) {
					e.printStackTrace();
			} 
		}
		for(int ii=0;ii<wordMap.size();ii++){
			try {
				p.get(ii).waitFor();
			} catch(Exception e) {
					e.printStackTrace();
			}
		}
		
		/*
		pb.clear();
		p.clear();
		for(int ii=0;ii<slaveList.size();ii++){
			String[] slave=slaveList.get(ii).split(" ");
			String name=slave[0];
			String ip=slave[1];
			System.out.println("asf"+ii);
			pb.add(new ProcessBuilder("ssh",name+"@"+ip,"scp","/tmp/ie/maps/UM"+ii+".txt","ie@192.168.1.199:Desktop/maps/"));       
			
			try {
				p.add(pb.get(ii).start());
				System.out.println("abc"+ii);
				InputStream isI=p.get(ii).getInputStream();
				isI.read();
				InputStream IsE=p.get(ii).getErrorStream();
			} catch(Exception e) {
					e.printStackTrace();
			}
		}
		for(int ii=0;ii<slaveList.size();ii++){
			try {
				p.get(ii).waitFor();
			} catch(Exception e) {
					e.printStackTrace();
			}
		}*/
			
	}

	public static void main(String[] args) throws IOException {
		try {		
			slaveOn("./a.txt");
			maping();
			shuffling();
		} catch(Exception e){
			e.printStackTrace();
		}
    	}

	
	

}
