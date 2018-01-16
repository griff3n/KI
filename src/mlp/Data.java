package mlp;

public class Data {
    double x1 = 0;
    double x2 = 0;
    double x3 = 0;
    double x4 = 0;
    Klasse k = null;

    public Data(double x1, double x2, double x3, double x4, Klasse k) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.k = k;
    }

    public double[] getData() {
        double[] data = {x1, x2, x3, x4};
        return data;
    }
}
