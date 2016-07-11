/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;
import sim.engine.SimState;
import sim.engine.Steppable;

/**
 *
 * @author dinos
 */
public class God implements Steppable {

    public int count1=0;
    public int countid=-1;
    public int count_couples=0;
    public int old_num_couples=0;
    int count_loops=0;

    int oti=0;

   // Demographic_Characteristics dc = new Demographic_Characteristics();

    Vector<Agent> men_agents = new Vector();
    Vector<Agent> women_agents = new Vector();
    Vector<Agent> boys_agents = new Vector();
    Vector<Agent> girls_agents = new Vector();
    Vector<Agent> all_agents = new Vector();
    Vector<Vector<Agent>> couples = new Vector();
    Vector<Vector<Vector<Agent>>> families = new Vector();
   // Vector<Vector<Vector<Agent>>> single_families = new Vector();

    Vector<Agent> men_temp = new Vector();
    Vector<Agent> women_temp = new Vector();

    Vector<Agent> dead_agents = new Vector();

    

    Random generator = new Random();
    SetParameters sp = new SetParameters();
    Economic_Structure es = new Economic_Structure();
    Social_Norms sn = new Social_Norms();
    Dymamics dyn = new Dymamics();

    public void step(SimState state) {

    }

    public void God(int men,int women, int children){

        int i10=0;
        int i20=0;
        int h1=0;
        int h2=0;
        Random t = new Random();
        double rt =0.0;

        while(i10<men || i20<women){
            if(i10-1==men)
                h1=1;
            if(i20-1==women)
                h2=1;

            rt=t.nextDouble();
            if(h1==0 && rt<0.5){
                Create_Man_Agents cma = new Create_Man_Agents();
                cma.Create_Man_Agents();
                men_agents.add(cma.agent);
                i10++;
            }

            if(h2==0 && rt>=0.5){
                Create_Women_Agents cwa = new Create_Women_Agents();
                cwa.Create_Women_Agents();
                women_agents.add(cwa.agent);
                i20++;
            }
        }


        double rand2001 =0.0;
        Random r = new Random();

        for(int i=0;i<children;i++){
            rand2001=r.nextDouble();
            if(rand2001<0.5){
                Create_Boy_Agents cba = new Create_Boy_Agents();
                cba.Create_Boy_Agents();
                boys_agents.add(cba.agent);
            }
            else{
                Create_Girl_Agents cga = new Create_Girl_Agents();
                cga.Create_Girl_Agents();
                girls_agents.add(cga.agent);
            }
        }
      

        all_agents.addAll((Collection<? extends Agent>) men_agents.clone());
        all_agents.addAll((Collection<? extends Agent>) women_agents.clone());
        

        Collections.shuffle(all_agents);

        Create_Social_Network csn = new Create_Social_Network();
        csn.Create_Social_Network();

        Create_Couples cc = new Create_Couples();
        cc.Create_Couples();

        Create_Families f = new Create_Families();
        f.Create_Families();


        for(int i=0;i<boys_agents.size();i++){
            if(boys_agents.get(i).parents.size()>0){
                all_agents.add(boys_agents.get(i));
                Social_Norms.boys_agents.add(boys_agents.get(i));
            }
        }

        for(int i=0;i<girls_agents.size();i++){
            if(girls_agents.get(i).parents.size()>0){
                all_agents.add(girls_agents.get(i));
                Social_Norms.girls_agents.add(girls_agents.get(i));
            }
        }

        

        Social_Norms.agents=(Vector<Agent>) all_agents.clone();
        Social_Norms.men_agents=(Vector<Agent>) men_agents.clone();
        Social_Norms.women_agents=(Vector<Agent>) women_agents.clone();
        boys_agents.clear();
        girls_agents.clear();
    }

    public void dynamic(){
       
        
    }

    public class Dymamics implements Steppable {
        
