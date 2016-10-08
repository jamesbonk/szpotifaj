package Szpotifaj;
	
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	double width = dim.getWidth();
	double height = dim.getHeight()-70;
	MenuButton file,edit,view,help;
	FlowPane centerPane;
	HBox downPane;
	GridPane upPane;
	VBox leftPane, rightPane;
	BorderPane borderPane;
	Listener listener;
	ToggleButton playlisty, przegladaj, utwory, albumy, wykonawcy;
	TextField szukaj;
	ProgressBar musicBar;
	Icons icons;
	Button chevronLeftButton,chevronRightButton;
	
	@Override
	
	public void start(Stage primaryStage) 
	{
		primaryStage.setTitle("Szpotifaj");
		borderPane = new BorderPane();
		listener = new Listener(this);
		icons = new Icons();
		
		//left panel//
		setLeftButtons();
		
		//up panel//
		setUpButtons();
			
			
		//center panel
		centerPane = new FlowPane();
		centerPane.setId("centerPane");
		borderPane.setCenter(centerPane);
		
		
		//right panel
		rightPane = new VBox();
		rightPane.setId("rightPane");
		borderPane.setRight(rightPane);
		
		
		//bottom panel
		musicBar = new ProgressBar();
		musicBar.setMinWidth(1000);
		musicBar.setId("musicBar");
		Label lab = new Label("Przelaczanieblablablablablablabla");
		downPane = new HBox(lab,musicBar);
		downPane.setId("downPane");
		borderPane.setBottom(downPane);
		
		//ustawianie sceny
		Scene scene = new Scene(borderPane,width,height);
		
		String cssPath = this.getClass().getResource("application.css").toExternalForm();
		String iconPath = this.getClass().getResource("App-Spotify-icon.png").toExternalForm();
		scene.getStylesheets().add(cssPath);
		primaryStage.getIcons().add(new Image(iconPath));
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void setUpButtons()
	{
		file = new MenuButton("File");
		file.setId("UpPanelMenu");
		edit = new MenuButton("Edit");
		edit.setId("UpPanelMenu");
		view = new MenuButton("View");
		view.setId("UpPanelMenu");
		help = new MenuButton("Help");
		help.setId("UpPanelMenu");
		szukaj = new TextField("   Szukaj");
		szukaj.setId("szukaj");
		
		chevronLeftButton = new Button("",icons.chevronLeft);
		chevronLeftButton.setId("chevronButtonLeft");
		
		chevronRightButton = new Button("",icons.chevronRight);
		chevronRightButton.setId("chevronButtonRight");
			
		upPane = new GridPane();
		upPane.setId("upPane");
			
		upPane.add(file, 0, 0, 1, 1);
		upPane.add(edit, 1, 0, 1, 1);
		upPane.add(view, 2, 0, 1, 1);
		upPane.add(help, 3, 0, 1, 1);
		upPane.add(szukaj, 3, 1, 1, 1);
		upPane.add(icons.search, 3, 1, 1, 1);
		upPane.add(chevronLeftButton, 0, 1, 1, 1);
		upPane.add(chevronRightButton, 1, 1, 1, 1);
			
		borderPane.setTop(upPane);
	}
	
	public void setLeftButtons()
	{
		Label menuLabel = new Label("Menu G��wne");
		menuLabel.setId("menuLabel");
		
		ToggleGroup menuGroup = new ToggleGroup();
		
		przegladaj = new ToggleButton("Przegl�daj",icons.archive);
		przegladaj.setToggleGroup(menuGroup);
		przegladaj.setId("menuButtonsClicked");
		przegladaj.setOnAction(listener);
		
		utwory = new ToggleButton("  Utwory",icons.note);
		utwory.setToggleGroup(menuGroup);
		utwory.setId("menuButtons");
		utwory.setOnAction(listener);
		
		albumy = new ToggleButton("  Albumy",icons.folderOpen); 
		albumy.setToggleGroup(menuGroup);
		albumy.setId("menuButtons");
		albumy.setOnAction(listener);
		
		wykonawcy = new ToggleButton("  Wykonawcy", icons.mic); 
		wykonawcy.setToggleGroup(menuGroup);
		wykonawcy.setId("menuButtons");
		wykonawcy.setOnAction(listener);
		
		playlisty = new ToggleButton("  Playlisty",icons.music);
		playlisty.setToggleGroup(menuGroup);
		playlisty.setId("menuButtons");
		playlisty.setOnAction(listener);
		
		listener.setFirstClicked();
		
		Button nowaPlaylista = new Button("  Nowa playlista",icons.plusSquare);
		nowaPlaylista.setId("nowaPlaylistaButton");
		
		leftPane = new VBox(menuLabel,przegladaj,utwory,albumy,wykonawcy,playlisty,nowaPlaylista);
		leftPane.setId("leftPane");
		
		borderPane.setLeft(leftPane);
	}
}