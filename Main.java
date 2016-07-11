/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;
import java.util.Vector;
import sim.engine.*;
import sim.util.*;
import sim.field.continuous.*;

/**
 *
 * @author dinos
 */

public class Main extends SimState{

    public Continuous2D community = new Continuous2D(1.0,70,70);
    public Continuous2D community2 = new Continuous2D(1.0,70,70);
    Demographic_Characteristics dc = new Demographic_Characteristics();
    Natural_Environment ne = new Natural_Environment();
    Economic_Structure es = new Economic_Structure();
    Institutions ins = new Institutions();
    Social_Norms sn = new Social_Norms();
    //God god = new God();
    public Vector<Integer> agents = new Vector();

    public Main(long seed){
        super(seed);
    }


    @Override
    @SuppressWarnings("static-access")
    public void start(){
        super.start();
        community.clear();

        
        boolean t1,t2,t3;

        t1=community2.setObjectLocation(ne, new Double2D(community.getWidth() * 0.4 + random.nextDouble() - 0.5 , community.getHeight() * 0.4+ random.nextDouble() - 0.5));
        t2=community2.setObjectLocation(ne, new Double2D(community.getWidth() * 0.5 + random.nextDouble() - 0.5, community.getHeight() * 0.3 + random.nextDouble() - 0.5));
        t3=community2.setObjectLocation(ne, new Double2D(community.getWidth() * 0.2 + random.nextDouble() - 0.5, community.getHeight() * 0.2 + random.nextDouble() - 0.5));

     

        dc.Demographic_Characteristics();
        ne.natural_environment();
        ins.institutions();
        es.Economic_Structure();
        sn.Social_Norms();

        int serialize=0;
        int deserialize=0;
        int normal=1;

//--------------------------serialization------------------------------------------
        if(serialize==1){

            God abc = new God();
            abc.God(dc.men, dc.women, dc.children);//initialize agents


            for(int i=0;i<abc.men_agents.size();i++){
                schedule.scheduleRepeating(abc.men_agents.get(i));
                //sn.men_agents.add(i);
            }

            for(int i=0;i<abc.women_agents.size();i++){
                schedule.scheduleRepeating(abc.women_agents.get(i));
               // sn.women_agents.add(i);
            }

            for(int i=0;i<Social_Norms.boys_agents.size();i++){
                schedule.scheduleRepeating(Social_Norms.boys_agents.get(i));
            }

            for(int i=0;i<Social_Norms.girls_agents.size();i++){
                schedule.scheduleRepeating(Social_Norms.girls_agents.get(i));
            }

            schedule.scheduleRepeating(abc.dyn);//dynamic environment

            Invisible_Hand ih = new Invisible_Hand();//changes
            SetParameters sp = new SetParameters();//change parameters
            Extract_Information ei = new Extract_Information();//extract infos
            schedule.scheduleRepeating(ih);
            schedule.scheduleRepeating(sp);
            schedule.scheduleRepeating(ei);

            Serialization s = new Serialization();//serialize the system
            s.Serialization();
            //schedule.scheduleOnce(s);
        }


  //------------------------deserialization-----------------------------------------
        if(deserialize==1){

            //fortose ola ta vectors apo oles tis claseis apo deserializate-economic_structure ktl
            Economic_Structure.buisness.clear();
            Economic_Structure.environment_char.clear();
            Economic_Structure.environment_char2.clear();
            Economic_Structure.non_working_buisness.clear();

            Social_Norms.agents.clear();
            Social_Norms.boys_agents.clear();
            Social_Norms.buisness_norms.clear();
            Social_Norms.couples.clear();
            Social_Norms.families.clear();
            Social_Norms.girls_agents.clear();
            Social_Norms.immigrants.clear();
            Social_Norms.men_agents.clear();
            Social_Norms.women_agents.clear();

            Demographic_Characteristics.plithismos_pos.clear();
            Demographic_Characteristics.skills_pos.clear();

            Deserialization ds = new Deserialization();
            ds.Deserialization();
            //schedule.scheduleOnce(ds);

            System.out.println("agents "+ds.est.buisness.size());
            Economic_Structure.buisness.addAll(ds.est.buisness);
            Economic_Structure.environment_char=(Vector<Vector<Integer>>) ds.est.environment_char.clone();
            Economic_Structure.environment_char2=(Vector<Vector<Integer>>) ds.est.environment_char2.clone();
            Economic_Structure.non_working_buisness=(Vector<Vector<Buisness>>) ds.est.non_working_buisness.clone();

            Natural_Environment.accesibility=ds.nen.accesibility;
            System.out.println("agents desiarilazi"+Natural_Environment.accesibility);
            System.out.println("agents desiarilazi"+ds.nen.accesibility);
            Natural_Environment.clima=ds.nen.clima;
            Natural_Environment.isolation=ds.nen.isolation;
            Natural_Environment.natural_resources=ds.nen.natural_resources;
            Natural_Environment.techdevelop=ds.nen.techdevelop;

            Social_Norms.education=ds.sno.education;
            Social_Norms.immigration=ds.sno.immigration;
            Social_Norms.minorities=ds.sno.minorities;
            Social_Norms.quality_of_life=ds.sno.quality_of_life;
            Social_Norms.agents=(Vector<Agent>) ds.sno.agents.clone();
            Social_Norms.boys_agents=(Vector<Agent>) ds.sno.boys_agents.clone();
            Social_Norms.buisness_norms=(Vector<Integer>) ds.sno.buisness_norms.clone();
            Social_Norms.couples=(Vector<Vector<Agent>>) ds.sno.couples.clone();
            Social_Norms.families=(Vector<Vector<Vector<Agent>>>) ds.sno.families.clone();
            Social_Norms.girls_agents=(Vector<Agent>) ds.sno.girls_agents.clone();
            Social_Norms.immigrants=(Vector<Agent>) ds.sno.immigrants.clone();
            Social_Norms.men_agents=(Vector<Agent>) ds.sno.men_agents.clone();
            Social_Norms.women_agents=(Vector<Agent>) ds.sno.women_agents.clone();

            
            Demographic_Characteristics.plithismos_pos=(Vector<Integer>) ds.dch.plithismos_pos.clone();
            Demographic_Characteristics.skills_pos=(Vector<Integer>) ds.dch.skills_pos.clone();

            Institutions.officials_behavior=ds.inst.officials_behavior;


            God abc = new God();


            for(int i=0;i<Social_Norms.agents.size();i++){
                schedule.scheduleRepeating(Social_Norms.agents.get(i));
            }

            schedule.scheduleRepeating(abc.dyn);

            Invisible_Hand ih = new Invisible_Hand();
            SetParameters sp = new SetParameters();
            Extract_Information ei = new Extract_Information();
            schedule.scheduleRepeating(ih);
            schedule.scheduleRepeating(sp);
            schedule.scheduleRepeating(ei);

        }


 //-------------------------normal run----------------------------------------------
        if(normal==1){

            God abc = new God();
            abc.God(dc.men, dc.women, dc.children);


            for(int i=0;i<abc.men_agents.size();i++){
                schedule.scheduleRepeating(abc.men_agents.get(i));
            }

            for(int i=0;i<abc.women_agents.size();i++){
                schedule.scheduleRepeating(abc.women_agents.get(i));
            }

            for(int i=0;i<Social_Norms.boys_agents.size();i++){
                schedule.scheduleRepeating(Social_Norms.boys_agents.get(i));
            }

            for(int i=0;i<Social_Norms.girls_agents.size();i++){
                schedule.scheduleRepeating(Social_Norms.girls_agents.get(i));
            }

            System.out.println("pop-"+abc.couples.size()+"-"+Social_Norms.boys_agents.size()+"-"+Social_Norms.girls_agents.size()+"-"+abc.men_agents.size()+"-"+abc.women_agents.size()+"-");

            schedule.scheduleRepeating(abc.dyn);


            Invisible_Hand ih = new Invisible_Hand();
            SetParameters sp = new SetParameters();
            Extract_Information ei = new Extract_Information();
            schedule.scheduleRepeating(ih);
            schedule.scheduleRepeating(sp);
            schedule.scheduleRepeating(ei);

           
            double temp=0.0;
       
        
        }
        
        
       


    }

    public static void main(String[] args){
        doLoop(Main.class, args);
        System.exit(0);
    }

}
