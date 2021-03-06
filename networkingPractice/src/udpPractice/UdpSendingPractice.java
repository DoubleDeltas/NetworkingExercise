package udpPractice;

import java.io.*;
import java.net.*;
import java.util.*;

public class UdpSendingPractice {
	
	
	public static void main(String[] args) {
		final 	String	ADDRESS		= "192.168.200.182";
		final	int		PORT		= 9;
		final	String	MACADDR		= "B4:2E:99:EF:97:6C";
		final	int		MAGIC_PACKET_LEN	= 102;
		
		DatagramSocket	udpSocket	= null;
		InetAddress		ipAddress	= null;
		try {
			ipAddress	= InetAddress.getByName(ADDRESS);
			udpSocket	= new DatagramSocket(PORT);
			
			String[]	words		= MACADDR.split(":");
			byte[]		macAddress 	= new byte[6];
			for (int i = 0; i < 6; i++)
				macAddress[i] = (byte)Integer.parseInt(words[i], 16);
			
			byte[]		buffer = new byte[MAGIC_PACKET_LEN];
			for (int i = 0; i < 6; i++)
				buffer[i] = (byte)0xFF;
			for (int i = 6; i < MAGIC_PACKET_LEN; i += 6)
				for (int j = 0; j < 6; j++)	buffer[i+j] = macAddress[j];
			
			DatagramPacket	packet
				= new DatagramPacket(buffer, MAGIC_PACKET_LEN, ipAddress, PORT);
			udpSocket.send(packet);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			udpSocket.close();
		}
	}
}
