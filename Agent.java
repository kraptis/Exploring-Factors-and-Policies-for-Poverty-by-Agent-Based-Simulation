/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;

import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import sim.engine.SimState;
import sim.engine.Steppable;

/**
 *
 * @author dinos
 */
public class Agent implements Steppable,java.io.Serializable{

    
    
    SetParameters sp = new SetParameters();
    Social_Norms sn = new Social_Norms();
    Institutions in = new Institutions();
    Natural_Environment ne = new Natural_Environment();

    //------------------beliefs------------------------

    public Integer id=0;
    public Integer gender=0;//0 male, 1 female
    public Vector<Agent> partner= new Vector();
    public Vector<Agent> children = new Vector();
    public Vector<Agent> parents = new Vector();
    public Integer adult=0;
    public Integer skills=0;//1-10
    public Double immigration=0.0;
    public Integer racism=0;
    public Double evolution=0.0;
    public Double family_evolution=0.0;
    public Double family_immigration=0.0;
    public Integer age=0;
    public Integer wage=0;
    public Double quality_of_life=0.0;
    public Double family_quality_of_life=0.0;
    public Double totalincome=0.0;
    public Double work_hours=0.0;
    public Vector<Integer> work=new Vector();// the company that the agent works
    public Vector<Agent> socialnetwork = new Vector();
    public Integer findjob=0;
    public Integer death=0;
    public Vector<Integer> life_criteria = new Vector();//[communitys quality of life,income,institutions,climate,immigrants,isolation,]quality of life
    public Integer kill_agent=0;//kill 1, allive 0
    public Integer retired=0;//1 true
    public Integer tries_to_make_children=0;//# of tries an agent has to make children
     //-------------------END-----------------------------



    //------------Desires-----------------------

    Integer avoid_poverty=0;//1->intention
    Integer upgrade_quality_of_life=0;//1->intention

    //-----------------------------------------

