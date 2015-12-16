package classifiers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import misc.IPerceptron;
import weka.classifiers.Classifier;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 *
 * @author 6523617
 */
public class RandomLinearPerceptron implements Classifier {

    private final ArrayList<IPerceptron> essemble;
    private final int DEFAULT_SIZE = 500;
    private final int size;

    public RandomLinearPerceptron() {
        this.essemble = new ArrayList<>(DEFAULT_SIZE);
        this.size = DEFAULT_SIZE;
    }

    public RandomLinearPerceptron(int size) {
        this.essemble = new ArrayList<>(size);
        this.size = size;
    }

    @Override
    public void buildClassifier(Instances i) throws Exception {
        boolean useLinear = new Random(System.currentTimeMillis()).nextInt(2) == 0;

        for (int index = 0; index < this.size; index++) {
            LinearPerceptron perceptron = new LinearPerceptron();

            int numToSelect = (int) Math.round(Math.sqrt((double) i.numAttributes()));
            int[] ints = new Random().ints(0, i.numAttributes() - 2).distinct().limit(numToSelect).toArray();

            ints = Arrays.copyOf(ints, ints.length + 1);
            ints[ints.length - 1] = i.numAttributes() - 1;

            Remove remove = new Remove();
            remove.setAttributeIndicesArray(ints);
            remove.setInvertSelection(true);
            remove.setInputFormat(i);

            Instances cloned = Filter.useFilter(i, remove);

            cloned.setClassIndex(cloned.numAttributes() - 1);

            perceptron.buildClassifier(cloned);

            perceptron.setIndexes(ints);

            this.essemble.add(index, perceptron);
        }
    }

    @Override
    public double classifyInstance(Instance instnc) throws Exception {
        double[] dist = this.distributionForInstance(instnc);

        return dist[0] > dist[1] ? -1 : dist[1] < dist[0] ? 1 : (double) (new Random(System.currentTimeMillis()).nextInt(2) + 1) == 0 ? -1 : 1;
    }

    @Override
    public double[] distributionForInstance(Instance instnc) throws Exception {
        double[] counted = {0, 0};

        for (IPerceptron perceptron : this.essemble) {
            double cls = perceptron.classifyInstance(instnc);

            if (cls == -1) {
                counted[0]++;
            } else {
                counted[1]++;
            }
        }

        return counted;
    }

    @Override
    public Capabilities getCapabilities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
