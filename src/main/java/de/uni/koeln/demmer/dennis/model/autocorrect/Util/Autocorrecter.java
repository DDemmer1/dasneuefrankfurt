package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import java.util.List;

public class Autocorrecter {


    public static String getAutocorrectText(List<Token> tokens){

        StringBuffer buffer = new StringBuffer();

        int index = 0;
        for (Token token: tokens) {
            buffer.append(token.getOrigin());
            if(index%10 == 0){
                buffer.append("\n");
            }
            else{
                buffer.append(" ");
            }
            index++;
        }

        return buffer.toString();
    }


    public String getTaggedText(List<Token> tokens){


        return "";
    }


    public String getCorrectedText(List<Token> tokens){


        return "";
    }
}
