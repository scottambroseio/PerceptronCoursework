package classifiers;

import java.util.ArrayList;
import java.util.Arrays;
import misc.AttributeValidator;
import misc.IPerceptron;
import misc.InvalidAttributesException;
import misc.PerceptronClassifier;
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
    private ArrayList<Double> weights;
    private int[] indexes;

    public LinearPerceptron() {
        this.weights = new ArrayList<>();
        this.bias = 1;
    }

    @Override
    public void buildClassifier(Instances instances) throws Exception {
        if (!AttributeValidator.validateAttributes(instances)) {
            throw new InvalidAttributesException();
        }

        PerceptronTrainer.online(instances, this);
    }

    @Override
    public double classifyInstance(Instance instnc) throws Exception {
        if (this.indexes == null) {
            return PerceptronClassifier.classifyInstance(instnc, weights);
        }
        
        return PerceptronClassifier.classifyInstance(instnc, Arrays.copyOf(indexes, indexes.length - 1), weights);
    }

    @Override
    public double[] distributionForInstance(Instance instnc) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Capabilities getCapabilities() {
        throw new UnsupportedOperationException("Not supported yet.");
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
    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }

    @Override
    public void setIndexes(int[] ints) {
        Arrays.sort(ints);

        this.indexes = ints;
    }
}
