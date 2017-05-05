package FX;
	import java.util.ArrayList;
	import java.util.List;
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

public class testPati extends Application {

		// TODO Auto-generated method stub
		private List<String> valueList = new ArrayList<String>();
		private Enum a;
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			launch(args);
		}
		
		@Override
		public void start(Stage primaryStage) throws Exception {

			GridPane g = new GridPane();
			
			primaryStage.setTitle("mein Test - Lineares Optimierungsproblem");
			primaryStage.show();
			g.setAlignment(Pos.CENTER);
			g.setHgap(10);
			g.setVgap(10);
			g.setPadding(new Insets(5, 5, 5, 5));

			/**************************************************************/
			ScrollPane sp = new ScrollPane();
			ScrollPane sp2 = new ScrollPane(); 
			 
			 /*Achsengroeßen: Axes(x_Laenge, y_Hoehe, Beginn der x-Achse, Wertebereich x-Achse, Schrittweite der x-Achse,
			  *  Beginn der y-Achse, Wertebereiche y-Achse,Schrittweite der y-Achse)*/
		     Axes axes = new Axes(
		                300, 400,
		                0, 10, 1,
		                0, 15, 1
		        );
		     
		     /*die Plots zeichnen die Graphen*/
		     
		        Plot graph1 = new Plot(
		                x -> x+2,
		                0, 8, 0.1,
		                axes
		        );
		        
		        Plot graph2 = new Plot(
		                x -> 0.0,
		                0, 8, 0.1,
		               axes
		        );

		        Pane layout = new Pane(
		                graph1, graph2
		        );
		        Pane layoutContainer=new Pane(layout);
		        
		        layoutContainer.setPadding(new Insets(50,50,50,50));
		        Label r = new Label();
		 
		        r.setText("      ");
		  
		        layout.setPadding(new Insets(20,20,20,20));
		     /*   layout.setPrefWidth(800);
		       layout.setStyle("-fx-background-color: rgb(35, 39, 50);");
		
		        //grid.add(layout, 1, 1);
		        sp.setContent(layoutContainer);
		        sp.setPadding(new Insets(20,20,20,20));
			 */
			  g.add(r, 6, 0, 1, 13);
		
			  g.add(layout, 7, 0, 1, 13);
			 
			/**************************************************************/
		        
		        
			/*ObservableList setzt eine vorgegebene Auswahl der Combobox - Hier: Min Max* und die mögliche 
			  * Anzahl der Restriktionen*/
			 
			ObservableList<String> min_max_Auswahl = FXCollections.observableArrayList("MIN", "MAX"

			);

			ObservableList<Integer> anzahlRestriktionen_Auswahl = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10

			);
			
			/*Label sind feste Textfelder*/
			
			Label zielfunktion = new Label();
			Label x = new Label();
			Label y = new Label();
			
			/*Textfelder deklarieren Eingabefelder fuer den User*/
			
			TextField wertfuerX = new TextField();
			wertfuerX.setPrefWidth(40);
			TextField wertfuerY = new TextField();
			
			ComboBox min_max = new ComboBox(min_max_Auswahl);
			min_max.setPrefWidth(80);
			
			wertfuerY.setPrefWidth(50);
			zielfunktion.setText("z(x,y)= ");
			x.setText("x + ");
			y.setText("y -> ");
			//g.setGridLinesVisible(true);
			ScrollPane spStart= new ScrollPane();
			spStart.setContent(g);
			//Scene scene = new Scene(spStart, 1200, 800);
			Scene scene = new Scene(spStart, 800, 500);
			primaryStage.setScene(scene);
			
			Label textAnzahlRestriktionen= new Label();
			textAnzahlRestriktionen.setText("Anzahl der Restriktionen: ");
			ComboBox anzahlRestriktionen = new ComboBox(anzahlRestriktionen_Auswahl);
			anzahlRestriktionen.setPrefWidth(40);
			Button proceed = new Button();
			
