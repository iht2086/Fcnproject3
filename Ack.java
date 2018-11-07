
public class Ack {
    
    long expected, received;

    public Ack() {
    }

    public Ack(long expected, long received) {
        this.expected = expected;
        this.received = received;
    }

    public long getExpected() {
        return expected;
    }

    public long getReceived() {
        return received;
    }

    public void setExpected(long expected) {
        this.expected = expected;
    }

    public void setReceived(long received) {
        this.received = received;
    }
    
}

