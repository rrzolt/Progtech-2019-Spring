package Model;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadData{
    public List<OtosLottoHet> otoshetek=new ArrayList<>();
    public String jackPot5=null;

    public LoadData(String sourceXml,String jackpotSourceHtml) throws Exception {
        Model.GetDataFromUrl.LoadSourceFromUrl();
        LoadListAndJackpot(sourceXml,jackpotSourceHtml);
    }

    private void LoadListAndJackpot(String sourceXml, String jackpotSourceHtml) throws IOException {
        FillListWithData(otoshetek,sourceXml);
        jackPot5=getJackPot(jackpotSourceHtml);
    }

    private String getJackPot(String url) throws IOException {
        org.jsoup.nodes.Document htmlDocument = Jsoup.connect(url).get();
        Elements lines = htmlDocument.select("h3");
        String jackPot;
        jackPot=new String(String.valueOf(lines.remove(1)).replaceAll("<h3>|</h3>",""));
        return jackPot;
    }


    private void FillListWithData(List list, String file){
        {
            try {
                File input = new File(file);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db= dbf.newDocumentBuilder();
                Document document=db.parse(input);
                document.getDocumentElement().normalize();
                NodeList nodeList=document.getElementsByTagName("Játék_hét");

                for(int i=0;i<nodeList.getLength();i++){
                    Node node=nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element= (Element) node;
                            list.add(new OtosLottoHet(element));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
