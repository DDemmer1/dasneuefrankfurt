package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextUtil {


    public static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static boolean isAllCaps(String text){

        for (Character c: text.toCharArray()) {

            if(c.charValue()>90){
                return false;
            }

        }


        return true;
    }



    public static boolean firstIsCaps(String text){

        Character c = text.toCharArray()[0];
        if(c.charValue()<97){
            return true;
        }
        return false;
    }


}
