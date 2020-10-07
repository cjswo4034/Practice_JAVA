package network;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class _06 {
	public static void main(String[] args) throws Exception {
		// java.lang.Object를 상속하며 서브 클래싱을 할 수 없는 Final class
		// -> public final class URL extends Object implements Serializable

		// JDBC는 java.sql에서, RMI는 java.rmi 패키지에서 지원한다
		// 이들은 URL로 접근할 수 없다

		try {
			URL u = new URL("http://www.jnu.ac.kr");
			URLConnection conn = u.openConnection();
			InputStream in = u.openStream();
			InputStream in2 = conn.getInputStream();

			int c;
			while ((c = in.read()) != -1)
				System.out.write(c);

			while ((c = in2.read()) != -1)
				System.out.write(c);

			// ==================================================================

			System.out.println("\n=======================================\n");
			System.out.println("The URL is " + u);
			System.out.println("The scheme is " + u.getProtocol());
			System.out.println("The user info is " + u.getUserInfo());

			String host = u.getHost();
			if (host != null) {
				System.out.println("The host is " + host);
			} else {
				System.out.println("The host is null.");
			}

			System.out.println("The port is " + u.getPort());
			System.out.println("The path is " + u.getPath());
			System.out.println("The ref is " + u.getRef());
			System.out.println("The query string is " + u.getQuery());

			// ==================================================================

			System.out.println("\n=======================================\n");
			URL www = new URL("http://www.ibiblio.org/a#1");
			URL ibiblio = new URL("http://ibiblio.org/a");
			if (ibiblio.equals(www)) {
				System.out.println(ibiblio + " is the same as " + www);
			} else {
				System.out.println(ibiblio + " is not the same as " + www);
			}

			if (ibiblio.sameFile(www)) { // #(FI)는 검사하지 않음
				System.out.println(ibiblio + " is the same as " + www);
			} else {
				System.out.println(ibiblio + " is not the same as " + www);
			}

			// ==================================================================

			System.out.println("\n=======================================\n");
			// 사용가능 문자 : 소문자, 대문자, 숫자, - _ . ! ~ * ' ,
			// 특별한 의미를 가진 문자 : / & ? @ # ; $ + = %

			String url = "http://www.google.com/search?";
			url += URLEncoder.encode("h=l", "UTF-8");
			url += "=";
			url += URLEncoder.encode("e&n", "UTF-8");
			url += "&";
			url += URLEncoder.encode("as_ +q", "UTF-8"); // ' '는 +로 인코딩 됨
			System.out.println(url);

			// ==================================================================

			System.out.println("\n=======================================\n");
			
			URL url1 = new URL("http://search.daum.net/search?q=" + "안녕");
			try (InputStream i = new BufferedInputStream(url1.openStream())) {
				InputStreamReader theHTML = new InputStreamReader(i);
				while ((c = theHTML.read()) != -1) {
					System.out.print((char) c);
				}
			} catch(Exception e) {
				System.out.println("Exception");
			}
		} catch (MalformedURLException ex) {
			System.err.println(" ju is not a URL I understand.");
		}

	}
}