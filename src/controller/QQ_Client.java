package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Properties;

public class QQ_Client {
	private DatagramSocket sock;
	private SocketChannel channel;
	private BufferedReader in;
	private PrintWriter out;
	private InetAddress IPAddress;
	private byte[] receiveData;
	private byte[] sendData;

	public QQ_Client(Properties p) throws UnknownHostException, IOException {
		this.setup();
	}

	private void setup() throws UnknownHostException, IOException {
		// set's up connection with qq
		sock = new DatagramSocket();
		sendData = new byte[1024];
		receiveData = new byte[1024];
		String str_ip="192.168.4.1";
		IPAddress = InetAddress.getByName(str_ip);
	    
		
	
		

	}

	/**
	 * 
	 * @param speeds
	 *            speed of qq rotors from 0 - 1000 [4,17,22,27]: rotor nr's
	 * @throws IOException 
	 *
	 */
	public void send(double[] speeds) throws IOException {
		int[] rotors = { 4, 17, 22, 27 };
		
		
		for (int i = 0; i < 4; i++) {
			
			String sendsel=rotors[i] + ":" + speeds[i];
			
			sendData=sendsel.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress, 9999);
			sock.send(sendPacket);
			
		}
		
	}

	/**
	 * 
	 * @return gyro (x,y,z), accelerometer(x,y,z)
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public double[] read() throws IOException, InterruptedException {
		
		String sendsel="---";
		sendData=sendsel.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress, 9999);
		sock.send(sendPacket);
		
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		
		String readsel=null;
		while(readsel==null){
			sock.receive(receivePacket);
			readsel=new String(receivePacket.getData());
		}
		System.out.println(readsel);
		String[] str_values = readsel.split(":");
		double[] dbl_returnsel = new double[6];
		for (int i = 0; i < dbl_returnsel.length; i++) {
			dbl_returnsel[i] = Double.parseDouble(str_values[i]);
		}
		return dbl_returnsel;
	}
}
