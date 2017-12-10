import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class RuleReader {
    public RuleReader(){

    }
    public ArrayList<Rule> readRules(){
        String rulePath = "src/ruleBank.xml";
        File file = new File(rulePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document=null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        ArrayList<Rule> rules = new ArrayList<>();
        NodeList list = document.getElementsByTagName("rule");
        for(int i=0;i<list.getLength();i++){
            Rule tmpRule = new Rule();
            tmpRule.setConditions(getConditions(list.item(i)));
            tmpRule.setConclusions(getConclusions(list.item(i)));
            tmpRule.setEndRule(isEndRule(list.item(i)));
            tmpRule.setId(getRuleId(list.item(i)));
            rules.add(tmpRule);
        }
        return rules;
    }
    private int getRuleId(Node node){
        String id = node.getAttributes().getNamedItem("id").getNodeValue();
        return Integer.parseInt(id);
    }
    private boolean isEndRule(Node node){
        String end = node.getAttributes().getNamedItem("end").getNodeValue();
        return Boolean.parseBoolean(end);
    }
    private ArrayList<String> getConditions(Node node){
        ArrayList<String> ret = new ArrayList<>();
        NodeList tmpList = node.getChildNodes();
        for(int i=0;i<tmpList.getLength();i++){
            if(tmpList.item(i) instanceof Element&& tmpList.item(i).getNodeName().equals("conditions")){
                node = tmpList.item(i);
                break;
            }
        }
        NodeList list = node.getChildNodes();
        for(int i=0;i<list.getLength();i++){
            if(list.item(i) instanceof Element){
                ret.add(list.item(i).getTextContent());
            }
        }
        return ret;
    }
    private ArrayList<String> getConclusions(Node node){
        ArrayList<String> ret = new ArrayList<>();
        NodeList tmpList = node.getChildNodes();
        for(int i=0;i<tmpList.getLength();i++){
            if(tmpList.item(i) instanceof Element&& tmpList.item(i).getNodeName().equals("conclusions")){
                node = tmpList.item(i);
                break;
            }
        }
        NodeList list = node.getChildNodes();
        for(int i=0;i<list.getLength();i++){
            if(list.item(i) instanceof Element){
                ret.add(list.item(i).getTextContent());
            }
        }
        return ret;
    }
}