    //-------mental state-----------------
        public void step(SimState state){
            Random rand = new Random();

            this.age++;//in every loop + 3 months
            if(this.id==1600)
               // System.out.println(this.age+"-------age---");
            if(this.age==72){//an agent become adult when  18*4 steps
                this.adult=1;
               // System.out.println("adult--");
                this.parents.clear();
                //Social_Norms.agents.add(this);
                if(this.gender==0){
                    for(int i=0;i<Social_Norms.boys_agents.size();i++){
                        if(Social_Norms.boys_agents.get(i).id==this.id){
                            Social_Norms.men_agents.add(Social_Norms.boys_agents.get(i));
                            Social_Norms.boys_agents.remove(i);
                            break;
                        }
                    }
                    
                }
                else{
                    for (int i = 0; i < Social_Norms.girls_agents.size(); i++) {
                        if (Social_Norms.girls_agents.get(i).id == this.id) {
                            Social_Norms.women_agents.add(Social_Norms.girls_agents.get(i));
                            Social_Norms.girls_agents.remove(i);
                            break;
                        }
                    }
                    
               
                }
            }

            if(age==260 && work.size()!=0 && wage!=0){
                 Economic_Structure.buisness.get(work.get(0)).get(work.get(1)).characteristics.get(work.get(2)).set(3,Economic_Structure.buisness.get(work.get(0)).get(work.get(1)).characteristics.get(work.get(2)).get(3)+1);
                 work.clear();
            }

            
            Integer randeath=rand.nextInt(10);
            if(kill_agent==1){
               // System.out.println("i am a dead agent motherfucker or i am an immigrant agent! los poulos!---"+this.gender);
                state.schedule.scheduleComplete();
            }

            if(age>=356 && randeath<6){
                this.remove_agent(this.id);
            }


            //totalincome=wage;
            
            if(partner.size()==0 && parents.size()==0)
            totalincome=(double)wage;
            if(partner.size()>0 && parents.size()==0)
            totalincome=(wage+partner.get(0).wage)*Math.sqrt(partner.size()+children.size()+1);
            if(partner.size()==0 && parents.size()==1)
            totalincome=parents.get(0).wage*Math.sqrt(1+parents.get(0).children.size());
            if(partner.size()==0 && parents.size()==2)
            totalincome=(parents.get(0).wage + parents.get(1).wage)*Math.sqrt(1+parents.get(0).children.size());

             //System.out.println(""+life_criteria.size()+"life_criteria size");
            //quality_of_life=(life_criteria.get(0)*Social_Norms.quality_of_life)+(life_criteria.get(1)*wage)+(life_criteria.get(2)*Institutions.officials_behavior);//!!!!!!prepei na prostheso klima.metanastes isolation
            if(state.schedule.getSteps()>0)
                quality_of_life=compute_quality_of_life(quality_of_life, state.schedule.getSteps());

            immigration=immigration+compute_immigration(immigration);
            evolution=evolution+compute_evolution(evolution);

            if(partner.size()>0){
                if(state.schedule.getSteps()>0)
                    family_quality_of_life=compute_family_quality_of_life(quality_of_life);
                
                family_immigration=compute_family_immigration(immigration);
                family_evolution=compute_family_evolution(evolution);
            }

            if(adult==1 && age<240){

                
                Find_Job fj0 = new Find_Job();
                fj0.step(state,1);

                Integer planA=1;// plan to  his/her update skills
                Integer planB=2;//plan to find a new job
                Integer old_wage=0;
                Integer old_skills=0;
                Random r = new Random();
                Integer ran=0;
                Integer ran2=0;
                Integer ran3=0;
                Integer ran4=0;
                Integer ran5=0;
                Integer ran6=0;

                Integer he=0;
                Integer he2=0;

                //System.out.println("---sp-------xcvxcvxcvxcv-"+Social_Norms.education+"spspspspsps"+Social_Norms.immigration+"alalalalallalala");
                //unemployment agents or very very poor
                if((SetParameters.poverty_treshold-totalincome)/SetParameters.poverty_treshold>1/4 && state.schedule.getSteps()>2 && he==0){
                    old_wage=wage;
                    Find_Job fj = new Find_Job();
                    fj.step(state,1);
                    if(wage==old_wage){
                        ran = r.nextInt(10)+1;
                        if(ran>2)
                            immigration=immigration+Math.abs(1/Social_Norms.immigration);
                        if(ran>5)
                            evolution=evolution+Math.abs(1/Social_Norms.education);
                        if(evolution>Social_Norms.education){
                            old_skills=skills;
                            Update_Skills us = new Update_Skills();
                            us.Update_Skills();
                            ran2 = r.nextInt(10)+1;
                            if(old_skills==skills && ran2>5)
                                immigration=immigration+Math.abs(1/Social_Norms.immigration);
                        }
                        if(immigration>Social_Norms.immigration){
                            //removed from his/her work, removed his/her whole family wit a probability
                            ran3=(r.nextInt(10)+1)*10;
                            ran4=r.nextInt(10)+1;
                            //System.out.println("eimai meeeeeeeeeeesssssssssssssssaaaaaaaaaaaaaaaa1--mporei na figo");
                            if(ran3<50){//kapios tha figei, eite monos tou eite me tin oikogeneia
                                //System.out.println("eimai meeeeeeeeeeesssssssssssssssaaaaaaaaaaaaaaaa1--efiga");
                                if(partner.size()!=0 || children.size()!=0){
                                    if(ran4>9){//tha figei monos tou
                                        remove_agent(id);
                                        Extract_Information.count_immigration++;
                                        he2=1;
                                    }
                                    else{//couple or the whole family is removed
                                        remove_family(id);
                                        he2=1;
                                        Extract_Information.count_immigration=Extract_Information.count_immigration+(this.partner.size()+this.children.size()+1);
                                    }
                                }
                                else{
                                    remove_agent(id);
                                    Extract_Information.count_immigration++;
                                    he2=1;
                                }


                            }

                        }
                    }

                    avoid_poverty=1;
                    activate_plans(planA);
                    activate_plans(planB);
                    //Intentions intentions = new Intentions();

                }

                he=0;
               // Extract_Information.count_immigration++;

              if(((family_quality_of_life<0.8*Social_Norms.quality_of_life&&family_quality_of_life>0)||(quality_of_life<0.8*Social_Norms.quality_of_life && family_quality_of_life==0))&& state.schedule.getSteps()>2 && he==0){

                //     System.out.println("eimai meeeeeeeeeeesssssssssssssssaaaaaaaaaaasdasdaaaaaa2---------------------------");
                    if(this.life_criteria.get(2)>50)
                        Invisible_Hand.count_voters++;
                    old_wage=wage;
                    Find_Job fj = new Find_Job();
                    fj.step(state,1);
                    if(wage==old_wage){
                        ran = r.nextInt(10)+1;
                        if(ran>2)
                            immigration=immigration+Math.abs(1/Social_Norms.immigration);
                        if(ran>5)
                            evolution=evolution+Math.abs(1/Social_Norms.education);
                        if(evolution>Social_Norms.education){
                            old_skills=skills;
                            Update_Skills us = new Update_Skills();
                            us.Update_Skills();
                            ran2 = r.nextInt(10)+1;
                            if(old_skills==skills && ran2>5)
                                immigration=immigration+Math.abs(1/Social_Norms.immigration);
                        }

                  //      System.out.println(immigration+"_im_"+Social_Norms.immigration);
                  //      System.out.println(quality_of_life+"_qua_"+Social_Norms.quality_of_life);
                        if(immigration>Social_Norms.immigration){

                          // System.out.println("eimai meeeeeeeeeeesssssssssssssssaaaaaaaaaaaaaaaa2--mporei");
                            ran3=(r.nextInt(10)+1)*10;
                            ran4=r.nextInt(10)+1;
                           if(ran3<50){//with a probabilty an agent alone or with his/her family will be removed
                               // System.out.println("eimai meeeeeeeeeeesssssssssssssssaaaaaaaaaaaaaaaa1--efiga");
                                if(partner.size()!=0 || children.size()!=0){
                                    if(ran4>9){//tha figei monos tou
                                        remove_agent(id);
                                        Extract_Information.count_immigration++;
                                        he2=1;
                                    }
                                    else{//couple or family
                                        remove_family(id);
                                        he2=1;
                                        Extract_Information.count_immigration=Extract_Information.count_immigration+(this.partner.size()+this.children.size()+1);
                                    }
                                }
                                else{
                                    remove_agent(id);
                                    Extract_Information.count_immigration++;
                                    he2=1;
                                }


                            }

                        }
                    }
                }

                double random3456 = state.random.nextDouble();
                if(random3456<0.001){
                    System.out.println("kasfhks");
                    if(partner.size()!=0 || children.size()!=0){
                                    if(ran4>9){//alone
                                        remove_agent(id);
                                        Extract_Information.count_immigration++;
                                        he2=1;
                                    }
                                    else{//as couple or family
                                        remove_family(id);
                                        he2=1;
                                        Extract_Information.count_immigration=Extract_Information.count_immigration+(this.partner.size()+this.children.size()+1);
                                    }
                                }
                                else{
                                    remove_agent(id);
                                    Extract_Information.count_immigration++;
                                    he2=1;
                                }

                }



            }

        }

