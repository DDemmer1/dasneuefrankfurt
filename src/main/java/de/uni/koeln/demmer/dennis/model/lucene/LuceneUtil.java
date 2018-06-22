package de.uni.koeln.demmer.dennis.model.lucene;

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

    public void buildIndex() throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();

        Directory index = new NIOFSDirectory(new File("index").toPath());

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter w = new IndexWriter(index, config);


        BufferedReader br = new BufferedReader(new FileReader("IncludedWords.txt"));
        try {
            String line = br.readLine();
            System.out.println("Begin Index building");

            while (line != null) {
                System.out.println(line);

                Document doc = new Document();
                doc.add(new StringField("word", line, Field.Store.YES));
                w.addDocument(doc);
                line = br.readLine();

            }
        } finally {
            br.close();
        }

        w.close();
    }


    public boolean wordInIndex(String word) throws IOException, ParseException {


        Directory index = new NIOFSDirectory(new File("index").toPath());


        if(searchFuzzy(word,index)){
        }else if(!searchWildcardF(word, index)){
            System.out.println("WORD NOT FOUND! -> remove");
        }


        return false;
    }



    private boolean searchWildcardF(String word, Directory index) throws IOException {

        String querystr = word.replaceAll("f", "?");

        Term term = new Term("word", querystr);
        Query q = new WildcardQuery(term);

        // Suche
        int hitsPerPage = 5;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        if (hits.length > 0) {
            System.out.println("TREFFER WILDCARD");
            reader.close();
            return true;
        } else {
            reader.close();
            return false;
        }

    }


    private boolean searchFuzzy(String word, Directory index) throws ParseException, IOException {

        String querystr = word + "~";
        Query q = new QueryParser("word", new StandardAnalyzer()).parse(querystr);

        // Suche
        int hitsPerPage = 5;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        if (hits.length > 0) {
            System.out.println("TREFFER FUZZY");
            reader.close();
            return true;
        } else{
            reader.close();
            return false;
        }
    }


}
