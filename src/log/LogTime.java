/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arthur
 */
public class LogTime {

    public static void writeStringCsvSetPiece(String time, String profundidade, String lugaresLivres) {
        BufferedWriter br;
        try {
            br = new BufferedWriter(new FileWriter("C:/Users/Arthur/Desktop/eadlogTempoPecaCsv.csv", true));
            br.append(time+";"+profundidade+";"+";"+lugaresLivres);
            br.newLine();
            br.flush();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(LogTime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
