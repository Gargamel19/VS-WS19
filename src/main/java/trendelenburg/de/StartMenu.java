package trendelenburg.de;

import edu.cads.bai5.vsp.tron.view.ITronView;
import edu.cads.bai5.vsp.tron.view.ViewUtility;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static trendelenburg.de.MainApp.startPlaying;

public class StartMenu extends VBox {
    private final ITronView view;
    private final Label labelReady;
    private final Button btnAddPlayer;
    private final Button btnStart;

    public StartMenu(String stylesheet, ITronView view) {
        super(20.0);

        AddPlayerMenu addPlayerMenu = new AddPlayerMenu(stylesheet, view);
        view.registerOverlay("addPlayerMenu", addPlayerMenu);

        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        this.view = view;

        labelReady = new Label("Ready?");
        labelReady.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        btnAddPlayer = new Button("Add Player");
        btnAddPlayer.setOnAction(event -> {
            view.hideOverlay("start");
            view.showOverlay("addPlayerMenu");
        });

        btnStart = new Button("Start Game");
        btnStart.setOnAction(event -> {
            System.out.println("click!");
            view.hideOverlays();
            startPlaying();
        });

        this.getChildren().add(labelReady);
        this.getChildren().add(btnAddPlayer);
        this.getChildren().add(btnStart);
    }
}
