package de.uni.koeln.demmer.dennis;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;


import de.uni.koeln.demmer.dennis.model.Util.TextProcessor;
import de.uni.koeln.demmer.dennis.model.Util.Token;
import de.uni.koeln.demmer.dennis.model.Util.Tokenizer;
import de.uni.koeln.demmer.dennis.model.lucene.LuceneUtil;
import java.util.List;

//@SpringBootApplication
public class Application {


    public static void main(String[] args) {


        String dasneuefrankfurt = ">\n" +
                "o\n" +
                "\n" +
                "DAS NEUE\n" +
                "\n" +
                "FRANKFURT\n" +
                "\n" +
                "o\n" +
                "\n" +
                "■ MONATSSCHRIFT FÜR DIE FRAGEN DER GROSSTADTGESTALTUNG\n" +
                "\n" +
                "Ö\n" +
                "et\n" +
                "x\n" +
                "<\n" +
                "\n" +
                "SCHRIFTLEITER: ERNST MAY| VERLAG ENGLERT UND SCHLOSSER . FRANKFURT AM MAIN\n" +
                "\n" +
                "ZUM GELEIT Von Oberbürgermeifter Dr. Landmann\n" +
                "\n" +
                "Neue Geiftes- und Seelenkultur: darum rang das deutfche Volk fchon vor\n" +
                "dem Kriege in qualvollem Streben. Einem war gegeben zu lagen, was er an\n" +
                "der Zeit und die Zeit an ihrer Zivilifation litt: einem Philofophen und Dichter:\n" +
                "Nietzfche! Sein Leben und Werk ift der tieffte Auffchrei der von einer ungei-\n" +
                "ftigen Zivilifation gemarterten Seele. Dann kam der Krieg. Aus den Trümmern\n" +
                "einer zerfchlagenen Herrlichkeit erhob sich ein neues Wollen und Streben,\n" +
                "ein Verfuch der Befinnung, des Sichzurechtfindens in der verwandelten Welt,\n" +
                "eine Rückkehr zu einem Geiftftreben, das anknüpft an das Innerlich-Vertiefte,\n" +
                "Äußerlich-Einfache und Edle der deutfchen Geiftes- und Gefühlswelt. Der Krieg\n" +
                "erwies sich wiederum einmal nicht als reiner Zerftörer, fondern als gewaltiger\n" +
                "Geftalter des Neuen.\n" +
                "\n" +
                "Diefem neuen Geftaltungswillen fich offen aufnehmend und freudig mit-\n" +
                "fchaffend zu erfchliefjen, an der Auflöfung alter, innerlich abgeworbener und\n" +
                "in einem Scheindafein dahinvegetierender Formen und Geftaltungen und dem\n" +
                "Aufbau neuer äfthetifcher, feelifcher Bindungen aus einem neuen Zeitgefühl,\n" +
                "Zeitwillen und Zeitumftänden heraus aufbauend mitzuarbeiten / muß Inhalt\n" +
                "und Aufgabe aller der Gegenwart und Zukunft zugewandten pofitiven Geifter\n" +
                "fein, überflüffig zu fagen, dafj das nicht Zerfchlagen des überkommenen Kul-\n" +
                "turgutes bedeutet, das auf die Gegenwart wirkend für fie feinen Wert behält,\n" +
                "Bild i. reisewagen im 17 jahrh Tempo insbefondere nicht Verachtung oder Zertrümmerung der hiftorifch als Ausdruck\n" +
                "\n" +
                "und Form fpiegeini den stand der Tech* des Geftaltungswillens der Vergangenheit bedeutfamen qeiftiq-feelifchen\n" +
                "nik jener Zeit. Aus: Eugen Diedenchs, Deut* -' ■ .. . -, .\n" +
                "\n" +
                "fehes Leben der Vergangenheit in Bildern. Werte oder Gebrauchsgüter. Aber ein neues Gefchlecht, eioejieue Zeit muß\n" +
                "\n" +
                "DAS PERSONENsFAHRZEUG\n" +
                "ALS AUSDRUCK DER KULTUR\n" +
                "\n" +
                "i";





Token testToken = new Token("fppfppfppfppfppf");


        TextProcessor textProcessor = new TextProcessor();

        textProcessor.checkCharacter(testToken,'f');

        System.out.println(testToken.toString());

//        Tokenizer tokenizer = new Tokenizer();
//
//        List<Token> tokenList = tokenizer.tokenize(dasneuefrankfurt);
//
//        LuceneUtil util = new LuceneUtil();
//
//
//        for (Token token : tokenList) {
//
//            util.processToken(token);
//
//            System.out.println(token.toString());
//
//        }
    }


}




