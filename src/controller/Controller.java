package controller;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;


public class Controller {
	
	private NNHandler nn_handler;
	private DataSet_handler ds_handler;
	private QQ_Client qq;
	public Controller(Properties p) throws UnknownHostException, IOException{
		this.nn_handler=new NNHandler(p);
		this.ds_handler=new DataSet_handler(p);
		this.qq=new QQ_Client(p);
	}
	
	/**
	 * set's speed of rotors
	 */
	
	public void set_speed(double[] speeds){
		this.qq.send(speeds);
	}
	
	public double[] get_ga() throws IOException{
		return qq.read();
	}
	
	/**
	 * 
	 * @param ga_old old gyra acc stats
	 * @param ga_new new gyra acc stats
	 * @param speeds qq rotor speeds
	 */
	public void add_to_ds(double[] ga_old,double[] ga_new,double[] speeds){
		this.ds_handler.add(ga_old, ga_new,speeds);
	}
	
	public void add_to_ds_ga(double[] ga){
		this.ds_handler.add_ga(ga);
	}
	
	public void add_to_ds_speeds(double[] speeds){
		this.ds_handler.add_speeds(speeds);
	}
	
	public double[] calculate(double[] current_ga,double[] desired_ga){
		return this.nn_handler.calculate(current_ga, desired_ga);
		
	}
	
	public void start() throws IOException, InterruptedException{
		double[][] speeds_array={
				{1200,1200,1200,1200},
				{1300,1300,1300,1300},
				{1400,1400,1400,1400},
				{1500,1500,1500,1500},
				{1600,1600,1600,1600},
				{1700,1700,1700,1700},
				{1800,1800,1800,1800}
		};
		
		for(double[] speeds:speeds_array){
			this.add_to_ds_ga(this.get_ga());
			this.set_speed(speeds);
			this.add_to_ds_speeds(speeds);
			Thread.sleep(500);
			this.add_to_ds_ga(this.get_ga());
		}
	}
	
	public void toFile(){
		this.ds_handler.to_file("ds_out.txt");
		this.nn_handler.to_file("nn_out.nnet");
	}
}
