package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logic.GameLogic;
import logic.SquareMark;
import logic.SquareState;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

public class MineSweeperSquare extends Pane {
	private final String oURL;
	private final String oneURL;
	private final String mineURL;
	private final String flagURL;

	private boolean isDrawn;
	private Color baseColor;
	private int xPosition;
	private int yPosition;

	public MineSweeperSquare(int x, int y) {
		oURL = "o.png";
		oneURL = "one.png";
		mineURL = "mine.png";
		flagURL = "flag.png";

		this.setxPosition(x);
		this.setyPosition(y);

		super.setPrefSize(100, 100);

		super.setMinSize(100, 100);

		this.setBaseColor(Color.MOCCASIN);
		this.initializeCellColor();
		super.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				onClickHandler();
			}
		});
	}

	public void onClickHandler() {
		if (GameLogic.getInstance().isGameEnd())
			return;
		if (!GameLogic.getInstance().isSecureMode()) {
			if (GameLogic.getInstance().getBoardState()[xPosition][yPosition] != SquareState.REVEALED) {
				switch (GameLogic.getInstance().getBoardMark()[xPosition][yPosition]) {
				case ONE: {
					this.draw(new Image(oneURL), Color.ORANGE);
					break;
				}
				case MINE: {
					this.draw(new Image(mineURL), Color.RED);
					break;
				}
				case NOTHING: {
					this.draw(new Image(oURL), Color.YELLOW);
					break;
				}
				default:
					break;
				}
				GameLogic.getInstance().updateState(xPosition, yPosition, SquareState.REVEALED);
			}
		} else {
			if (!this.isDrawn) {
				this.draw(new Image(flagURL), Color.GREEN);
				GameLogic.getInstance().updateState(xPosition, yPosition, SquareState.SECURED);
			}
		}
	}

	public void initializeCellColor() {
		BackgroundFill bgFill = new BackgroundFill(this.baseColor, CornerRadii.EMPTY, Insets.EMPTY);
		this.setBackground(new Background(bgFill));
		this.setDrawn(false);
	}

	private void draw(Image image, Color backgroundColor) {
		BackgroundFill bgFill = new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		BackgroundSize bgSize = new BackgroundSize(100, 100, false, false, false, false);
		BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
		BackgroundImage[] bgImgA = { bgImg };
		this.setBackground(new Background(bgFillA, bgImgA));
		this.setDrawn(true);
	}

	public boolean isDrawn() {
		return isDrawn;
	}

	public void setDrawn(boolean isDrawn) {
		this.isDrawn = isDrawn;
	}

	public Color getBaseColor() {
		return baseColor;
	}

	public void setBaseColor(Color baseColor) {
		this.baseColor = baseColor;
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
}
