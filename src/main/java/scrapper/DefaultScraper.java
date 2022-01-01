package scrapper;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DefaultScraper implements Scraper {
    private static final String PRICE_SELECTOR = ".detail__info-xlrg";
    private static final String BEDS_SELECTOR = ".nhs_BedsInfo";
    private static final String BATHS_SELECTOR = ".nhs_BathsInfo";
    private static final String GARAGE_SELECTOR = ".nhs_GarageInfo";

    @Override @SneakyThrows
    public Home scrape(String url) {
        Document doc = Jsoup.connect(url).get();
//        System.out.println(doc);
        int price = getPrice(doc);
        return new Home(price, getEquipment(doc, BEDS_SELECTOR), getEquipment(doc, BATHS_SELECTOR), getEquipment(doc, GARAGE_SELECTOR));
    }

    private int getPrice(Document doc) {
        String priceElement = doc.selectFirst(PRICE_SELECTOR).text();
        String price = priceElement.substring(
                priceElement.indexOf("$") + 1,
                priceElement.indexOf(","));
        //System.out.println(price);
        //price = priceElement.text().replaceAll("[^0-9]+", "");
        return Integer.parseInt(price);
    }

    private double getEquipment(Document doc, String selector) {
        String element = doc.selectFirst(selector).text();
        String eq = element.substring(
                element.indexOf("<span>") + 1,
                element.indexOf(" "));
        return Double.parseDouble(eq);
    }
}
