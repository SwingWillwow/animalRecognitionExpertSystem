
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class RuleWriter {
    private Document document;
    public RuleWriter(){

    }
    public void writeRules(ArrayList<Rule> rules){
        initDocument();
        Element root = document.createElement("rules");
        document.appendChild(root);
        for(Rule rule:rules){
            Element ruleElem = document.createElement("rule");
            ArrayList<String> conditions = rule.getConditions();
            ArrayList<String> conclusions = rule.getConclusions();
            ruleElem.appendChild(writeConditions(conditions));
            ruleElem.appendChild(writeConclusions(conclusions));
            ruleElem.setAttribute("id",Integer.toString(rules.indexOf(rule)+1));
            ruleElem.setAttribute("end",Boolean.toString(rule.isEndRule()));
            root.appendChild(ruleElem);
        }
        writeDocument();

    }
    private Element writeConditions(ArrayList<String> conditions){
        Element ret = document.createElement("conditions");
        for(String condition:conditions){
            Element conditionElem = document.createElement("condition");
            conditionElem.setAttribute("id",Integer.toString(conditions.indexOf(condition)+1));
            conditionElem.setTextContent(condition);
            ret.appendChild(conditionElem);
        }
        return ret;
    }
    private Element writeConclusions(ArrayList<String> conclusions){
        Element ret = document.createElement("conclusions");
        for(String conclusion:conclusions){
            Element conclusionElem = document.createElement("conclusion");
            conclusionElem.setAttribute("id",Integer.toString(conclusions.indexOf(conclusion)+1));
            conclusionElem.setTextContent(conclusion);
            ret.appendChild(conclusionElem);
        }
        return ret;
    }
    private void initDocument(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private  void writeDocument(){
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new DOMSource(document),new StreamResult(new File("src/saveRuleBank.xml")));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
