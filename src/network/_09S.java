package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;

public class _09S {
	static long differenceBetweenEpochs = 2208988800L;

	public static void main(String[] args) throws Exception {
		http(8080);
	}
	
	public static void http(int port) {
		try(ServerSocket server = new ServerSocket(port)){
			while(true) {
				try(Socket sock = server.accept()) {
					BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
					BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
					
					String tmp;
					while((tmp = br.readLine()) != null ) {
						if(tmp.equals("")) break;
						System.out.println(tmp);
					}
					tmp = null;
					
					StringBuilder content = new StringBuilder();
					System.out.print("보낼 파일?");
					BufferedReader f = new BufferedReader(new InputStreamReader(new FileInputStream(input.readLine())));
					while((tmp = f.readLine()) != null) {
						content.append(tmp);
					}
					
					String header = "HTTP/1.1 200 OK\r\n" +
							 "Content-length: " + content.toString().getBytes().length + "\r\n" + 
							 "Content-type: " + "text/html;" +
							 "charset=" + "utf-8" +
							 "\r\n\r\n";

					bw.write(header);
					bw.write(content.toString());
					bw.flush();
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void scanner() {
		try{
			ServerSocket sock = new ServerSocket(0);	// 0 : 바인드된 포트 중 랜덤한 포트
			System.out.println("server runs on port " + sock.getLocalPort());
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		for(int port = 1 ; port < 65536 ; port++) {
			try{
				ServerSocket sock = new ServerSocket();
				SocketAddress add = new InetSocketAddress(port);
				sock.bind(add);
			} catch(IOException e) {
				System.out.println("there is a socket on port " + port);
			}
		}
	}
	
	public static void dayTime(int port) {
		try(ServerSocket server = new ServerSocket(port)){
			while(true) {
				try {
					Socket sock = server.accept();
					Thread task = new DaytimeThread(sock);
					task.start();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void echo(int port) {
		try(ServerSocket server = new ServerSocket(port)){
			while(true) {
				try(Socket sock = server.accept()) {
					BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
					BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
					
					while(true) {
						String ans = br.readLine();
						if(ans == null) {
							System.out.println("종료함");
							break;
						}
						System.out.println("클라이언트 : " + ans);
						bw.write(ans + "\r\n");
						bw.flush();
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static class DaytimeThread extends Thread {
		private Socket connection;

		DaytimeThread(Socket connection) {
			this.connection = connection;
		}

		public void run() {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				
				Date now = new Date();
				
				bw.write(now.toString() + "\r\n");
				bw.flush();
			} catch (IOException ex) {
				System.err.println("???" + ex);
			} finally {
				try {
					connection.close();
				} catch (IOException e) {
					// ignore;
				}
			}
		}
	}
}