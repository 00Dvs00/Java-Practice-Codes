import org.pcap4j.core.*;
import org.pcap4j.packet.*;

public class AnalyzePcap {
    public static void main(String[] args) throws PcapNativeException, NotOpenException {
        String pcapFile = "telnet_traffic.pcap";
        PcapHandle handle = Pcaps.openOffline(pcapFile);

        Packet packet;
        while ((packet = handle.getNextPacket()) != null) {
            System.out.println(packet); // Print packet details
            if (packet.contains(TcpPacket.class)) {
                TcpPacket tcpPacket = packet.get(TcpPacket.class);
                byte[] payload = tcpPacket.getPayload().getRawData();
                if (payload != null) {
                    System.out.println("Payload: " + new String(payload)); // Extract plain text
                }
            }
        }

        handle.close();
    }
}