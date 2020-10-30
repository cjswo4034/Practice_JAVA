package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class _09C {
	public static long differenceBetweenEpochs = 2208988800L;

	public static void main(String[] args) throws Exception {
		echo(13);
	}

	public static void dayTime(int port) {
		try (Socket sock = new Socket("127.0.0.1", port)) {
			BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

			System.out.println(br.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void echo(int port) {
		try (Socket sock = new Socket("127.0.0.1", port)) {
			BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.print("서버로 보낼 내용 : ");
				String ans = input.readLine();
				if (ans.equals(".")) {
					break;
				}

				bw.write(ans + "\r\n");
				bw.flush();
				
				System.out.println("답신 : " + br.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
