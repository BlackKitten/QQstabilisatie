package app;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;

import controller.Controller;

public class Main_v2 {
	public static void main(String[] args) {
		try {
			Controller troller = new Controller(new Properties());
			try {
				Thread.sleep(5000);
				troller.start();
				troller.toFile();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
