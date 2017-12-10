import java.util.ArrayList;

public class ExpertSystem {


    private ArrayList<Rule> rules;
    private DataBase dataBase;
    public static void main(String[] args) {
        ExpertSystem system = new ExpertSystem();
        system.rules = new RuleReader().readRules();
        system.dataBase = new DataBase();
        //system.dataBase.initFacts();
        //system.deduce();
        new RuleWriter().writeRules(system.rules);
    }
    public ExpertSystem(){

    }
    private Rule stepForward(){
        for(Rule rule:rules){
            if(dataBase.tryRule(rule))return rule;
        }
        return null;
    }
    private void deduce(){
        Rule rule;
        while((rule=stepForward())!=null){
            if(rule.isEndRule()){
                break;
            }
        }
    }
}
