
import java.io.Serializable;

public class Packet implements Serializable{
    Header header;
    byte[] data;
    public Packet(){
        
    }
    public Packet(Header header, byte[] data){
        this.header = header;
        this.data = data;
    }

    public Header getHeader() {
        return header;
    }

    public byte[] getData() {
        return data;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
}

