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
 * @author Scott
 */
public interface IPerceptron extends Classifier {

    public double getBias();

    public void setBias(double bias);

    public ArrayList<Double> getWeights();

    public void setWeights(ArrayList<Double> weights);

    public double getLearningRate();

    public void setLearningRate(double learningRate);
}
