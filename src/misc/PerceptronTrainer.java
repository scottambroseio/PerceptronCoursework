/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.ArrayList;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author 6523617
 */
public final class PerceptronTrainer {

    private PerceptronTrainer() {
    }

    // take in a type of perceptron?
    public static void online(Instances training, IPerceptron perceptron) throws Exception {
        ArrayList<Double> weights = perceptron.getWeights();

        int numAttrs = training.numAttributes() - 1;

        do {
            for (Instance train : training) {
                double y = perceptron.classifyInstance(train);

                // don't update weights if coprrectly classified as redundant
                for (int i = 0; i <= numAttrs - 1; i++) {
                    double newWeight = perceptron.getBias() * perceptron.getLearningRate() * (getClassValue(train) - y) * train.value(i);

                    weights.set(i, weights.get(i) + newWeight);
                    perceptron.setWeights(weights);
                }
            }

            break;
            // do stopping condition
        } while (true);
    }

    public static void offline(Instances training, IPerceptron perceptron) throws Exception {

    }

    private static double getClassValue(Instance instance) {
        return instance.classValue() == 1 ? 1 : -1;
    }

}
