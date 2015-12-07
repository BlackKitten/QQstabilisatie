package app;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;

import controller.Controller;

public class Main {

	public static void main(String[] args) {
		
		try {
			Controller troller = new Controller(new Properties());
			double[] speeds={1,2,3,4};
			troller.set_speed(speeds);
			double[] ga=troller.get_ga();
			for (double d :ga){
				System.out.println(String.valueOf(d));
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
