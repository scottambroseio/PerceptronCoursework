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
public class AttributeStandardizer {

    private final ArrayList<Pair<Double, Double>> meanAndSd;

    public AttributeStandardizer() {
        this.meanAndSd = new ArrayList<>();
    }

    public void standardize(Instances instances) {
        int numAttrs = instances.numAttributes() - 2;

        for (int attrIndex = 0; attrIndex <= numAttrs; attrIndex++) {
            double mean = getMeanForAttribute(attrIndex, instances);
            double sd = getStandardDeviationForAttribute(attrIndex, instances);

            // cache mean and sd
            this.meanAndSd.add(new Pair<>(mean, sd));

            for (Instance i : instances) {
                double score = standardScore(i.value(attrIndex), mean, sd);

                i.setValue(attrIndex, score);
            }
        }
    }

    public void standardize(Instance instance) {
        int numAttrs = instance.numAttributes() - 2;

        for (int attrIndex = 0; attrIndex <= numAttrs; attrIndex++) {
            Pair<Double, Double> element = this.meanAndSd.get(attrIndex);

            double mean = element.getFirst();
            double sd = element.getLast();

            double score = standardScore(instance.value(attrIndex), mean, sd);

            instance.setValue(attrIndex, score);
        }
    }

    private double getMeanForAttribute(int index, Instances instances) {
        double mean = 0;

        mean = instances.stream().map((i) -> i.value(index)).reduce(mean, (accumulator, _item) -> accumulator + _item);

        return mean /= instances.numInstances();
    }

    private double getStandardDeviationForAttribute(int index, Instances instances) {
        double mean = this.getMeanForAttribute(index, instances);
        double sd = 0;

        sd = instances.stream().map((i) -> Math.pow(i.value(index) - mean, 2)).reduce(sd, (accumulator, _item) -> accumulator + _item);

        return sd /= instances.numInstances();
    }

    private double standardScore(double i, double m, double sd) {
        return ((i - m) / sd);
    }
}