        public void step(SimState state){
            int population=Social_Norms.agents.size();
            int children_per_step=0;
            int death_per_step=0;


            int count1=0;
            
            double rand2001 =0.0;
            Random r = new Random();

            if(population!=0){
                //System.out.println("asdjhakjgfkajgfkasgfkajgfkasghfjkasdhgfjkasghfjk");
                children_per_step=(int) (2.3 * population / 1000);
                death_per_step=(int) (2.5 * population / 1000);
                if(children_per_step==0){
                    children_per_step++;
                    death_per_step++;
                }
                
            }
            for(int i=0;i<children_per_step;i++){
                rand2001=r.nextDouble();
                if(rand2001<0.5){
                    Create_Boy_Agents cba = new Create_Boy_Agents();
                    cba.Create_Boy_Agents();
                    cba.agent.age=0;
                    boys_agents.add(cba.agent);
                }
                else{
                    Create_Girl_Agents cga = new Create_Girl_Agents();
                    cga.Create_Girl_Agents();
                    cga.agent.age=0;
                    girls_agents.add(cga.agent);
                }
            }


            Collections.shuffle(boys_agents);
            Collections.shuffle(girls_agents);
            

            Create_Couples cc = new Create_Couples();
            cc.Create_Couples();

           
            Create_Families f = new Create_Families();
            f.Create_Families();

            for(int i=0;i<boys_agents.size();i++){
            if(boys_agents.get(i).parents.size()>0){
                Social_Norms.boys_agents.add(boys_agents.get(i));
                Social_Norms.agents.add(boys_agents.get(i));
                state.schedule.scheduleRepeating(boys_agents.get(i));
                count1++;
            }
        }

        for(int i=0;i<girls_agents.size();i++){
            if(girls_agents.get(i).parents.size()>0){
                Social_Norms.agents.add(girls_agents.get(i));
                Social_Norms.girls_agents.add(girls_agents.get(i));
                state.schedule.scheduleRepeating(girls_agents.get(i));
                count1++;
            }
        }

            boys_agents.clear();
            girls_agents.clear();

            death_per_step=count1;
    
            count1=0;
            double ran100=0;
            double ran200=0;
            double ran300=0;
            double ran400=0;
            double ran500=0;
            Collections.shuffle(Social_Norms.agents);
            
            for(int i=0;i<death_per_step;i++){
                ran100=generator.nextDouble();
                for(int j=0;j<Social_Norms.agents.size();j++){
                    
                    ran200=generator.nextDouble();
                    ran300=generator.nextDouble();
                    ran400=generator.nextDouble();
                    ran500=generator.nextDouble();
                    if(ran100<0.63 && Social_Norms.agents.get(j).age>240 && Social_Norms.agents.get(j).age<356 && !dead_agents.contains(Social_Norms.agents.get(j))){
                        dead_agents.add(Social_Norms.agents.get(j));
                        break;
                    }
                    if(ran100>=0.63 && ran100<0.78 && Social_Norms.agents.get(j).age>180 && Social_Norms.agents.get(j).age<=240 && !dead_agents.contains(Social_Norms.agents.get(j))){
                        dead_agents.add(Social_Norms.agents.get(j));
                        break;
                    }
                    if(ran100>=0.78 && ran100<0.93 && Social_Norms.agents.get(j).age>140 && Social_Norms.agents.get(j).age<=180 && !dead_agents.contains(Social_Norms.agents.get(j))){
                        dead_agents.add(Social_Norms.agents.get(j));
                        break;
                    }
                    if(ran100>=0.93 && ran100<0.98 && Social_Norms.agents.get(j).age>80 && Social_Norms.agents.get(j).age<=140 && !dead_agents.contains(Social_Norms.agents.get(j))){
                        dead_agents.add(Social_Norms.agents.get(j));
                        break;
                    }
                    if(ran100>=0.98 && ran100<1.0 && Social_Norms.agents.get(j).age>0 && Social_Norms.agents.get(j).age<=80 && !dead_agents.contains(Social_Norms.agents.get(j))){
                        dead_agents.add(Social_Norms.agents.get(j));
                        break;
                    }
                }
            }

            for(int i=0;i<dead_agents.size();i++){
                dead_agents.get(i).remove_agent(dead_agents.get(i).id);
            }


            dead_agents.clear();


            Add_Immigrants ai = new Add_Immigrants();
            state.schedule.scheduleOnce(ai);
         
            double ran15=0;
            for(int i=0;i<Social_Norms.agents.size();i++){
                ran15=generator.nextDouble();
                if(ran15<0.01){
                    Social_Norms.agents.get(i).life_criteria.clear();
                    
                    int wage_rate=0;
                    int institutions_rate=0;
                    int quality_rates=0;
                    int clime_rate=0;
                    int isolation_rate=0;
                    int imigrants_rate=0;

                    wage_rate=generator.nextInt(80)+1;
                    institutions_rate=generator.nextInt(100-wage_rate)+1;
                    if(100-wage_rate-institutions_rate>0)
                        quality_rates=generator.nextInt(100-wage_rate-institutions_rate)+1;
                    else{
                        quality_rates=0;
                        clime_rate=0;
                        isolation_rate=0;
                        imigrants_rate=0;
                    }

                    if(100-wage_rate-institutions_rate-quality_rates>0)
                        clime_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates)+1;
                    else{
                        clime_rate=0;
                        isolation_rate=0;
                        imigrants_rate=0;
                    }

                    if(100-wage_rate-institutions_rate-quality_rates-clime_rate>0)
                        isolation_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates-clime_rate)+1;
                    else{
                        isolation_rate=0;
                        imigrants_rate=0;
                    }

                    if(100-wage_rate-institutions_rate-quality_rates-clime_rate-isolation_rate>0)
                        imigrants_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates-clime_rate-isolation_rate)+1;
                    else
                        imigrants_rate=0;



