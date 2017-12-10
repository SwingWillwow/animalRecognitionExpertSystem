import java.util.ArrayList;

public class Rule {
    class FactNode{
        public FactNode(){

        }
        private String fact;
        private String type;
        public String getFact() {
            return fact;
        }

        public void setFact(String fact) {
            this.fact = fact;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
    private int id;
    private ArrayList<String> conditions = new ArrayList<>();
    private ArrayList<String> conclusions = new ArrayList<>();
    private boolean endRule;
    public ArrayList<String> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<String> conditions) {
        this.conditions = conditions;
    }

    public ArrayList<String> getConclusions() {
        return conclusions;
    }

    public void setConclusions(ArrayList<String> conclusions) {
        this.conclusions = conclusions;
    }

    public boolean isEndRule() {
        return endRule;
    }

    public void setEndRule(boolean endRule) {
        this.endRule = endRule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void printRule(){
        System.out.print("use No."+getId()+" rule:");
        System.out.print("IF ");
        ArrayList<String> conditions = getConditions();
        ArrayList<String> conclusions = getConclusions();
        System.out.print(conditions.get(0));
        for(int i=1;i<conditions.size();i++){
            System.out.print(" AND "+conditions.get(i));
        }
        System.out.print(" THEN ");
        System.out.print(conclusions.get(0));
        for(int i=1;i<conclusions.size();i++){
            System.out.print(" AND "+conclusions.get(i));
        }
        System.out.println();
    }
    public void test(){
        System.out.println("hello world!");
    }
}
