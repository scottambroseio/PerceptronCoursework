package classifiers;

import java.util.ArrayList;
import misc.AttributeValidator;
import misc.IPerceptron;
import misc.InvalidAttributesException;
import misc.PerceptronTrainer;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author 6523617
 */
public class LinearPerceptron implements IPerceptron {

    private double bias;
    private double learningRate;
    private ArrayList<Double> weights;

    public LinearPerceptron() {
        this.weights = new ArrayList<>();
        this.bias = 1;
        this.learningRate = 1;
    }

    @Override
    public void buildClassifier(Instances instances) throws Exception {
        if (!AttributeValidator.validateAttributes(instances)) {
            throw new InvalidAttributesException();
        }

        // initialize default weights
        for (int count = instances.numAttributes() - 1; count > 0; count--) {
            weights.add(1.0);
        }
        
        PerceptronTrainer.offline(instances, this);
    }

    @Override
    public double classifyInstance(Instance instnc) throws Exception {
        double result = 0;

        for (int index = instnc.numAttributes() - 2; index >= 0; index--) {
            result += instnc.value(index) * weights.get(index);
        }
        
        return result >= 0 ? 1 : -1;
    }

    @Override
    public double[] distributionForInstance(Instance instnc) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Capabilities getCapabilities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Double> getWeights() {
        return weights;
    }

    @Override
    public double getBias() {
        return bias;
    }

    @Override
    public void setBias(double bias) {
        this.bias = bias;
    }

    @Override
    public double getLearningRate() {
        return learningRate;
    }

    @Override
    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    @Override
    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }
}
