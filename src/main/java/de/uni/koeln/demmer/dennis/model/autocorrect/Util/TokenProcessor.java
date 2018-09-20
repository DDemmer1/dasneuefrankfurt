package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import de.uni.koeln.demmer.dennis.model.autocorrect.lucene.LuceneUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TokenProcessor {



    public List<Token> process(List<Token> tokenList){

        LuceneUtil util = new LuceneUtil();

        for (Token token : tokenList) {
            util.processToken(token);
        }


        return tokenList;
    }






}
