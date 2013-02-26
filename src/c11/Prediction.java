package c11;

//: c11:Prediction.java 
//Predicting the weather with groundhogs. 

public class Prediction {
	private boolean shadow = Math.random() > 0.5;

	public String toString() {
		if (shadow)
			return "Six more weeks of Winter!";
		else
			return "Early Spring!";
	}
} // /:~ 