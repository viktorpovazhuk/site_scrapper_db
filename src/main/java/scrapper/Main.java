package scrapper;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.newhomesource.com/specdetail/2227-almond-creek-lane-fulshear-tx-77423/2047420";
//        String url = "localhost";
        Scraper defaultScraper = new CacheScraper();
        Home home = defaultScraper.scrape(url);
        System.out.println(home);
    }
}
