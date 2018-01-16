package mlp;

public enum Klasse {
    SETOSA("setosa"), VERSICOLOR("versicolor"), VIRGINICA("virginica");

    private String klasse;

    Klasse(String klasse) {
        this.klasse = klasse;
    }

    @Override
    public String toString() {
        return klasse;
    }

    public static Klasse parseKlasse(String klasse) {
        if(klasse.equals("setosa")) {
            return  SETOSA;
        }
        if (klasse.equals("versicolor")) {
            return  VERSICOLOR;
        }
        if (klasse.equals("virginica")) {
            return  VIRGINICA;
        }
        return null;
    }
}
