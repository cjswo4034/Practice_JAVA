package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class _10C {
	static long differenceBetweenEpochs = 2208988800L;

	public static void main(String[] args) throws Exception {
		// checksum 접근 불가능
		// 최대길이 65535 - (20 + 8). ip헤더, udp헤더 
		// tcp와 동일한 번호의 포트를 사용할 수 있다.
		
		/*	수신용	*/
		// DatagramPacket(byte[] buf, int length)
		// DatagramPacket(byte[] buf, int offset, int length)
		// -> length <= buf - offset : illegalArgumentException
		
		/*	송신용	*/
		// DatagramPacket(byte[] data, int length, InetAddress dest, int port)
		// DatagramPacket(byte[] data, int offset, int length, InetAddress dest, int port)
		// DatagramPacket(byte[] data, int length, SocketAddress dest, int port)
		// DatagramPacket(byte[] data, int offset, int length, SocketAddress dest, int port)
		
		// setData(byte[] data)
		// setData(byte[] data, int offset, int length)

		//udp("localhost", 13);
		pork(13);
	}
	
	public static void pork(int port) {
		try(DatagramSocket sock = new DatagramSocket(0)){
			InetAddress addr = InetAddress.getByName("localhost");
			DatagramPacket pack = new DatagramPacket(new byte[1], 0, 1, addr, port);
			sock.send(pack);
			
			DatagramPacket receive = new DatagramPacket(new byte[2], 0, 2);
			byte[] length = receive.getData();
			int leng = ((length[1] & 0xff) << 2) + length[0] & 0xff;
			System.out.println(leng);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void udp(String host, int port) {
		DatagramSocket sock = null;
		try {
			sock = new DatagramSocket(0);
			sock.setSoTimeout(10000);	// UDP는 사용자에게 예외를 알리지 않는다.
			
			InetAddress addr = InetAddress.getByName(host);
			
			DatagramPacket request = new DatagramPacket(new byte[1], 1, addr, port);
			DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
			
			sock.send(request);
			sock.receive(response);
			
			String result = new String(response.getData(), 0, response.getLength());
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}