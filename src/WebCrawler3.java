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



                    if(!inf.contains("(") ){
                        if(!f.parent().parent().is("ul"))
                            if (f.parent().parent().nextElementSibling() != null) {
                                int lis = f.parent().parent().nextElementSibling().getElementsByTag("li").size();
                                if (lis == 0) {
                                    lis = f.parent().parent().nextElementSibling().nextElementSibling().getElementsByTag("li").size();
                                }
                                String ulf = infs.get(j + lis).text();
                                String ull = infs.get(j + 1).text();
                                String[] s = getYearsAndName(ulf);
                                String[] sm = getYearsAndName(ull);

                                Crawler.Dynasties.add(new Dynasty(information[0], sm[1], s[2], des));
                            }
                        else {
                                Crawler.Dynasties.add(new Dynasty(information[0], information[1], information[2], des));
                            }

                    }

                    else {
                        Crawler.Dynasties.add(new Dynasty(information[0], information[1], information[2], des));
                    }

                }
                        JSONObject jo = new JSONObject();
            JSONArray x = new JSONArray();
            for(int i = 0; i < Crawler.Dynasties.size(); i++){
                Map m = new LinkedHashMap(4); // Dynasties
                m.put("id", Crawler.Dynasties.get(i).getId());
                m.put("name", Crawler.Dynasties.get(i).getName());
                m.put("yearStart", Crawler.Dynasties.get(i).getYearStart());
                m.put("yearEnd", Crawler.Dynasties.get(i).getYearEnd());
                m.put("description", Crawler.Dynasties.get(i).getDescription());
                x.add(m);
            }
            jo.put("dynasties", x);

            PrintWriter pw = new PrintWriter("JSONExample.json");
            String prettyJson = jo.toString();
            pw.write(jo.toJSONString());

            pw.flush();
            pw.close();










//
//                if (e.child(i).attr("font-weight: 700"))
//                if (e.child(i).getElementsByTag("li").size() > 0){
//                    Elements  es = e.child(i).getElementsByTag("li");
//                    for (Element esm : es){
//                        if (esm.attr("font-weight: 700") != null && esm.text().split(":")[0].length() < 54){
//                                inf = esm.text().split(":")[0];
//                        }
//                    }
//                }
//
//                else {
//                    if (e.child(i).attr("font-weight: 700") != null && e.child(i).text().split(":")[0].length() < 54) {
//                        if (!e.child(i).text().contains("\\)") ) {
//                            inf = e.child(i).text().split(":")[0];
//                        }
//                    }
//                }
//                System.out.println(inf);


//                if (inf.contains("\\(")){
//                    String[] infs = inf.split(" \\(");
//                String name = infs[0];
//                String year = infs[1];
//                String[] years = infs[1].split(" – ");
//                String yearStart = years[0];
//                String yearEnd = years[1].split("\\)")[0];
//
//                Dynasty newDynasty = new Dynasty(name, yearStart, yearEnd);
//                Crawler.Dynasties.add(newDynasty);
//                }
//                else {
//                    Crawler.Dynasties.add(new Dynasty(inf));
//                }
//








//            for (Element e: elements){
//                System.out.println(e.text());
//            }






        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
