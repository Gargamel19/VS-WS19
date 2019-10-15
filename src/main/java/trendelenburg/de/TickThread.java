package trendelenburg.de;

import edu.cads.bai5.vsp.tron.view.ITronView;
import trendelenburg.de.Utils.BikerUtil;

/**
 * this Thread is ticking in the ivery "TICKRATE" ms and checks every tick, if only 1 or no Biker is alive
 */

public class TickThread extends Thread{

    private ITronView view;
    private boolean running = true;

    public TickThread(ITronView view) {
        this.view = view;
    }

    @Override
    public void run() {
        while (running){
            try {
                if(MainApp.isStarted()){
                    view.clear();
                    for (Biker eachBiker: MainApp.biker) {
                        if(eachBiker.isAlive()){
                            if(MainApp.isStarted()){
                                BikerUtil.computeBiker(eachBiker);
                                if(!BikerUtil.isOutside(eachBiker.getLastPosition())){
                                    view.draw(eachBiker.getTrail(), eachBiker.getPlayerColor());
                                    eachBiker.setLastDirection(eachBiker.getDirection());
                                }

                            }


                        }

                    }
                    if(BikerUtil.getAmmoundOfAlives()<=1){
                        GameLocics.end();
                    }
                }
                Thread.sleep(MainApp.TICKRATE);
                //TODO global


            }catch (InterruptedException ie){
                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }
}
