package mlp;

import java.util.ArrayList;

public class Neuron extends AbstractNeuron {

    Neuron(int id) {
        super(id);
    }

    @Override
    public void setInputs(ArrayList<AbstractNeuron> inputs) {
        if (inputs == null) {
            return;
        }
        if(inputs.size() < 2) {
            throw new  IllegalArgumentException("Zu wenig Input Neurone.");
        }
        this.inputs = inputs;
        gewichte = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            gewichte.add(Math.random() / 10);
        }
    }

    public void aktivierungsFkt() {
        out = 0;
        for (int i = 0; i < inputs.size(); i++) {
            out += inputs.get(i).out * gewichte.get(i);
        }
        out = 1 / (1 + Math.exp(-out));
    }

    @Override
    public void backpropagation() {
        fehler = 0;
        for (AbstractNeuron n : outputs) {
            if(n.gewichte.size() == 1) {
                fehler += n.gewichte.get(0) * n.fehler;
            } else {
                fehler += n.gewichte.get(id) * n.fehler;
            }
        }
        fehler = out * (1 - out) * fehler;
    }

    @Override
    public void gradientenabstieg() {
        for (int i = 0;i < inputs.size(); i++) {
            double d = gewichte.get(i) + -MagicNumbers.LAUFSCHRITTWEITE * fehler * inputs.get(i).out;
            gewichte.set(i, d);
        }
    }
}
