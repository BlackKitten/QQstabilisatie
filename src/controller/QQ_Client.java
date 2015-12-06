package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Properties;

public class QQ_Client {
	private Socket sock;
	private SocketChannel channel;
	private BufferedReader in;
	private PrintWriter out;

	public QQ_Client(Properties p) throws UnknownHostException, IOException {
		this.setup();
	}

	private void setup() throws UnknownHostException, IOException {
		// set's up connection with qq
		sock = new Socket("127.0.0.1", 9999);
		this.out = new PrintWriter(sock.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(
				sock.getInputStream()));

	}

	/**
	 * 
	 * @param speeds
	 *            speed of qq rotors from 0 - 1000 [4,17,22,27]: rotor nr's
	 *
	 */
	public void send(double[] speeds) {
		int[] rotors = { 4, 17, 22, 27 };

		for (int i = 0; i < 4; i++) {

			this.out.write(rotors[i] + ":" + speeds[i] + ";");
			this.out.flush();
		}
	}

	/**
	 * 
	 * @return gyro (x,y,z), accelerometer(x,y,z)
	 * @throws IOException
	 */
	public double[] read() throws IOException {
		String readsel = in.readLine();
		String[] str_values = readsel.split(":");
		double[] dbl_returnsel = new double[6];
		for (int i = 0; i < dbl_returnsel.length; i++) {
			dbl_returnsel[i] = Double.parseDouble(str_values[i]);
		}
		return dbl_returnsel;
	}
}
