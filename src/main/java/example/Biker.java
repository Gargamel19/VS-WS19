package example;

import edu.cads.bai5.vsp.tron.view.Coordinate;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Class thant Represents The Biker
 */

public class Biker {

    private List<Coordinate> trail = new ArrayList<>();
    private Color playerColor;
    private String name;
    private Direction lastDirection = Direction.UP;
    private Direction direction = Direction.UP;
    private boolean alive = true;

    /**
     * constructor for the Player with Startposition, Color and Name of the Player
     * seting up the Track-list with the first Coordinate made of the Stasrtposition values x and y
     * @param startX Startposition x value
     * @param startY Startposition y value
     * @param playerColor Color of the Player un the Feeld
     * @param name Name of the Player
     */
    public Biker(int startX, int startY, Color playerColor, String name) {

        Coordinate coordinate = new Coordinate(startX, startY);
        addPosition(coordinate);
        this.playerColor = playerColor;
        this.name = name;


    }

    /**
     * getter for the TrackList of the Biker
     * @return
     */
    public List<Coordinate> getTrail() {
        return trail;
    }

    /**
     * adding a new Position of the Bike at the end of the Track-list
     * @param coordinate
     * @return true if coordinate not null und the Insert worked
     */
    public boolean addPosition(Coordinate coordinate){
        if(coordinate!=null){
            trail.add(trail.size(), coordinate);
            return true;
        }
        return false;
    }

    /**
     * getting the last insert and the actuell position in the TrackList
     * @return
     */
    public Coordinate getLastPosition(){
        if(trail!=null){
            return trail.get(trail.size()-1);
        }else{
            return null;
        }
    }

    /**
     * getter for the Color of the Bike
     * @return
     */
    public Color getPlayerColor() {
        return playerColor;
    }

    /**
     * getter for the Name of the Biker
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * getter for the Direction the bike is looking at
     * @return
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * setter for the Direction the Bike is looking at
     * @param direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * getter for the sate if the Biker is alive
     * @return
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * setter for the state if the Biker is alive
     * @param alive
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * getter for the last Direction, the Bike has looked at
     * @return
     */
    public Direction getLastDirection() {
        return lastDirection;
    }

    /**
     * setter for the last Direction, the Bike has looked at
     * @param lastDirection
     */
    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }

    /**
     * equals compares inly the name of the Biker
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Biker){
            Biker otherBiker = (Biker) obj;
            return name.equals(otherBiker.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        Coordinate coordinate = trail.get(trail.size()-1);
        return name + " [x: " +coordinate.x + ", y:" +  coordinate.y + "]";
    }
}
