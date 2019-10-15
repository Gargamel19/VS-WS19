package trendelenburg.de;

import edu.cads.bai5.vsp.tron.view.ITronView;
import edu.cads.bai5.vsp.tron.view.TronView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import trendelenburg.de.Utils.BikerUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class MainApp extends Application {

    public final static String VIEW_CONFIG_FILE = "view.properties";
    public static List<Biker> biker = new ArrayList<>();

    private static ITronView view = null;
    private static Stage stage = null;

    private static boolean started = false;

    private static ReStartMenu reStartMenu;

    private static TickThread tickThread;

    public static int ROWS;
    public static int COLUMNS;
    public static int TICKRATE = 300;

    public static boolean isStarted() {
        return started;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        view = new TronView(VIEW_CONFIG_FILE);

        // Build and register start menu
        StartMenu startMenu = new StartMenu("menu.css", view);
        view.registerOverlay("start", startMenu);



        // init view and show start menu
        view.init();
        view.showOverlay("start");

        // configure and show stage
        stage.setTitle("TRON - Light Cycles");
        stage.setScene(view.getScene());

        //setting up the Game-settings
        setupGame();
        stage.show();

    }

    public static void showRestartMenue(Biker biker){
        Platform.runLater(() -> {

            reStartMenu = new ReStartMenu("menu.css", view, biker);
            view.registerOverlay("REVANTSCHE?", reStartMenu);
            view.showOverlay("REVANTSCHE?");
        });
    }

    public static void main(String[] args) {

        //Loading Inforamtions from Properties File
            //ROWS & COLUMNS
        try{
            Properties prop = new Properties();
            prop.load(new FileInputStream(VIEW_CONFIG_FILE));

            ROWS = Integer.parseInt(prop.getProperty("rows"));
            COLUMNS = Integer.parseInt(prop.getProperty("columns"));
            TICKRATE = Integer.parseInt(prop.getProperty("ticks"));

        }catch (FileNotFoundException fnfe){
            System.out.println("File: \"" + VIEW_CONFIG_FILE + "\" not found ");
        }catch (IOException e){
            System.out.println("Error on reading File: \"" + VIEW_CONFIG_FILE + "\"");
        }

        launch(args);
    }

    /**
     * setting up the Game with ArrayList witch holds the Biker
     * setting the Bikes in the middle of the Feeld, and Initializing the BikeController
     */
    public static void setupGame(){

        ArrayList<Biker> tempBiker = new ArrayList<>();
        Random random = new Random();
        for (Biker biker: biker){
            int x = random.nextInt(MainApp.COLUMNS-Math.round(MainApp.COLUMNS/5)) + MainApp.COLUMNS/5/2;
            int y = random.nextInt(MainApp.ROWS-Math.round(MainApp.ROWS/5)) + MainApp.ROWS/5/2;
            tempBiker.add(new Biker(x, y, biker.getPlayerColor(), biker.getName()));
        }
        biker=tempBiker;

        view.clear();

        new BikeController("w", "s", "a", "d", "p", "ö", "l", "ä", stage);


    }

    /**
     * Creating a "TickThread", witch represents the Haerdbeat of the Game
     */
    public static void startPlaying(){
        started = true;
        tickThread = new TickThread(view);
        tickThread.start();
    }

    /**
     * stopping the "TickThread"
     */
    public static void stopPlaying(){
        started = false;
        tickThread.interrupt();

    }

    /**
     * is preparing the Game for a new Round and shows the Overlay between the Rounds
     */
    public static void restartPlaying(){
        stopPlaying();


        if(BikerUtil.getAmmoundOfAlives()==1){
            showRestartMenue(BikerUtil.getlastAliveBiker());
        }else{
            showRestartMenue(null);
        }

        setupGame();

    }
}