        public void activate(){
            //Find_Job fj = new Find_Job();
            //fj.find_job();
        }


    //-------------DESIRES------------------------------



/*    public class desires{

        public desires(){
            if(totalincome<sp.poverty_treshold)
                avoid_poverty=1;
        }

    }*/

    //----------------END------------------------


    //----------------INTENTIONS---------------

    public class Intentions{
        int planA=1;// plan update skills
        int planB=2;//plan find new job

        public Intentions(){
            if(avoid_poverty==1){
                activate_plans(planA);
                activate_plans(planB);
            }

            if(upgrade_quality_of_life==1){
                activate_plans(planA);
                activate_plans(planB);
            }
                
        }

    }
    //----------------END---------------------


    public void remove_agent(int agentid){
        if(wage!=0 && work.size()!=0){//quit a job
            Economic_Structure.buisness.get(work.get(0)).get(work.get(1)).characteristics.get(work.get(2)).set(3,Economic_Structure.buisness.get(work.get(0)).get(work.get(1)).characteristics.get(work.get(2)).get(3)+1);
            work.clear();
        }

        for(int i=0;i<Social_Norms.agents.size();i++){
            int j=0;
            while(j<Social_Norms.agents.get(i).socialnetwork.size()){
                if(Social_Norms.agents.get(i).socialnetwork.get(j).id==agentid){
                    Social_Norms.agents.get(i).socialnetwork.remove(j);
                }
                else j++;
            }
        }

        for(int i=0;i<Social_Norms.agents.size();i++){
            if(Social_Norms.agents.get(i).id==agentid){
                Social_Norms.agents.remove(i);
                break;
            }
        }
        if(gender==0 && adult==1){
            for(int i=0;i<Social_Norms.men_agents.size();i++){
                if(Social_Norms.men_agents.get(i).id==agentid){
             //       System.out.println("i am a man");
                    Social_Norms.men_agents.remove(i);
                    break;
                }
            }
        }
        else if(gender==1 && adult==1){
            for(int i=0;i<Social_Norms.women_agents.size();i++){
                if(Social_Norms.women_agents.get(i).id==agentid){
             //       System.out.println("i am a woman");
                    Social_Norms.women_agents.remove(i);
                    break;
                }
            }
        }
        else if(gender==0 && adult==0){
            for(int i=0;i<Social_Norms.boys_agents.size();i++){
                if(Social_Norms.boys_agents.get(i).id==agentid){
                    Social_Norms.boys_agents.remove(i);
                    break;
                }
            }
        }
        else if(gender==1 && adult==0){
            for(int i=0;i<Social_Norms.girls_agents.size();i++){
                if(Social_Norms.girls_agents.get(i).id==agentid){
                    Social_Norms.girls_agents.remove(i);
                    break;
                }
            }
        }
        
        kill_agent=1;
    }

   

