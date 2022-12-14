/*
 * Ashvin Loghashankar, Period 1
 * Program took: 1 hour, 30 mins
 * Reflection: This lab took me a bit longer than the previous lab, but this was because I procrastinated
 * it to Monday night and had to watch the videos to re-familiarize myself with the api. I forgot
 * how you would add controls and as a result of that, it took me some more time to remember what
 * was covered in the lecture in class on Friday. The videos on the slideshow were helpful for that.
 * In addition, I only had one issue which was the slider but I fixed that by making a second
 * checker to see if the slider was moving or not and based upon that I was able to complete this.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class P1_Loghashankar_Ashvin_GUI_4 extends Application{
	VBox vbox;
	HBox hbox1;
	HBox hbox2;
	HBox hbox3;
	HBox hbox4;
	HBox hbox5;
	Slider rating;
	CheckBox offer;
	Button button;
	TextField enter;
	boolean doneChanging;
	boolean ratedApp;
	boolean offerPlaced;
	
	public static void main(String[] args){
		launch();
	}
	
	public void start(Stage stage) throws Exception {

		
		Group group = new Group();
		Scene scene = new Scene(group,800,800);
		stage.setScene(scene);
		
		
		vbox = new VBox();
		Text title = new Text("HOUSE FOR SALE: 123 HOUSE LANE");
		title.setFont(new Font(title.getFont().getSize()*2));
		vbox.getChildren().add(title);
		vbox.setSpacing(15);
		hbox1 = new HBox();
		hbox1.setPadding(new Insets(10));
		hbox1.setSpacing(3);
		hbox2 = new HBox();
		hbox2.setPadding(new Insets(10));
		hbox3 = new HBox();
		hbox3.setPadding(new Insets(10));
		hbox3.setSpacing(4);
		hbox4 = new HBox();
		hbox4.setPadding(new Insets(10));
		hbox5 = new HBox();
		hbox5.setPadding(new Insets(10));
		hbox5.setSpacing(4);
		vbox.getChildren().add(hbox1);
		vbox.getChildren().add(hbox2);
		vbox.getChildren().add(hbox3);
		vbox.getChildren().add(hbox4);
		vbox.getChildren().add(hbox5);
		
		
		button = new Button("Done");
		button.setMinWidth(50);
		ButtonHandler b = new ButtonHandler();
		button.setOnAction(b);
		
		hbox5.getChildren().add(button);
		
		hbox2.getChildren().add(getGroup());
		
		offer = new CheckBox("Offer Placed");
		OfferSelected chad = new OfferSelected();
		offer.selectedProperty().addListener(chad);
		hbox3.getChildren().add(offer);
		
		Label askingOffer = new Label("Please enter your offer: $");
		hbox1.getChildren().add(askingOffer);
		
		enter = new TextField();
		hbox1.getChildren().add(enter);
		
		Label plsEnter = new Label("Please rate this application: ");
		hbox1.setSpacing(3);
		hbox4.getChildren().add(plsEnter);
		
		rating = new Slider();
		MyChangeListener bob = new MyChangeListener();
		rating.setMin(0);
		rating.setMax(5);
		rating.setMajorTickUnit(1);
		rating.setMinorTickCount(0);
		rating.setShowTickLabels(true);
		rating.setSnapToTicks(true);
		rating.setShowTickMarks(true);
		rating.valueProperty().addListener(bob);
		MyChangingListener adam = new MyChangingListener();
		rating.valueChangingProperty().addListener(adam);
		hbox4.getChildren().add(rating);
		
		
		group.getChildren().add(vbox);
		
		
		
		
		stage.show();
	}
	
	public Group getGroup() { //sun removed cuz its real estate ad
		Group group = new Group();
		double h = 120;
		double w = 120;
		
		Rectangle a= new Rectangle(w, h);
		a.setFill(Color.LIGHTSKYBLUE);
		group.getChildren().add(a);
		
		double houseW = w/2.0;
		double houseH = h/3.0;
		
		double houseX = houseW/2;
		double houseY = h-houseH*2;
		
		Rectangle b = new Rectangle(0,h/2 + houseH/2,w,h/2);
		b.setFill(Color.FORESTGREEN);
		group.getChildren().add(b);
				
		Rectangle c = new Rectangle(houseX,houseY,houseW,houseH);
		c.setFill(Color.TAN);
		group.getChildren().add(c);
		
		double doorW = houseW/5;
		double doorH = houseH/2;
		
		double doorX = houseX + houseW/2 - doorW/2;
		double doorY = houseY + houseY - doorH;
		Rectangle d = new Rectangle(doorX,doorY,doorW,doorH);
		d.setFill(Color.SADDLEBROWN);
		group.getChildren().add(d);
		
		double doorknobX = doorX + doorW*4/5;
		double doorknobY = doorY + doorH/2;
		
		double doorknobR = doorW/9;
		Circle e = new Circle(doorknobX, doorknobY, doorknobR);
		e.setFill(Color.GOLD);
		group.getChildren().add(e);
		
		for(int i =0; i<=1; i++) {
			double windowX = houseX + houseW/2.5 + Math.pow(-1, i)*houseW/5;
			double windowY = houseY + houseH/4;
			
			double windowWidth = houseW/6;
			double windowHeight = houseH/6;
			
			Rectangle f = new Rectangle(windowX,windowY,windowWidth,windowHeight);
			f.setFill(Color.WHITE);
			group.getChildren().add(f);
			
			double vertLineX = windowX + windowWidth/2;
			double horizLineY = windowY + windowHeight/2;
			
			Line g = new Line(windowX, horizLineY, windowX+windowWidth, horizLineY);
			Line l = new Line(vertLineX, windowY, vertLineX, windowY + windowHeight);
			group.getChildren().add(g);
			group.getChildren().add(l);
		}
		
		double triangleTopX = houseX + houseW/2;
		double triangleTopY = houseY - houseH/2;
		
		Polygon p = new Polygon(houseX, houseY, houseX+houseW, houseY, triangleTopX, triangleTopY);
		group.getChildren().add(p);
		
		return group;
	}

	private class OfferSelected implements ChangeListener<Boolean>{

		@Override
		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			Label t = new Label("Offer amount finalized.");
			hbox3.getChildren().add(t);
			offerPlaced = true;
		}
		
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if(offerPlaced && ratedApp) {
				Label t = new Label("Offer successfully submitted.");
				hbox5.getChildren().add(t);
			}
			else {
				System.out.println("Please try again");
				System.exit(0);
			}
			
		}
		
	}
	
	private class MyChangingListener implements ChangeListener<Boolean>{

		@Override
		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			if(doneChanging) return;
			if(!rating.isValueChanging()) {
				doneChanging = true;
			}
		}
		
	}
	
	
	private class MyChangeListener implements ChangeListener<Number>{

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if(doneChanging) {
				Label t = new Label("You rated this app with a " + newValue);
				hbox4.getChildren().add(t);
				ratedApp = true;
			}
			else return;
			
		}
		
	}
}
