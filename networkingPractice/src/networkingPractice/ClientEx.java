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
				System.out.print("������>>");
				String outputMessage = scanner.nextLine();
				
				out.write(outputMessage + "\n");
				out.flush();
				if(outputMessage.equals("bye"))
					break;
				
				String inputMessage	= in.readLine();
				System.out.println("����: " + inputMessage);				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				scanner.close();
				if(socket != null) socket.close();
			} catch (IOException e) {
				System.out.println("������ ä�� �� ������ �߻��߽��ϴ�.");
			}
		}
	}
}