//			b1.setStyle("-fx-background-color: lightBlue");
			
					//	b1.setStyle("-fx-text-fill: yellow");
			
			proceed.setText("Proceed");
			proceed.setPrefWidth(80);
			g.add(zielfunktion, 0, 0);
			g.add(wertfuerX, 1, 0);
			g.add(x, 2, 0);
			g.add(wertfuerY, 3, 0);
			g.add(y, 4, 0);
			g.add(min_max, 5, 0);
			g.add(textAnzahlRestriktionen, 0,1);
			g.add(anzahlRestriktionen, 1,1);
			g.add(proceed,5,1);
			
			primaryStage.show();
		
			
			
			proceed.setOnAction(new EventHandler<ActionEvent>()
			{
			
				public void handle(ActionEvent event)
				{
					
					
					if(min_max.getSelectionModel().isEmpty()){System.out.println("Wählen Sie MIN oder MAX");
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText("Wählen Sie MIN oder MAX");
					
					alert.showAndWait();
					
					}
						else{
							if(anzahlRestriktionen.getSelectionModel().isEmpty()){System.out.println("Wählen Sie die Anzahl der Restriktionen ");
							
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Information Dialog");
							alert.setHeaderText("Wählen Sie die Anzahl der Restriktikonen");
							
							alert.showAndWait();
							}
							else{
					System.out.println("X: " + wertfuerX.getText().toString());
					System.out.println("Y : " + wertfuerY.getText().toString());
					System.out.println("Min/Max : " + min_max.getValue().toString());
					System.out.println("Anzahl der Restriktionen : " + anzahlRestriktionen.getValue().toString() );

					
					GridPane g2 = proceedRestricts(wertfuerX.getText(),wertfuerY.getText(),min_max.getValue().toString(),anzahlRestriktionen.getValue().toString(),g);
					GridPane g3 =proceesOutput(wertfuerX.getText().toString(), wertfuerY.getText().toString(),min_max.getValue().toString(),  anzahlRestriktionen.getValue().toString(),  g2);
					sp2.setContent(g3);
				
					scene.setRoot(sp2);
				
					
				primaryStage.show();
						
						}	
						}
					}
					
				
			});
			}
			
		
		public GridPane proceedRestricts(String wertfuerX, String wertfuerY, String min_max, String anzahlRestriktionen, GridPane g)
			{
			int width=40;
		
			for(int i=0;i<Integer.parseInt(anzahlRestriktionen);i++)
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
					
					if(min_max=="MAX") operator="<=";
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
			Button calculate = new Button();
			calculate.setText("Calculate");
			calculate.setPrefWidth(60);
					g.add(calculate,5,Integer.parseInt(anzahlRestriktionen)+2);
				return g;
			}
		
		
		public GridPane proceesOutput(String wertfuerX, String wertfuerY, String min_max, String anzahlRestriktionen, GridPane g)
		{
		int width=40;

		
		Label nb  = new Label();
		Label nb1 = new Label();
		Label nb2 = new Label();
		Label nbValue  = new Label();
		Label nbValue2 = new Label();
		Label nbErg2 = new Label();
		nb.setText("test value");
		
//		String operator;
	//	
//		if(min_max=="MAX") operator="<=";
//		else operator = ">=";
//		nb.setText("NB: ");
//		nbValue.setText("test");
//		nb1.setText("x + ");
//		nbValue2.setText("test");
//		nb2.setText("y "+ operator);
//		nbErg2.setText("test");
		
//		for(int i=0;i<Integer.parseInt(c2);i++)
//			{
//				
//				Label nb  = new Label();
//				Label nb1 = new Label();
//				Label nb2 = new Label();
//				Label nbValue  = new Label();
//				Label nbValue2 = new Label();
//				Label nbErg2 = new Label();
//				
//				
//				String operator;
//				
//				if(min_max=="MAX") operator="<=";
//				else operator = ">=";
//				nb.setText("NB"+i+": ");
//				nbValue.setText(t);
//				nb1.setText("x + ");
//				nbValue2.setText(t2);
//				nb2.setText("y "+ operator);
//				nbErg2.setText(c1);
//				
//				g.add(nb,0,1+i);
//				g.add(nbValue,1, 1+i);
//				g.add(nb1,2, 1+i);
//				g.add(nbValue2,3, 1+i);
//				g.add(nb2,4,1+i);
//				g.add(nbErg2,5, 1+i);
//				
//			}
		Label b = new Label();
		b.setText("Sie haben eingegeben: ");
		b.setPrefWidth(200);
				g.add(b,0,13,13,1);
				g.add(nb,0,14,14,1);
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
	 int bindValue=25;
		            xAxis = new NumberAxis(xLow, xHi, xTickUnit);
		            xAxis.setSide(Side.BOTTOM);
		            xAxis.setMinorTickVisible(false);
		            xAxis.setPrefWidth(width);
		            xAxis.setLayoutY(height);
		         
//		          xAxis.layoutXProperty().bind(
//			                Bindings.subtract(
//			                    (bindValue) + 1,
//			                    xAxis.widthProperty()
//			                )
//			            );  
		           
		            
		            
		            yAxis = new NumberAxis(yLow, yHi, yTickUnit);
		            yAxis.setSide(Side.LEFT);
		            yAxis.setMinorTickVisible(false);
		            yAxis.setPrefHeight(height);
		            yAxis.layoutXProperty().bind(
		                Bindings.subtract(
		                    0 + 1,
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
		            path.setStroke(Color.BLUE.deriveColor(0, 1, 1, 0.6));
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

