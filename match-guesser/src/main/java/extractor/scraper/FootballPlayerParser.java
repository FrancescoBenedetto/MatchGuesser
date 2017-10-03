package extractor.scraper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FootballPlayerParser {

	public void scriviFile(File file) {
		try {
			Document doc = Jsoup.parse(file, "UTF-8", "http://html.it/");
			// Document doc 
			// Jsoup.connect("http://www.futhead.com/13/players/13545/antonio-di-natale/").get();
			int dimensione = doc.body().getElementsByClass("player-stat-title").next().size();
			String titolo = doc.title();
			int lunghezzaNome = this.definisciLunghezzaNome(titolo);
			int differenza = titolo.length() - lunghezzaNome;
			String nomeGiocatore = titolo.substring(0, titolo.length() - differenza);
			File fileOutput = new File("C:\\Users\\Davben-Nerd\\Desktop\\SerieA\\" + nomeGiocatore + ".txt");
			if (!fileOutput.exists()) {
				fileOutput.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(fileOutput);
			PrintWriter pw = new PrintWriter(fos);
			pw.println(nomeGiocatore);
			pw.println();
			if (this.isPortiere(doc)) {
				for (int i = 0; i < dimensione; i++) {
					pw.print(doc.body().getElementsByClass("player-stat-title").get(i).text() + ",");
					pw.print(doc.body().getElementsByClass("player-stat-title").next().get(i).text() + ";");
				}
			} else {
				for (int i = 7; i < dimensione; i++) {
					pw.print(doc.body().getElementsByClass("player-stat-title").get(i).text() + ",");
					pw.print(doc.body().getElementsByClass("player-stat-title").next().get(i).text() + ";");
				}
			}
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public boolean isPortiere(Document doc) {
		if (!doc.body().getElementsByClass("player-stat-title").next().get(0).text().equals("None"))
			return true;
		return false;
	}

	public void trascriviInCSV(String path) {
		File folderprincipale = new File(path);
		File[] listaGiocatori = folderprincipale.listFiles();
		for (int i = 0; i < listaGiocatori.length; i++) {
			this.scriviFile(listaGiocatori[i]);
		}
	}

	public int definisciLunghezzaNome(String titolo) {
		for (int i = 0; i < titolo.length(); i++) {
			if ((Character.isUpperCase(titolo.charAt(i))) && Character.isUpperCase(titolo.charAt(i + 1)))
				return i - 1;
		}
		return 0;
	}

	public static void main(String[] args) {
		String path = "C:\\Users\\Davben-Nerd\\Downloads\\13-seriea-players\\13-seriea-players";
		FootballPlayerParser jp = new FootballPlayerParser();
		jp.trascriviInCSV(path);
	}
	// public static void main(String[] args){
	// try{
	// Document doc =
	// Jsoup.connect("http://www.futhead.com/13/players/13545/antonio-di-natale/").get();
	// System.out.println(doc.html());
	//
	// }
	// catch(Exception e){
	// e.printStackTrace();
	// }
	// }
}
