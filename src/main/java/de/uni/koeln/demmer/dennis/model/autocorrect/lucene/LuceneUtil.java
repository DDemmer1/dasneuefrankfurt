package de.uni.koeln.demmer.dennis.model.autocorrect.lucene;

import de.uni.koeln.demmer.dennis.model.autocorrect.Util.TextProcessor;
import de.uni.koeln.demmer.dennis.model.autocorrect.Util.Token;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LuceneUtil {

    /**
     * Erzeugt einen Invertierten Index im Ordner "Index", aus einer .txt Datei.
     * Dabei muss die txt Datei so angelegt sein, dass pro Zeile ein Wort steht
     *
     * @param path Der Pfad zur .txt Datei
     * @throws IOException
     */
    public void buildDictionary(String path) throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = new NIOFSDirectory(new File("index").toPath());
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = new IndexWriter(index, config);

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine();
        System.out.println("Begin Index building");

        while (line != null) {
            System.out.println(line);

            Document doc = new Document();
            doc.add(new StringField("word", line, Field.Store.YES));
            w.addDocument(doc);
            line = br.readLine();

        }
        br.close();

        w.close();
    }

    /**
     * Erzeugt einen Invertierten Index im Ordner "goldstd", aus einer .txt Datei.
     *
     * @param path Der Pfad zur .txt Datei des Goldstandards
     * @throws IOException
     */
    public void buildGoldStandard(String path) throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = new NIOFSDirectory(new File("goldstd").toPath());
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = new IndexWriter(index, config);


        System.out.println("Begin Index building");

        String goldstd = TextProcessor.readFile(path, StandardCharsets.ISO_8859_1);

        Document doc = new Document();
        doc.add(new StringField("word", goldstd, Field.Store.YES));
        w.addDocument(doc);


        w.close();
    }


    /**
     * Verarbeitet ein Token und sucht nach Uebereinstimmungen und Aehnlichkeiten im Woerterbuch.
     * Alle gefundenen Eintraege werden im Token gespeichert.
     *
     * @param token Das zu verarbeitende Token
     * @return
     */
    public Token processToken(Token token) {
        try {
            if (Character.isLetterOrDigit(token.getOrigin().charAt(0)) && token.getOrigin().length() >= 1) {
                Directory index = new NIOFSDirectory(new File("index").toPath());
                token = search(token, index, false);

                if (!token.isInWB()) {
                    token = search(token, index, true);
                }
            } else if (!token.isBlank()) {
                token.setSpecialChar(true);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return token;
    }


    private Token search(Token token, Directory index, boolean fuzzy) throws ParseException, IOException {

        //Die fToS Liste als erstes abarbeiten
        if (!token.getfToS().isEmpty() && !fuzzy) {

            for (String ftos : token.getfToS()) {

                String ftosResult = searchFtoS(ftos, index);

                if (!ftosResult.equals("")) {
                    token.setInWB(true);
                    token.getMostSimiliar().add(ftosResult);
                }

            }
            return token;
        }


        Query q = null;
        if (fuzzy) {
            String querystr = token.getOrigin();
            q = new FuzzyQuery(new Term("word", querystr));
        } else {
            String querystr = token.getOrigin();
//            q = new QueryParser("word", new StandardAnalyzer()).parse(querystr);
            q = new TermQuery(new Term("word",querystr));
        }


        // Suche
        int hitsPerPage = 5;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        if (hits.length > 0) {


            if (!fuzzy) {
                token.setInWB(true);
            }
            for (int i = 0; i < hits.length; ++i) {

                int docId = hits[i].doc;
                Document d = searcher.doc(docId);
                String hit = d.get("word");
                token.getMostSimiliar().add(hit);
            }

        } else {

            token.setInWB(false);
        }

        reader.close();
        return token;
    }



    private String searchFtoS(String ftos, Directory index) throws ParseException, IOException {

        String ftosResult = "";
        Query q = new TermQuery(new Term("word",ftos));

        int hitsPerPage = 5;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        if (hits.length > 0) {

            int docId = hits[0].doc;
            Document d = searcher.doc(docId);
            ftosResult = d.get("word");

        }
        reader.close();
        return ftosResult;
    }

}
