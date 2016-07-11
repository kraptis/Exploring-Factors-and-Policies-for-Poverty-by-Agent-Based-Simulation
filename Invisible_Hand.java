/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;

import java.util.Random;
import sim.engine.SimState;
import sim.engine.Steppable;

/**
 *
 * @author dinos
 */
public class Invisible_Hand implements Steppable{
    private int dyn=0;
    private int old_positions=0;
    private int new_positions=0;
    public static int count_voters=0;

    public void step(SimState state) {

        if(state.schedule.getSteps()>1){
        
            int i=0;//add companies
            old_positions=0;
            new_positions=0;
            double pososto_ayksisis=0.0;

            for(int i3=0;i3<Economic_Structure.buisness.size();i3++){
              
                for(int j=0;j<Economic_Structure.buisness.get(i3).size();j++){
               
                    for(int m=0;m<Economic_Structure.buisness.get(i3).get(j).characteristics.size();m++){
                  
                        old_positions=old_positions+Economic_Structure.buisness.get(i3).get(j).characteristics.get(m).get(3);
                        }
                    }
            }

            while(i<Economic_Structure.non_working_buisness.size()){
                if(Economic_Structure.environment_char2.get(i).get(2)<=Natural_Environment.isolation && Economic_Structure.environment_char2.get(i).get(4)<=Institutions.officials_behavior){
                    Economic_Structure.buisness.add(Economic_Structure.non_working_buisness.get(i));
                    Economic_Structure.environment_char.add(Economic_Structure.environment_char2.get(i));
                    Economic_Structure.non_working_buisness.remove(i);
                    Economic_Structure.environment_char2.remove(i);
                }
                else
                    i++;
            }

            int i2=0;//remove companies
            while(i2<Economic_Structure.buisness.size()){
                if(Economic_Structure.environment_char.get(i).get(2)>Natural_Environment.isolation && Economic_Structure.environment_char.get(i).get(4)>Institutions.officials_behavior ){
                    Economic_Structure.non_working_buisness.add(Economic_Structure.buisness.get(i2));
                    Economic_Structure.environment_char2.add(Economic_Structure.environment_char.get(i2));
                    Economic_Structure.buisness.remove(i2);
                    Economic_Structure.environment_char.remove(i2);
                }
                else
                    i2++;
            }


            for(int i3=0;i3<Economic_Structure.buisness.size();i3++){
                for(int j=0;j<Economic_Structure.buisness.get(i3).size();j++){
                    for(int m=0;m<Economic_Structure.buisness.get(i3).get(j).characteristics.size();m++){
                        new_positions=new_positions+Economic_Structure.buisness.get(i3).get(j).characteristics.get(m).get(3);
                        }
                    }
            }
            System.out.println(old_positions+"--old_positions");
            if(old_positions>0){
                pososto_ayksisis=(new_positions-old_positions)/old_positions;
                Social_Norms.immigration=Social_Norms.immigration+pososto_ayksisis*Social_Norms.immigration/100;
                Social_Norms.education=Social_Norms.education+pososto_ayksisis*Social_Norms.education/100;
            }

            double pososto_apoliseon=0.0;

            if(new_positions>old_positions){
                //meiono misthous
                Random r15 = new Random();
                double r25=0.0;
                for(int i15=0;i15<Social_Norms.agents.size();i15++){
                    r25=r15.nextDouble();
                    if(Social_Norms.agents.get(i15).wage>0 && r25<0.10)
                        Social_Norms.agents.get(i15).wage=(int) (Social_Norms.agents.get(i15).wage - 0.05 * Social_Norms.agents.get(i15).wage);
                }
                pososto_apoliseon=0.01;

            }
            if(old_positions>=new_positions){
                //raise wages
                Random r16 = new Random();
                double r26=0.0;
                for(int i15=0;i15<Social_Norms.agents.size();i15++){
                    r26=r16.nextDouble();
                    if(Social_Norms.agents.get(i15).wage>0 && r26<0.10)
                        Social_Norms.agents.get(i15).wage=(int) (Social_Norms.agents.get(i15).wage + 0.05 * Social_Norms.agents.get(i15).wage);
                }
                pososto_apoliseon=0.005;
            }

            dyn=1;//1 if dynamic changed values for the experiments
            //update variable every four years
            if(state.schedule.getSteps()%8==0  && dyn==1){
               

                if(Institutions.officials_behavior>0 )
                    Institutions.officials_behavior--;
              
                if(Natural_Environment.accesibility>0)
                Natural_Environment.accesibility--;
                if( Natural_Environment.techdevelop>0)
                Natural_Environment.techdevelop--;
               // Social_Norms.quality_of_life=Social_Norms.quality_of_life-1/(Institutions.officials_behavior-1)*Social_Norms.quality_of_life/100;
            }
            if(Institutions.officials_behavior<10 && count_voters>2000){
                Random q = new Random();
                double q1=q.nextDouble();
                if(q1>0.8){
                    Institutions.officials_behavior++;
                    count_voters=0;
                }
            }

            //fires
            Random r= new Random();
            double ran = 0.0;
            for(int j=0;j<Social_Norms.agents.size();j++){
                ran=r.nextDouble();
                if(ran<pososto_apoliseon){
                    if(Social_Norms.agents.get(j).wage!=0 && Social_Norms.agents.get(j).work.size()!=0){//quit
                    Economic_Structure.buisness.get(Social_Norms.agents.get(j).work.get(0)).get(Social_Norms.agents.get(j).work.get(1)).characteristics.get(Social_Norms.agents.get(j).work.get(2)).set(3,Economic_Structure.buisness.get(Social_Norms.agents.get(j).work.get(0)).get(Social_Norms.agents.get(j).work.get(1)).characteristics.get(Social_Norms.agents.get(j).work.get(2)).get(3)+1);
                    Social_Norms.agents.get(j).work.clear();
                    Social_Norms.agents.get(j).wage=0;
                    }
                }

            }
        }
        
    }


}
