package trendelenburg.de;

import javafx.stage.Stage;

/**
 * Controlling the Bikes with the keys witch are given in the Parameter
 */

class BikeController {

    /**
     *
     * it changes the Direction from the Player if the Player is not looking in the Same or in the Oppodit Direction
     *
     * @param P1_UP UP key caracter of the Player 1
     * @param P1_DOWN DOWN key caracter of the Player 1
     * @param P1_LEFT LEFT key caracter of the Player 1
     * @param P1_RIGHT RIGHT key caracter of the Player 1
     * @param P1_bike bike of the Player 1
     * @param P2_UP UP key caracter of the Player 2
     * @param P2_DOWN DOWN key caracter of the Player 2
     * @param P2_LEFT LEFT key caracter of the Player 2
     * @param P2_RIGHT RIGHT key caracter of the Player 2
     * @param P2_bike bike the Player 2
     * @param stage stage where the Controller should listen to
     */
    BikeController(String P1_UP, String P1_DOWN, String P1_LEFT, String P1_RIGHT, Biker P1_bike , String P2_UP, String P2_DOWN, String P2_LEFT, String P2_RIGHT, Biker P2_bike, Stage stage) {

        stage.getScene().setOnKeyTyped(keyEvent -> {
            if(keyEvent.getCharacter().equals(P1_UP)){
                if(!(P1_bike.getLastDirection()==Direction.UP || P1_bike.getLastDirection()==Direction.DOWN)){
                    P1_bike.setDirection(Direction.UP);
                }
            }else if(keyEvent.getCharacter().equals(P1_DOWN)){
                if(!(P1_bike.getLastDirection()==Direction.UP || P1_bike.getLastDirection()==Direction.DOWN)){
                    P1_bike.setDirection(Direction.DOWN);
                }
            }else if(keyEvent.getCharacter().equals(P1_LEFT)){
                if(!(P1_bike.getLastDirection()==Direction.LEFT || P1_bike.getLastDirection()==Direction.RIGHT)){
                    P1_bike.setDirection(Direction.LEFT);
                }
            }else if(keyEvent.getCharacter().equals(P1_RIGHT)){
                if(!(P1_bike.getLastDirection()==Direction.LEFT || P1_bike.getLastDirection()==Direction.RIGHT)){
                    P1_bike.setDirection(Direction.RIGHT);
                }
            }else if(keyEvent.getCharacter().equals(P2_UP)){
                if(!(P2_bike.getLastDirection()==Direction.UP || P2_bike.getLastDirection()==Direction.DOWN)){
                    P2_bike.setDirection(Direction.UP);
                }
            }else if(keyEvent.getCharacter().equals(P2_DOWN)){
                if(!(P2_bike.getLastDirection()==Direction.UP || P2_bike.getLastDirection()==Direction.DOWN)){
                    P2_bike.setDirection(Direction.DOWN);
                }
            }else if(keyEvent.getCharacter().equals(P2_LEFT)){
                if(!(P2_bike.getLastDirection().equals(Direction.LEFT) || P2_bike.getLastDirection().equals(Direction.RIGHT))){
                    P2_bike.setDirection(Direction.LEFT);
                }
            }else if(keyEvent.getCharacter().equals(P2_RIGHT)){
                if(!(P2_bike.getLastDirection().equals(Direction.LEFT) || P2_bike.getLastDirection().equals(Direction.RIGHT))){
                    P2_bike.setDirection(Direction.RIGHT);
                }
            }
        });


    }
}
