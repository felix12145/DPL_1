package panel;

//asdfasdfasdf
import java.util.ArrayList;
import java.util.List;

import constructors.Axes;
import constructors.InOutPanels;
import constructors.Plot;
import coordinateAxesTics.Ranges;
import coordinateAxesTics.Tics;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Panel extends Application{
		public static int fR=3;
		public static List<Object> restrictList = new ArrayList<Object>();
		public static List<Object> headList = new ArrayList<Object>();				//new 
		public static List<TextField> restrictValues = new ArrayList<TextField>();
		public static int farben=0;
		GridPane uniGrid;
		 Pane firstGraph;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		GridPane firstGrid = new GridPane();
		
		ScrollPane firstScrollPane = new ScrollPane();
		
		
		
		firstGrid.setAlignment(Pos.CENTER);
		firstGrid.setHgap(10);
		firstGrid.setVgap(10);
		firstGrid.setPadding(new Insets(5, 5, 5, 5));
		
		primaryStage.setTitle("Lineare Optimierung - Graphisch");
		primaryStage.show();
		Tics defaultTic = new Tics(1);
		Ranges defaultRange = new Ranges(20);
		
		 Axes axes = new Axes(
	                500, 500,
	                0, new Ranges(20).getRange(), defaultTic.getTic(),
	                0, new Ranges(20).getRange(), defaultTic.getTic()
	        );
		 
		 Plot plot = new Plot(
	                x -> 0.0,
	                0, 8, 0.1,
	                axes,Panel.farben
	        );
		 plot.setC();
	        
//	     constructors.Plot plot1 = new constructors.Plot(
//	                x -> 0.0,
//	                0, 8, 0.1,
//	                axes
//	        );
	        
	         firstGraph = new Pane(
	                plot
	        );
	        
	        Pane graphContainer=new Pane(firstGraph); 
	        graphContainer.setPadding(new Insets(50,50,50,50));
	        
	        Label placeHolderLabel = new Label();
	        placeHolderLabel.setText("    ");
	  	  
	        firstGraph.setPadding(new Insets(20,20,20,20));
	        
	        firstGrid.add(placeHolderLabel, 6, 0);
	        firstGrid.add(firstGraph, 7, fR, 1, 13);
	
	
			ObservableList<String> minMaxDropdown = FXCollections.observableArrayList("MIN", "MAX");
			ObservableList<Integer> restrictsDropdown = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);

			
			Label zFlabel1 = new Label();
			zFlabel1.setText("z(x,y)= ");
			
			Label zFlabel2 = new Label();
			zFlabel2.setText("x + ");
			
			Label zFlabel3 = new Label();
			zFlabel3.setText("y -> ");
			
			TextField zFtext1 = new TextField();
			zFtext1.setPrefWidth(40);
			
			TextField zFtext2 = new TextField();
			zFtext2.setPrefWidth(50);
			
			ComboBox<String> minMaxComboBox = new ComboBox<String>(minMaxDropdown);
			minMaxComboBox.setPrefWidth(80);
			
			ComboBox<Integer> restrictsComboBox = new ComboBox<Integer>(restrictsDropdown);
			restrictsComboBox.setPrefWidth(40);
			
			Label headLabelRestrictions= new Label();
			headLabelRestrictions.setText("Anzahl der Restriktionen: ");
			
			
			
			
			
			
			firstGrid.setGridLinesVisible(false);		// zum Testen
			
			 MenuBar menuBar = new MenuBar();
			 
		        // --- Menu File
		        Menu menuFile = new Menu("File");
		 
		        // --- Menu Edit
		        Menu menuEdit = new Menu("Edit");
		 
		        // --- Menu View
		        Menu menuView = new Menu("View");
		 
		        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
			
		        firstGrid.add(menuBar,0,0,16,2);
			
			Label outPut = new Label();
			outPut.setText("Output: ");
			
			TextArea output = new TextArea();
			output.setEditable(false);
			firstGrid.add(outPut, 0, 15+fR);
			firstGrid.add(output, 0, 16+fR,8,1);
			ScrollPane spStart= new ScrollPane();
			spStart.setContent(firstGrid);
			
			Scene scene = new Scene(spStart, 1000, 850);
			
			primaryStage.setScene(scene);
			
			
			
			
			Button generateInputButton = new Button();
			generateInputButton.setText("Proceed");
			generateInputButton.setPrefWidth(80);
			
			Button fireButton = new Button();
			fireButton.setText("Calculate");
			fireButton.setPrefWidth(60);
			
			
			firstGrid.add(zFlabel1, 0, fR);
			firstGrid.add(zFtext1, 1, fR);
			firstGrid.add(zFlabel2, 2, fR);
			firstGrid.add(zFtext2, 3, fR);
			firstGrid.add(zFlabel3, 4, fR);
			firstGrid.add(minMaxComboBox, 5, fR);
			firstGrid.add(headLabelRestrictions, 0,fR+1);
			firstGrid.add(restrictsComboBox, 1,fR+1);
			firstGrid.add(generateInputButton,5,fR+1);
			
			primaryStage.show();
	
			generateInputButton.setOnAction(new EventHandler<ActionEvent>()
			{
			
				public void handle(ActionEvent event)
				{
					
				
					
					headList.add(zFtext1.getText());
					headList.add(zFtext2.getText());
					headList.add(restrictsComboBox.getValue());
					headList.add(minMaxComboBox.getValue());
					
					if(minMaxComboBox.getSelectionModel().isEmpty()){
					
						
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText("Wählen Sie MIN oder MAX");
					alert.showAndWait();
					}
					
					else{
							if(restrictsComboBox.getSelectionModel().isEmpty()){
							
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Information Dialog");
							alert.setHeaderText("Wählen Sie die Anzahl der Restriktikonen");
							alert.showAndWait();
							}
							
							else{
				
								System.out.println("TBox1: " + zFtext1.getText().toString());
								System.out.println("TBox2 : " + zFtext2.getText().toString());
								System.out.println("CBox2 : " + restrictsComboBox.getValue().toString() );
								System.out.println("CBox1 : " + minMaxComboBox.getValue().toString());
								
	
					InOutPanels InOut = new InOutPanels();
										
					GridPane g2 = InOut.proceedRestricts(minMaxComboBox.getValue().toString(),restrictsComboBox.getValue().toString(),firstGrid);
					
					g2.add(fireButton, 5, restrictsComboBox.getValue() + 2+fR);
					GridPane g3 = InOut.proceesOutput(minMaxComboBox.getValue().toString(),  restrictsComboBox.getValue().toString(),  g2);
					uniGrid=g3;
				
					firstScrollPane.setContent(uniGrid);
					scene.setRoot(firstScrollPane);
					primaryStage.show();
						
					}	
				}
			}
		});
	
			fireButton.setOnAction(new EventHandler<ActionEvent>()
			{
			
				public void handle(ActionEvent event)
				{
					for(int i=0;i<restrictValues.size();i++ )
					{
						restrictList.add(restrictValues.get(i).getText());;
						
					}
					
					for(int i=0;i<restrictList.size();i++ )
					{
						System.out.println("restrictlist[" +i+"]= "+restrictList.get(i).toString());
						
					}
					constructors.CalculateRestrictFunction cRf = new constructors.CalculateRestrictFunction();
//					cRf.calculate(Panel.restrictList);
//					
//					
//					constructors.Axes axes = new constructors.Axes(
//			                500, 500,
//			                0, defaultRange.getRange(), defaultTic.getTic(),
//			                0, defaultRange.getRange(), defaultTic.getTic()
//			        );
//					
//					Plot plot1 = new Plot(
//			                x -> cRf.getFactor1()*x+cRf.getFactor2(),
//			                0, 8, 0.1,
//			                axes
//			        );	
//					
					
					 firstGraph = new Pane();
					 
					 List <Plot> plotList=cRf.getPlotList(restrictList);
				        firstGraph.getChildren().addAll(plotList);
					 restrictList.clear();
				        Pane graphContainer=new Pane(firstGraph); 
				        graphContainer.setPadding(new Insets(50,50,50,50));
				        
				        Label placeHolderLabel = new Label();
				        placeHolderLabel.setText("    ");
				  	  
				        firstGraph.setPadding(new Insets(20,20,20,20));
				        
				        firstGrid.add(placeHolderLabel, 6, 0);
				        firstGraph.getChildren().remove(firstGraph);
				        firstGrid.add(firstGraph, 7, fR, 1, 13);
				
					
					
			        	
					
				}
				
				
				
				
			}
		);
			
	}
	
	
	
}