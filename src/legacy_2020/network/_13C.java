package network;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class _13C {
	public static void main(String[] args) throws Exception {
		// MulticastSocket()
		// MulticastSocket(int port) 
		// MulticastSocket(SocketAddress bindAddress)
		
		multi("224.0.0.1", 20001);
	}
	
	public static void multi(String host, int port) {
		try {
			MulticastSocket sock = new MulticastSocket(port);
			InetAddress add = InetAddress.getByName(host);
			sock.joinGroup(add);
			
			while(true) {
				DatagramPacket pack = new DatagramPacket(new byte[1024], 0, 1024);
				sock.receive(pack);
				
				System.out.println(new String(pack.getData(), 0, pack.getLength()));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}