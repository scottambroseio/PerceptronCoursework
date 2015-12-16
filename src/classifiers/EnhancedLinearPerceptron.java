package classifiers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
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
    private ArrayList<Double> weights;
    private boolean useOffline;
    private final boolean standardize;
    private final AttributeStandardizer standardizer;
    private boolean modelSelection;
    private int[] indexes;

    public EnhancedLinearPerceptron() {
        this.weights = new ArrayList<>();
        this.bias = 1;
        this.useOffline = true;
        this.standardize = true;
        this.standardizer = new AttributeStandardizer();
    }

    public EnhancedLinearPerceptron(boolean standardize) {
        this.weights = new ArrayList<>();
        this.bias = 1;
        this.useOffline = true;
        this.standardize = standardize;
        this.standardizer = new AttributeStandardizer();
    }

    public EnhancedLinearPerceptron(boolean standardize, boolean useOffline) {
        this.weights = new ArrayList<>();
        this.bias = 1;
        this.useOffline = useOffline;
        this.standardize = standardize;
        this.standardizer = new AttributeStandardizer();
    }

    public void modelSelection(Instances instances) throws Exception {
        int folds = new Random(System.currentTimeMillis()).nextInt(9) + 2;

        Instances randData = new Instances(instances);

        randData.randomize(new Random(System.currentTimeMillis()));

        double generalError = 0;

        for (int i = 0; i < folds; i++) {
            int onlineError = 0;
            int offlineError = 0;

            Instances train = randData.trainCV(folds, i);
            Instances test = randData.testCV(folds, i);

            PerceptronTrainer.online(train, this);

            onlineError = test.stream()
                    .filter((instnc) -> (PerceptronClassifier.classifyInstance(instnc, weights) != getClassValue(instnc.classValue())))
                    .map((_item) -> 1)
                    .reduce(onlineError, Integer::sum);

            PerceptronTrainer.offline(train, this);

            offlineError = test.stream()
                    .filter((instnc) -> (PerceptronClassifier.classifyInstance(instnc, weights) != getClassValue(instnc.classValue())))
                    .map((_item) -> 1)
                    .reduce(offlineError, Integer::sum);

            generalError += onlineError - offlineError;
        }

        generalError *= (1.0 / (double) folds);

        if (generalError < 0) {
            this.useOffline = false;
        } else if (generalError > 0) {
            this.useOffline = true;
        }
    }

    private static double getClassValue(double value) {
        return value == 1 ? 1 : -1;
    }

    @Override
    public void buildClassifier(Instances instances) throws Exception {
        if (!AttributeValidator.validateAttributes(instances)) {
            throw new InvalidAttributesException();
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

        if (this.indexes == null) {
            return PerceptronClassifier.classifyInstance(instnc, weights);
        }

        return PerceptronClassifier.classifyInstance(instnc, Arrays.copyOf(indexes, indexes.length - 1), weights);
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
