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

public class WebCrawler1 extends WebCrawler{

    public WebCrawler1(String url) {
        super();
        this.url = url;
        Crawler();
    }

    void Crawler(){
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass("table toccolours").select("tr td");

            for (int i = 2; i < elements.size(); i++){
                Element e = elements.get(i);
                Elements names = e.select("b a[href]");
                Elements year = e.select("font");

                if (names.size() != 0 && year.size() != 0 ) {
                      String name = names.get(0).ownText();
                      String[] years =  year.get(0).text().split(" – ");
                      years[0] = years[0].substring(1);
                      if(years.length >= 2) {
                          String y = years[1].split(" ")[0];
                              years[1] = y.substring(0, y.length() - 1 );
                          String yearStart = years[0];
                          String yearEnd = years[1];
                          Dynasty newDynasty = new Dynasty(name, yearStart, yearEnd);
                          Crawler.Dynasties.add(newDynasty);
                      } else {
                          years[0] = years[0].substring(0, years[0].length()-2);
                          String yearStart = years[0];
                          Dynasty newDynasty = new Dynasty(name, yearStart);
                          Crawler.Dynasties.add(newDynasty);
                      }
                }
                else if (names.size() != 0){
                    String name = names.get(0).ownText();
                    Dynasty newDynasty = new Dynasty(name);
                    Crawler.Dynasties.add(newDynasty);
                }
            }


//            JSONObject jo = new JSONObject();
//            JSONArray x = new JSONArray();
//            for(int i = 0; i < Crawler.Dynasties.size(); i++){
//                Map m = new LinkedHashMap(4); // Dynasties
//                m.put("id", Crawler.Dynasties.get(i).getId());
//                m.put("name", Crawler.Dynasties.get(i).getName());
//                m.put("yearStart", Crawler.Dynasties.get(i).getYearStart());
//                m.put("yearEnd", Crawler.Dynasties.get(i).getYearEnd());
//                x.add(m);
//            }
//            jo.put("dynasties", x);
//
//            PrintWriter pw = new PrintWriter("JSONExample.json");
//            String prettyJson = jo.toString();
//            pw.write(jo.toJSONString());
//
//            pw.flush();
//            pw.close();




//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            //System.out.println(gson.toJson (LocateArray));
//            try (FileWriter file = new FileWriter("LocateList.json")){
//                file.write(gson.toJson (Dynasties).toString());
//                file.flush();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
