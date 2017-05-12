package constructors;

import java.util.ArrayList;
import java.util.List;

public class CalculateRestrictFunction {

	double factor1, factor2, border;
	double wertbeiX, wertbeiY, ergebnis;
	int resticts;
	int resticts2;
	
	public CalculateRestrictFunction(List<Object> restrictList)
	{
		factor1= Double.parseDouble(restrictList.get(4).toString());
		factor2= Double.parseDouble(restrictList.get(5).toString());
		border=Double.parseDouble(restrictList.get(6).toString());
			
		
	}
	
	public void calculate(List<Object> restrictList)
	{
		
		for(int i=4; i<= (restrictList.size()-1);i=i+3){
			wertbeiX = Double.parseDouble(restrictList.get(i).toString());
			wertbeiY = Double.parseDouble(restrictList.get(i++).toString());
			ergebnis = Double.parseDouble(restrictList.get(i+2).toString());
			
	
		
			
		}
		
		
		
		double a1=(-1)*wertbeiX/wertbeiY;
		double a2=ergebnis/wertbeiY;
		wertbeiX=a1;
		wertbeiY=a2;
		
		
		
	}
	
	
	

	public double getFactor1() {
		return factor1;
	}

	public void setFactor1(double factor1) {
		this.factor1 = factor1;
	}

	public double getFactor2() {
		return factor2;
	}

	public void setFactor2(double factor2) {
		this.factor2 = factor2;
	}

	public double getBorder() {
		return border;
	}

	public void setBorder(double border) {
		this.border = border;
	}

	public int getResticts() {
		return resticts;
	}

	public void setResticts(int resticts) {
		this.resticts = resticts;
	}
	
}
