import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.sun.javafx.scene.control.IntegerField;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class MinesweeperView {
	MinesweeperController controller;
	Stage stage;
	String title;
	Image[] images;

	
	//MinesweeperView initiates constructor to make view
	public MinesweeperView() {
		setPictures();
	}
	
	//Set game parameters
	public void SetOptions(Stage topLevelStage, MinesweeperController controller, String title) {
		this.stage = topLevelStage;
		this.controller = controller;
		this.title = title;
	}
	
	//Load pictures to images array
	public void setPictures() {
		this.images = new Image[12];
		
		for(int i = 0; i<12; i++) {
			String name ="images/" + i + ".png";
			images[i] = new Image(name);
			
		}
	}
	
	/*
	 * Load pictures from view to game. If out of bounds, last index is returned.
	 * Input: x (array index)
	 * Output: Image from array index (x)
	 */
	public Image getPicture(int x) {
		if (!(x<this.images.length)||(x<0)) {
			x = this.images.length-1;
		}
		
		return images[x];
	}
	
	//Main menu Stage
	public Stage mainMenu() {
		this.stage.setTitle("Minesweeper");
		
		
		//New game button
		Button newGameButton = new Button();
		newGameButton.setText("New game");
		newGameButton.setOnAction(e -> mainSetup());
		
			
		
		StackPane layout = new StackPane();
		layout.getChildren().add(newGameButton);
		
		Scene scene = new Scene(layout, 800, 600);
		stage.setScene(scene);
		return stage;
	}
	public Stage mainSetup() {
		this.stage.setTitle("Minesweeper (Setup Game)");
		
		
		//Easy button
		Button easyButton = new Button();
		easyButton.setText("Easy");
		easyButton.setOnAction(e -> controller.gotoNewGame(10,10,10));
		
	   
		//Medion button
		Button mediumButton = new Button();
		mediumButton.setText("Medium");
		mediumButton.setOnAction(e -> controller.gotoNewGame(16,16,40));
	   
		
		//Hard button
		Button hardButton = new Button();
		hardButton.setText("Hard");
		hardButton.setOnAction(e -> controller.gotoNewGame(30,16,99));
	   
		
		//Hard button
		Button customButton = new Button();
		customButton.setText("Custom");
		customButton.setOnAction(e -> customizeGame());
		
									
		
		VBox layout = new VBox();
		layout.getChildren().addAll(easyButton, mediumButton, hardButton,customButton);
		
		Scene scene = new Scene(layout, 800, 600);
		stage.setScene(scene);
		return stage;
	}
	
	
	
	
	//Sets the stage
	public Stage basicGame() {
		this.stage.setTitle(title);
		StackPane layout = new StackPane();
		layout.getChildren().add(controller.getGrid());
		Scene scene = new Scene(layout, 800, 600);
		//Scene scene = new Scene(layout, 23*controller.model.getm(), 25*controller.model.getn());
		stage.setScene(scene);
		return stage;
	}
	
	/*
	 * Open game over window.
	 * Input: Title and message to display.
	 * Output: new Stage with game over window. This window contains a button, if pressed begins a new game.
	 */
	
	public void alertBox(String title, String text) {
		//controller.clearButtonAction();
		Stage window = new Stage();
		window.setTitle(title);
		window.setOnCloseRequest(e -> controller.gotoMainMenu());
		//Force user to interact with window
		window.initModality(Modality.APPLICATION_MODAL);
		
		//
		Label label = new Label();
		label.setText(text);
		
		//button (Begin new game)
		Button button = new Button();
		button.setText("Main Menu");
		button.setOnAction(e -> {
			controller.gotoMainMenu();
			window.close();
		});
		
		
		
		//Layout
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, button);
		layout.setMinWidth(200);
		layout.setAlignment(Pos.CENTER);
		Scene scene= new Scene(layout);
		window.setScene(scene);
		window.show();
		// When window is closed. opens a new game.
		
	}

	public Stage customizeGame() {
		this.stage.setTitle("Minesweeper (Custom Game)");
		
		IntegerField n = new IntegerField();
		IntegerField m = new IntegerField();
		IntegerField bombs = new IntegerField();
		
		//New game button
		Button newGameButton = new Button();
		newGameButton.setText("Start game");
		newGameButton.setOnAction(e ->  controller.gotoNewGame(n.getValue(),m.getValue(),bombs.getValue()));
									
		
		VBox layout = new VBox();
		layout.getChildren().addAll(n,m,bombs,newGameButton);
		
		Scene scene = new Scene(layout, 800, 600);
		stage.setScene(scene);
		return stage;
	}


}
	