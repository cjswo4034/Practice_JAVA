package network;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class _08C {
	public static long differenceBetweenEpochs = 2208988800L;

	public static void main(String[] args) throws Exception {
		// Socket(String host, int port)
		// Socket(InetAddress host, int port)
		// Socket(String host, int port, InetAddress localAddress, int localPort)

		// 입/출력 스트림 중 하나가 닫혔을 때
		// 해당 프로그램이 종료했을 때
		// 가비지 컬렉터에 의해 Socket 객체가 처리되어 소켓 객체가 소멸될 때
		// Socket이 닫힌 상태에서도 getInetAddress(), getPort, getLocalAddress, getLocalPort()는 사용가능

		//Daytime(13);
		time(13);
		//scanner("localhost");
	}
	
	public static void Daytime(int port) {
		try(Socket sock = new Socket("127.0.0.1", port)){
			InputStream in = sock.getInputStream();
			int c;
			while((c = in.read()) != -1) {
				System.out.write((char)c);
			}
			
			System.out.println(sock.isConnected());	// 연결된 적이 있는가
			System.out.println(sock.isClosed());	// 열리지 않은 경우에도 false
			System.out.println(sock.isBound());		// local 포트에 바인드 된 적이 있는가
			System.out.println(!sock.isClosed() && sock.isConnected());
		} catch(Exception e) {
			
		}
	}
	
	public static void time(int port) {
		Socket sock = new Socket();
		SocketAddress add = new InetSocketAddress("127.0.0.1", port);
		try{
			sock.connect(add);
			System.out.println(sock.getLocalPort());
			System.out.println(sock.getLocalAddress());
			InputStream in = sock.getInputStream();
			
			long secondsSince1900 = 0;
			for(int i = 0 ; i < 4; i++) {
				secondsSince1900 = (secondsSince1900 << 8) | in.read();
			}
			
			long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;
			secondsSince1970 = secondsSince1970 * 1000;
			
			Date day = new Date(secondsSince1970);
			
			System.out.println(day);
		} catch(Exception e) {
			
		}
	}
	
	public static void scanner(String host) {
		for(int i = 153 ; i < 1024; i++) {
			try(Socket sock = new Socket(host, i)){
				System.out.println("There is a server on port " + i + " of " + host);
				sock.close();
			} catch(UnknownHostException e) {
				System.err.println(e);
				break;
			} catch(Exception e) {
				System.err.println(e);
			}
		}
	}
}