package Model;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Arrays;

public class OtosLottoHet {
    private int ev;
    private int het;
    private String datum;
    private int otosdb;
    private long otosFt;
    private int negyesdb;
    private int negyesFt;
    private int harmasdb;
    private int harmasFt;
    private int kettesdb;
    private int kettesFt;
    private int[] szamok=new int[5];

    protected OtosLottoHet(Element element) {
        this.ev = Integer.parseInt(element.getElementsByTagName("Év").item(0).getTextContent());
        this.het = Integer.parseInt(element.getElementsByTagName("Hét").item(0).getTextContent());
        try {
            this.datum = element.getElementsByTagName("Húzásdátum").item(0).getTextContent();
        } catch (Exception e) {
            this.datum=null;
        }
        this.otosdb = Integer.parseInt(element.getElementsByTagName("Öt_találat_db").item(0).getTextContent());
        this.otosFt = Long.valueOf(element.getElementsByTagName("Öt_találat_Ft").item(0).getTextContent());
        this.negyesdb = Integer.parseInt( element.getElementsByTagName("Négy_találat_db").item(0).getTextContent());
        this.negyesFt = Integer.parseInt(element.getElementsByTagName("Négy_találat_Ft").item(0).getTextContent());
        this.harmasdb = Integer.parseInt(element.getElementsByTagName("Három_találat_db").item(0).getTextContent());
        this.harmasFt = Integer.parseInt(element.getElementsByTagName("Három_találat_Ft").item(0).getTextContent());
        this.kettesdb = Integer.parseInt(element.getElementsByTagName("Kettő_találat_db").item(0).getTextContent());
        this.kettesFt = Integer.parseInt(element.getElementsByTagName("Kettő_találat_Ft").item(0).getTextContent());
        this.szamok = ToIntArray(element.getElementsByTagName("value"));
    }

    public static int[] ToIntArray(NodeList values) {
        int[]számok=new int[values.getLength()];
        for (int i=0;i<values.getLength();i++){
            Node node=values.item(i);
            if(node.getNodeType()==node.ELEMENT_NODE){
                Element element= (Element) node;
                számok[i]=Integer.parseInt(element.getTextContent());
            }
        }
        return számok;
    }

    public int getEv() {
        return ev;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }

    public int getHet() {
        return het;
    }

    public void setHet(int het) {
        this.het = het;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getOtosdb() {
        return otosdb;
    }

    public void setOtosdb(int otosdb) {
        this.otosdb = otosdb;
    }

    public long getOtosFt() {
        return otosFt;
    }

    public void setOtosFt(long otosFt) {
        this.otosFt = otosFt;
    }

    public int getNegyesdb() {
        return negyesdb;
    }

    public void setNegyesdb(int negyesdb) {
        this.negyesdb = negyesdb;
    }

    public int getNegyesFt() {
        return negyesFt;
    }

    public void setNegyesFt(int negyesFt) {
        this.negyesFt = negyesFt;
    }

    public int getHarmasdb() {
        return harmasdb;
    }

    public void setHarmasdb(int harmasdb) {
        this.harmasdb = harmasdb;
    }

    public int getHarmasFt() {
        return harmasFt;
    }

    public void setHarmasFt(int harmasFt) {
        this.harmasFt = harmasFt;
    }

    public int getKettesdb() {
        return kettesdb;
    }

    public void setKettesdb(int kettesdb) {
        this.kettesdb = kettesdb;
    }

    public int getKettesFt() {
        return kettesFt;
    }

    public void setKettesFt(int kettesFt) {
        this.kettesFt = kettesFt;
    }

    public int[] getSzamok() {
        return szamok;
    }

    public void setSzamok(int[] szamok) {
        this.szamok = szamok;
    }

    @Override
    public String toString() {
        return "OtosLottoHet{" +
                "ev=" + ev +
                ", het=" + het +
                ", datum=" + datum +
                ", otosdb=" + otosdb +
                ", otosFt=" + otosFt +
                ", negyessdb=" + negyesdb +
                ", negyesFt=" + negyesFt +
                ", harmassdb=" + harmasdb +
                ", harmasFt=" + harmasFt +
                ", kettesdb=" + kettesdb +
                ", kettesFt=" + kettesFt +
                ", szamok=" + Arrays.toString(szamok) +
                '}';
    }

}