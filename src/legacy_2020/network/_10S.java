package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class _10S {
	static long differenceBetweenEpochs = 2208988800L;

	public static void main(String[] args) throws Exception {
		/* DatagramPacket */
		// setAddress(InetAddress addr)
		// setAddress(SocketAddress addr)
		// setData(byte[] data, offset, size)
		
		/* DatagramSocket */
		// DatagramSocket(int port)
		// DatagramSocket(int port, InetAddress addr)
		// DatagramSocket(SocketAddress addr)
		
		/* 생성 실패 */
		// 지정된 포트가 이미 사용 중일 때 
		// 루트 권한이 없는 자가 1024번 이하의 포트를 사용하려고 할 때 
		// 제시된 주소가 실제 존재하지 않는 네트워크 인터페이스 주소일 때

		//udp(13);
		pork(13);
	}

	public static void pork(int port) {
		try (DatagramSocket sock = new DatagramSocket(13)) {
			while (true) {
				try {
					DatagramPacket receive = new DatagramPacket(new byte[1], 1);
					sock.receive(receive);
					System.out.println("receive");
					
					byte[] ans = new Date().toString().getBytes();
					byte[] leng = new byte[2];
					leng[0] = (byte)(ans.length & 0xff);
					leng[1] = (byte)(ans.length >> 8);
					
					DatagramPacket response = new DatagramPacket(
							leng, 2,
							receive.getAddress(),
							receive.getPort()
							);
					
					sock.send(response);
					
					response.setLength(ans.length);
					response.setData(ans, 0, ans.length);
					sock.send(response);						
				} catch (Exception e) {

				}
			}
		} catch (Exception e) {

		}
	}
	
	public static void udp(int port) {
		try(DatagramSocket sock = new DatagramSocket(port)){
			while(true) {
				try{
					DatagramPacket request = new DatagramPacket(new byte[1024], 0, 1024);
					sock.receive(request);
					
					String daytime = new Date().toString();
					byte[] date = daytime.getBytes();
					DatagramPacket response = new DatagramPacket(
							date, date.length,
							request.getAddress(),
							request.getPort());
					sock.send(response);
					
					System.out.println("send to " + request.getAddress());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] dayTime() {
		return new Date().toString().getBytes();
	}
	
	public static byte[] charGen() {
		byte[] ret = new byte[98 - 33];
		for(int i = 33; i <= 97 ; i++) {
			ret[i - 33] = (byte)i;
		}
		
		return ret;
	}
	
	public static byte[] time() {
		byte[] ret = new byte[4];
		
		long milliSince1970 = new Date().getTime();
		long secondSince1970 = milliSince1970 / 1000;
		long secondSince1900 = secondSince1970 + differenceBetweenEpochs;
		
		ret[3] = (byte)((secondSince1900 & 0x00000000FF000000) >> 24);
		ret[2] = (byte)((secondSince1900 & 0x0000000000FF0000) >> 16);
		ret[1] = (byte)((secondSince1900 & 0x000000000000FF00) >> 8);
		ret[0] = (byte)(secondSince1900 & 0x00000000000000FF);
		
		return ret;
	}
}