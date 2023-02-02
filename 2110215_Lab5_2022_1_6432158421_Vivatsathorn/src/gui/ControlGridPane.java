package gui;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import logic.GameLogic;
import logic.SquareMark;
import logic.SquareState;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class ControlGridPane extends GridPane {
	private final String miningImageURL;
	private ControlPane controlPane;
	
	public ControlGridPane(ControlPane controlPane) {
		miningImageURL = "bitcoin.png";
		ImageView imageView = new ImageView(this.miningImageURL);
		imageView.setFitWidth(150);
		imageView.setFitHeight(150);
		
		BorderPane bPane = new BorderPane();
		bPane.setPrefSize(150, 200);
		bPane.setCenter(imageView);
		
		super.add(bPane, 0, 0);
		super.add(controlPane, 0, 1);
		super.setAlignment(Pos.TOP_CENTER);
	}
}
