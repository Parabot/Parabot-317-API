package org.rev317.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This reader reads the information about the server provider from a file
 *
 * @author Paradox, Everel
 */
public class Reader {
    public static String readProvider(String s) {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(System.getProperty("user.home") + "/serverProvider.pb"));
            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.startsWith(s)) {
                	br.close();
                    return sCurrentLine.replace(s + ": ", "");
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
