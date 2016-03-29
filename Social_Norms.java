/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;

//import edu.uci.ics.jung.graph.Graph;
//import edu.uci.ics.jung.graph.SparseMultigraph;
import java.util.Vector;
import sim.engine.SimState;
import sim.engine.Steppable;

/**
 *
 * @author dinos
 */
public class Social_Norms implements Steppable{


    
    //Buisness b = new Buisness();
    public static Double immigration=0.0; // diathesi metansteysis tis koinotitas
    public static Double education=0.0;//generator.s_SN.education; // diathesi ekpaideysis tis koinotitas
    public static Double quality_of_life=0.0;
    public static Integer minorities=0;//
    public static Vector<Integer> buisness_norms = new Vector();//1h thesi buisness A, 2h thesi B etc.... 1 allowed, 0 not allowed
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
       // System.out.println("--------"+immigration+"Social_Norms.immigration=generator.immigration");
        Social_Norms.education=generator.education;
        //Social_Norms.quality_of_life=generator.quality_of_life;
        Social_Norms.minorities=generator.minorities;
        Social_Norms.buisness_norms.addAll(generator.buisness_norms);   
    }

    public void step(SimState state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public class Social_Networks{

    //    Graph<Integer,Integer> graph1 = new  SparseMultigraph<Integer, Integer>();
    //    Graph<Integer,Integer> graph2 = new  SparseMultigraph<Integer, Integer>();
    //    Graph<Integer,Integer> graph3 = new  SparseMultigraph<Integer, Integer>();
    //    Graph<Integer,Integer> graph4 = new  SparseMultigraph<Integer, Integer>();

        

        Social_Networks(){

        }

        
    }
}
