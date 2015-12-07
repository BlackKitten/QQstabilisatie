package controller;
import java.util.Properties;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.DynamicBackPropagation;


public class NN_handler {
	private MultiLayerPerceptron nn;
	private DataSet ds;
	
	public NN_handler(Properties p){
		this.nn=createNN();
	}
	
	private MultiLayerPerceptron createNN(){
		MultiLayerPerceptron nn= new MultiLayerPerceptron(12,100,50,6);
        DynamicBackPropagation rule=new DynamicBackPropagation();
        nn.setLearningRule(rule);
        return nn;		
	}
	public void start(){
		DynamicBackPropagation rule=(DynamicBackPropagation)this.nn.getLearningRule();
		rule.learn(this.ds);
	}
	public void stop(){
		nn.stopLearning();
	}
	
	public void update_dataset(){
		this.stop();
		this.start();
	}
	public String[] getInfo() {
        String s1=""+nn.getLearningRule().getPreviousEpochError();
        String s2=""+nn.getLearningRule().getCurrentIteration();
        String[] s_all={s1,s2};
        return s_all;
	}

	public void to_file(String fn){
		this.nn.save(fn);
	}
	
	public void load_file(String fn){
		this.nn=(MultiLayerPerceptron) new NeuralNetwork<>().createFromFile(fn);
	}
	
	public void load_ds(DataSet ds){
		this.ds=ds;
	}
	
	public double[] calculate(double[] old_ga,double[] new_ga){
		double[] input=new double[6];
		
		System.arraycopy(old_ga, 0, input, 0, old_ga.length);
		System.arraycopy(new_ga, 0, input, 3, new_ga.length);
		this.nn.setInput(input);
		this.nn.calculate();
		return this.nn.getOutput();
	}
}
