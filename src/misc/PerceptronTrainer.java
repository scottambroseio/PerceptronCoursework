package misc;

import java.util.ArrayList;
import java.util.Random;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author 6523617
 */
public final class PerceptronTrainer {

    private PerceptronTrainer() {
    }

    public static void online(Instances training, IPerceptron perceptron) throws Exception {
        ArrayList<Double> weights = new ArrayList<>();

        for (int count = training.numAttributes() - 1; count > 0; count--) {
            double weight = (new Random().nextDouble() * 20) - 10;
            weights.add(weight);
        }

        int numAttrs = training.numAttributes() - 1;
        int x = 0;
        do {
            training.stream().forEach((train) -> {
                double predictedClass = classifyInstance(train, weights);

                for (int i = 0; i < numAttrs; i++) {
                    double newWeight = perceptron.getBias() * (getClassValue(train) - predictedClass) * train.value(i);

                    weights.set(i, weights.get(i) + newWeight);
                }
            });
        } while (x++ < 10);

        perceptron.setWeights(weights);
    }

    public static void offline(Instances training, IPerceptron perceptron) throws Exception {
        ArrayList<Double> weights = new ArrayList<>();

        for (int count = training.numAttributes() - 1; count > 0; count--) {
            double weight = (new Random().nextDouble() * 20) - 10;
            weights.add(weight);
        }

        int numAttrs = training.numAttributes() - 1;
        int x = 0;

        do {
            ArrayList<Double> deltaWeights = new ArrayList<>();

            for (int i = 0; i < numAttrs; i++) {
                deltaWeights.add(0.0);
            }

            training.stream().map((train) -> {
                double predictedClass = classifyInstance(train, weights);

                for (int i = 0; i < numAttrs; i++) {
                    double newWeight = perceptron.getBias() * (getClassValue(train) - predictedClass) * train.value(i);

                    deltaWeights.set(i, deltaWeights.get(i) + newWeight);
                }
                return train;
            });

            for (int i = 0; i < numAttrs; i++) {
                weights.set(i, weights.get(i) + deltaWeights.get(i));
            }
        } while (x++ < 10);

        perceptron.setWeights(weights);
    }

    public static double classifyInstance(Instance instnc, ArrayList<Double> weights) {
        double result = 0;

        for (int index = instnc.numAttributes() - 2; index >= 0; index--) {
            result += instnc.value(index) * weights.get(index);
        }

        return result >= 0 ? 1 : -1;
    }

    public static double classifyInstance(double[] instnc, ArrayList<Double> weights) {
        double result = 0;

        for (int index = 0; index < instnc.length; index++) {
            result += instnc[index] * weights.get(index);
        }

        return result >= 0 ? 1 : -1;
    }

    private static double getClassValue(Instance instance) {
        return instance.classValue() == 1 ? 1 : -1;
    }
}
