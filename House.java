/*
 * Ashvin Loghashankar, Period 1
 * Program took: 45 minutes
 * Reflection: I did not find this lab wayy too difficult. It took me some time at first because
 * I was not sure how the api worked but I looked at the slides and remembered what we did in class
 * and was able to continue. Also, I didnt initially scale so I had to restart and make sure that
 * everything would be scaled based upon the height and the width of the scale so that there is not
 * an abnormally large or small house. I decided on the house as a throwback to the house project
 * so that I could do something similar, but using different tools and skills with javafx instead 
 * of gpdraw. I like javafx more than gpdraw.
 */
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class House extends Application{
	
	private Group group = new Group();
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Scene scene = new Scene(group,800,800);
		stage.setScene(scene);
		double h = scene.getHeight();
		double w = scene.getWidth();
		
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
		
		Circle sun = new Circle(w/6);
		sun.setFill(Color.YELLOW);
		group.getChildren().add(sun);
		
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
		
	}
	
	public Group getGroup() {
		return group;
	}
	
	
}
