package FX;


	import java.util.function.Function;

	import javafx.application.Application;
	import javafx.beans.binding.Bindings;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.geometry.Insets;
	import javafx.geometry.Pos;
	import javafx.geometry.Side;
	import javafx.scene.Scene;
	import javafx.scene.chart.Axis;
	import javafx.scene.chart.NumberAxis;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.GridPane;
	import javafx.scene.layout.Pane;
	import javafx.scene.paint.Color;
	import javafx.scene.shape.LineTo;
	import javafx.scene.shape.MoveTo;
	import javafx.scene.shape.Path;
	import javafx.scene.shape.Rectangle;
	import javafx.stage.Stage;

	public class drawInput extends Application{
		
		
		public static void main(String[] args){
			
			launch(args); 
			
		}

		@Override
		public void start(Stage primaryStage) throws Exception {
			
			GridPane gridpane = new GridPane();
			
			primaryStage.setTitle("DPL1");
			primaryStage.show();
			
			gridpane.setAlignment(Pos.CENTER);
			gridpane.setHgap(10); // The width of the horizontal gaps between columns.
			gridpane.setVgap(10); // The height of the vertical gaps between rows.
			gridpane.setPadding(new Insets(5,5,5,5));
			
			Label Zielfunktion = new Label();
			Label ZFx = new Label();
			Label ZFy = new Label();
			Zielfunktion.setText("Z(x,y) =");
			ZFx.setText("x + ");
			ZFy.setText("y ");
			
			TextField Zx = new TextField();
			TextField Zy = new TextField();
			Zx.setPrefWidth(40);
			Zy.setPrefWidth(40);
			
			//double value1 = Double.parseDouble(Zx.getText());
			//double value2 = Double.parseDouble(Zy.getText());
			
			Button drawButton = new Button();
			drawButton.setText("Draw Function");
			drawButton.setPrefWidth(80);
			
			Scene scene = new Scene(gridpane,600,600);
			
			gridpane.add(Zielfunktion, 0, 0);
			gridpane.add(ZFx, 2, 0);
			gridpane.add(ZFy, 4, 0);
			gridpane.add(Zx, 1, 0);
			gridpane.add(Zy, 3, 0);
			gridpane.add(drawButton, 0, 1);
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			drawButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
				draw(gridpane,Zx,Zy);
					
				}
			});
			
		}
		
		
		
		public void draw(GridPane p, TextField test1, TextField test2){
			
			double value1;
			double value2; 
			
			value1 = Double.parseDouble(test1.getText());
			value2 = Double.parseDouble(test2.getText());
			
			Axes axes = new Axes(
					500, 500,
					0,30,1,
					0,30,1);	
			
			Plot plot = new Plot(
					x-> value1*x + value2,
					0,20,0.1,
					axes);
			
			Plot plot1 = new Plot(
					x-> 0.0,
					0,0,0,
					axes);
					
			
			Pane layout = new Pane(plot,plot1);
			Pane layoutContainer = new Pane(layout);
			
			layoutContainer.setPadding(new Insets(50,50,50,50));
			
			layout.setPadding(new Insets(20,20,20,20));
			
			p.add(layout, 4, 4, 3, 3);
			
		}
		
		class Axes extends Pane{
			private NumberAxis xAxis;
			private NumberAxis yAxis; 
			
			public Axes(
					int width, int height,
					double xLow, double xHi, double xTickUnit,
					double yLow, double yHi, double yTickUnit
					){
				setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
				setPrefSize(width,height); 
				setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
				xAxis = new NumberAxis(xLow, xHi, xTickUnit);
				xAxis.setSide(Side.BOTTOM);
				xAxis.setMinorTickVisible(false);
				xAxis.setPrefWidth(width);
				xAxis.setLayoutY(height);
				
				
				yAxis = new NumberAxis(yLow, yHi, yTickUnit);
				yAxis.setSide(Side.LEFT);
				yAxis.setMinorTickVisible(false);
				yAxis.setPrefHeight(height);
				yAxis.layoutXProperty().bind(Bindings.subtract(0 + 1, yAxis.widthProperty()));
				
				getChildren().setAll(xAxis, yAxis);
			}

			public NumberAxis getXAxis(){
				return xAxis;
			}
			
			public NumberAxis getYAxis(){
				return yAxis;
			}
			
		}
		
		class Plot extends Pane{
			public Plot(
					Function<Double, Double> f, 
					double xMin, double xMax, double xInc,
					Axes axes){
				Path path = new Path();
				path.setStroke(Color.ORANGE.deriveColor(0, 1, 1, 1));
				path.setStrokeWidth(2);
				
				path.setClip(
						new Rectangle(
								0,0,
								axes.getPrefWidth(),
								axes.getPrefHeight()
								)
						);
				
				double x = xMin;
				double y = f.apply(x);
				
				path.getElements().add(new MoveTo(mapX(x,axes),mapY(y,axes)));
				
				x += xInc;
				while (x <xMax) {
					y = f.apply(x);
					
					path.getElements().add(new LineTo(mapX(x,axes), mapY(y,axes)));
					
					x += xInc;
					
				}
				
				setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
				setPrefSize(axes.getPrefWidth(),axes.getPrefHeight());
				setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
				
				getChildren().setAll(axes,path);
			}
			
			private double mapX(double x, Axes axes){
				double tx = 0;
				double sx = axes.getPrefWidth() / 
						(axes.getXAxis().getUpperBound() -
						 axes.getXAxis().getLowerBound());
				
				return x * sx + tx;
			}
			
			private double mapY(double y, Axes axes){
				double ty = axes.getPrefHeight();
				double sy = axes.getPrefHeight() / 
						(axes.getYAxis().getUpperBound() - 
						 axes.getYAxis().getLowerBound());
				
				return -y * sy + ty; 
			}
			
		}

	


}
