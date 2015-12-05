package classifiers;

import java.util.ArrayList;
import misc.AttributeStandardizer;
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
public class EnhancedLinearPerceptron implements IPerceptron {

    private double bias;
    private double learningRate;
    private final ArrayList<Double> weights;
    private final boolean useOffline;
    private final boolean standardize;
    private final AttributeStandardizer standardizer;

    public EnhancedLinearPerceptron() {
        this.weights = new ArrayList<>();
        this.bias = 1;
        this.learningRate = 1;
        this.useOffline = true;
        this.standardize = true;
        this.standardizer = new AttributeStandardizer();
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

        if (this.standardize) {
            standardizer.standardize(instances);
        }

        if (this.useOffline) {
            PerceptronTrainer.offline(instances, this);
        } else {
            PerceptronTrainer.online(instances, this);
        }
    }

    @Override
    public double classifyInstance(Instance instnc) throws Exception {
        if (this.standardize) {
            this.standardizer.standardize(instnc);
        }

        return PerceptronClassifier.classifyInstance(instnc, weights);
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
    public void setIndexes(int[] indexes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
