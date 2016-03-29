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
public class Demographic_Characteristics implements Steppable{
    
    

    public Integer population=0;//generator.s_DCh.population;
    public Integer men=0;//generator.s_DCh.men*population/100;
    public Integer women=0;//generator.s_DCh.women*population/100;
    public Integer old=0;//generator.s_DCh.old*population/100;
    public Integer single_women=0;//generator.s_DCh.single_women*women/100;
    public Integer single_men=0;//generator.s_DCh.single_men*men/100;
    public Integer children=0;//generator.s_DCh.children*population/100;
    public Integer immigrants=0;//generator.s_DCh.immigrants*population/100;
    public Integer single_women_with_children=0;//generator.s_DCh.single_women_with_children*single_women/100;
    public Integer single_men_with_children=0;//generator.s_DCh.single_men_with_children*single_men/100;
    public Integer unemployment=0;//population*generator.s_DCh.unemployment/100;
    public static Vector<Integer> plithismos_pos = new Vector();
    public static Vector<Integer> skills_pos = new Vector();

    public void Demographic_Characteristics(){
        Generator generator = new Generator();
        generator.demographic_characteristics();
        this.population=generator.population;
        this.men=generator.men*population/100;
        this.children=generator.children*population/100;
        this.women=population-men;
        this.population=population+children;
        System.out.println("----Population: "+this.population);
        System.out.println("----Population,children: "+this.children);
        Demographic_Characteristics.plithismos_pos=(Vector<Integer>) generator.pososta_plithismou.clone();
        Demographic_Characteristics.skills_pos=(Vector<Integer>) generator.pososta_skills;


    }

    public void step(SimState state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
