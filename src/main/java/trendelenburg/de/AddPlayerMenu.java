package trendelenburg.de;

import edu.cads.bai5.vsp.tron.view.ITronView;
import edu.cads.bai5.vsp.tron.view.ViewUtility;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Random;

public class AddPlayerMenu extends VBox {
    private final ITronView view;
    private final Button btnBack;
    private final Button btnAdd;
    private Label labelPlayerName;
    private TextField textPlayerName;
    private Button btnNewColor;
    private Label labelPlayerColor;
    Color color;

    public AddPlayerMenu(String stylesheet, ITronView view) {
        super(20.0);
        this.getStylesheets().add(stylesheet);
        this.setAlignment(Pos.CENTER);

        this.view = view;

        btnNewColor = new Button();
        btnNewColor.setText("new Color:");
        btnNewColor.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        labelPlayerColor = new Label();
        labelPlayerColor.setText("COLOR");
        makeNewColor();
        labelPlayerColor.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(color) + ";");

        btnNewColor.setOnAction(event -> {
            makeNewColor();
        });


        labelPlayerName = new Label();
        labelPlayerName.setText("Player Name:");
        labelPlayerName.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(Color.PAPAYAWHIP.brighter()) + ";");

        textPlayerName = new TextField();
        textPlayerName.getStyleClass().add("my-name");

        btnAdd = new Button("Add New Player");
        btnAdd.setOnAction(event -> {
            if(!textPlayerName.getText().isEmpty()){
                Random random = new Random();
                int x = random.nextInt(MainApp.COLUMNS-Math.round(MainApp.COLUMNS/5)) + MainApp.COLUMNS/5/2;
                int y = random.nextInt(MainApp.ROWS-Math.round(MainApp.ROWS/5)) + MainApp.ROWS/5/2;
                MainApp.biker.add(new Biker(x, y, color, textPlayerName.getText()));

                Label lbl = new Label();
                lbl.setText(textPlayerName.getText());
                lbl.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(color) + ";");
                this.getChildren().add(lbl);
                textPlayerName.setText("");
                makeNewColor();
            }
        });

        btnBack = new Button("Back");
        btnBack.setOnAction(event -> {
            view.hideOverlay("addPlayerMenu");
            view.showOverlay("start");
        });

        this.getChildren().add(labelPlayerName);
        this.getChildren().add(textPlayerName);
        this.getChildren().add(btnNewColor);
        this.getChildren().add(labelPlayerColor);
        this.getChildren().add(btnAdd);
        this.getChildren().add(btnBack);
    }

    private void makeNewColor(){
        color = Color.color(Math.random(), Math.random(), Math.random());
        labelPlayerColor.setStyle("-fx-text-fill: " + ViewUtility.getHexTriplet(color) + ";");
    }
}
