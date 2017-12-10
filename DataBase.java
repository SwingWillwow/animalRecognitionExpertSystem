import java.util.ArrayList;
import java.util.Scanner;

public class DataBase {
    private ArrayList<String> facts = new ArrayList<>();
    private ArrayList<String> possibleInitFacts = new ArrayList<>();
    public DataBase(){

    }

    public ArrayList<String> getFacts() {
        return facts;
    }

    public void setPossibleInitFacts(ArrayList<Rule> rules){
        ArrayList<String> allFacts = new ArrayList<>();
        for(Rule rule:rules){
            ArrayList<String> conditions = rule.getConditions();
            for(String condition: conditions){
                if(!allFacts.contains(condition)){
                    allFacts.add(condition);
                }
            }
        }
        for(Rule rule:rules){
            ArrayList<String> conclusions = rule.getConclusions();
            for(String conclusion:conclusions){
                if(allFacts.contains(conclusion)){
                    allFacts.remove(conclusion);
                }
            }
        }
        possibleInitFacts = allFacts;

    }

    public ArrayList<String> getPossibleInitFacts() {
        return possibleInitFacts;
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
        facts.clear();
        ArrayList<Integer> numbers = new ArrayList<>();
        System.out.println("those are possible facts. please choice them with number(one per line).");
        System.out.println("end with a sharp(#)");
        for(String fact:possibleInitFacts){
            System.out.println("NO."+(possibleInitFacts.indexOf(fact)+1)+": "+fact);
        }
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.next();
            if(input.length()==1&&input.charAt(0)=='#'){
                break;
            }
            if(!isInteger(input)) continue;
            if(numbers.contains(Integer.parseInt(input))){
                System.out.println("duplicated number.");
                continue;
            }
            numbers.add(Integer.parseInt(input));
        }
        for(Integer num:numbers){
            facts.add(possibleInitFacts.get(num-1));
        }
        System.out.println("init complete. start deduce.");
    }

    private boolean isInteger(String input){
        try {
            int id = Integer.parseInt(input);
        }
        catch (Exception e){
            System.out.println("invalid input! please try again!");
            return false;
        }
        return true;
    }



}
