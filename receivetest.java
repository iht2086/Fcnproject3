/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author thakk
 */import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

public class receivetest extends Thread{
    
    static long received_no ;
    static long expected_no;
    public static void main(String args[]){
	expected_no = 0;
	
	receivetest r = new receivetest();
	thread_class th = new thread_class();
	Thread t1 = new Thread(new Runnable(){
		public void run(){
			th.receive();
		}	
	});
	
	Thread t2 = new Thread(new Runnable(){
		public void run(){
			th.send();
		}
	});

	try{
		t2.start();
		t1.start();
		//t2.join();
		//t1.join();
	}catch(Exception e){

	}
        
    }

    public static class thread_class{
	Object o = new Object();
	public void receive(){
		synchronized(o){
		try{
            		DatagramSocket ds = new DatagramSocket(8585);
             		byte[] b = new byte[10000];
                    	DatagramPacket dp = new DatagramPacket(b,b.length);

            		while(true){
//                      	byte[] b = new byte[1024];
  //                  	DatagramPacket dp = new DatagramPacket(b,b.length);
                    	ds.receive(dp);
                    	byte[] data = dp.getData();
                    	ByteArrayInputStream in = new ByteArrayInputStream(data);
                    	ObjectInputStream is = new ObjectInputStream(in);
                    	Packet p = (Packet)is.readObject();
			Header header = (Header)p.header;
			received_no = header.sequence_no;
                    	if(expected_no == received_no){
				expected_no++;
			}
			System.out.println(received_no+ " "+new String(p.data) );
			o.notify();
			o.wait();
            		}	

        	}catch(Exception e){
            		System.out.println("class not found "+e.getMessage());
       		}
		}

	}

	public void send(){
		synchronized(o){
		try{
			while(true){
				o.wait();
				
				DatagramSocket ds = new DatagramSocket();
				String to_send = Long.toString(expected_no);
				byte[] b = to_send.getBytes();
				DatagramPacket dp = new DatagramPacket(b, b.length, InetAddress.getByName("129.21.22.196"),9000);
				ds.send(dp);
				o.notify();
			}

		}catch(Exception e){
			System.out.println("In send function: "+e.getMessage());
		}
		}
	}
    }
/*

    public void run(){

	try{
            DatagramSocket ds = new DatagramSocket(8585);
             byte[] b = new byte[10000];
                    DatagramPacket dp = new DatagramPacket(b,b.length);

	    while(true){
//               	byte[] b = new byte[1024];
  //                  DatagramPacket dp = new DatagramPacket(b,b.length);
                    ds.receive(dp);
                    byte[] data = dp.getData();
                    ByteArrayInputStream in = new ByteArrayInputStream(data);
                    ObjectInputStream is = new ObjectInputStream(in);
                    Packet p = (Packet)is.readObject();
                    System.out.println(p.sequence_number+ " "+new String(p.data) );
	    }

        }catch(Exception e){
            System.out.println("class not found "+e.getMessage());
        }

    }*/
}

