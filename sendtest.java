/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thakk
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.util.*;
public class sendtest extends Thread{ 
    public static void main(String args[]){
       
    	sendtest s = new sendtest();
	s.start();
    }

    public void run(){
	try{

	File f = new File("largetext.txt");
	FileInputStream fis = new FileInputStream(f);
	BufferedReader br = new BufferedReader(new FileReader(f));
	int sequence_no = 0;
	int count = 1024;
	String line = br.readLine();
	DatagramSocket ds = new DatagramSocket();
	byte[] to_read = new byte[1024];
	while(count< f.length()){

            try{
		
		fis.read(to_read);
		byte[] send = line.getBytes();
       		long checksum = 0;
       		for(byte cs: to_read){
			checksum+= (int)cs;
		}		
                //byte[]  b = s.getBytes();
                Header h = new Header();
                h.setSequence_no(sequence_no);
		h.setChecksum(checksum/45000);
		h.setDestination("129.21.30.37");
		
		Packet p = new Packet(h,to_read);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                oos.writeObject(p);
                oos.flush();
                byte[] b = outputStream.toByteArray();
                DatagramPacket dp = new DatagramPacket(b,b.length,InetAddress.getByName("129.21.34.80"),8585);
                ds.send(dp);
		line = br.readLine();
		sequence_no++;
		count+=1024;
			
		System.out.println(sequence_no+" "+new String(to_read));
		//Thread.sleep(25);
            }catch(Exception e){
		System.out.println(e.getMessage());
            }
        }

    }
    catch(Exception e){

    }
}
}
