package classifiers;

import java.util.ArrayList;
import misc.IPerceptron;
import weka.classifiers.Classifier;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author 6523617
 */
public class RandomLinearPerceptron implements Classifier {
    private final ArrayList<IPerceptron> essemble;
    private final int DEFAULT_SIZE = 500;
    
    public RandomLinearPerceptron() {
        this.essemble = new ArrayList<>(DEFAULT_SIZE);
    }
    
    public RandomLinearPerceptron(int size) {
        this.essemble = new ArrayList<>(size);
    }    

    @Override
    public void buildClassifier(Instances i) throws Exception {
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
    
}
