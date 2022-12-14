/*
 * Ashvin Loghashankar, Period 1, 3/25/20
 * Program took: 1 hour, 30 mins
 * Reflection: I did not find this lab a lot more difficult than some of the previous labs
 * but this was largely because some of the labs were quite difficult. I had some errors with the
 * typecasting in the timeListener converting to long, but I was able to fix this once I researched
 * about the number class and how it should be converted to an integer in order to finish the computation
 * with the long variable. I also had an error with my logic which took me a while to find out
 * but it was just that i used the pressedProperty instead of the selectedProperty. 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class P1_Loghashankar_Ashvin_LifeGUI_2 extends Application implements GenerationListener{

	P1_Loghashankar_Ashvin_LifeModel model;
	BooleanGridPane view;
	Button clear;
	Menu menu;
	MenuBar menubar;
	MenuItem open;
	MenuItem save;
	Button nextGeneration;
	Label generationNumber;
	ToggleButton autoGen;
	Slider size;
	Stage stage;
	Slider time;
	long timeDif;
	int count;

	public static void main(String[] args) {
		launch();
	}

	public void start(Stage stage) throws Exception {		
		BorderPane bp = new BorderPane();
		this.stage = stage;

		Scene scene = new Scene(bp,1000,1000);
		stage.setScene(scene);

		menubar = new MenuBar();
		menu = new Menu("File");
		open = new MenuItem("Open");
		save = new MenuItem("Save");
		menu.getItems().add(open);
		menu.getItems().add(save);
		menubar.getMenus().add(menu);
		autoGen = new ToggleButton("Select this button to go automatically.");
		bp.setTop(menubar);

		HBox bottom = new HBox();
		bottom.setPadding(new Insets(10));
		bottom.setSpacing(35);
		bp.setBottom(bottom);

		clear = new Button("clear");
		nextGeneration = new Button("next generation");
		ButtonHandler b = new ButtonHandler();
		open.setOnAction(b);
		save.setOnAction(b);
		clear.setOnAction(b);
		nextGeneration.setOnAction(b);
		size = new Slider();

		generationNumber = new Label("Generation 0");

		size.setMin(20);
		size.setMax(100);
		size.setMajorTickUnit(20);
		size.setMinorTickCount(2);
		size.setShowTickLabels(true);
		size.setSnapToTicks(true);
		size.setShowTickMarks(true);
		MyChangeListener bob = new MyChangeListener();
		size.valueProperty().addListener(bob);
		
		time = new Slider();
		time.setMin(100);
		time.setMax(1000);
		time.setMajorTickUnit(300);
		time.setMinorTickCount(3);
		time.setShowTickLabels(true);
		time.setSnapToTicks(true);
		time.setShowTickMarks(true);
		MyTimeListener joe = new MyTimeListener();
		time.valueProperty().addListener(joe);
		
		
		ToggleChangeListener d = new ToggleChangeListener();
		autoGen.selectedProperty().addListener(d);

		bottom.getChildren().add(clear);
		bottom.getChildren().add(nextGeneration);
		bottom.getChildren().add(generationNumber);
		bottom.getChildren().add(size);
		bottom.getChildren().add(autoGen);
		bottom.getChildren().add(time);

		Boolean[][] arr = { {true, true, true, true, true}, {true, true, true, true, true}, {true, true, false, true, true}, {true, true, true, true, true}, {true, true, true, true, true}};

		model = new P1_Loghashankar_Ashvin_LifeModel(arr);
		model.addGenerationListener(this);
		view = new BooleanGridPane();
		view.setModel(model);
		GridHandler a = new GridHandler();
		view.setOnMouseDragged(a);
		view.setOnMousePressed(a);


		bp.setCenter(view);
		bp.setAlignment(view, Pos.CENTER);


		stage.show();

	}

	private class GridHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {

			if(!event.getEventType().equals(MouseEvent.MOUSE_DRAGGED) && !event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
				return;
			}


			if(event.getButton() == MouseButton.PRIMARY) {
				int row = view.rowForYPos(event.getY());
				int col = view.colForXPos(event.getX());
				model.setValueAt(row,col,true);
			}
			else {
				int row = view.rowForYPos(event.getY());
				int col = view.colForXPos(event.getX());
				model.setValueAt(row,col,false);
			}
			// TODO Auto-generated method stub
			int row = view.rowForYPos(event.getY());
			int col = view.colForXPos(event.getX());
			int totalCol = model.getNumCols();
			int totalRow = model.getNumRows();
			int tempRow;
			int tempCol;
			for(int i = 0; i<9; i++) {
				if(i == 4) continue;
				tempRow = i/3 + row - 1;
				tempCol = i%3 + col - 1;
				if(tempRow >=0 && tempRow<totalRow && tempCol >= 0 && tempCol<totalCol) {
					model.setValueAt(tempRow,tempCol,!model.getValueAt(tempRow, tempCol));
				}
			}

		}	

	}

	private class ButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if(event.getSource() == open) {
				model.setGeneration(0);
				FileChooser f = new FileChooser();
				f.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
				File selectedFile = f.showOpenDialog(stage);
				Scanner scanner;
				try{
					scanner = new Scanner(selectedFile);
				}
				catch(FileNotFoundException e) {
					return;
				}
				int numRows = scanner.nextInt();
				int numColumns = scanner.nextInt();
				System.out.println(numRows + " " + numColumns);
				Boolean[][] arr = new Boolean[numRows][numColumns];
				for(int i = 0; i<numRows; i++) {
					for(int j = 0; j<numColumns; j++) {
						if(scanner.next().equals("X")) {
							arr[i][j] = true;
						}
						else {
							arr[i][j] = false;
						}
					}
				}

				model.setGrid(arr);

			}
			else if(event.getSource() == save) {
				FileWriter f = null;
				try{
					f = new FileWriter("save.txt");
					f.write(model.getNumRows());
					f.write(" " + model.getNumCols());
					for(int i = 0; i<model.getNumRows(); i++) {
						for(int j = 0; j<model.getNumCols(); j++) {
							if(model.getValueAt(i, j)) {
								f.write("X ");
							}
							else {
								f.write("O ");
							}
						}
						f.write("\n");
					}
					f.close();
				}
				catch(IOException e) {
					return;
				}


			}

			else if(event.getSource() == clear){
				Boolean[][] arr = {{false, false, false, false, false},{false, false, false, false, false},{false, false, false, false, false},{false, false, false, false, false},{false, false, false, false, false}};
				model.setGeneration(0);
				model.setGrid(arr);
			}
			else if(event.getSource() == nextGeneration) {
				model.setGeneration(model.getGeneration()+1);
			}
		}

	}


	private class MyTimer extends AnimationTimer{

		long previousTime = 0;

		@Override
		public void handle(long now) {
			while(autoGen.isSelected()) {
				
				if(now - previousTime > timeDif) {
					previousTime = now;
					model.setGeneration(model.getGeneration()+1);
				}
				else {
					break;
				}
			}
		}

	}

	private class MyChangeListener implements ChangeListener<Number>{

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			view.setTileSize(size.getValue());
		}

	}
	
	private class MyTimeListener implements ChangeListener<Number>{

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			long mils = 1000000l;
			int value = newValue.intValue();
			timeDif = value * mils;
		}
	
	}
	
	private class ToggleChangeListener implements ChangeListener<Boolean>{

		@Override
		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			new MyTimer().start();
		}
		
	}



	public void generationChanged(int oldValue, int newValue) {
		generationNumber.setText("Generation " + newValue);
		model.nextGeneration();
	}
}

