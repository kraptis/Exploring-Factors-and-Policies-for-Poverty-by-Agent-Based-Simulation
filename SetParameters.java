/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;

import java.util.Collections;
import java.util.Vector;
import sim.engine.SimState;
import sim.engine.Steppable;

/**
 *
 * @author dinos
 */
public class SetParameters implements Steppable{
    public static int years=0;
    public static double poverty_treshold=0;
    public static double absolute_poverty_treshold=0;
    public static int immigrants=2;
    public static Vector<Double> diamesos = new Vector();//median


    public void step(SimState state) {
        if(state.schedule.getSteps()>=1){
            for(int i=0;i<Social_Norms.agents.size();i++){
                if(Social_Norms.agents.get(i).age>=72){
                    diamesos.add(Social_Norms.agents.get(i).totalincome);
                }
            }
double d=0;
            Collections.sort(diamesos);
            if(diamesos.size()>0)
                d=diamesos.get(diamesos.size()/2);


            poverty_treshold=(0.6 * d);

          if(state.schedule.getSteps()==2)
                absolute_poverty_treshold=poverty_treshold;
           
        }
    }

}
