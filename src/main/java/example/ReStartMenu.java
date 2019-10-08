package example;

import edu.cads.bai5.vsp.tron.view.ITronView;
import edu.cads.bai5.vsp.tron.view.ViewUtility;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ReStartMenu extends VBox {
    private final ITronView view;
    private final Button btnStart;
    private Label labelReady;

    public ReStartMenu(String stylesheet, ITronView view) {
        super(20.0);
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        this.view = view;

        labelReady = new Label("RAVANTSCHE?");
        labelReady.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        btnStart = new Button("REVANTSCHE");
        btnStart.setOnAction(event -> {
            view.clear();
            System.out.println("click!");
            view.hideOverlays();
            MainApp.startPlaying();
        });
        this.getChildren().add(labelReady);
        this.getChildren().add(btnStart);
    }
}
