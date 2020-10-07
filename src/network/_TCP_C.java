package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class _TCP_C {
	Socket sock;
	Sender sender;
	Receiver receiver;
	
	InetAddress addr;
	String host;
	int port;
	
	BufferedReader server_in, user_in;
	BufferedWriter server_out;
	String alias;
	
	_TCP_C(String host, int port){
		this.host = host;
		this.port = port;
		
		init();
		
		sender = new Sender(server_out, user_in, alias);
		sender.start();
		receiver = new Receiver(server_in, alias);
		receiver.start();
	}
	
	public void init(){		
		try {
			addr = InetAddress.getByName(host);
			sock = new Socket(addr, port);

			server_out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
			server_in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			user_in = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("닉네임 입력 : ");
			alias = user_in.readLine();
			
			server_out.write(alias + "\r\n");
			server_out.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		new _TCP_C("localhost", 20001);
	}
	
	public static class Receiver extends Thread {
		BufferedReader server_in;
		String alias;
		
		Receiver(BufferedReader server_in, String alias){
			this.server_in = server_in;
			this.alias = alias;			
		}
		
		public void run() {
			try {
				while(true) {
					String msg = server_in.readLine();
					
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
				e.printStackTrace();
			}
		}
	}

	public static class Sender extends Thread {
		BufferedWriter server_out;
		BufferedReader user_in;
		String alias;
		
		Sender(BufferedWriter server_out, BufferedReader user_in, String alias){
			this.server_out = server_out;
			this.user_in = user_in;
			this.alias = alias;
		}
		
		public void run() {
			try {
				while(true) {
					System.out.print(">>> ");
					String msg = user_in.readLine();
					server_out.write(alias + " : " + msg + "\r\n");
					server_out.flush();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}