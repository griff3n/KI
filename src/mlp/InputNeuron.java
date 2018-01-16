package mlp;

import java.util.ArrayList;

public class InputNeuron extends AbstractNeuron {

    InputNeuron(int id) {
        super(id);
        out = 1;
    }

    public void setIn(double in) {
        out = in;
    }

    @Override
    public void setInputs(ArrayList<AbstractNeuron> inputs) {}

    @Override
    public void backpropagation() {}

    @Override
    public void gradientenabstieg() {}
}
