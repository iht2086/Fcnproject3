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

		Thread t3 = new Thread(new Runnable(){
			public void run(){
				th.send1();
			}
		});

		Thread t4 = new Thread(new Runnable(){
			public void run(){
				th.send2();
			}
		});
	}

	public class thread_class{
		
		public void receive1(){
			DatagramSocket send_socket = new DatagramSocket();
			DatagramSocket receive_socket = new DatagramSocket(8585);
			byte[] b = new byte[1024];
			DatagramPacket dp = new DatagramPacket(b, b.length);
			
			while(true){
				receive_socket.receive(dp);
				ByteArrayInputStream in = new ByteArrayInputStream(data);
        	                ObjectInputStream is = new ObjectInputStream(in);
	                        Packet p = (Packet)is.readObject();
				Header h = (Header) p.header;
				String Destination = h.getDestination();
					
			}
		}

		public void receive2(){
	
		}

		public void send1(){

		}

		public void send2(){

		}

	}
}
