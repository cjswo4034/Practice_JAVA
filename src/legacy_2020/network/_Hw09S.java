package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class _Hw09S {
	static long differenceBetweenEpochs = 2208988800L;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] ans = new Date().toString().getBytes();
		byte[] leng = new byte[2];
		leng[0] = (byte)(65535 & 0xff);
		leng[1] = (byte)(65535 >> 8);
		
		//System.out.println(((leng[1] & 0xff) << 8) + (leng[0] & 0xff));
		
		Thread daytime = new Daytime();
		Thread chargen = new Chargen();
		Thread time = new Time();
		
		daytime.start();
		chargen.start();
		time.start();
	}

	public static class Daytime extends Thread {		
		public void run() {
			System.out.println("Daytime 서버 실행");
			try (DatagramSocket sock = new DatagramSocket(13)) {
				while (true) {
					try {
						DatagramPacket receive = new DatagramPacket(new byte[1], 1);
						sock.receive(receive);
						
						byte[] ans = new Date().toString().getBytes();
						
						DatagramPacket response = new DatagramPacket(
								ans, ans.length,
								receive.getAddress(),
								receive.getPort()
								);
						
						sock.send(response);						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static class Chargen extends Thread {
		public void run() {
			System.out.println("Chargen 서버 실행");
			try (DatagramSocket sock = new DatagramSocket(19)) {
				while (true) {
					try {
						DatagramPacket receive = new DatagramPacket(new byte[1], 1);
						sock.receive(receive);
						
						byte[] ans = new byte[98 - 33];
						for(int i = 33 ; i < 98 ; i++) {
							ans[i - 33] = (byte)i;
						}
						
						DatagramPacket response = new DatagramPacket(
								ans, ans.length,
								receive.getAddress(),
								receive.getPort()
								);
						
						sock.send(response);						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static class Time extends Thread {
		public void run() {
			System.out.println("Time 서버 실행");
			try (DatagramSocket sock = new DatagramSocket(37)) {
				while (true) {
					try {
						DatagramPacket receive = new DatagramPacket(new byte[1], 1);
						sock.receive(receive);
						
						byte[] ans = new byte[4];
						
						long milliSince1970 = new Date().getTime();
						long secondSince1970 = milliSince1970 / 1000;
						long secondSince1900 = secondSince1970 + differenceBetweenEpochs;
						
						ans[3] = (byte)((secondSince1900 & 0x00000000FF000000) >> 24);
						ans[2] = (byte)((secondSince1900 & 0x0000000000FF0000) >> 16);
						ans[1] = (byte)((secondSince1900 & 0x000000000000FF00) >> 8);
						ans[0] = (byte)(secondSince1900 & 0x00000000000000FF);
						
						DatagramPacket response = new DatagramPacket(
								ans, ans.length,
								receive.getAddress(),
								receive.getPort()
								);
						
						sock.send(response);						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}