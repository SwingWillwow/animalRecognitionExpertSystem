import java.util.ArrayList;
import java.util.Scanner;

public class ExpertSystem {

    private  boolean run = true;
    private ArrayList<Rule> rules;
    private DataBase dataBase;
    public static void main(String[] args) {
        ExpertSystem system = new ExpertSystem();
        system.initSystem();//初始化系统
        system.runSystem();//运行系统
        system.closeSystem();//结束系统，并保存规则
    }
    public ExpertSystem(){

    }
    private Rule stepForward(){
        for(Rule rule:rules){
            if(dataBase.tryRule(rule))return rule;
        }
        return null;
    }
    private void waitUserCheck(){
        System.out.println("Press Enter to continue.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    private void deduce(){
        Rule rule;
        while((rule=stepForward())!=null){
            if(rule.isEndRule()){
                System.out.println("Finish!");
                System.out.println(rule.getConclusions().get(0));
                break;
            }
        }
    }

    private void initSystem(){
        rules = new RuleReader().readRules();
        dataBase = new DataBase();
        dataBase.setPossibleInitFacts(rules);
    }
    private void runSystem(){
        while (run){
            showMenu();
            char order = getValidOrder();
            executeByOrder(order);
        }
    }
    private void executeByOrder(char order){
        switch (order){
            case 'a':
                addNewRule();
                dataBase.setPossibleInitFacts(rules);
                break;
            case 'd':
                deleteRule();
                break;
            case 's':
                showAllRules();
                break;
            case 'r':
                startDeduce();
                break;
            case '#':
                run=false;
                break;
        }
    }
    private void startDeduce(){
        dataBase.initFacts();
        System.out.println("use rules in following order.");
        deduce();
        waitUserCheck();
    }
    private void showMenu(){
        System.out.println("Chose want to do next.");
        System.out.println("enter a to add new rule");
        System.out.println("enter d to delete rule");
        System.out.println("enter s to show all rules");
        System.out.println("enter r to run deduce.");
        System.out.println("enter # to end the program.");
    }
    private char getValidChar(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        char c;
        if(input.length()!=1){
            System.out.println("invalid input.");
            c = getValidChar();
        }
        else {
            c = input.charAt(0);
        }
        return c;
    }
    private int getValidInt(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        int ret;
        try {
            ret = Integer.parseInt(input);
        }
        catch (Exception e){
            System.out.println("invalid integer.please input again!");
            ret = getValidInt();
        }
        return ret;
    }
    private char getValidOrder(){
        char order = getValidChar();
        if(!isOrderValid(order)){
            return getValidOrder();
        }
        return order;
    }
    private boolean getValidBoolean(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if(input.equals("true")||input.equals("false")){
            return Boolean.parseBoolean(input);
        }
        else{
            return getValidBoolean();
        }
    }
    private boolean isOrderValid(char c){
        return c=='a'||c=='d'||c=='s'||c=='r'||c=='#';
    }
    private void closeSystem(){
        new RuleWriter().writeRules(rules);
    }
    private void addNewRule(){
        int id = rules.get(rules.size()-1).getId()+1;
        Rule rule = new Rule();
        rule.setId(id);
        ArrayList<String> conditions = new ArrayList<>();
        ArrayList<String> conclusions = new ArrayList<>();
        System.out.println("please input conditions(one per line), enter @ to delete least condition if input wrong one. and end with #.");
        rule.setConditions(getNewConditionsOrConclusions());
        System.out.println("please input conclusions(one per line), enter @ to delete least conclusions if input wrong one. and end with #.");
        rule.setConclusions(getNewConditionsOrConclusions());
        System.out.println("is this a end rule? please input true or false");
        rule.setEndRule(getValidBoolean());
        rules.add(rule);
        System.out.println("success.");
    }
    private ArrayList<String> getNewConditionsOrConclusions(){
        ArrayList<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            String condition = scanner.nextLine();
            if(condition.length()==1){
                char c=condition.charAt(0);
                switch (c){
                    case '@':
                        if(list.size()!=0){
                            list.remove(list.get(list.size()-1));
                        }
                        break;
                    case '#':
                        flag = false;
                        break;
                }
            }
            else {
                list.add(condition);
            }
        }
        return list;
    }
    private void deleteRule(){
        System.out.println("please input the id of the rule to delete.");
        int id = getValidInt();
        for(Rule rule:rules){
            if(rule.getId()==id){
                rules.remove(rule);
                System.out.println("success.");
                return;
            }
        }
        System.out.println("do not have such rule.");
    }
    private void showAllRules(){
        for(Rule rule:rules){
            rule.printRule();
        }
    }

}