    public void remove_family(int agentid){
        int i=0;
        while(i<children.size()){
            remove_agent(children.get(i).id);
            i++;
        }
        if(partner.size()>0)
        remove_agent(partner.get(0).id);
        remove_agent(agentid);
    }

    public double compute_immigration(double imm){
        double temp1=0;
        for(int i=0;i<socialnetwork.size();i++){
            temp1=temp1+((socialnetwork.get(i).immigration-immigration)/Social_Norms.immigration)/socialnetwork.size();
        }
        imm=temp1;
        return imm;
    }

    public double compute_evolution(double evo){
        double temp1=0;
        for(int i=0;i<socialnetwork.size();i++){
            temp1=temp1+((socialnetwork.get(i).evolution-evolution)/Social_Norms.education)/socialnetwork.size();
        }
        evo=temp1;
        return evo;
    }

    public double compute_quality_of_life(double qua, long steps){
        int temp0=life_criteria.get(0);
        int temp1=life_criteria.get(1);
        int temp2=life_criteria.get(2);
        int temp3=life_criteria.get(3);
        int temp4=life_criteria.get(4);
        int temp5=life_criteria.get(5);

        double temp=0.0;
        temp=((life_criteria.get(0)*5)+(life_criteria.get(1)*wage/220)+(life_criteria.get(2)*Institutions.officials_behavior)+(life_criteria.get(3)*Natural_Environment.clima)+(life_criteria.get(4)*Social_Norms.minorities)+(life_criteria.get(5)*Natural_Environment.isolation))/100;//!!!!!!prepei na prostheso klima.metanastes isolation
   //     System.out.println(temp2+"temp2 qua");
        if(steps==1)
            qua=temp;
        if(steps>1 && socialnetwork.size()>0){
      //  else{
            for(int i=0;i<socialnetwork.size();i++){
                temp0=temp0+socialnetwork.get(i).life_criteria.get(0);
                temp1=temp1+socialnetwork.get(i).life_criteria.get(1);
                temp2=temp2+socialnetwork.get(i).life_criteria.get(2);
                temp3=temp3+socialnetwork.get(i).life_criteria.get(3);
                temp4=temp4+socialnetwork.get(i).life_criteria.get(4);
                temp5=temp5+socialnetwork.get(i).life_criteria.get(5);
                
            }
            temp0=temp0/socialnetwork.size();
            temp1=temp1/socialnetwork.size();
            temp2=temp2/socialnetwork.size();
            temp3=temp3/socialnetwork.size();
            temp4=temp4/socialnetwork.size();
            temp5=temp5/socialnetwork.size();

            life_criteria.set(0, temp0);
            life_criteria.set(1, temp1);
            life_criteria.set(2, temp2);
            life_criteria.set(3, temp3);
            life_criteria.set(4, temp4);
            life_criteria.set(5, temp5);

            temp=((life_criteria.get(0)*5)+(life_criteria.get(1)*wage/220)+(life_criteria.get(2)*Institutions.officials_behavior)+(life_criteria.get(3)*Natural_Environment.clima)+(life_criteria.get(4)*Social_Norms.minorities)+(life_criteria.get(5)*Natural_Environment.isolation))/100;

            qua=temp;
      //      System.out.println(temp+"temp qua");
       // }
        }
        if(steps>1 && socialnetwork.size()==0)
            qua=((life_criteria.get(0)*5)+(life_criteria.get(1)*wage/220)+(life_criteria.get(2)*Institutions.officials_behavior)+(life_criteria.get(3)*Natural_Environment.clima)+(life_criteria.get(4)*Social_Norms.minorities)+(life_criteria.get(5)*Natural_Environment.isolation))/100;


        return qua;
    }
    
    public double compute_family_quality_of_life(double qua){
        Random r = new Random();
        int ran1=0;

        double temp1=0.0;
        double temp2=0.0;
        temp1=(qua+partner.get(0).quality_of_life)/2;
        if(children.size()>0){
            for(int i=0;i<children.size();i++){
                temp2=temp2+children.get(i).quality_of_life;
            }
            temp2=temp2/children.size();
            ran1=r.nextInt(9)+1;
            qua=(temp2*ran1)+(temp1*(10-ran1))/10;
        }
        else qua=temp1;


        return qua;
    }
    
