package gui;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameLogic;
import logic.SquareMark;
import logic.SquareState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class ControlPane extends VBox {
	private Text gameText;
	private Button newGameButton;
	private Button secureModeButton;

	private MineSweeperPane mineSweeperPane;

	public ControlPane(MineSweeperPane mineSweeperPane) {
		this.setMineSweeperPane(mineSweeperPane);

		super.setAlignment(Pos.CENTER);
		super.setPrefWidth(300);
		super.setSpacing(20);

		this.initializeGameText();
		this.initializeNewGameButton();
		this.initializeSecureModeButton();

		super.getChildren().addAll(this.gameText, this.newGameButton, this.secureModeButton);

	}

	private void initializeGameText() {
		String tileText = "Tiles left : ";
		tileText += GameLogic.getInstance().getTileCount();
		Text text = new Text(tileText);
		text.setFont(Font.font("Arial", FontWeight.NORMAL, 35));
		this.setGameText(text);
	}

	public void updateGameText(String text) {
		gameText.setText(text);
	}

	private void initializeNewGameButton() {
		Button btn = new Button("New Game");
		btn.setPrefWidth(100);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				newGameButtonHandler();
			}
		});
		this.setNewGameButton(btn);
	}

	private void initializeSecureModeButton() {
		Button btn = new Button("Secure Mode : OFF");
		btn.setPrefWidth(150);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				secureModeButtonHandler();
			}
		});
		this.setSecureModeButton(btn);
	}

	private void newGameButtonHandler() {
		GameLogic.getInstance().newGame();
		this.secureModeButton.setText("Secure Mode : OFF");
		this.gameText.setText("Tiles left : " + GameLogic.getInstance().getTileCount());
		for (MineSweeperSquare square : this.mineSweeperPane.getAllCells()) {
			square.initializeCellColor();
		}
	}

	private void secureModeButtonHandler() {
		GameLogic.getInstance().toggleSecureMode();
		secureModeButton.setText(GameLogic.getInstance().isSecureMode() ? "Secure mode : ON" : "Secure mode : OFF");
	}

	private void setGameText(Text gameText) {
		this.gameText = gameText;
	}

	private void setNewGameButton(Button newGameButton) {
		this.newGameButton = newGameButton;
	}

	private void setSecureModeButton(Button secureModeButton) {
		this.secureModeButton = secureModeButton;
	}

	private void setMineSweeperPane(MineSweeperPane mineSweeperPane) {
		this.mineSweeperPane = mineSweeperPane;
	}
}
