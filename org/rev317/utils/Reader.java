package org.rev317.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * This reader reads the information about the server provider from a file
 *
 * @author Paradox
 *
 */
public class Reader {
    public static String readProvider(String s) {
        BufferedReader br = null;
        String Jar = null;
        boolean contin = true;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(System.getProperty("user.home") + "/serverProvider.txt"));
            while ((sCurrentLine = br.readLine()) != null && contin) {
                if (sCurrentLine.startsWith(s)) {
                    Jar = sCurrentLine.replace(s + ": ", "");
                    contin = false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (contin == false) {
            return Jar;
        } else {
            return "";
        }
    }
}