    public double compute_family_immigration(double imm){
        Random r = new Random();
        int ran1=0;

        double temp1=0.0;
        double temp2=0.0;
        temp1=(imm+partner.get(0).immigration)/2;
        if(children.size()>0){
            for(int i=0;i<children.size();i++){
                temp2=temp2+children.get(i).immigration;
            }
            temp2=temp2/children.size();
            ran1=r.nextInt(9)+1;
            imm=(temp2*ran1)+(temp1*(10-ran1))/10;
        }
        else imm=temp1;

        return imm;
    }
    
    public double compute_family_evolution(double evo){
        Random r = new Random();
        int ran1=0;

        double temp1=0.0;
        double temp2=0.0;
        temp1=(evo+partner.get(0).evolution)/2;
        if(children.size()>0){
            for(int i=0;i<children.size();i++){
                temp2=temp2+children.get(i).evolution;
            }
            temp2=temp2/children.size();
            ran1=r.nextInt(9)+1;
            evo=(temp2*ran1)+(temp1*(10-ran1))/10;
        }
        else evo=temp1;

        return evo;
    }



    public int activate_plans(int plan){
        int done=0;
        if(plan==1){

            //trying to upgrade his/her skills
            //until succeed the trhread is still opened
            // 

            if(done==1){
                done=0;
                return 1;
            }
        }

        if(plan==2){

            //trying to upgrade his/her skills
            //until succeed the trhread is still opened
            // 

             if(done==1){
                done=0;
                return 1;
            }
        }

        return 0;
    }

    public class Find_Job{

        
        Random generator = new Random();
        public void step(SimState state, int b){
            Economic_Structure es = new Economic_Structure();
            findjob=b;
//            System.out.println("----------xcvxcvxcvxcv-"+Economic_Structure.buisness.get(1).get(0).characteristics.get(0)+"");
             if(state.schedule.getSteps()==0 || findjob==1){
              a:
                for(int i=0;i<Economic_Structure.buisness.size();i++){
                   // System.out.println("----------xcvxcvxcvxcv-");
                    for(int j=0;j<Economic_Structure.buisness.get(i).size();j++){
                        for(int m=0;m<Economic_Structure.buisness.get(i).get(j).characteristics.size();m++){
                            int ran1=generator.nextInt(100);
                            if(state.schedule.getSteps()==0){
                                ran1=ran1+80;
                            }
                            int ran2=generator.nextInt(401)-200;
                            if(Math.abs(Economic_Structure.buisness.get(i).get(j).characteristics.get(m).get(0)-skills)<10 && Economic_Structure.buisness.get(i).get(j).characteristics.get(m).get(3)!=0 && ran1>1 && Economic_Structure.buisness.get(i).get(j).characteristics.get(m).get(1)+ran2>wage){
                                if(wage!=0 && work.size()!=0 && state.schedule.getSteps()!=0){//exei proigoumenei doulia pou prepei na afisei(paraitisi)
                                    //System.out.println(""+work.size()+"-----work.size()");
                                    Economic_Structure.buisness.get(work.get(0)).get(work.get(1)).characteristics.get(work.get(2)).set(3,Economic_Structure.buisness.get(work.get(0)).get(work.get(1)).characteristics.get(work.get(2)).get(3)+1);
                                   work.clear();//!!!!!!prosoxi mipos den svinontai
                                }
                                wage=Economic_Structure.buisness.get(i).get(j).characteristics.get(m).get(1)+ran2;
                                //System.out.println("------------"+es.buisness.get(i).get(j).characteristics.get(m).get(3)+"posi");
                                Economic_Structure.buisness.get(i).get(j).characteristics.get(m).set(3,Economic_Structure.buisness.get(i).get(j).characteristics.get(m).get(3)-1);
                                //count1++;
                                work.add(i);//apothikeyetai stin ousia i thesi ergasias tou agent
                                work.add(j);
                                work.add(m);
                                //System.out.println("----------xcvxcvxcvxcv-");
                                break a;
                            }
                        }
                    }
                }
            
             }
        }
    }

    public class Update_Skills{
        Random r = new Random();
        int random=0;
        public void Update_Skills(){
            this.random=this.r.nextInt(100);
            if(this.random<3 && skills<10){   //3% probabilty to get education and upgrade their skills
                skills++;
            }
        }
    }


}
