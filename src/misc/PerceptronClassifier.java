package misc;

import java.util.ArrayList;
import weka.core.Instance;

public final class PerceptronClassifier {
    public static double classifyInstance(Instance instance, ArrayList<Double> weights) {
        double result = 0;

        for (int index = instance.numAttributes() - 2; index >= 0; index--) {
            result += instance.value(index) * weights.get(index);
        }

        return result >= 0 ? 1 : -1;
    }
}
