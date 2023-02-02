import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebCrawler2 extends WebCrawler {
    public WebCrawler2(String url){
        super();
        this.url = url;
        Crawler();
    }

    void Crawler(){
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass("mod-articlescategories categories-module mod-list");

            for (Element e: elements){
                System.out.println(e.text());
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
