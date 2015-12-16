/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
