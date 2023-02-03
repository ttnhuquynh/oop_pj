import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class WebCrawler3 extends WebCrawler {
    public WebCrawler3(String url){
        super();
        this.url = url;
        Crawler();
    }


    private String[] getYearsAndName(String inf) {
        String[] information = new String[3];

        if (inf.contains("(")) {
            String[] atts = inf.split(" \\(");
            String name = atts[0];
            information[0] = name;
            String year = atts[1];
            String[] years = {""};
            if (year.contains("–")) {
                years = year.split(" – ");
            } else {
                years = year.split("-");
            }

            if (years.length == 2) {
                information[1] = years[0];
                information[2] = years[1].split("\\)")[0];

            } else {
                information[1] = years[0];

            }

        }
        else {
            information[0] = inf;
        }
        return information;
    }


    void Crawler(){
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass("noidung1");
            Element e = elements.get(0);


                Elements infs = e.getElementsByAttributeValueContaining("style", "font-weight: 700");
                for (int j = 0; j < infs.size(); j++) {
                    Element f = infs.get(j);
                    String inf = f.text().split(":")[0];
                    String[] information = getYearsAndName(inf);

                    Element f1 = infs.get(j);
                    String inf1 = f1.text().split(":")[0];
                    String[] information1 = getYearsAndName(inf1);
                    String des = "";
                    if (f.nextElementSibling() != null)
                        des = f.nextElementSibling().ownText();
                    else des = null;



                    if(!inf.contains("(") ) {
                        if (!f.parent().parent().is("ul")){
                            if (f.parent().parent().nextElementSibling() != null) {
                                int lis = f.parent().parent().nextElementSibling().getElementsByTag("li").size();
                                if (lis == 0) {
                                    lis = f.parent().parent().nextElementSibling().nextElementSibling().getElementsByTag("li").size();
                                }
//                                System.out.println(lis);
                                String ulf = infs.get(j + lis).text();
                                String ull = infs.get(j + 1).text();
                                String[] s = getYearsAndName(ulf);
                                String[] sm = getYearsAndName(ull);
                                Dynasty newDy = new Dynasty(information[0], sm[1], s[2], des, lis);
//                                System.out.println(newDy.getId() + " " + newDy.getName() +" " + newDy.getRelated());
                                Crawler.Dynasties.add(newDy);

                            } else {
                                Crawler.Dynasties.add(new Dynasty(information[0], information[1], information[2], des, 0));
                            }
                    }
                    }

                    else {
                        Crawler.Dynasties.add(new Dynasty(information[0], information[1], information[2], des, 0));
                    }

                }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
