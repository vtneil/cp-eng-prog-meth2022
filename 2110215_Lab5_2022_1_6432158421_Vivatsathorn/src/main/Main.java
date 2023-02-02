package main;

import gui.ControlGridPane;
import gui.ControlPane;
import gui.MineSweeperPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.GameLogic;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		MineSweeperPane mineSweeperPane = new MineSweeperPane();
		ControlPane controlPane = new ControlPane(mineSweeperPane);
		ControlGridPane controlGridPane = new ControlGridPane(controlPane);
		
		HBox hbMain = new HBox();
		hbMain.setAlignment(Pos.CENTER);
		hbMain.setPadding(new Insets(10));
		hbMain.setPrefSize(800, 400);
		
		hbMain.getChildren().add(mineSweeperPane);
		hbMain.getChildren().add(controlGridPane);
		
		Scene scene = new Scene(hbMain);
		
		GameLogic.getInstance().setControlPane(controlPane);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("MineSweeper");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