                    Social_Norms.agents.get(i).life_criteria.add(quality_rates);
                    Social_Norms.agents.get(i).life_criteria.add(wage_rate);
                    Social_Norms.agents.get(i).life_criteria.add(institutions_rate);
                    Social_Norms.agents.get(i).life_criteria.add(clime_rate);
                    Social_Norms.agents.get(i).life_criteria.add(imigrants_rate);
                    Social_Norms.agents.get(i).life_criteria.add(isolation_rate);
                }
            }

            //rebuild the social network
            double ran20=0.0;
            double ran40=0.0;
            int ran30=0;
            int ran2000=0;
            int i10=0;
            Vector<Agent> temp_agents_for_sn = new Vector();
            temp_agents_for_sn=(Vector<Agent>) Social_Norms.agents.clone();
            Collections.shuffle(temp_agents_for_sn);
            for(int i=0;i<Social_Norms.agents.size();i++){
                ran20=generator.nextDouble();
                ran30=generator.nextInt(Social_Norms.agents.size());
                ran40=generator.nextDouble();
                if(ran20<0.05 && Social_Norms.agents.get(ran30).id!=Social_Norms.agents.get(i).id && Social_Norms.agents.get(i).socialnetwork.size()>0){
                    Collections.shuffle(Social_Norms.agents.get(i).socialnetwork);
                    Social_Norms.agents.get(i).socialnetwork.removeElementAt(0);
                    if(ran40>0.5)
                    Social_Norms.agents.get(i).socialnetwork.add(Social_Norms.agents.get(ran30));
                }
                if(Social_Norms.agents.get(i).socialnetwork.size()==0){
                    ran2000=generator.nextInt(5)+1;
                    while(i10<temp_agents_for_sn.size() || i10<ran2000){
                        Social_Norms.agents.get(i).socialnetwork.add(temp_agents_for_sn.get(i10));
                        i10++;
                    }
                }

            }

            temp_agents_for_sn.clear();
            
            
            Vector<Double> d = new Vector();

         //calculate new social norms values
            if(state.schedule.getSteps()>1){
                double temp1=0.0;
                for(int i=0;i<Social_Norms.agents.size();i++){
                    if(Social_Norms.agents.get(i).age>48){
                        temp1=temp1+Social_Norms.agents.get(i).quality_of_life;
                        d.add(Social_Norms.agents.get(i).quality_of_life);
                    }
                }
                Social_Norms.quality_of_life=temp1/Social_Norms.agents.size();
            }

            double d2=0.0;
            if(d.size()>0){
                Collections.sort(d);
                d2=d.get(d.size()/2);
            }

            d.clear();
