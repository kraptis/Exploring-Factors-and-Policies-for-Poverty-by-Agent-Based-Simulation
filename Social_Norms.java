/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;

import java.util.Vector;
import sim.engine.SimState;
import sim.engine.Steppable;

/**
 *
 * @author dinos
 */
public class Social_Norms implements Steppable{


    
    
    public static Double immigration=0.0; // disposion of community's immigration
    public static Double education=0.0;
    public static Double quality_of_life=0.0;
    public static Integer minorities=0;//
    public static Vector<Integer> buisness_norms = new Vector();//1st buisness A, 2nd thesi B etc.... 1 allowed, 0 not allowed
    public static Vector<Agent> agents=new Vector();
    public static Vector<Agent> men_agents=new Vector();
    public static Vector<Agent> women_agents=new Vector();
    public static Vector<Agent> boys_agents=new Vector();
    public static Vector<Agent> girls_agents=new Vector();
    public static Vector<Agent> immigrants=new Vector();
    public static Vector<Vector<Agent>> couples =new Vector();
    public static Vector<Vector<Vector<Agent>>> families =new Vector();

    public void Social_Norms(){
        Generator generator = new Generator();
        generator.social_norms();
        Social_Norms.immigration=generator.immigration;
        Social_Norms.education=generator.education;
        Social_Norms.minorities=generator.minorities;
        Social_Norms.buisness_norms.addAll(generator.buisness_norms);   
    }

    public void step(SimState state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public class Social_Networks{

        Social_Networks(){

        }

        
    }
}
