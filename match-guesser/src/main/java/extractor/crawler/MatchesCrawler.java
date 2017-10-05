package extractor.crawler;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MatchesCrawler  extends WebCrawler{

	 private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp4|zip|gz))$");
	 
	 private final String baseUrl = "http://www.diretta.it/calcio/italia/";
	
	 //just from 2010 to 2018
	 private final String seriea = "serie-a-201[0-9]-201[0-9]/"; 
	 
	 private final String filesPath = "/home/francesco/crawled/output/matches/";
	
	 
	 public MatchesCrawler() {
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
       		 	acceptPageLink(href);
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);
        
        if(isAMatchFormationsPageLink(url) && page.getParseData() instanceof HtmlParseData){
       	 HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
       	 String html = htmlParseData.getHtml();
       	 writeHtmlContent(html, url);
        }
   }
    
    private boolean acceptPageLink(String href){
    	return isASerieAChampionshipPageLink(href)
    			||
    			isAResultsPageLink(href)
    			||
    			isAMatchInfoPageLink(href)
    			||
    			isAMatchFormationsPageLink(href);
    }
    
    
    private boolean isASerieAChampionshipPageLink(String href) {
   	 return href.matches(baseUrl + seriea);
    }
    
    private boolean isAResultsPageLink(String href) {
    	return href.matches(baseUrl + seriea + "risultati/");
    }
    
    private boolean isAMatchInfoPageLink(String href) {
    	return href.matches("http://www.diretta.it/partita/.*/#informazioni-partita");
    }
    
    private boolean isAMatchFormationsPageLink(String href) {
    	return href.matches("http://www.diretta.it/partita/.*/#formazioni;1");
    }
    
    
    private void writeHtmlContent(String htmlPage, String url) {
   	 /*try {
    		PrintWriter writer = new PrintWriter(filesPath + url.split("/")[3] + "/" + url.split("/")[6] + "_" + UUID.randomUUID().toString());
				writer.print(htmlPage);
				writer.close();
				} 
    	catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
    }
}