package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class _Hw09C {
	static long differenceBetweenEpochs = 2208988800L;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		pork(13);
		pork(19);
		pork(37);
	}
	
	public static void pork(int port) {
		try(DatagramSocket sock = new DatagramSocket(0)){
			InetAddress addr = InetAddress.getByName("localhost");
			DatagramPacket pack = new DatagramPacket(new byte[1], 0, 1, addr, port);
			sock.send(pack);
			
			DatagramPacket receive = new DatagramPacket(new byte[1024], 0, 1024);
			sock.receive(receive);
			
			System.out.print("receive message" + "(from " + receive.getPort() + ") : ");
			if(receive.getPort() != 37) {
				System.out.println(new String(receive.getData(), 0, receive.getLength()));
			} else {
				byte[] ans = receive.getData();
				
				long secondsSince1900 = 0;
				for(int i = 0 ; i < 4; i++) {
					secondsSince1900 = (secondsSince1900 << 8) | (ans[3 - i] & 0xff);
				}
				
				long secondsSince1970 = (secondsSince1900 - differenceBetweenEpochs) * 1000;
				
				System.out.println(secondsSince1900 + " ===> " + new Date(secondsSince1970));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}