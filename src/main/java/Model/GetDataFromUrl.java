package Model;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Element;
import javax.net.ssl.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.security.cert.X509Certificate;

public class GetDataFromUrl {

    public static void LoadSourceFromUrl() throws Exception {
        IgnoreCertificate();
        GenerateXmlFromUrl();
    }

    private static void GenerateXmlFromUrl(){
        try {
            GenerateXml("https://bet.szerencsejatek.hu/cmsfiles/otos.html","Otoslotto_hetek.xml");
        } catch (Exception e) {
            System.out.println("Generating xml from https://bet.szerencsejatek.hu/cmsfiles/otos.html FAILED");
        }
    }

    private static void GenerateXml(String url,String xmlOutputName) throws Exception {
        Document htmlDocument = Jsoup.connect(url).get();
        Elements lines = htmlDocument.select("tr");

        String tableHeader= String.valueOf(lines.remove(0));
        String tableHeaderFormatted= tableHeaderFormatter(tableHeader);
        String[]headers= removeColspanTagFromHeader(tableHeaderFormatted.split(";"));
        int numberOfHeaders= countNumberOfHeaders(tableHeaderFormatted);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document xmlDocument = documentBuilder.newDocument();
        Element root = xmlDocument.createElement("hetek");
        xmlDocument.appendChild(root);

        for (org.jsoup.nodes.Element tableRows : lines) {
            String line = lineFormatter(tableRows);
            int numberOfPicks= countNumberOfPicks(numberOfHeaders,line);

            Element week = xmlDocument.createElement("Játék_hét");
            root.appendChild(week);

            int i;
            for(i=0;i<numberOfHeaders;i++) {
                Element temporary = xmlDocument.createElement(headers[i]);
                temporary.appendChild(xmlDocument.createTextNode(line.split(";")[i]));
                week.appendChild(temporary);
            }

            Element numbers=xmlDocument.createElement(headers[i]);
            for(int j=0;j<numberOfPicks;j++){
                Element number=xmlDocument.createElement("value");
                number.appendChild(xmlDocument.createTextNode(line.split(";")[i+j]));
                numbers.appendChild(number);
            }
            numbers.appendChild(xmlDocument.createTextNode(null));
            week.appendChild(numbers);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(xmlDocument);
        StreamResult result = new StreamResult(new File(xmlOutputName));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

    private static String[] removeColspanTagFromHeader(String[] headers) {
        String last=headers[headers.length-1];
        headers[headers.length-1]= last.substring(last.indexOf('>') + 1);
        return headers;
    }

    private static String lineFormatter(org.jsoup.nodes.Element tableRows) {
        String line=String.valueOf(tableRows)
                .replaceAll("\n", ";")
                .replaceAll(" <td>|</td>|<tr>;|;</tr>", "")
                .replaceAll(" Ft", "")
                .replaceAll(" ","");
        if(line.charAt(line.length()-1)==';')
            line=line.substring(0,line.length()-2);
        return line;
    }

    private static int countNumberOfPicks(int numberOfHeaders, String line) {
        int db=1;
        for(int i=0;i<line.length();i++)
            if(line.charAt(i)==';')
                db++;
            return db-numberOfHeaders;
    }

    private static String tableHeaderFormatter(String tableHeader) {
        return tableHeader
                .replaceAll("\n",";")
                .replaceAll(" <th>|</th> |<tr> ;|;</tr>","")
                .replaceAll(" ","_")
                .replaceAll("1","Egy")
                .replaceAll("2","Kettő")
                .replaceAll("3","Három")
                .replaceAll("4","Négy")
                .replaceAll("5","Öt")
                .replaceAll("6","Hat")
                .replaceAll("7","Hét")
                .replaceAll("\\+","_plusz_")
                .replaceAll("\\(|\\)","");
    }

    private static int countNumberOfHeaders(String tableHeaderFormatted) {
        int db=0;
        for(int i=0;i<tableHeaderFormatted.length();i++)
            if(tableHeaderFormatted.charAt(i)==';')
                db++;
            return db;
    }

    /*USING OF THIS METHOD WAS PREVIOUSLY DISCUSSED WITH THE INSTRUCTOR*/
    private static void IgnoreCertificate() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {  }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {  }

                }
        };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }
}