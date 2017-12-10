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
    private ArrayList<FactNode> conditions = new ArrayList<>();
    private ArrayList<FactNode> conclusions = new ArrayList<>();
    private boolean endRule;
    public ArrayList<FactNode> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<FactNode> conditions) {
        this.conditions = conditions;
    }

    public ArrayList<FactNode> getConclusions() {
        return conclusions;
    }

    public void setConclusions(ArrayList<FactNode> conclusions) {
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
        ArrayList<FactNode> conditions = getConditions();
        ArrayList<FactNode> conclusions = getConclusions();
        System.out.print(conditions.get(0));
        for(int i=1;i<conditions.size();i++){
            System.out.print(" AND "+conditions.get(i).getFact());
        }
        System.out.print(" THEN ");
        System.out.print(conclusions.get(0));
        for(int i=1;i<conclusions.size();i++){
            System.out.print(" AND "+conclusions.get(i).getFact());
        }
        System.out.println();
    }
    public void test(){
        System.out.println("hello world!");
    }
}
