package mlp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Data> data;
    private static NeuronalesNetzwerk mlp;

    public static void main(String[] args) {
        DataReader dr = openFile(MagicNumbers.FILE);
        if (dr == null) {
            return;
        }
        data = dr.readData();
        int[] schichten = {20, 20};
        mlp = new NeuronalesNetzwerk(schichten, MagicNumbers.ANZAHLOUTPUTNEURONEN);
        double trainingsfehler;
        do {
            trainingsfehler = 0;
            for (int i = 0; i < 1000; i++) {
                trainingsfehler = 0;
                for (Data d : data) {
                    mlp.feedforward(d);
                    mlp.backpropagation();
                    trainingsfehler += mlp.berchneFehler();
                }
            }
            System.out.println(trainingsfehler);
        } while (trainingsfehler > MagicNumbers.SCHWELLWERTTRAININGSFEHLER);
    }

    private static DataReader openFile(String file) {
        DataReader dr;
        try {
            dr = new DataReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            return null;
        }
        return  dr;
    }
}
