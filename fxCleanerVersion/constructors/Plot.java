package constructors;

import java.util.function.Function;

import coordinateAxesTics.ColorEnum;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import panel.Panel;

public class Plot extends Pane {
	
	Color c;
	
	public Color getC() {
		return c;
	}

	public void setC() {
	
		Color.ORANGE.deriveColor(0, 1, 1, 0.6);
		this.c = Color.WHITE.deriveColor(0, 1, 1, 0.6);
	}

	public Plot(Function<Double, Double> f, double xMin, double xMax, double xInc, Axes axes, int farben) {
		Path path = new Path();
		if(Panel.farben==0){
			path.setStroke(Color.WHITE.deriveColor(0, 1, 1, 0.6));
			Panel.farben++;
		}
		else if(Panel.farben==1)
		{
			path.setStroke(Color.DARKGRAY.deriveColor(0, 1, 1, 0.6));
			Panel.farben++;
		}
		else if(Panel.farben==2)
		{
			path.setStroke(Color.GREENYELLOW.deriveColor(0, 1, 1, 0.6));
			Panel.farben++;
		}
		else
		{
			path.setStroke(Color.LIGHTSKYBLUE.deriveColor(0, 1, 1, 0.6));
			Panel.farben++;	
		}
		
		
//		path.setStroke(this.getC());
		path.setStrokeWidth(2);

		path.setClip(new Rectangle(0, 0, axes.getPrefWidth(), axes.getPrefHeight()));

		double x = xMin;
		double y = f.apply(x);

		path.getElements().add(new MoveTo(mapX(x, axes), mapY(y, axes)));

		x += xInc;
		while (x < xMax) {
			y = f.apply(x);

			path.getElements().add(new LineTo(mapX(x, axes), mapY(y, axes)));

			x += xInc;
		}

		setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
		setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
		setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

		getChildren().setAll(axes, path);
	}

	private double mapX(double x, Axes axes) {
		double tx = 0;
		double sx = axes.getPrefWidth() / (axes.getXAxis().getUpperBound() - axes.getXAxis().getLowerBound());
//		System.out.println("x: "+x);
//		System.out.println("tx: "+tx);
//				System.out.println("sx :"+sx);
//System.out.println("axes.getXAxis().getUpperBound(): "+axes.getXAxis().getUpperBound());
//System.out.println("axes.getXAxis().getLowerBound(): "+axes.getXAxis().getLowerBound());
//System.out.println("axes.getPrefWidth(): "+axes.getPrefWidth());
//System.out.println("axes.getXAxis().getLowerBound(): "+axes.getXAxis().getLowerBound());
		return x * sx + tx;
	}

	private double mapY(double y, Axes axes) {
		double ty = axes.getPrefHeight();
		double sy = axes.getPrefHeight() / (axes.getYAxis().getUpperBound() - axes.getYAxis().getLowerBound());
//		System.out.println("axes.getYAxis().getUpperBound(): "+axes.getYAxis().getUpperBound());
//		System.out.println("axes.getYAxis().getLowerBound(): "+axes.getYAxis().getLowerBound());
//		System.out.println("axes.getPrefHeight(): "+axes.getPrefHeight());
//		System.out.println("axes.getYAxis().getLowerBound(): "+axes.getYAxis().getLowerBound());
//		System.out.println("y: "+y);
		
		return -y * sy + ty;
	}

	
}
