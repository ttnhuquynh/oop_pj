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
            Elements elements = doc.getElementsByClass("MsoTableGrid");

          for (Element element:  elements) {

            Elements elements1 = element.getElementsByTag("tr");

            for(int i = 1; i < elements1.size(); i++){
                Elements names = elements1.get(i).selectFirst("td").getElementsByAttributeValueContaining("style", "font-family:'Times New Roman'");


                String countryName = "";
                if (elements1.get(i).select("td").size() <= 3){
                    countryName = elements1.get(i).select("td").get(2).getElementsByTag("span").get(0).text();
                }
                else {
                    countryName = elements1.get(i).select("td").get(3).text();
                }

                for (int j = 0; j < Crawler.Dynasties.size(); j++){
                    for (Element name : names) {

                        String nameOfDynasty3 = Crawler.Dynasties.get(j).getName().toLowerCase();
                        String nameOfDynasty2 = name.text().toLowerCase();
                        if (nameOfDynasty2.contains("-"))
                            nameOfDynasty2 = nameOfDynasty2.split("-")[1];
                        if (nameOfDynasty2.contains("(")) {
                            nameOfDynasty2 = nameOfDynasty2.split("\\(")[0];
                        }
                        if (nameOfDynasty2.contains("–")){
                            nameOfDynasty2 = nameOfDynasty2.split("–")[0];
                        }

                        if (nameOfDynasty2.contains("#")){
                            nameOfDynasty2= nameOfDynasty2.split("#")[1];
                        }

                        nameOfDynasty2 = nameOfDynasty2.stripLeading().stripTrailing();


                        if (nameOfDynasty3.contains(nameOfDynasty2)) {
//                            System.out.println(Crawler.Dynasties.get(j).getId() + " " +Crawler.Dynasties.get(j).getName()+ " "  + Crawler.Dynasties.get(i).getRelated() );
                            for (int l = 0; l <=  Crawler.Dynasties.get(j).getRelated(); l++) {
//                                System.out.println(Crawler.Dynasties.get(j).getRelated() + "cekkkd");
                                Crawler.Dynasties.get(j + l).setCountryName(countryName);
                            }

                        }
                    }
                }
            }

          }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
