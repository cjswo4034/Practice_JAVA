package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class _13S {
	public static void main(String[] args) throws Exception {
		multi("224.0.0.1", 20001);
	}
	
	public static void multi(String addr, int port) {
		try {
			InetAddress add = InetAddress.getByName(addr);
			
			MulticastSocket sock = new MulticastSocket(port);
			sock.joinGroup(add);
			
			while(true) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String tmp = br.readLine();
				if(tmp.equals("exit")) break;
				
				byte[] msg = tmp.getBytes();
				
				DatagramPacket pack = new DatagramPacket(msg, msg.length, add, port);
				sock.send(pack);
			}
			
			sock.leaveGroup(add);			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}