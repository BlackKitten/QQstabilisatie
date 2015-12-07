package controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.exceptions.NeurophException;


public class DataSet_handler {
	private DataSet ds;
	private double[] ga_old;
	private double[] ga_new;
	private double[] speeds;
	public DataSet_handler(Properties p) {
		try{
			load_file("ds_out.txt");
		}catch(NeurophException e){
			
		
		this.ds=new DataSet(12, 4);
		}
	}

	/**
	 * 
	 * @param old_ga
	 * @param new_ga
	 */
	public void add(double[] old_ga,double[] new_ga, double[] speeds){
		double[] input=new double[12];
		System.arraycopy(old_ga, 0, input, 0, old_ga.length);
		System.arraycopy(new_ga, 0, input, 6, new_ga.length);
		this.ds.addRow(input, speeds);
	}
	
	public DataSet get(){
		return this.ds;
	}
	
	public void load_file(String fn){
		this.ds=DataSet.createFromFile(fn, 12, 4, ";");
	}
	
	public void to_file(String fn) throws IOException{
		this.ds.saveAsTxt(fn, ";");
		util.File_Handler.removeFirstLine(fn);
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
