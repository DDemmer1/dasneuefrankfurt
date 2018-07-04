package de.uni.koeln.demmer.dennis.model.autocorrect.lucene;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import de.uni.koeln.demmer.dennis.model.autocorrect.Util.TextProcessor;
import org.apache.commons.math3.linear.*;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.*;
import org.apache.lucene.store.*;
import org.apache.lucene.util.*;

public class CosineDocumentSimilarity {

    public static final String CONTENT = "Content";

    private final Set<String> terms = new HashSet<>();
    private final RealVector v1;
    private final RealVector v2;

    public CosineDocumentSimilarity(String goldstd, String origin) throws IOException {
        Directory directory = createIndex(goldstd, origin);
        IndexReader reader = DirectoryReader.open(directory);
        Map<String, Integer> f1 = getTermFrequencies(reader, 0);
        Map<String, Integer> f2 = getTermFrequencies(reader, 1);
        reader.close();
        v1 = toRealVector(f1);
        v2 = toRealVector(f2);
    }

    private IndexableFieldType getField(){

        FieldType field = new FieldType(TextField.TYPE_STORED);

        field.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
        field.setTokenized(true);
        field.setStored(true);
        field.setStoreTermVectors(true);
        field.setStoreTermVectorPositions(true);
        field.freeze();



        return field;
    }


    private Directory createIndex(String goldstd, String output) throws IOException {
        Directory directory = new RAMDirectory();
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = new IndexWriter(directory, config);
        System.out.println("Begin Index building");

        Document doc1 = new Document();
        doc1.add(new Field(CONTENT, goldstd, getField()));
        w.addDocument(doc1);


        Document doc2 = new Document();
        doc2.add(new Field(CONTENT, output, getField()));
        w.addDocument(doc2);


        w.close();
        return directory;
    }



    double getCosineSimilarity() {
        return (v1.dotProduct(v2)) / (v1.getNorm() * v2.getNorm());
    }

    public static double getCosineSimilarity(String goldstd, String origin)
            throws IOException {
        return new CosineDocumentSimilarity(goldstd, origin).getCosineSimilarity();
    }

    private Map<String, Integer> getTermFrequencies(IndexReader reader, int docId)
            throws IOException {
        Terms vector = reader.getTermVector(docId, CONTENT);
        TermsEnum termsEnum = vector.iterator();

        Map<String, Integer> frequencies = new HashMap<>();
        BytesRef text = null;
        while ((text = termsEnum.next()) != null) {
            String term = text.utf8ToString();
            int freq = (int) termsEnum.totalTermFreq();
            frequencies.put(term, freq);
            terms.add(term);
        }
        return frequencies;
    }

    private RealVector toRealVector(Map<String, Integer> map) {
        RealVector vector = new ArrayRealVector(terms.size());
        int i = 0;
        for (String term : terms) {
            int value = map.containsKey(term) ? map.get(term) : 0;
            vector.setEntry(i++, value);
        }
        return (RealVector) vector.mapDivide(vector.getL1Norm());
    }
}