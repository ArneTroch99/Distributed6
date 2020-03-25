import java.net.*;
import java.io.*;

public class UDPClient {
	
	private DatagramSocket ds = null;
	
	public void createSocket(int port) throws SocketException{
		
		try{
			ds = new DatagramSocket(port); 
		}catch(SocketException e){e.printStackTrace();};
	}
	
	public void sendString(String text, int port, String addr) throws Exception{
	
		//InetAddress ip = InetAddress.getLocalHost();
		InetAddress ip = InetAddress.getByName(addr);
		byte buf[] = null;
		buf = text.getBytes();
		
		DatagramPacket DpSend = new DatagramPacket (buf, buf.length, ip, port);

		ds.send(DpSend);
	
	}
	
	public String listenString() throws Exception{
		
		byte[] receive = new byte[65535];
		DatagramPacket DpReceive = null;

		DpReceive = new DatagramPacket(receive, receive.length);
		
		ds.receive(DpReceive);
		/*while(DpReceive.length == 0){
			ds.receive(DpReceive);
		}*/	
                return data(receive).toString();

	}

	public void receiveFile(String fileName) throws Exception{
	
		byte[] receive = new byte[65535];
		
		DatagramPacket DpReceive = null;
		DpReceive = new DatagramPacket(receive, receive.length);
		ds.receive(DpReceive);
		
		int i = 0;
		for (byte b : receive){
			if (b == 0){
			     break;
			}
			i++;
		}
		FileOutputStream out = new FileOutputStream(fileName);	

		out.write(receive, 0, i);
		out.close();   	 
	}
	private static StringBuilder data(byte[] a) {	 
        	
		if (a == null) 
            		return null; 
        	StringBuilder ret = new StringBuilder(); 
        	int i = 0; 
        	while (a[i] != 0) 
        	{ 
            		ret.append((char) a[i]); 
            		i++; 
        	} 
        	return ret; 
    	}
 	}
