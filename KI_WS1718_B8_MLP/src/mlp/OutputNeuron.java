package mlp;

import java.util.ArrayList;

public class OutputNeuron extends  AbstractNeuron {

    private Klasse klasse;

    OutputNeuron(int id) {
        super(id);
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
        out = inputs.get(0).out;
    }

    @Override
    public void setInputs(ArrayList<AbstractNeuron> inputs) {
        if (inputs == null) {
            return;
        }
        if(inputs.size() != 1) {
            throw new IllegalArgumentException("Ein Output Neuron darf nur ein Input Neuron haben");
        }
        this.inputs = inputs;
        gewichte = new ArrayList<>();
        gewichte.add(1.0);
    }

    @Override
    public  void setOutputs(ArrayList<AbstractNeuron> outputs) {}

    @Override
    public void backpropagation() {
        fehler = out - klasse.ordinal();
    }

    @Override
    public void gradientenabstieg() {}
}
