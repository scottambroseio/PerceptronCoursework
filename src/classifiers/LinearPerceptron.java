package classifiers;

import java.util.ArrayList;
import misc.AttributeValidator;
import misc.InvalidAttributesException;
import weka.classifiers.Classifier;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author 6523617
 */
public class LinearPerceptron implements Classifier {
    private int bias;
    private final ArrayList<Double> weights;
    
    public LinearPerceptron() {
        this.weights = new ArrayList<>();
    }

    @Override
    public void buildClassifier(Instances i) throws Exception {
        if (!AttributeValidator.validateAttributes(i)) throw new InvalidAttributesException();

        // initialize default weights
        for(int numAttrs = i.numAttributes() - 1; numAttrs > 0; numAttrs--)
            weights.add(1.0);
        
        while(true) { //some stopping cobndition
            break;
        }
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double classifyInstance(Instance instnc) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double[] distributionForInstance(Instance instnc) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Capabilities getCapabilities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getBias() {
        return bias;
    }

    public void setBias(int bias) {
        this.bias = bias;
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }
    
}
