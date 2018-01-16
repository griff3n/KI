package mlp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader extends BufferedReader {

    DataReader(FileReader file) {
        super(file);
    }

    public ArrayList<Data> readData() {
        ArrayList<Data> data = new ArrayList<>();
        String zeile;
        try {
            while ((zeile = readLine()) != null) {
                String[] s = zeile.split(",",5);
                double x1 = Double.parseDouble(s[0]);
                double x2 = Double.parseDouble(s[1]);
                double x3 = Double.parseDouble(s[2]);
                double x4 = Double.parseDouble(s[3]);
                Klasse k = Klasse.parseKlasse(s[4]);
                data.add(new Data(x1, x2, x3, x4, k));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