;

            double temp2=0.0;
            for(int i=0;i<Social_Norms.agents.size();i++){
                if(Social_Norms.agents.get(i).age>48)
                temp2=temp2+Social_Norms.agents.get(i).evolution;
            }
            Social_Norms.education=temp2/Social_Norms.agents.size();

    

            double temp3=0.0;
            for(int i=0;i<Social_Norms.agents.size();i++){
                if(Social_Norms.agents.get(i).age>48)
                temp3=temp3+Social_Norms.agents.get(i).immigration;
            }
            Social_Norms.immigration=temp3/Social_Norms.agents.size();
            
        }


    }
    

    int males_count=0;
    int females_count=0;
    



   public class Create_Man_Agents  {
       Agent agent = new Agent();
       int wage_rate =0;
       int clime_rate=0;
       int isolation_rate=0;
       int institutions_rate=0;
       int imigrants_rate=0;
       int quality_rates=0;

      public void Create_Man_Agents(){
   
            countid++;
         
            agent.id=countid;
            agent.gender=0;
            agent.adult=1;
            agent.evolution=Social_Norms.education+((generator.nextInt(10)-5)/100)*Social_Norms.education;
            agent.immigration=Social_Norms.immigration+((generator.nextInt(10)-5)/100)*Social_Norms.immigration;
            agent.quality_of_life=(double)generator.nextInt(600);
           

            for(int i=0;i<Demographic_Characteristics.skills_pos.size();i++){
                if(Demographic_Characteristics.skills_pos.get(i)>0){
                    agent.skills=i+1;
                    Demographic_Characteristics.skills_pos.set(i, Demographic_Characteristics.skills_pos.get(i)-1);
                    break;
                }
                if(i==Demographic_Characteristics.skills_pos.size()-1 && agent.skills==0){
                    agent.skills=generator.nextInt(10)+1;
                    break;
                }

            }

            if(agent.skills==0){
                 Random r = new Random();
                 double random10=r.nextDouble();
                if(random10<0.3)
                    agent.skills=generator.nextInt(3)+1;
                if(random10>=0.3 && random10<0.7)
                    agent.skills=generator.nextInt(4)+4;
                if(random10>=0.7)
                    agent.skills=generator.nextInt(3)+8;
            }


            agent.racism=generator.nextInt(1);

            for(int i=4;i<Demographic_Characteristics.plithismos_pos.size();i++){
                if(Demographic_Characteristics.plithismos_pos.get(i)>0){
                    agent.age=(generator.nextInt(5)+i*5)*4;
                    Demographic_Characteristics.plithismos_pos.set(i, Demographic_Characteristics.plithismos_pos.get(i)-1);
                    break;
                }
                if(i==Demographic_Characteristics.plithismos_pos.size()-1 && agent.age==0){
                    agent.age=generator.nextInt(50)+75;
                    break;
                }
            }


            agent.death=generator.nextInt(80)+1;
            wage_rate=generator.nextInt(80)+1;
            institutions_rate=generator.nextInt(100-wage_rate)+1;
            if(100-wage_rate-institutions_rate>0)
                quality_rates=generator.nextInt(100-wage_rate-institutions_rate)+1;
            else{
                quality_rates=0;
                clime_rate=0;
                isolation_rate=0;
                imigrants_rate=0;
            }

            if(100-wage_rate-institutions_rate-quality_rates>0)
                clime_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates)+1;
            else{
                clime_rate=0;
                isolation_rate=0;
                imigrants_rate=0;
            }

            if(100-wage_rate-institutions_rate-quality_rates-clime_rate>0)
                isolation_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates-clime_rate)+1;
            else{
                isolation_rate=0;
                imigrants_rate=0;
            }

            if(100-wage_rate-institutions_rate-quality_rates-clime_rate-isolation_rate>0)
                imigrants_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates-clime_rate-isolation_rate)+1;
            else
                imigrants_rate=0;
            
            agent.life_criteria.add(quality_rates);
            agent.life_criteria.add(wage_rate);
            agent.life_criteria.add(institutions_rate);
            agent.life_criteria.add(clime_rate);
            agent.life_criteria.add(imigrants_rate);
            agent.life_criteria.add(isolation_rate);
          ////  System.out.println(""+agent.life_criteria.size()+"life_criteria size ;akjsdgh;akjsdgf");
          //  men_agents.add(agent);


          


            
        }

       
    }

    class Create_Women_Agents{
   // class Create_Women_Agents implements Steppable{
        Agent agent = new Agent();

        int wage_rate =0;
       int clime_rate=0;
       int isolation_rate=0;
       int institutions_rate=0;
       int imigrants_rate=0;
       int quality_rates=0;
  //      public void step(SimState state) {
        public void Create_Women_Agents(){
            //dimiourgia ginaikon
            countid++;
          //  Agent agent = new Agent();
            agent.id=countid;
            agent.gender=1;
            agent.adult=1;
            agent.evolution=Social_Norms.education+((generator.nextInt(10)-5)/100)*Social_Norms.education;
            agent.immigration=Social_Norms.immigration+((generator.nextInt(10)-5)/100)*Social_Norms.immigration;
            //agent.quality_of_life=generator.nextInt(10);


             for(int i=0;i<Demographic_Characteristics.skills_pos.size();i++){
                if(Demographic_Characteristics.skills_pos.get(i)>0){
                    agent.skills=i+1;
                    Demographic_Characteristics.skills_pos.set(i, Demographic_Characteristics.skills_pos.get(i)-1);
                    break;
                }
                if(i==Demographic_Characteristics.skills_pos.size()-1 && agent.skills==0){
                    agent.skills=generator.nextInt(10)+1;
                    break;
                }

            }


            agent.racism=generator.nextInt(1);


            for(int i=4;i<Demographic_Characteristics.plithismos_pos.size();i++){
                if(Demographic_Characteristics.plithismos_pos.get(i)>0){
                    agent.age=(generator.nextInt(5)+i*5)*4;
                    Demographic_Characteristics.plithismos_pos.set(i, Demographic_Characteristics.plithismos_pos.get(i)-1);
                    break;
                }
                if(i==Demographic_Characteristics.plithismos_pos.size()-1 && agent.age==0){
                    agent.age=generator.nextInt(40)+75;
                    break;
                }
            }

            if(agent.skills==0){
                 Random r = new Random();
                 double random10=r.nextDouble();
                if(random10<0.3)
                    agent.skills=generator.nextInt(3)+1;
                if(random10>=0.3 && random10<0.7)
                    agent.skills=generator.nextInt(4)+4;
                if(random10>=0.7)
                    agent.skills=generator.nextInt(3)+8;
            }

            wage_rate=generator.nextInt(80)+1;
            institutions_rate=generator.nextInt(100-wage_rate)+1;
            if(100-wage_rate-institutions_rate>0)
                quality_rates=generator.nextInt(100-wage_rate-institutions_rate)+1;
            else{
                quality_rates=0;
                clime_rate=0;
                isolation_rate=0;
                imigrants_rate=0;
            }

            if(100-wage_rate-institutions_rate-quality_rates>0)
                clime_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates)+1;
            else{
                clime_rate=0;
                isolation_rate=0;
                imigrants_rate=0;
            }

            if(100-wage_rate-institutions_rate-quality_rates-clime_rate>0)
                isolation_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates-clime_rate)+1;
            else{
                isolation_rate=0;
                imigrants_rate=0;
            }

            if(100-wage_rate-institutions_rate-quality_rates-clime_rate-isolation_rate>0)
                imigrants_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates-clime_rate-isolation_rate)+1;
            else
                imigrants_rate=0;

            agent.life_criteria.add(quality_rates);
            agent.life_criteria.add(wage_rate);
            agent.life_criteria.add(institutions_rate);
            agent.life_criteria.add(clime_rate);
            agent.life_criteria.add(imigrants_rate);
            agent.life_criteria.add(isolation_rate);


        }

        
    }

    class Create_Boy_Agents{
        Agent agent = new Agent();

        int wage_rate =0;
       int clime_rate=0;
       int isolation_rate=0;
       int institutions_rate=0;
       int imigrants_rate=0;
       int quality_rates=0;
       double random10=0.0;

        public void Create_Boy_Agents(){
            countid++;
           // Agent agent = new Agent();
            agent.id=countid;
            agent.gender=0;
            agent.adult=0;
            agent.evolution=Social_Norms.education+((generator.nextInt(10)-5)/100)*Social_Norms.education;
            agent.immigration=Social_Norms.immigration+((generator.nextInt(10)-5)/100)*Social_Norms.immigration;
            agent.wage=0;
            agent.age=0;
            //agent.quality_of_life=generator.nextInt(10);

            Random r = new Random();
            random10=r.nextDouble();
            if(random10<0.3)
                agent.skills=generator.nextInt(3)+1;
            if(random10>=0.3 && random10<0.7)
                agent.skills=generator.nextInt(4)+4;
            if(random10>=0.7)
                agent.skills=generator.nextInt(3)+8;
            
            agent.racism=generator.nextInt(1);


            for(int i=0;i<4;i++){
                if(Demographic_Characteristics.plithismos_pos.get(i)>0){
                    agent.age=(generator.nextInt(5)+i*5)*4;
                    Demographic_Characteristics.plithismos_pos.set(i, Demographic_Characteristics.plithismos_pos.get(i)-1);
                    break;
                }
            }

            wage_rate=generator.nextInt(80)+1;
            institutions_rate=generator.nextInt(100-wage_rate)+1;
            if(100-wage_rate-institutions_rate>0)
                quality_rates=generator.nextInt(100-wage_rate-institutions_rate)+1;
            else{
                quality_rates=0;
                clime_rate=0;
                isolation_rate=0;
                imigrants_rate=0;
            }

            if(100-wage_rate-institutions_rate-quality_rates>0)
                clime_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates)+1;
            else{
                clime_rate=0;
                isolation_rate=0;
                imigrants_rate=0;
            }

            if(100-wage_rate-institutions_rate-quality_rates-clime_rate>0)
                isolation_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates-clime_rate)+1;
            else{
                isolation_rate=0;
                imigrants_rate=0;
            }

            if(100-wage_rate-institutions_rate-quality_rates-clime_rate-isolation_rate>0)
                imigrants_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates-clime_rate-isolation_rate)+1;
            else
                imigrants_rate=0;

            agent.life_criteria.add(quality_rates);
            agent.life_criteria.add(wage_rate);
            agent.life_criteria.add(institutions_rate);
            agent.life_criteria.add(clime_rate);
            agent.life_criteria.add(imigrants_rate);
            agent.life_criteria.add(isolation_rate);

          //  boys_agents.add(agent);
        }

    }

    class Create_Girl_Agents{
        Agent agent = new Agent();

        int wage_rate =0;
       int clime_rate=0;
       int isolation_rate=0;
       int institutions_rate=0;
       int imigrants_rate=0;
       int quality_rates=0;
       double random10=0.0;

        public void Create_Girl_Agents(){
            countid++;
            //Agent agent = new Agent();
            agent.id=countid;
            agent.gender=1;
            agent.adult=0;
            agent.evolution=Social_Norms.education+((generator.nextInt(10)-5)/100)*Social_Norms.education;
            agent.immigration=Social_Norms.immigration+((generator.nextInt(10)-5)/100)*Social_Norms.immigration;
            agent.wage=0;
            agent.age=0;
            //agent.quality_of_life=generator.nextInt(10);


            Random r = new Random();
            random10=r.nextDouble();
            if(random10<0.3)
                agent.skills=generator.nextInt(3)+1;
            if(random10>=0.3 && random10<0.7)
                agent.skills=generator.nextInt(4)+4;
            if(random10>=0.7)
                agent.skills=generator.nextInt(3)+8;
            
            agent.racism=generator.nextInt(1);


            agent.racism=generator.nextInt(1);


            for(int i=0;i<4;i++){
                if(Demographic_Characteristics.plithismos_pos.get(i)>0){
                  //  System.out.println("---a-a-a-a-a-a--a-a-nnncncnncncncn");
                    agent.age=(generator.nextInt(5)+i*5)*4;
                    Demographic_Characteristics.plithismos_pos.set(i, Demographic_Characteristics.plithismos_pos.get(i)-1);
                    break;
                }
            }

            wage_rate=generator.nextInt(80)+1;
            institutions_rate=generator.nextInt(100-wage_rate)+1;
            if(100-wage_rate-institutions_rate>0)
                quality_rates=generator.nextInt(100-wage_rate-institutions_rate)+1;
            else{
                quality_rates=0;
                clime_rate=0;
                isolation_rate=0;
                imigrants_rate=0;
            }

            if(100-wage_rate-institutions_rate-quality_rates>0)
                clime_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates)+1;
            else{
                clime_rate=0;
                isolation_rate=0;
                imigrants_rate=0;
            }

            if(100-wage_rate-institutions_rate-quality_rates-clime_rate>0)
                isolation_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates-clime_rate)+1;
            else{
                isolation_rate=0;
                imigrants_rate=0;
            }

            if(100-wage_rate-institutions_rate-quality_rates-clime_rate-isolation_rate>0)
                imigrants_rate=generator.nextInt(100-wage_rate-institutions_rate-quality_rates-clime_rate-isolation_rate)+1;
            else
                imigrants_rate=0;

            agent.life_criteria.add(quality_rates);
            agent.life_criteria.add(wage_rate);
            agent.life_criteria.add(institutions_rate);
            agent.life_criteria.add(clime_rate);
            agent.life_criteria.add(imigrants_rate);
            agent.life_criteria.add(isolation_rate);

           // girls_agents.add(agent);

        }

    }

    class Create_Couples{
        Vector<Agent> temp1 = new Vector();
        int countdel=0;
        double posib=0.0;
        int voithia=0;
        int count_couples_help=0;

        public void Create_Couples(){
            if(count_loops>0){
               // System.out.println(women_agents.size()+"size before");
                men_agents=(Vector<Agent>) Social_Norms.men_agents.clone();
                women_agents=(Vector<Agent>) Social_Norms.women_agents.clone();
              //  System.out.println(women_agents.size()+"size after");
            }
            men_temp.clear();
            women_temp.clear();
            men_temp=(Vector<Agent>) men_agents.clone();
            women_temp=(Vector<Agent>) women_agents.clone();
            for(int i=0;i<men_agents.size();i++){
             //   System.out.println("------------"+i+"-------------i");
                for(int j=0;j<women_temp.size();j++){
                    posib=generator.nextDouble();
                    if(count_loops<1){
                        posib=posib-0.009;
                    }
                    else voithia=1;
                    if(men_agents.get(i).age<women_agents.get(j).age+4 && men_agents.get(i).age>women_agents.get(j).age-10 && men_agents.get(i).partner.size()==0 && women_agents.get(j).partner.size()==0 && posib<0.0003 && men_agents.get(i).age<180 &&count_couples_help<5){
                    //if(men_agents.get(i).partner.size()==0 && women_agents.get(j).partner.size()==0 && posib<0.71){
                        temp1.add(men_agents.get(i));
                        temp1.add(women_temp.get(j));
                        men_agents.get(i).partner.add(women_temp.get(j));
                        //women_temp.get(j).partner.add(men_agents.get(i));
                        //women_temp.remove(j);
                       // men_temp.remove(i-countdel);!!!!!!!allages
                        countdel++;
                    //    System.out.println("------------"+temp1.get(0).id+"-------------man-couples");
                   //      System.out.println("------------"+temp1.get(1).id+"-------------woman-couples");
                        if(count_loops>0)
                            count_couples_help++;
                          //  System.out.println("what?");
                        
                        couples.add((Vector<Agent>) temp1.clone());
                       //  System.out.println("what?"+couples.size());
                        count_couples++;
//                        System.out.println("------------"+couples.get(i).get(0).id+"-------------couples");
                        temp1.clear();
                        break;
                    }
                }
            }

            for(int i=0;i<men_agents.size();i++){
                if(men_agents.get(i).partner.size()!=0)
                    men_agents.get(i).partner.get(0).partner.add(men_agents.get(i));
            }

         //   for(int i=0;i<couples.size();i++){
         //       System.out.println("------------"+couples.get(i).get(1).id+"-------------couples");
         //   }

            count_couples_help=0;
        }

    }

    class Create_Families3{

        int numberofchildren=generator.nextInt(3);
        int gender=generator.nextInt(2);
        Vector<Agent> children = new Vector();
        Vector<Vector<Agent>> couples_temp = new Vector();
        //Vector<Agent> boys_temp = new Vector();
        //Vector<Agent> girls_temp = new Vector();
        Vector<Vector<Agent>> family = new Vector();
        Vector<Agent> single_fam = new Vector();

        int count1=0;
        int count2=0;
        int count3=0;
        int count4=0;
        int count5=0;
        int count6=0;

        public void Create_Families3(){

            int help=0;

            int counthelp=0;

            double rand2001 =0.0;
            Random r = new Random();

         
            Collections.shuffle(couples);
            for(int j=0;j<couples.size();j++){
                if(couples.get(j).get(0).children.size()==0 && couples.get(j).get(0).tries_to_make_children==0 && couples.get(j).get(1).tries_to_make_children==0)
                    help=1;
                else
                    help=0;
                numberofchildren=generator.nextInt(3);
                couples.get(j).get(0).tries_to_make_children++;
                couples.get(j).get(1).tries_to_make_children++;

                if(couples.get(j).get(0).children.size()<2){

                for(int i=0;i<numberofchildren;i++){
                    rand2001 = r.nextDouble();
                    if(rand2001<0.5){
                        Create_Boy_Agents cba = new Create_Boy_Agents();
                        cba.Create_Boy_Agents();
                        boys_agents.add(cba.agent);
                        children.add(cba.agent);
                    }
                    else{
                        Create_Girl_Agents cga = new Create_Girl_Agents();
                        cga.Create_Girl_Agents();
                        girls_agents.add(cga.agent);
                        children.add(cga.agent);
                    }

                }
                couples.get(j).get(0).children.addAll(children);
                couples.get(j).get(1).children.addAll(children);

                }

                if(help==1){
                    family.add(couples.get(j));
                    family.add((Vector<Agent>) children.clone());
                    families.add( (Vector<Vector<Agent>>) family.clone());
                    family.clear();
                    counthelp++;
                }

                children.clear();
            }

        }
    }

    class Create_Families{
        int numberofchildren=generator.nextInt(3);
        int gender=generator.nextInt(2);
        Vector<Agent> children = new Vector();
        Vector<Vector<Agent>> couples_temp = new Vector();
        Vector<Agent> boys_temp = new Vector();
        Vector<Agent> girls_temp = new Vector();
        Vector<Vector<Agent>> family = new Vector();
        Vector<Agent> single_fam = new Vector();

        int count1=0;
        int count2=0;
        int count3=0;
        int count4=0;
        int count5=0;
        int count6=0;
        public void Create_Families(){

            int help=0;

            int counthelp=0;

            int po1=0;

            int counthelp2=0;

            boys_temp = (Vector<Agent>) boys_agents.clone();
            girls_temp = (Vector<Agent>) girls_agents.clone();
            Collections.shuffle(boys_temp);
            Collections.shuffle(girls_temp);
            Collections.shuffle(couples);
            for(int j=0;j<couples.size();j++){
                if(couples.get(j).get(0).children.size()==0 && couples.get(j).get(0).tries_to_make_children==0 && couples.get(j).get(1).tries_to_make_children==0)
                    help=1;
                else
                    help=0;

                po1=generator.nextInt(2);
             
                    numberofchildren=generator.nextInt(4);
                    counthelp2=counthelp2+numberofchildren;
                    System.out.println("#children--"+numberofchildren+"--"+counthelp2);
                couples.get(j).get(0).tries_to_make_children++;
                couples.get(j).get(1).tries_to_make_children++;
                for(int i=0;i<numberofchildren;i++){
                    gender=generator.nextInt(2);
                    if(gender==0 && count1<boys_temp.size()&& couples.get(j).get(0).children.size()<4){
                        children.add(boys_temp.get(count1));
                        boys_temp.get(count1).parents.add(couples.get(j).get(0));
                        boys_temp.get(count1).parents.add(couples.get(j).get(1));
                        boys_temp.remove(count1);
                        count1++;
                    }
                    if(gender==1 && count2<girls_temp.size()&& couples.get(j).get(0).children.size()<4){
                        children.add(girls_temp.get(count2));
                        girls_temp.get(count2).parents.add(couples.get(j).get(0));
                        girls_temp.get(count2).parents.add(couples.get(j).get(1));
                        girls_temp.remove(count2);
                        count2++;
                    }

                }
                count1=0;
                count2=0;
                couples.get(j).get(0).children.addAll(children);
                couples.get(j).get(1).children.addAll(children);

                if(help==1){
                    family.add(couples.get(j));
                    family.add((Vector<Agent>) children.clone());
                    families.add( (Vector<Vector<Agent>>) family.clone());
                    family.clear();
                    counthelp++;
                }

                children.clear();
            }

          
            counthelp=0;

            double rand2001 =0.0;
            double rand2002 =0.0;
            Random r = new Random();

            for(int i=0;i<men_temp.size();i++){
                
                rand2001 = r.nextDouble();
                if(count_loops==0)
                    rand2001=rand2001+0.03;
                if(men_temp.get(i).partner.size()==0 && rand2001>0.9995&& men_temp.get(i).children.size()==0){
                    numberofchildren=generator.nextInt(3);
                    single_fam.add(men_temp.get(i));
                   
                    oti++;
                    for(int j=0;j<numberofchildren;j++){
                        gender=generator.nextInt(2);
                        if(gender==0 && count3<boys_temp.size()){
                            children.add(boys_temp.get(count3));
                            boys_temp.get(count3).parents.add(men_temp.get(i));
                            boys_temp.remove(count3);
                            count3++;
                        }
                        if(gender==1 && count4<girls_temp.size()){
                            children.add(girls_temp.get(count4));
                            girls_temp.get(count4).parents.add(men_temp.get(i));
                            girls_temp.remove(count4);
                            count4++;
                        }
                    }
                    count3=0;
                    count4=0;

                    men_temp.get(i).children.addAll((Collection<? extends Agent>) children.clone());
                    family.add((Vector<Agent>) children.clone());
                    family.add((Vector<Agent>) single_fam.clone());
                    families.add( (Vector<Vector<Agent>>) family.clone());
                    family.clear();
                    children.clear();
                    single_fam.clear();
                }
               
                
            }

          

            for(int i=0;i<women_temp.size();i++){
                rand2002 = r.nextDouble();
                if(count_loops==0)
                    rand2002=rand2002+0.08;
                if(women_temp.get(i).partner.size()==0 && rand2002>0.9989 && women_temp.get(i).children.size()==0){
                    numberofchildren=generator.nextInt(3);
                    single_fam.add(women_temp.get(i));
                    //System.out.println("----xaa--a---xa-----xaaxaxax------");
                    oti++;
                    for(int j=0;j<numberofchildren;j++){
                        gender=generator.nextInt(2);
                        if(gender==0 && count5<boys_temp.size()){
                            children.add(boys_temp.get(count5));
                            boys_temp.get(count5).parents.add(women_temp.get(i));
                            boys_temp.remove(count5);
                            count5++;
                     //       System.out.println("----xaa--a---xa-----xaaxaxax-exo paidia-----");
                        }
                        if(gender==1 && count6<girls_temp.size()){
                            children.add(girls_temp.get(count6));
                            girls_temp.get(count6).parents.add(women_temp.get(i));
                            girls_temp.remove(count6);
                            count6++;
                        //    System.out.println("----xaa--a---xa-----xaaxaxax---exo paidia---");
                        }


                    }
                    count5=0;
                    count6=0;
                    women_temp.get(i).children.addAll((Collection<? extends Agent>) children.clone());
                    family.add((Vector<Agent>) children.clone());
                    family.add((Vector<Agent>) single_fam.clone());
                    families.add( (Vector<Vector<Agent>>) family.clone());
                    family.clear();
                    children.clear();
                    single_fam.clear();


                }
                
            }

        

            if(count_loops==0){
               Social_Norms.couples.addAll((Collection<? extends Vector<Agent>>) couples.clone());
               count_loops++;
               old_num_couples=count_couples;
            }
            else{
                for(int i=old_num_couples;i<couples.size();i++){
                  couples_temp.add((Vector<Agent>) couples.get(i).clone());
         
                }
                old_num_couples=count_couples;
            }

            Social_Norms.couples.addAll((Collection<? extends Vector<Agent>>) couples_temp.clone());
            Social_Norms.families.addAll((Collection<? extends Vector<Vector<Agent>>>) families.clone());
            couples_temp.clear();
            families.clear();

       
        }
        
    }

    class Create_Social_Network implements Steppable{

        public void Create_Social_Network(){

            for(int i=0;i<men_agents.size();i++){
                for(int j=0;j<all_agents.size();j++){
                    int random=generator.nextInt(10);
                    if(random==0 && men_agents.get(i).id!=all_agents.get(j).id){
                        men_agents.get(i).socialnetwork.add(all_agents.get(j));
                    }
                }
            }

            for(int i=0;i<women_agents.size();i++){
                for(int j=0;j<all_agents.size();j++){
                    int random=generator.nextInt(10);
                    if(random==0 && women_agents.get(i).id!=all_agents.get(j).id){
                        women_agents.get(i).socialnetwork.add(all_agents.get(j));
                    }
                }
            }

            for(int i=0;i<boys_agents.size();i++){
                for(int j=0;j<all_agents.size();j++){
                    int random=generator.nextInt(10);
                    if(random==0 && boys_agents.get(i).id!=all_agents.get(j).id){
                        boys_agents.get(i).socialnetwork.add(all_agents.get(j));
                    }
                }
            }

            for(int i=0;i<girls_agents.size();i++){
                for(int j=0;j<all_agents.size();j++){
                    int random=generator.nextInt(10);
                    if(random==0 && girls_agents.get(i).id!=all_agents.get(j).id){
                        girls_agents.get(i).socialnetwork.add(all_agents.get(j));
                    }
                }
            }

        }

       

        public void step(SimState state){

        }
    }


        public class Add_Immigrants implements Steppable{
            int immigrants=0;
            int positions=0;
            int count100=0;
            int count200=0;
            Random r = new Random();
            int ran=0;
            int ran2=0;
            double ran3=0.0;
            int ran4=0;
            int ran5=0;
            int ran6=0;
            int old_positions=0;
           
            public void step(SimState state){
                if(state.schedule.getSteps()>5){
                old_positions=0;

                 for(int i3=0;i3<Economic_Structure.buisness.size();i3++){
               // System.out.println("---1-1-1-1-1--13");
                for(int j=0;j<Economic_Structure.buisness.get(i3).size();j++){
                //    System.out.println("---1-1-1-1-1--12");
                    for(int m=0;m<Economic_Structure.buisness.get(i3).get(j).characteristics.size();m++){
                   //     System.out.println("---1-1-1-1-1--1");
                        old_positions=old_positions+Economic_Structure.buisness.get(i3).get(j).characteristics.get(m).get(3);
                        }
                    }
            }


                immigrants=(old_positions/10)/4;//gia kathe 10 kenes theseis ergasias 1 metanastis(ton xrono) dia 4 mines pou einai to kathe loop
              System.out.println("immigrants+++"+immigrants);
                count200=immigrants;
                for(int i=0; i<immigrants;i++){
                    ran=r.nextInt(10)+1;
                    ran2=r.nextInt(10)+1;
                    if(ran<6 && ran2 <8){
                        Create_Man_Agents cma = new Create_Man_Agents();
                        cma.Create_Man_Agents();
                        Social_Norms.agents.add(cma.agent);
                        Social_Norms.men_agents.add(cma.agent);
                        state.schedule.scheduleRepeating(cma.agent);
                        count200--;
                    }
                    if(ran>=6 && ran2<8){
                        Create_Women_Agents cwa = new Create_Women_Agents();
                        cwa.Create_Women_Agents();
                        Social_Norms.agents.add(cwa.agent);
                        Social_Norms.women_agents.add(cwa.agent);
                        state.schedule.scheduleRepeating(cwa.agent);
                        count200--;
                    }
                }

                count200=(int) (count200 - (count200 * 0.7));

                for(int i=0;i<count200;i++){
                    ran3=r.nextDouble();
                    ran4=r.nextInt(6)-5;
                    ran5=r.nextInt(3);
                    if(ran3<0.002){
                        Create_Man_Agents cma = new Create_Man_Agents();
                        cma.Create_Man_Agents();
                        cma.agent.age=(35+ran4)*4;
                        Social_Norms.agents.add(cma.agent);
                        Social_Norms.men_agents.add(cma.agent);
                        state.schedule.scheduleRepeating(cma.agent);
                        

                        Create_Women_Agents cwa = new Create_Women_Agents();
                        cwa.Create_Women_Agents();
                        cwa.agent.age=(cma.agent.age-4)*4;
                        Social_Norms.agents.add(cwa.agent);
                        Social_Norms.women_agents.add(cwa.agent);
                        state.schedule.scheduleRepeating(cwa.agent);

                        cma.agent.partner.add(cwa.agent);
                        cwa.agent.partner.add(cma.agent);

                        for(int j=0;j<ran5;j++){
                            ran6=r.nextInt(10);
                            if(ran6<5){
                                Create_Boy_Agents cba = new Create_Boy_Agents();
                                cba.Create_Boy_Agents();
                                cba.agent.age=r.nextInt(61);
                                Social_Norms.boys_agents.add(cba.agent);
                                Social_Norms.agents.add(cba.agent);
                                cma.agent.children.add(cba.agent);
                                cwa.agent.children.add(cba.agent);
                                cba.agent.parents.add(cma.agent);
                                cba.agent.parents.add(cwa.agent);
                                state.schedule.scheduleRepeating(cba.agent);

                            }
                            else{
                                Create_Girl_Agents cga = new Create_Girl_Agents();
                                cga.Create_Girl_Agents();
                                cga.agent.age=r.nextInt(61);
                                Social_Norms.girls_agents.add(cga.agent);
                                Social_Norms.agents.add(cga.agent);
                                cma.agent.children.add(cga.agent);
                                cwa.agent.children.add(cga.agent);
                                cga.agent.parents.add(cma.agent);
                                cga.agent.parents.add(cwa.agent);
                                state.schedule.scheduleRepeating(cga.agent);
                            }

                        }
                    }
                }
                }
            }

           
        }

}
