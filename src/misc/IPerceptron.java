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

    public void setIndexes(int[] ints);

    public void setWeights(ArrayList<Double> weights);
}
