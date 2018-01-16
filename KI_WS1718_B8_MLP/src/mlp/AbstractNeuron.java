package mlp;

import java.util.ArrayList;

public abstract class AbstractNeuron {

    protected int id;
    protected double out;
    protected double fehler;
    ArrayList<AbstractNeuron> inputs;
    ArrayList<AbstractNeuron> outputs;
    protected ArrayList<Double> gewichte = null;

    AbstractNeuron(int id) {
        this.id = id;
        out = 0;
        fehler = 0;
    }

    public abstract void setInputs(ArrayList<AbstractNeuron> inputs);

    public  void setOutputs(ArrayList<AbstractNeuron> outputs) {
        if(outputs == null) {
            return;
        }
        if(outputs.size() < 1 ) {
            throw new  IllegalArgumentException("Zu wenig Output Neurone.");
        }
        this.outputs = outputs;
    }

    public abstract void backpropagation();

    public abstract void gradientenabstieg();

    public void print() {
        System.out.println(id);
        System.out.println("Inputs:");
        if (inputs == null) {
            System.out.println("null");
        } else {
            for (AbstractNeuron i : inputs) {
                System.out.print(i);
                System.out.print(" - ");
            }
        }
        System.out.println();
        System.out.println("Outputs:");
        if (outputs == null) {
            System.out.println("null");
        } else {
            for (AbstractNeuron o : outputs) {
                System.out.print(o);
                System.out.print(" - ");
            }
        }
        System.out.println();
        System.out.println();
    }
}
