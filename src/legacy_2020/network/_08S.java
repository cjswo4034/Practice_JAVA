package network;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class _08S {
	static long differenceBetweenEpochs = 2208988800L;

	public static void main(String[] args) throws Exception {
		//Daytime(13);
		time(13);
	}

	public static void Daytime(int port) {
		try(ServerSocket sock = new ServerSocket(port)){
			while(true) {
				try(Socket conn = sock.accept()){
					Writer out = new OutputStreamWriter(conn.getOutputStream());
					Date now = new Date();
					out.write(now.toString() + "\r\n");
					out.flush();
					
					conn.shutdownOutput();
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void time(int port) {
		try(ServerSocket sock = new ServerSocket(port)){
			while(true) {
				try(Socket conn = sock.accept()){
					OutputStream out = conn.getOutputStream();
					Date now = new Date();
					
					long msSince1970 = now.getTime();
					long secondsSince1970 = msSince1970 / 1000;
					long secondsSince1900 = secondsSince1970 + differenceBetweenEpochs;
					
					byte[] time = new byte[4];
					time[0] = (byte)((secondsSince1900 & 0x00000000FF000000L) >> 24);
					time[1] = (byte)((secondsSince1900 & 0x0000000000FF0000L) >> 16);
					time[2] = (byte)((secondsSince1900 & 0x000000000000FF00L) >> 8);
					time[3] = (byte)((secondsSince1900 & 0x00000000000000FFL));
					
					out.write(time);
					out.flush();
					
					conn.shutdownOutput();					
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}