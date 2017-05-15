package constructors;

import java.util.ArrayList;
import java.util.List;

import coordinateAxesTics.Ranges;
import coordinateAxesTics.Tics;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import panel.Panel;

public class CalculateRestrictFunction {

	double factor1, factor2, border;
	
	double[] ergebnis;
	int resticts;
	int resticts2;
	
//	
	
	public List<Plot> getPlotList(List<Object> restrictList)
		{
		
		List<Plot> plotList = new ArrayList<Plot>();
		
	System.out.println("restrictList.size()/3: "+restrictList.size()/3);
	
		List<Object> o = new ArrayList<Object>();
		for(int i=0;i<Integer.parseInt(Panel.headList.get(2).toString());i++)
				{
			o.add(new Object());
				}
		int i=0;
		for(Object a : o){
		
			
			double wertbeiX= (-1)*Double.parseDouble(restrictList.get(i).toString())/Double.parseDouble(restrictList.get(i+1).toString());
			System.out.println("wertbeiX["+i+"]: "+wertbeiX);
			
			System.out.println("a1,a2,c ["+i+"]"+Integer.parseInt(restrictList.get(i).toString())+","+Integer.parseInt(restrictList.get(i+1).toString())+","+Integer.parseInt(restrictList.get(i+2).toString()));
			
			double wertbeiY = Double.parseDouble(restrictList.get(i+2).toString())/Double.parseDouble(restrictList.get(i+1).toString());
			System.out.println("wertbeiY["+i+"]: "+wertbeiY);
		constructors.Axes axes = new constructors.Axes(
                500, 500,
                0, new Ranges(20).getRange(), new Tics(1).getTic(),
                0, new Ranges(20).getRange(), new Tics(1).getTic()
        );
	if(restrictList.get(i+1).toString()=="0"){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("y darf nicht 0 sein!");
		alert.showAndWait();
		
	}
//	else if(restrictList.get(i).toString()=="0" ||restrictList.get(i+2).toString()=="0" )
//	{
//		if(Double.parseDouble(restrictList.get(i+1).toString())<=0)
//		{
//			if(Double.parseDouble(restrictList.get(i).toString())<=0)
//			{
//				
//			}
//			else
//			{
//				
//			}
//		}
//		else
//	}
		else{
		plotList.add( new Plot(x -> wertbeiX*x + wertbeiY,0, 20, 0.1,axes,Panel.farben));
		i+=3;
		}
		}
		return plotList;
		
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
