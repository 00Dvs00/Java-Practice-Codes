import pyshark

def analyze_pcap(file_path):
    try:
        capture = pyshark.FileCapture(file_path)

        print("Analyzing packets...\n")
        for packet in capture:
            if 'TCP' in packet:
                if packet.tcp.srcport == '23' or packet.tcp.dstport == '23':
                    print("Packet Details:")
                    print(f"Source: {packet.ip.src}")
                    print(f"Destination: {packet.ip.dst}")
                    print(f"Source Port: {packet.tcp.srcport}")
                    print(f"Destination Port: {packet.tcp.dstport}")
                    
                    if hasattr(packet.tcp, 'payload'):
                        payload = packet.tcp.payload
                        try:
                            text = bytes.fromhex(payload.replace(':', '')).decode('utf-8')
                            print(f"Payload: {text}")
                        except Exception as e:
                            print(f"Unable to decode payload: {e}")
                    print("-" * 50)
        capture.close()
    except Exception as e:
        print(f"Error analyzing the pcap file: {e}")

pcap_file = "telnet_traffic.pcap"

analyze_pcap(pcap_file)