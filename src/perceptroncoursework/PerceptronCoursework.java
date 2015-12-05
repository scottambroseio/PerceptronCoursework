package perceptroncoursework;

import classifiers.LinearPerceptron;
import misc.ArffReader;
import misc.AttributeIterator;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author 6523617
 */
public class PerceptronCoursework {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        String path = "../Datasets/cancer/cancer.arff";
        Instances training = ArffReader.read(path);
        
        AttributeIterator a1 = new AttributeIterator(training);
        AttributeIterator a2 = new AttributeIterator(training);

        LinearPerceptron perceptron = new LinearPerceptron();
        LinearPerceptron perceptron2 = new LinearPerceptron();
        LinearPerceptron perceptron3 = new LinearPerceptron();
        
        perceptron.buildClassifier(a1);
        perceptron2.buildClassifier(a2);
        perceptron3.buildClassifier(training);
        
        int numCorrect = 0;
        int total = training.size();
        
        for(Instance i: training) {
            if (perceptron.classifyInstance(i) == getClassValue(i)) numCorrect++;
        }
        
        System.out.printf("%d/%d correct\n", numCorrect, total);
        
        numCorrect = 0;
        
        for(Instance i: training) {
            if (perceptron2.classifyInstance(i) == getClassValue(i)) numCorrect++;
        }
        
        System.out.printf("%d/%d correct\n", numCorrect, total);
        
        numCorrect = 0;
        
        for(Instance i: training) {
            if (perceptron3.classifyInstance(i) == getClassValue(i)) numCorrect++;
        }
        
        System.out.printf("%d/%d correct\n", numCorrect, total);
        
    }
    
    private static double getClassValue(Instance instance) {
        return instance.classValue() == 1 ? 1 : -1;
    }

}
