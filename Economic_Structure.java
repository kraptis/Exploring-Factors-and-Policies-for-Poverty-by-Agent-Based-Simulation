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
public class Economic_Structure implements Steppable,java.io.Serializable{

    
    
    public static boolean mix = true;//type of industries
    
    public static Vector<Vector<Buisness>> buisness = new Vector();//  generator.buisness;
    public static Vector<Vector<Buisness>> non_working_buisness= new Vector();
    public static Vector<Vector<Integer>> environment_char = new Vector();
    public static Vector<Vector<Integer>> environment_char2 = new Vector();
    
    public void Economic_Structure(){
        
       // if (ne.cli.hostile==0 && ne.iso.state==false && ne.n_u.yes==1)//
       //     mix=true;
      //  else mix=false;
        Generator generator = new Generator();
        generator.economic_structure();
        Natural_Environment ne = new Natural_Environment();
        Social_Norms sn = new Social_Norms();
        environment_char=(Vector<Vector<Integer>>) generator.environment_char.clone();
       // System.out.println(environment_char.get(0).size()+"--------size");
       // System.out.println(generator.environment_char.get(0).size()+"--------size");
      //  System.out.println(generator.buisness.size()+"-2-------size");
        for(int i=0;i<generator.buisness.size();i++){
            //System.out.println(generator.environment_char.get(i).get(2));
            //System.out.println(Natural_Environment.isolation);
            //           if(generator.environment_char.get(i).get(0)<=Natural_Environment.clima && generator.environment_char.get(i).get(1)<=Natural_Environment.natural_resources && generator.environment_char.get(i).get(2)<=Natural_Environment.isolation && generator.environment_char.get(i).get(3)<=Social_Norms.minorities && generator.environment_char.get(i).get(4)<=Social_Norms.buisness_norms.get(i) ){
            if(generator.environment_char.get(i).get(2)<=Natural_Environment.isolation && generator.environment_char.get(i).get(4)<=Institutions.officials_behavior){
                buisness.add(generator.buisness.get(i));
            }
            else{
                non_working_buisness.add(generator.buisness.get(i));
                environment_char2.add(generator.environment_char.get(i));
            }
        }


      //  System.out.println(non_working_buisness.size()+"--non--1029328937c410982357y0");
      //  System.out.println(buisness.size()+"-----1029328937c410982357y0");
       // buisness=generator.buisness;
        System.out.println("-----Non Working Buisness: "+non_working_buisness.size()+"---Working Buisness: "+buisness.size()+"");
    }

    public void step(SimState state) {
        
    }


}
