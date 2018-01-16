package mlp;

import java.util.ArrayList;

class NeuronalesNetzwerk {

    private int[] schichten;
    private int anzahlOutputNeuronen;
    private ArrayList<ArrayList<AbstractNeuron>> mlp;

    NeuronalesNetzwerk(int[] schichten, int anzahlOutputNeuronen) {
        this.schichten = schichten;
        this.anzahlOutputNeuronen = anzahlOutputNeuronen;
        mlp = new ArrayList<>();
        ArrayList<AbstractNeuron> inputschicht = new ArrayList<>();
        int i, j;
        for (i = 0; i < MagicNumbers.ANZAHLEINGABEN; i++) {
            inputschicht.add(new InputNeuron(i));
        }
        mlp.add(inputschicht);
        for (i = 0; i < schichten.length; i++) {
            ArrayList<AbstractNeuron> schicht = new ArrayList<>();
            for (j = 0; j <= schichten[i]; j++) {
                if(j == 0) {
                    schicht.add(new InputNeuron(j));
                } else {
                    schicht.add(new Neuron(j));
                }
            }
            mlp.add(schicht);
        }
        ArrayList<AbstractNeuron> outputschicht = new ArrayList<>();
        ArrayList<AbstractNeuron> outputs = new ArrayList<>();
        for (i = 0; i <= anzahlOutputNeuronen; i++) {
            if(i == 0) {
                outputschicht.add(new InputNeuron(i));
                outputs.add(new InputNeuron(i));
            } else {
                outputschicht.add(new Neuron(i));
                outputs.add(new OutputNeuron(i));
            }
        }
        mlp.add(outputschicht);
        mlp.add(outputs);
        linkSchichten();
    }

    private void linkSchichten() {
        ArrayList<AbstractNeuron> outputs = mlp.get(mlp.size() - 1);
        ArrayList<AbstractNeuron> outputschicht = mlp.get(mlp.size() - 2);
        for (int i = 1; i < outputs.size(); i++) {
            AbstractNeuron o1 = outputs.get(i);
            AbstractNeuron o2 = outputschicht.get(i);
            ArrayList<AbstractNeuron> in = new ArrayList<>();
            in.add(o2);
            ArrayList<AbstractNeuron> out = new ArrayList<>();
            out.add(o1);
            o1.setInputs(in);
            o2.setOutputs(out);
        }
        for (int j = mlp.size() - 2; j > 0; j--) {
            ArrayList<AbstractNeuron> out = new ArrayList<>();
            ArrayList<AbstractNeuron> output = mlp.get(j);
            for (int i = 1; i < output.size(); i++) {
                out.add(output.get(i));
            }
            ArrayList<AbstractNeuron> in = mlp.get(j - 1);
            for (AbstractNeuron o : out) {
                o.setInputs(in);
            }
            for (AbstractNeuron i : in) {
                i.setOutputs(out);
            }
        }
    }

    private void print() {
        int i = 0;
        for (ArrayList<AbstractNeuron> s : mlp) {
            i++;
            System.out.println("Schicht " + i);
            for (AbstractNeuron n : s) {
                n.print();
            }
            System.out.println();
        }
    }

    public void feedforward(Data data) {
        ArrayList<AbstractNeuron> in = mlp.get(0);
        double[] d = data.getData();
        for (int j = 1; j < in.size(); j++) {
            AbstractNeuron i = in.get(j);
            ((InputNeuron) i).setIn(d[j - 1]);
        }
        for (int i = 1; i < mlp.size() - 1; i++) {
            ArrayList<AbstractNeuron> vn = mlp.get(i);
            for (int j = 1; j < vn.size(); j++) {
                AbstractNeuron n = vn.get(j);
                ((Neuron) n).aktivierungsFkt();
            }
        }
        ArrayList<AbstractNeuron> outputs = mlp.get(mlp.size() - 1);
        for (int j = 1; j < outputs.size(); j++) {
            AbstractNeuron o = outputs.get(j);
            ((OutputNeuron) o).setKlasse(data.k);
        }
    }

    public void backpropagation() {
        for (int i = mlp.size() - 1; i > 0; i--) {
            ArrayList<AbstractNeuron> schicht = mlp.get(i);
            for (AbstractNeuron n : schicht) {
                n.backpropagation();
            }
        }
        for (int i = mlp.size() - 1; i > 0; i--) {
            ArrayList<AbstractNeuron> schicht = mlp.get(i);
            for (AbstractNeuron n : schicht) {
                n.gradientenabstieg();
            }
        }
    }

    public double berchneFehler() {
        double fehler = 0;
        ArrayList<AbstractNeuron> out = mlp.get(mlp.size() - 1);
        for (AbstractNeuron o: out) {
            fehler += (o.fehler * o.fehler) / 2;
        }
        return fehler;
    }
}
