package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class _04 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		InetAddress ia = InetAddress.getByName("localhost"); // DNS O, localhost/127.0.0.1이 나옴
		InetAddress l1 = InetAddress.getLocalHost(); // DNS O
		
		byte[] a = {127,0,0,1};
		InetAddress l2 = InetAddress.getByAddress(a); // DNS X, 호스트가 존재하는지 보장하지 않음
		InetAddress ia2 = InetAddress.getByName("168.131.31.206"); // DNS X, too
		
		System.out.println(ia);
		System.out.println(l1);
		System.out.println(l2);
		System.out.println(ia2);
		
		System.out.println(ia2.getHostName());	// 호스트명을 모를 때만 DNS 호출
		System.out.println(l1.getCanonicalHostName());// DNS 호출 가능하면 매번 호출
		System.out.println(ia2.getHostAddress());
		
		
		InetAddress ibiblio = InetAddress.getByName("today.chonnam.ac.kr");
	    InetAddress helios = InetAddress.getByName("www.jnu.ac.kr");

		System.out.println(ibiblio);
		System.out.println(helios);
		System.out.println(ibiblio.equals(helios));	// equals : 같은 IP주소라면 equal
	}
}