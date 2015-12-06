
public class QQ_Client {
	public void setup(){
		//set's up connection with qq
	}
	
	/**
	 * 
	 * @param speeds speed of qq rotors from 0 - 1000
	 * [4,17,22,27]: rotor nr's
	 *
	 */
	public void send(int[] speeds)
	{
		//sends commands to qq
	}
	
	/**
	 * 
	 * @return gyro (x,y,z), accelerometer(x,y,z)
	 */
	public int[] read(){
		return new int[6];
	}
}
