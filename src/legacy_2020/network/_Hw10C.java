package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class _Hw10C {
	public static void main(String[] args) throws Exception{
		DatagramSocket sock = new DatagramSocket(0);
		
		Sender sender = new Sender(7, sock);
		sender.start();
		
		Receiver receiver = new Receiver(sender, sock);
		receiver.start();
	}

	public static class Receiver extends Thread {
		DatagramSocket sock;
		Sender th;
		
		Receiver(Sender th, DatagramSocket sock){
			try {
				this.th = th;
				this.sock = sock;
			} catch(Exception e) {
				e.printStackTrace();
			}		
		}
		
		public void run() {
			try {
				while(true) {
					DatagramPacket pack = new DatagramPacket(new byte[1024], 1024);
					sock.receive(pack);
	
					String ans = new String(pack.getData(), 0, pack.getLength());
					if(!ans.equals("")) {
						System.out.println("Server : " + ans);
						th.interrupt();
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static class Sender extends Thread {
		DatagramSocket sock;
		BufferedReader br;
		int port;
		
		Sender(int port, DatagramSocket sock){
			try {
				this.port = port;
				this.sock = sock;
				br = new BufferedReader(new InputStreamReader(System.in));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			try {
				byte[] input;
				InetAddress addr = InetAddress.getByName("localhost");
				DatagramPacket pack;
				while(true) {
					System.out.print("입력 : ");
					input = br.readLine().getBytes();
					pack = new DatagramPacket(
							input, 0, input.length,
							addr, port 
							);
					sock.send(pack);
					
					try {
						this.sleep(3000);
						System.err.println("Timeout");
						this.sleep(10);
					} catch(InterruptedException e) {
						continue;
					}
				
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}