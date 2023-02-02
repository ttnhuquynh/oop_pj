
import com.google.gson.Gson;

import java.util.ArrayList;


public class Crawler {
    static String json = "...";
    static ArrayList<Dynasty> Dynasties = new ArrayList<>();
    public static void main(String[] args) {
        String url1 = "https://vi.wikipedia.org/wiki/L%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam#";
        String url2 = "https://nguoikesu.com/";
        String url3 = "https://openedu.vn/Kho-tri-thuc/Tom-luoc-lich-su-Viet-Nam-qua-cac-thoi-dai";

//        new WebCrawler1(url1);
//        new WebCrawler2(url2);
        new WebCrawler3(url3);




//      for (int i = 0; i < Dynasties.size(); i++){
//          System.out.println(Dynasties.get(i).getId() +"\n" + Dynasties.get(i).getName() +"\n" + Dynasties.get(i).getYearStart() +"\n" + Dynasties.get(i).getYearEnd() +"\n" );
//      }
    }
}
