package trendelenburg.de.Utils;

import edu.cads.bai5.vsp.tron.view.Coordinate;
import trendelenburg.de.Biker;
import trendelenburg.de.MainApp;

/**
 * a UTIL class to compute with the Biker Instances
 */

public class UtilBiker {

    /**
     * move the Player one tick in the Direction of the BIKER.getDirection()
     * @param biker the Biker
     */
    public static void computeBiker(Biker biker) {
        switch (biker.getDirection()) {
            case UP:
                moveUp(biker);
                break;
            case DOWN:
                moveDown(biker);
                break;
            case LEFT:
                moveLeft(biker);
                break;
            case RIGHT:
                moveRight(biker);
                break;
        }
    }

    /**
     * insert the next Position of BIKER in the Direction "UP" in the track-List
     * @param biker the Biker
     */
    private static void moveUp(Biker biker){
        Coordinate coordinate = new Coordinate(biker.getLastPosition().x, biker.getLastPosition().y - 1);
        for (Biker eachBiker: MainApp.biker) {
            if(eachBiker.getTrail().contains(coordinate) || isOutside(coordinate)){
                playerDie(biker);
            }
        }
        biker.addPosition(coordinate);
    }

    /**
     * insert the next Position of BIKER in the Direction "DOWN" in the track-List
     * @param biker the Biker
     */
    private static void moveDown(Biker biker){
        Coordinate coordinate = new Coordinate(biker.getLastPosition().x, biker.getLastPosition().y + 1);
        for (Biker eachBiker: MainApp.biker) {
            if(eachBiker.getTrail().contains(coordinate) || isOutside(coordinate)){
                playerDie(biker);
            }
        }
        biker.addPosition(coordinate);


    }

    /**
     * insert the next Position of BIKER in the Direction "LEFT" in the track-List
     * @param biker the Biker
     */
    private static void moveLeft(Biker biker){
        Coordinate coordinate = new Coordinate(biker.getLastPosition().x - 1, biker.getLastPosition().y);
        for (Biker eachBiker: MainApp.biker) {
            if(eachBiker.getTrail().contains(coordinate) || isOutside(coordinate)){
                playerDie(biker);
            }
        }
        biker.addPosition(coordinate);
    }

    /**
     * insert the next Position of BIKER in the Direction "RIGHT" in the track-List
     * @param biker the Biker
     */
    private static void moveRight(Biker biker){
        Coordinate coordinate = new Coordinate(biker.getLastPosition().x + 1, biker.getLastPosition().y);
        MainApp.biker.stream().filter(biker1 -> biker1.isAlive()).forEach(tempBiker -> {
            if(tempBiker.getTrail().contains(coordinate) || isOutside(coordinate)){
                playerDie(biker);
            }
        });

        biker.addPosition(coordinate);
    }

    /**
     * is Killing the Biker
     * @param biker the Biker
     */
    private static void playerDie(Biker biker){
        biker.setAlive(false);
        System.out.println(biker.getName() + " died");
    }

    /**
     * returning the Ammoind of the living Biker of the Biker-list
     * @return ammound
     */
    public static long getAmmoundOfAlives() {
        return MainApp.biker.stream().filter(Biker::isAlive).count();
    }

    /**
     * returning the last Biker alive. If there are more then 1 Player alive in the List return null
     * @return
     */
    public static Biker getlastAliveBiker() {
        if(getAmmoundOfAlives()==1L){
            if(MainApp.biker.stream().anyMatch(Biker::isAlive)){
                return MainApp.biker.stream().filter(Biker::isAlive).findFirst().get();
            }
        }
        return null;
    }

    /**
     * checks if Coordinate are outside of the Feeld
     * @param coordinate checked coordinates
     * @return boolean true if the coordinate is outside of the Screen
     */
    public static boolean isOutside(Coordinate coordinate){
        return coordinate.x<0 || coordinate.x>=MainApp.COLUMNS || coordinate.y<0 || coordinate.y>=MainApp.ROWS;
    }

}