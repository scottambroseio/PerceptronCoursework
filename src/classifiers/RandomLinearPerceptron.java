package classifiers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import misc.AttributeIterator;
import misc.AttributeStandardizer;
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
        for (int index = 0; index < DEFAULT_SIZE; index++) {
            AttributeIterator iterator = new AttributeIterator(i);

            IPerceptron perceptron = new Random(System.currentTimeMillis()).nextInt(2) == 0 ? new LinearPerceptron() : new EnhancedLinearPerceptron();

            perceptron.buildClassifier(iterator);

            this.essemble.add(index, perceptron);
        }
    }

    @Override
    public double classifyInstance(Instance instnc) throws Exception {
        double[] dist = this.distributionForInstance(instnc);

        ArrayList<Double> list = new ArrayList<>();

        for (double num : dist) {
            list.add(num);
        }

        int x = Collections.frequency(list, -1.0);
        int y = Collections.frequency(list, 1.0);

        return x > y ? -1 : x < y ? 1 : (double) (new Random(System.currentTimeMillis()).nextInt(2) + 1);
    }

    @Override
    public double[] distributionForInstance(Instance instnc) throws Exception {
        return this.essemble.stream().mapToDouble((perceptron) -> {
            try {
                return perceptron.classifyInstance(instnc);
            } catch (Exception ex) {
                Logger.getLogger(RandomLinearPerceptron.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }).toArray();
    }

    @Override
    public Capabilities getCapabilities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
