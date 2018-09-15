package DnfWrapper;

import Cheiron.Cheiron;
import Cheiron.Datasource.Datasource;
import Cheiron.Datatarget.Datatarget;
import Cheiron.Pipeline.Pipeline;

import java.util.ArrayList;
import java.util.Properties;

public class DNFUtil {


    public static void clearCheiron(){

        Cheiron.properties = new Properties();
        Cheiron.configfile = new String();
        Cheiron.writelog = new String();

        Cheiron.Datasource = new ArrayList<Datasource>();
        Cheiron.Pipeline = new ArrayList<Pipeline>();
        Cheiron.Datatarget = new ArrayList<Datatarget>();
    }
}
