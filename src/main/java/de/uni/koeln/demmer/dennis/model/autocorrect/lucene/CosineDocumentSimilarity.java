package de.uni.koeln.demmer.dennis.model.autocorrect.lucene;

import java.io.IOException;
import java.util.*;

import org.apache.commons.math3.linear.*;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.store.*;
import org.apache.lucene.util.*;

public class CosineDocumentSimilarity {

    private static final String CONTENT = "Content";
    private final Set<String> terms = new HashSet<>();
    private final RealVector v1;
    private final RealVector v2;

    /**
     * Eine Klasse zum vergleichen zweier Strings, mithilfe der Kosinus Aehnlichkeit.
     * Es werden zwei Strings auf Vektoren uebertragen und deren Winkel zueinander berechnet.
     *
     * @param goldstd
     * @param output
     * @throws IOException
     */
    public CosineDocumentSimilarity(String goldstd, String output) throws IOException {
        Directory directory = createIndex(goldstd, output);
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

    /**
     * Gibt die Kosinus Aehnlichkeit der Strings zurueck.
     *
     * @return Die Kosinus Aehnlichkeit der Strings als Double
     */
    public double getCosineSimilarity() {
        return (v1.dotProduct(v2)) / (v1.getNorm() * v2.getNorm());
    }

    private Directory createIndex(String goldstd, String output) throws IOException {
        Directory directory = new RAMDirectory();
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = new IndexWriter(directory, config);

        Document doc1 = new Document();
        doc1.add(new Field(CONTENT, goldstd, getField()));
        w.addDocument(doc1);

        Document doc2 = new Document();
        doc2.add(new Field(CONTENT, output, getField()));
        w.addDocument(doc2);
        w.close();
        return directory;
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