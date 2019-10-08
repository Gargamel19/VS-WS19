package trendelenburg.de;

import edu.cads.bai5.vsp.tron.view.ITronView;
import edu.cads.bai5.vsp.tron.view.TronView;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MainApp extends Application {

    public final static String VIEW_CONFIG_FILE = "view.properties";
    public static List<Biker> biker = new ArrayList<Biker>();

    public static final int TICKRATE = 300;

    private static ITronView view = null;
    private static Stage stage = null;

    private static boolean started = false;

    private static ReStartMenu reStartMenu;

    private static TickThread tickThread;

    public static int ROWS;
    public static int COLUMNS;

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

        // Build and register the Restart Menu
        reStartMenu = new ReStartMenu("menu.css", view);
        view.registerOverlay("REVANTSCHE?", reStartMenu);

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

    public static void main(String[] args) {

        //Loading Inforamtions from Properties File
            //ROWS & COLUMNS
        try{
            Properties prop = new Properties();
            prop.load(new FileInputStream(VIEW_CONFIG_FILE));

            ROWS = Integer.parseInt(prop.getProperty("rows"));
            COLUMNS = Integer.parseInt(prop.getProperty("columns"));

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

        biker = new ArrayList<>();

        Biker bike1 = new Biker(Math.round(COLUMNS*2/3), Math.round(ROWS/2), Color.RED, "Player 1");
        Biker bike2 = new Biker(Math.round(COLUMNS/3), Math.round(ROWS/2), Color.YELLOW, "Player 2");

        biker.add(bike1);
        biker.add(bike2);

        new BikeController("w", "s", "a", "d", bike2, "p", "ö", "l", "ä", bike1, stage);


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

        view.showOverlay("REVANTSCHE?");

        setupGame();

    }
}
