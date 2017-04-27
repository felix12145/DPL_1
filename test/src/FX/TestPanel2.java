package FX;

import java.util.function.Function;

import FX.CartesianPlot.Axes;
import FX.CartesianPlot.Plot;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TestPanel2 extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	

	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane g = new GridPane();

		primaryStage.setTitle("DPL1");
		primaryStage.show();
		g.setAlignment(Pos.CENTER);
		g.setHgap(10);
		g.setVgap(10);
		g.setPadding(new Insets(5, 5, 5, 5));

		/**************************************************************/
		ScrollPane sp = new ScrollPane();
		 
		 
		 
	     Axes axes = new Axes(
	                500, 500,
	                0, 10, 1,
	                0, 10, 1
	        );

	        Plot plot = new Plot(
	                x -> 0.0,
	                0, 8, 0.1,
	                axes
	        );
	        
	        Plot plot1 = new Plot(
	                x -> 0.0,
	                0, 8, 0.1,
	                axes
	        );

	        Pane layout = new Pane(
	                plot,plot1
	        );
	        layout.setPadding(new Insets(20));
	//        layout.setStyle("-fx-background-color: rgb(35, 39, 50);");
	        //grid.add(layout, 1, 1);
	        sp.setContent(layout);
		 
		  g.add(sp, 7, 0, 1, 13);
		 
		 
		 /**************************************************************/
		ObservableList<String> options = FXCollections.observableArrayList("MIN", "MAX"

		);

		ObservableList<Integer> options2 = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10

				);
		
		Label l = new Label();
		Label l2 = new Label();
		Label l3 = new Label();
		
		TextField t = new TextField();
		t.setPrefWidth(40);
		TextField t2 = new TextField();
		
		ComboBox c1 = new ComboBox(options);
		c1.setPrefWidth(80);
		
		t2.setPrefWidth(50);
		l.setText("z(x,y)= ");
		l2.setText("x + ");
		l3.setText("y -> ");
		// g.setGridLinesVisible(true);
		Scene scene = new Scene(g, 1600, 800);
		primaryStage.setScene(scene);
		
		Label l4= new Label();
		l4.setText("Anzahl der Restriktionen: ");
		ComboBox c2 = new ComboBox(options2);
		c2.setPrefWidth(40);
		Button b1 = new Button();
		
//		b1.setStyle("-fx-background-color: lightBlue");
		
				//	b1.setStyle("-fx-text-fill: yellow");
		
		b1.setText("Proceed");
		b1.setPrefWidth(80);
		g.add(l, 0, 0);
		g.add(t, 1, 0);
		g.add(l2, 2, 0);
		g.add(t2, 3, 0);
		g.add(l3, 4, 0);
		g.add(c1, 5, 0);
		g.add(l4, 0,1);
		g.add(c2, 1,1);
		g.add(b1,5,1);
		
		primaryStage.show();
	
		
		
		b1.setOnAction(new EventHandler<ActionEvent>()
		{
		
			public void handle(ActionEvent event)
			{
				
				
				if(c1.getSelectionModel().isEmpty()){System.out.println("Wählen Sie MIN oder MAX");
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Wählen Sie MIN oder MAX");
				
				alert.showAndWait();
				
				}
					else{
						if(c2.getSelectionModel().isEmpty()){System.out.println("Wählen Sie die Anzahl der Restriktionen ");
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText("Wählen Sie die Anzahl der Restriktikonen");
						
						alert.showAndWait();
						}
						else{
				System.out.println("TBox1: " + t.getText());
				System.out.println("TBox2 : " + t2.getText());
				System.out.println("CBox1 : " + c1.getValue().toString());
				System.out.println("CBox2 : " + c2.getValue().toString() );
				
				GridPane g2 = proceedRestricts(t.getText(),t2.getText(),c1.getValue().toString(),c2.getValue().toString(),g);
				
				
				scene.setRoot(g2);
			
				
			primaryStage.show();
					
					}	
					}
				}
				
			
		});
		}
		
	
	public GridPane proceedRestricts(String t, String t2, String c1, String c2, GridPane g)
		{
		int width=40;
	
		for(int i=0;i<Integer.parseInt(c2);i++)
			{
				
				Label nb  = new Label();
				Label nb1 = new Label();
				Label nb2 = new Label();
				TextField a1 = new TextField();
				TextField a2 = new TextField();
				TextField db = new TextField();
				
				a1.setPrefWidth(width);
				a2.setPrefWidth(width);
				db.setPrefWidth(width);
				String operator;
				
				if(c1=="MAX") operator="<=";
				else operator = ">=";
				nb.setText("NB"+i+": ");
				nb1.setText("x + ");
				nb2.setText("y "+ operator);
				
				g.add(nb,0,2+i);
				g.add(a1,1, 2+i);
				g.add(nb1,2, 2+i);
				g.add(a2,3, 2+i);
				g.add(nb2,4,2+i);
				g.add(db,5, 2+i);
				
			}
		Button b = new Button();
		b.setText("Calculate");
		b.setPrefWidth(60);
				g.add(b,5,Integer.parseInt(c2)+2);
			return g;
		}
	 class Axes extends Pane {
	        private NumberAxis xAxis;
	        private NumberAxis yAxis;

	        public Axes(
	                int width, int height,
	                double xLow, double xHi, double xTickUnit,
	                double yLow, double yHi, double yTickUnit
	        ) {
	            setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
	            setPrefSize(width, height);
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
	            yAxis.layoutXProperty().bind(
	                Bindings.subtract(
	                    (0) + 1,
	                    yAxis.widthProperty()
	                )
	            );

	            getChildren().setAll(xAxis, yAxis);
	        }

	        public NumberAxis getXAxis() {
	            return xAxis;
	        }

	        public NumberAxis getYAxis() {
	            return yAxis;
	        }
	    }

	    class Plot extends Pane {
	        public Plot(
	                Function<Double, Double> f,
	                double xMin, double xMax, double xInc,
	                Axes axes
	        ) {
	            Path path = new Path();
	            path.setStroke(Color.ORANGE.deriveColor(0, 1, 1, 0.6));
	            path.setStrokeWidth(2);

	            path.setClip(
	                    new Rectangle(
	                            0, 0, 
	                            axes.getPrefWidth(), 
	                            axes.getPrefHeight()
	                    )
	            );

	            double x = xMin;
	            double y = f.apply(x);

	            path.getElements().add(
	                    new MoveTo(
	                            mapX(x, axes), mapY(y, axes)
	                    )
	            );

	            x += xInc;
	            while (x < xMax) {
	                y = f.apply(x);

	                path.getElements().add(
	                        new LineTo(
	                                mapX(x, axes), mapY(y, axes)
	                        )
	                );

	                x += xInc;
	            }

	            setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
	            setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
	            setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

	            getChildren().setAll(axes, path);
	        }

	        private double mapX(double x, Axes axes) {
	            double tx =0;
	            double sx = axes.getPrefWidth() / 
	               (axes.getXAxis().getUpperBound() - 
	                axes.getXAxis().getLowerBound());

	            return x * sx + tx;
	        }

	        private double mapY(double y, Axes axes) {
	            double ty = axes.getPrefHeight();
	            double sy = axes.getPrefHeight() / 
	                (axes.getYAxis().getUpperBound() - 
	                 axes.getYAxis().getLowerBound());

	            return -y * sy + ty;
	        }
	    }
}
