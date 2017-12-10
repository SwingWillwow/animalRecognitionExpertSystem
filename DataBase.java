import java.util.ArrayList;

public class DataBase {
    private ArrayList<String> facts = new ArrayList<>();
    private ArrayList<String> possibleInitFacts = new ArrayList<>();
    public DataBase(){

    }

    public ArrayList<String> getFacts() {
        return facts;
    }

    public void setPossibleInitFacts(ArrayList<Rule> rules){

    }
    public void setFacts(ArrayList<String> facts) {
        this.facts = facts;
    }

    public void addFacts(ArrayList<String> newFacts){
        facts.addAll(newFacts);
    }
    public boolean tryRule(Rule rule){
        if(testRule(rule)){
            if(useThen(rule)){
                rule.printRule();
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    //use-then in textbook
    private boolean useThen(Rule rule){
        ArrayList<String> conclusions = rule.getConclusions();
        boolean flag = false;
        for(String conclusion:conclusions){
            if(remember(conclusion)){
                flag = true;
            }
        }
        return flag;
    }
    //remember in textbook
    private boolean remember(String fact){
        if(facts.contains(fact)){
            return false;
        }
        else {
            facts.add(fact);
            return true;
        }
    }

    private boolean testRule(Rule rule){
        ArrayList<String> conditions = rule.getConditions();
        for (String condition:conditions){
            if(!recallSingleFact(condition)) return false;
        }
        return true;
    }
    //recall in textbook
    private boolean recallSingleFact(String fact){
        return facts.contains(fact);
    }

    public void initFacts(){
        facts.add("animal has dark spots");
        facts.add("animal has black stripes");
        facts.add("animal has tawny color");
        facts.add("animal eats meat");
        facts.add("animal has hair");
    }


}
