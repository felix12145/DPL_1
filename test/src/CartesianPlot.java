package Graph;



	
	import javafx.application.Application;
	import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
	import javafx.scene.Scene;
	import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
	import javafx.scene.paint.Color;
	import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

	import java.util.function.Function;

	// Java 8 code
	public class CartesianPlot
	extends Application {

	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) {
	    	
	    	 primaryStage.setTitle("DPL1");
	    	    primaryStage.show();
	    	    
	            GridPane grid = new GridPane();
	            grid.setAlignment(Pos.CENTER);
	            grid.setHgap(10);
	            grid.setVgap(10);
	            grid.setPadding(new Insets(50, 50, 50, 50));

	            
	            
	            
	            Scene scene = new Scene(grid, 800, 800);
	            primaryStage.setScene(scene);
	    	    
	            
	            Text scenetitle = new Text("Welcome");
	            scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	            grid.add(scenetitle, 0, 0, 2, 1);
	            
	        Axes axes = new Axes(
	                500, 500,
	                0, 10, 1,
	                0, 10, 1
	        );

	        Plot plot = new Plot(
	                x -> x*x,
	                0, 8, 0.1,
	                axes
	        );
	        
	        Plot plot1 = new Plot(
	                x -> -x + 10,
	                0, 8, 0.1,
	                axes
	        );

	        Pane layout = new Pane(
	                plot,plot1
	        );
	        layout.setPadding(new Insets(20));
	//        layout.setStyle("-fx-background-color: rgb(35, 39, 50);");
	        grid.add(layout, 1, 1);
	        
	        
	 //       grid.setGridLinesVisible(true);
	        grid.setVgap(5);
	        grid.setHgap(30);
	        Button btn = new Button();
	        btn.setText("send");
	        btn.setPrefWidth(100);
	                btn.setOnAction(new EventHandler<ActionEvent>(){
	                public void handle(ActionEvent event)
	                {
	                   
	                }
	                
	                });
	        grid.add(btn,0,1);
	        
	        
	       // primaryStage.setTitle("y = \u00BC(x+4)(x+1)(x-2)");
	       // primaryStage.setScene(new Scene(layout, Color.rgb(35, 39, 50)));
	        primaryStage.show();
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


	