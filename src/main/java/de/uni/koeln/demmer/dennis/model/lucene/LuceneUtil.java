package de.uni.koeln.demmer.dennis.model.lucene;

import de.uni.koeln.demmer.dennis.model.Util.Token;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LuceneUtil {

    /**
     * Erzeugt einen Invertierten Index im Ordner "Index", aus einer .txt Datei.
     *
     * @param path Der Pfad zur .txt Datei
     * @throws IOException
     */
    public void buildIndex(String path) throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = new NIOFSDirectory(new File("index").toPath());
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = new IndexWriter(index, config);

        BufferedReader br = new BufferedReader(new FileReader("IncludedWords.txt"));
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
     * Verarbeitet ein Token und sucht nach Uebereinstimmungen und Aehnlichkeiten im Woerterbuch
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
            } else {
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


        Query q = null;
        if (fuzzy) {
            String querystr = token.getOrigin();
            q = new FuzzyQuery(new Term("word", querystr));
        } else {
            String querystr = token.getOrigin();
            q = new QueryParser("word", new StandardAnalyzer()).parse(querystr);
        }


        // Suche
        int hitsPerPage = 5;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        if (hits.length > 0) {

            if (!fuzzy)
                token.setInWB(true);

            for (int i = 0; i < hits.length; ++i) {

                int docId = hits[i].doc;
                Document d = searcher.doc(docId);
                String hit = d.get("word");
                token.getMostSimiliar().add(hit);
            }

        } else {

            token.setInWB(false);
        }
        return token;
    }
}
