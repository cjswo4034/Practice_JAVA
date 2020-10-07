package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class _UDP{
	MulticastSocket sock;
	DatagramPacket pack;
	InetAddress addr;
	int port;
	
	BufferedReader br;	
	String alias;
	
	Sender sender;
	Receiver receiver;
	
	public _UDP() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));

			port = 20001;
			sock = new MulticastSocket(port);
			addr = InetAddress.getByName("224.0.0.1");
			
			init();			

			sender = new Sender(sock, pack, addr, port, alias, this);
			sender.start();
			
			receiver = new Receiver(sock, alias);
			receiver.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() throws Exception{
		System.out.print("닉네임 입력 : ");
		alias = br.readLine();
		
		byte[] msg = ("[" + alias + "]님이 입장하였습니다.").getBytes();
		
		pack = new DatagramPacket(msg, 0, msg.length,
				addr, port);

		sock.joinGroup(addr);
		sock.send(pack);
	}
	
	public void exit() {
		sender.interrupt();
		receiver.interrupt();
		
		sock.close();
	}
	
	public static void main(String[] args) {
		new _UDP();
	}

	public static class Receiver extends Thread {		
		MulticastSocket sock;
		DatagramPacket pack;
		String alias;
		
		Receiver(MulticastSocket sock, String alias){
			this.sock = sock;
			this.alias = alias;
		}
		
		public void run() {
			try {
				while(true) {
					pack = new DatagramPacket(new byte[2048], 2048);
					sock.receive(pack);
					
					String msg = new String(pack.getData(), 0, pack.getLength());
					if(msg.indexOf(" : ") != -1) {
						String alias_tmp = msg.substring(0, msg.indexOf(" : "));
						if(alias_tmp.equals(alias)) {
							msg = "나 : " + msg.substring(msg.indexOf(" : ") + 3);
						}
					}
					
					System.out.println("\b\b\b\b" + msg);
					System.out.print(">>> ");
				}
			} catch (Exception e) {
				System.out.println("\b\b\b\b프로그램이 종료되었습니다.");
			}
		}
	}

	public static class Sender extends Thread {
		MulticastSocket sock;
		DatagramPacket pack;
		InetAddress addr;
		int port;
		
		BufferedReader br;
		String alias;
		
		_UDP client;
		
		Sender(MulticastSocket sock, DatagramPacket pack, InetAddress addr, int port, String alias, _UDP client){
			this.sock = sock;
			this.pack = pack;
			this.addr = addr;
			this.port = port;
			
			this.br = new BufferedReader(new InputStreamReader(System.in));
			this.alias = alias;
			
			this.client = client;
		}
		
		public void run() {
			try {
				while(true) {
					System.out.print(">>> ");
					String msg = br.readLine();
					if(msg.equals("exit")) {
						byte[] msg_b = ("[" + alias + "]님이 퇴장하였습니다.").getBytes();
						pack.setData(msg_b);
						sock.send(pack);
						break;
					}					
					byte[] msg_b = (alias + " : " + msg).getBytes();
					
					pack.setData(msg_b);				
					sock.send(pack);
				}
				
				client.exit();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
