package trendelenburg.de;

import trendelenburg.de.Utils.BikerUtil;

public class GameLocics {

    /**
     * ending up the Game and checking who won or if it is DRAWN then it restarts the Game
     */

    public static void end(){
        MainApp.stopPlaying();
        if(BikerUtil.getAmmoundOfAlives()==1){
            System.out.println(BikerUtil.getlastAliveBiker().getName() + " winns :)");
            MainApp.restartPlaying();
        }else if(BikerUtil.getAmmoundOfAlives()==0){
            System.out.println("DRAWN");
            MainApp.restartPlaying();
        }
    }

}
