/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Scott
 */
public class AttributeIterator implements Iterator<Pair<double[], Double>>, Supplier<Pair<double[], Double>> {

    private final Instances instances;
    private int currentIndex;
    private final Random random;
    int[] indexes;

    public AttributeIterator(Instances instances) {
        this.instances = instances;
        this.currentIndex = 0;
        this.random = new Random(System.currentTimeMillis());

        generateIndexes();
    }
    
    public int[] getIndexes() {
        return this.indexes;
    }

    private void generateIndexes() {
        int totalAttrs = this.instances.numAttributes();

        do {
            this.indexes = random.ints((int) Math.round((int) Math.sqrt((double) totalAttrs)), 0, totalAttrs - 1).toArray();
        } while (indexValuesAreNotUnqiue());
    }

    private boolean indexValuesAreNotUnqiue() {
        int length = this.indexes.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (indexes[i] == indexes[j]) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean hasNext() {
        return this.currentIndex < this.instances.size() - 1;
    }

    @Override
    public Pair<double[], Double> next() {
        Instance instance = this.instances.get(currentIndex);

        currentIndex++;

        double classValue = getClassValue(instance);

        double[] attrValues = Arrays.stream(this.indexes)
                .mapToDouble((index) -> instance.value(index))
                .toArray();

        return new Pair<>(attrValues, classValue);
    }

    public int size() {
        return this.instances.size();
    }

    public int numAttributes() {
        return this.indexes.length;
    }
    
    public int numInstances() {
        return this.instances.size();
    }

    private static double getClassValue(Instance instance) {
        return instance.classValue() == 1 ? 1 : -1;
    }

    @Override
    public Pair<double[], Double> get() {
        if (this.hasNext()) return this.next();
        
        return null;
    }
    
    public void reset() {
        this.currentIndex = 0;
    }

}
