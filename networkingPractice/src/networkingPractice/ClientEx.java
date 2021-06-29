package networkingPractice;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientEx {
	public static void main(String[] args) {
		BufferedReader	in		= null;
		BufferedWriter	out		= null;
		Socket			socket	= null;
		Scanner			scanner	= new Scanner(System.in);
		try {
			socket	= new Socket("localhost", 59595);
			in		= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out		= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while (true) {
				System.out.print("보내기>>");
				String outputMessage = scanner.nextLine();
				
				out.write(outputMessage + "\n");
				out.flush();
				if(outputMessage.equals("bye"))
					break;
				
				String inputMessage	= in.readLine();
				System.out.println("서버: " + inputMessage);				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				scanner.close();
				if(socket != null) socket.close();
			} catch (IOException e) {
				System.out.println("서버와 채팅 중 오류가 발생했습니다.");
			}
		}
	}
}
