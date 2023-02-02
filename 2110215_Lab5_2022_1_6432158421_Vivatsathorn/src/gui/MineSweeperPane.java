package gui;

import java.util.ArrayList;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import logic.GameLogic;
import logic.SquareMark;
import logic.SquareState;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MineSweeperPane extends GridPane {
	private ArrayList<MineSweeperSquare> allCells;

	public MineSweeperPane() {
		this.setAllCells(new ArrayList<MineSweeperSquare>());

		super.setHgap(8);
		super.setVgap(8);
		
		super.setPadding(new Insets(8));
		super.setPrefWidth(500);
		super.setAlignment(Pos.CENTER);

		BorderStroke bStroke = new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				BorderWidths.DEFAULT);
		super.setBorder(new Border(bStroke));

		BackgroundFill bgFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
		this.setBackground(new Background(bgFill));

		for (int i = 0; i < 25; ++i) {
			MineSweeperSquare square = new MineSweeperSquare(i % 5, i / 5);
			this.allCells.add(square);
			super.add(square, square.getxPosition(), square.getyPosition());
		}

	}

	public ArrayList<MineSweeperSquare> getAllCells() {
		return allCells;
	}

	private void setAllCells(ArrayList<MineSweeperSquare> allCells) {
		this.allCells = allCells;
	}
}
