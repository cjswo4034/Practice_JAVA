package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class _TCP_S {
	static ArrayList<User> userlist;
	static final int port = 20001;
	
	public static void main(String[] args) {
		userlist = new ArrayList<>();
		
		try(ServerSocket sock = new ServerSocket(port)) {
			while(true) {
				try {
					Socket conn = sock.accept();
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					
					String alias = br.readLine();
					
					Receiver receiver = new Receiver(userlist, br, alias);
					userlist.add(new User(conn.getInetAddress(), alias , conn.getPort(), bw));
					
					System.out.println(conn.getInetAddress().getHostAddress() + "(port : " + (conn.getPort()) + ")"
							+ "에서 접속 (현재 접속인원 : " + userlist.size() + ")");
					
					receiver.start();					
				} catch(Exception e) {
				}
			}
		} catch(Exception e) {
		}
	}
	
	public static class Receiver extends Thread{
		ArrayList<User> userlist;
		BufferedReader br;
		String alias;
		
		Receiver(ArrayList<User> userlist, BufferedReader br, String alias) throws Exception{
			this.userlist = userlist;
			this.br = br;
			this.alias = alias;
			
			sendMsg("[" + alias + "]님이 참여하였습니다. \r\n");
		}
		
		public void run() {
			try {
				while(true) {
					String msg = br.readLine();
					if(msg.equals("exit")) {
						msg = "[" + alias + "]님이 퇴장하였습니다. "	// 퇴장 메세지 전송
								+ "(현재 채팅 참여인원 : " + (userlist.size() - 1) + ")";
						
						for(int i = 0; i < userlist.size() ; i++) {	// list에서 지우기
							if(userlist.get(i).alias.equals(alias)) {
								userlist.remove(i);
								break;
							}
						}
					}
					sendMsg(msg);	// 메세지 전송
				}
			} catch(Exception e) {
				
			}
		}
		
		void sendMsg(String msg) {
			try {
				for(int i = 0 ; i < userlist.size() ; i++) {
					User user = userlist.get(i);
					user.user_out.write(msg + "\r\n");
					user.user_out.flush();
				}
			} catch(Exception e) {
				
			}
		}
	}
	
	public static class User{
		public InetAddress host;
		public String alias;
		public int port;
		public BufferedWriter user_out;
		
		User(InetAddress host, String alias, int port, BufferedWriter user_out){
			this.host = host;
			this.alias = alias;
			this.port = port;
			this.user_out = user_out;
		}
	}
}