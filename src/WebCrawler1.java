//import org.json.JSONArray;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class WebCrawler1 {
    public static void main(String[] args) throws IOException, ParseException {
        int i = 0;

        Document doc = Jsoup.connect("https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam").get();

        Elements table = doc.getElementsByTag("table");
        for(Element a : table) {
            Elements infs = a.getElementsByAttributeValue("cellpadding","0");
            for(int i1 = 2; i1 < infs.size(); i1++) {
                Element d = infs.get(i1);
                if(d.text() != null) {
                    System.out.println(d.text());
                }
            }
        }


//        for(Element row : table) {
//           if (i != 0) {
//                JSONObject king = new JSONObject();
//                Element d = table.get(i);
//                king.put("Vua", d.select("td").get(1).text());
//                king.put("Tên húy", d.select("td").get(5).text());
//                king.put("Thế thứ", d.select("td").get(6).text());
//                king.put("Trị vì", d.select("td").get(7).text());
//
//                	KingArray.put(king);
//                	i++;
//
//            } else {
//                i++;
//                continue;
//            }
//        }
//        for(int m=0; m<KingArray.length(); m++){
//            JSONObject obj = KingArray.getJSONObject(m);
//            String name = obj.getString("Vua");
//            King item = new King();
//            item.setTitle(name);
//			b.add(item);
//        }
//        for(int l = 0; l < b.size(); l++) {
//			System.out.println(b.get(l).getTitle());
//		}
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        //System.out.println(gson.toJson (LocateArray));
//        try (FileWriter file = new FileWriter("KingList.json")){
//            file.write(gson.toJson (KingArray).toString());
//            file.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("File has been created");
//
    }
}