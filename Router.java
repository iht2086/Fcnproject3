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

public class Router extends Thread{

	public static void main(String args[]){
		thread_class th = new thread_class();
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				th.receive1();
			}
		});	

		Thread t2 = new Thread(new Runnable(){
			public void run(){
				th.send2();
			}
		});

		t1.start();
		t2.start();
	}

	public static class thread_class{
		
		public void receive1(){
			try{

			DatagramSocket send_socket = new DatagramSocket();
			DatagramSocket receive_socket = new DatagramSocket(8585);
			byte[] b = new byte[10000];
			DatagramPacket dp = new DatagramPacket(b, b.length);
			
			while(true){
				
				receive_socket.receive(dp);
				byte[] data = dp.getData();
				ByteArrayInputStream in = new ByteArrayInputStream(data);
        	                ObjectInputStream is = new ObjectInputStream(in);
	                        Packet p = (Packet)is.readObject();
				Header h = (Header) p.header;
				String Destination = h.getDestination();
			        
				
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(outputStream);
				oos.writeObject(p);
				oos.flush();
				byte[] send_this = outputStream.toByteArray();
				DatagramPacket dp1 = new DatagramPacket(send_this, send_this.length, InetAddress.getByName(Destination), 8585);
				send_socket.send(dp1);
				}
			}catch(Exception e){
				System.out.println("Receive 1: "+e.getMessage());
			}
		}

		public void receive2(){
			try{
			DatagramSocket send_socket = new DatagramSocket();
                        DatagramSocket receive_socket = new DatagramSocket(8586);
                        byte[] b = new byte[10000];
                        DatagramPacket dp = new DatagramPacket(b, b.length);

                        while(true){
                                
				receive_socket.receive(dp);
				byte[] data = dp.getData();
                                ByteArrayInputStream in = new ByteArrayInputStream(data);
                                ObjectInputStream is = new ObjectInputStream(in);
                                Packet p = (Packet)is.readObject();
                                Header h = (Header) p.header;
                                String Destination = h.getDestination();


                                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                                oos.writeObject(p);
                                oos.flush();
                                byte[] send_this = outputStream.toByteArray();
                                DatagramPacket dp1 = new DatagramPacket(send_this, send_this.length, InetAddress.getByName(Destination), 8585);
                                send_socket.send(dp1);
				}
			}catch(Exception e){
				System.out.println("Receive 2: "+e.getMessage());
			}	

		}

		public void send1(){

		}

		public void send2(){

		}

	}
}
