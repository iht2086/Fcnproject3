
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver extends Thread{
    
    public static void main(String args[]){
        Receiver r = new Receiver();
        r.start();
    }
    public void run(){
        while(true){
            try{
                DatagramSocket ds = new DatagramSocket(8585);
                byte[] b = new byte[1024];
                DatagramPacket dp = new DatagramPacket(b, b.length);
                ds.receive(dp);
                byte[] data = dp.getData();
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		Packet p = (Packet)is.readObject();
               	

            }catch(Exception e){
                
                
                System.out.println("receiver: "+e.getMessage());
            }
	}
        
    }
}

