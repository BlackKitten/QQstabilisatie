package controller;
import java.util.Properties;

import org.neuroph.core.data.DataSet;


public class DataSet_handler {
	private DataSet ds;
	private double[] ga_old;
	private double[] ga_new;
	private double[] speeds;
	public DataSet_handler(Properties p) {
		this.ds=new DataSet(12, 4);
	}

	/**
	 * 
	 * @param old_ga
	 * @param new_ga
	 */
	public void add(double[] old_ga,double[] new_ga, double[] speeds){
		double[] input=new double[6];
		System.arraycopy(old_ga, 0, input, 0, old_ga.length);
		System.arraycopy(new_ga, 0, input, 3, new_ga.length);
		this.ds.addRow(input, speeds);
	}
	
	public DataSet get(){
		return this.ds;
	}
	
	public void load_file(String fn){
		this.ds=DataSet.createFromFile(fn, 12, 6, ";");
	}
	
	public void to_file(String fn){
		this.ds.save(fn);
	}

	public void add_ga(double[] ga) {
		if(this.ga_old==null){
			this.ga_old=ga;
		}else{
			this.ga_new=ga;
			add(this.ga_old,this.ga_new,this.speeds);
			this.ga_old=null;
			this.ga_new=null;
			this.speeds=null;
		}
		
	}

	public void add_speeds(double[] speeds) {
		this.speeds=speeds;
		
	}
}
