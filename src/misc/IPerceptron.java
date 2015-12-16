package misc;

import java.util.ArrayList;
import weka.classifiers.Classifier;

/**
 *
 * @author 6523617
 */
public interface IPerceptron extends Classifier {

    public double getBias();

    public void setBias(double bias);

    public ArrayList<Double> getWeights();

    public double getLearningRate();

    public void setLearningRate(double learningRate);

    public void setIndexes(int[] indexes);

    public void buildClassifier(AttributeIterator instances) throws Exception;
}
