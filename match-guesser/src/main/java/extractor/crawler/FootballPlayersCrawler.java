package extractor.crawler;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class FootballPlayersCrawler extends WebCrawler{

	 private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
             + "|png|mp3|mp4|zip|gz))$");
	 
	 private final String baseUrl = "http://www.futhead.com/";
	 
	 private final String filesPath = "/home/francesco/crawled/output/";
	
	 
	 public FootballPlayersCrawler() {
		 super();
	 }
	 /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         String href = url.getURL().toLowerCase();
         String referringPageUrl = referringPage.getWebURL().getURL().toLowerCase();
         return !FILTERS.matcher(href).matches()
        		 	&&
        		(
        	isALeaguePageLink(href)
        			||
        	(isASerieALeaguePageLink(referringPageUrl) && isAPlayersPageLink(href))
        				 
                );
     }

     /**
      * This function is called when a page is fetched and ready
      * to be processed by your program.
      */
     @Override
     public void visit(Page page) {
         String url = page.getWebURL().getURL();
         System.out.println("URL: " + url);
         
         if(isAPlayersPageLink(url) && page.getParseData() instanceof HtmlParseData){
        	 HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
        	 String html = htmlParseData.getHtml();
        	 writeHtmlContent(html, url);
         }
    }
     
     private boolean isALeaguePageLink(String href) {
    	 return href.matches(baseUrl + "[0-9]{2}/leagues/((seriea/|serie-a/|italy-serie-a/|calcio-a/)(\\?page=[0-9]{1,2})?)?");
     }
     
     private boolean isASerieALeaguePageLink(String href) {
    	 return href.matches(baseUrl + "[0-9]{2}/leagues/(seriea|serie-a|italy-serie-a|calcio-a)/(\\?page=[0-9]{1,2})?");
     }
     
     private boolean isAPlayersPageLink(String href) {
    	 return href.matches(baseUrl + "[0-9]{2}/players/[0-9]*/.*");
     }
     
     private void writeHtmlContent(String htmlPage, String url) {
    	 try {
     		PrintWriter writer = new PrintWriter(filesPath + url.split("/")[3] + "/" + url.split("/")[6] + "_" + UUID.randomUUID().toString());
				writer.print(htmlPage);
				writer.close();
				} 
     	catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     }
}
