package misc;

import java.util.ArrayList;
import weka.core.Instance;

/**
 *
 * @author 6523617
 */
public final class PerceptronClassifier {

    public static double classifyInstance(Instance instance, ArrayList<Double> weights) {
        double result = 0;

        for (int index = instance.numAttributes() - 2; index >= 0; index--) {
            result += instance.value(index) * weights.get(index);
        }

        return result >= 0 ? 1 : -1;
    }

    public static double classifyInstance(double[] instance, ArrayList<Double> weights) {
        double result = 0;

        for (int index = instance.length - 1; index >= 0; index--) {
            result += instance[index] * weights.get(index);
        }

        return result >= 0 ? 1 : -1;
    }
}
