
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class Crawler {
    static String json = "...";
    static ArrayList<Dynasty> Dynasties = new ArrayList<>();
    public static void main(String[] args) {

        String url2 = "http://leloi.phuyen.edu.vn/sinh-hoat-chuyen-mon/to-su-gdcd/lich-su-viet-nam-qua-cac-trieu-dai-2879-tcn-1976-.html";
        String url3 = "https://openedu.vn/Kho-tri-thuc/Tom-luoc-lich-su-Viet-Nam-qua-cac-thoi-dai";

        new WebCrawler3(url3);
        new WebCrawler2(url2);

        int xx = 0;
        for (int i = 0; i < Dynasties.size(); i++) {
            if (Dynasties.get(i).getRelated() != 0) {
                System.out.println(Crawler.Dynasties.get(i).getId() + " " +Crawler.Dynasties.get(i).getName() + " " + Crawler.Dynasties.get(i).getRelated() + " " + Crawler.Dynasties.get(i).getCountryName() );
            }
        }



        try {
            JSONObject jo = new JSONObject();
            JSONArray x = new JSONArray();
            for (int i = 0; i < Crawler.Dynasties.size(); i++) {
                Map m = new LinkedHashMap(4); // Dynasties
                m.put("id", Crawler.Dynasties.get(i).getId());
                m.put("name", Crawler.Dynasties.get(i).getName());
                m.put("yearStart", Crawler.Dynasties.get(i).getYearStart());
                m.put("yearEnd", Crawler.Dynasties.get(i).getYearEnd());
                m.put("description", Crawler.Dynasties.get(i).getDescription());
                m.put("countryName", Crawler.Dynasties.get(i).getCountryName() );
                x.add(m);
            }
            jo.put("dynasties", x);

            PrintWriter pw = new PrintWriter("JSONExample.json");
            String prettyJson = jo.toString();
            pw.write(jo.toJSONString());

            pw.flush();
            pw.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
