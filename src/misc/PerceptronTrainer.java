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

    public static void online(Instances training, IPerceptron perceptron) throws Exception {
        ArrayList<Double> weights = perceptron.getWeights();

        int numAttrs = training.numAttributes() - 1;

        do {
            for (Instance train : training) {
                double y = classifyInstance(train, weights);

                // don't update weights if coprrectly classified as redundant
                for (int i = 0; i < numAttrs; i++) {
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
        ArrayList<Double> weights = perceptron.getWeights();

        int numAttrs = training.numAttributes() - 1;
        
        do {
            ArrayList<Double> deltaWeights = new ArrayList<>();
            
            for(int i = 0; i < numAttrs; i++) deltaWeights.add(0.0);
            
            for(Instance train: training) {
                double y = classifyInstance(train, weights);
                
                for(int i = 0; i < numAttrs; i++) {
                    double newWeight = perceptron.getBias() * perceptron.getLearningRate() * (getClassValue(train) - y) * train.value(i);
                    
                    deltaWeights.set(i, deltaWeights.get(i) + newWeight);
                }
                
                for(int i = 0; i < numAttrs; i++) {                    
                    weights.set(i, weights.get(i) + deltaWeights.get(i));
                }
            }
            
            break;
            // do stopping condition
        } while(true);
    }
    
    public static double classifyInstance(Instance instnc, ArrayList<Double> weights) {
        double result = 0;

        for (int index = instnc.numAttributes() - 2; index >= 0; index--) {
            result += instnc.value(index) * weights.get(index);
        }
        
        return result >= 0 ? 1 : -1;
    }

    private static double getClassValue(Instance instance) {
        return instance.classValue() == 1 ? 1 : -1;
    }

}
