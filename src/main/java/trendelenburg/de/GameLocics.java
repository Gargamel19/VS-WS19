package trendelenburg.de;

import trendelenburg.de.Utils.UtilBiker;

public class GameLocics {

    /**
     * ending up the Game and checking who won or if it is DRAWN then it restarts the Game
     */

    public static void end(){
        MainApp.stopPlaying();
        if(UtilBiker.getAmmoundOfAlives()==1){
            System.out.println(UtilBiker.getlastAliveBiker().getName() + " winns :)");
            MainApp.restartPlaying();
        }else if(UtilBiker.getAmmoundOfAlives()==0){
            System.out.println("DRAWN");
            MainApp.restartPlaying();
        }
    }

}
