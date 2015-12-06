import java.util.Properties;


public class Controller {
	
	private NNHandler nn_handler;
	private DataSet_handler ds_handler;
	public Controller(Properties p){
		this.nn_handler=new NNHandler(p);
		this.ds_handler=new DataSet_handler(p);
	}
	
	/**
	 * set's speed of rotors
	 */
	
	public void set_speed(double[] speeds){
		
	}
	
	public double[] get_ga(){
		return new double[6];
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
	
	public double[] calculate(double[] current_ga,double[] desired_ga){
		return this.nn_handler.calculate(current_ga, desired_ga);
		
	}
}
