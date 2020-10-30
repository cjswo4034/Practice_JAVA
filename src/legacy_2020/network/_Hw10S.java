package network;

import java.net.*;
import java.io.*;

public class _Hw10S {
	protected DatagramSocket socket;
	static int DEFAULT_PORT = 7;

	public _Hw10S(int port) throws IOException {
		socket = new DatagramSocket(port);
	}

	public void execute() throws IOException {
		while (true) {
			DatagramPacket packet = receive();
			if (Math.random() < .65)
				sendEcho(packet.getAddress(), packet.getPort(), packet.getData(), packet.getLength());
			else
				System.out.println("discarded");
		}
	}

	protected DatagramPacket receive() throws IOException {
		byte buffer[] = new byte[65535];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		socket.receive(packet);
		System.out.println("Received " + packet.getLength() + " bytes.");
		return packet;
	}

	protected void sendEcho(InetAddress address, int port, byte data[], int length) throws IOException {
		DatagramPacket packet = new DatagramPacket(data, length, address, port);
		socket.send(packet);
		System.out.println("Sent response.");
	}

	public static void main(String args[]) throws IOException {
		_Hw10S echo = new _Hw10S(DEFAULT_PORT);
		echo.execute();
	}
}