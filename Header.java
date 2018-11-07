import java.io.Serializable;
public class Header implements Serializable{
    
    long sequence_no, checksum;
    String destination;
    public Header() {
    }

    public Header(long sequence_no, long checksum, String destination) {
        this.sequence_no = sequence_no;
        this.checksum = checksum;
	this.destination = destination;
    }

    public void setSequence_no(long sequence_no) {
        this.sequence_no = sequence_no;
    }

    public void setChecksum(long checksum) {
        this.checksum = checksum;
    }

    public long getSequence_no() {
        return sequence_no;
    }

    public long getChecksum() {
        return checksum;
    }

    public void setDestination(String destination){
	this.destination = destination;
    }

    public String getDestination(){
	return destination;
    }
    
}

