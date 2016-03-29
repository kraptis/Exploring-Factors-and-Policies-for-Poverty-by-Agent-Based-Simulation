/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;

import sim.engine.SimState;
import sim.engine.Steppable;

/**
 *
 * @author dinos
 */
public class Institutions implements Steppable{
    
    public static Integer officials_behavior=0;//generator.s_In.officials_behavior;

    public void institutions(){
        Generator generator = new Generator();
        generator.institutions();
        Institutions.officials_behavior=generator.officials_behavior;
        System.out.println("-------Institutions: "+Institutions.officials_behavior);
    }

    public void step(SimState state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
 