package perceptroncoursework;

import classifiers.LinearPerceptron;
import classifiers.RandomLinearPerceptron;
import misc.ArffReader;
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
        String trainingPath = "../Datasets/adult/adult_train.arff";
        String testingPath = "../Datasets/adult/adult_test.arff";
        Instances training = ArffReader.read(trainingPath);
        Instances testing = ArffReader.read(testingPath);
        
        LinearPerceptron perceptron = new LinearPerceptron();
        RandomLinearPerceptron random = new RandomLinearPerceptron();
        
        perceptron.buildClassifier(training);
        random.buildClassifier(training);
        
        int numCorrect = 0;
        int total = testing.size();
        
        for(Instance i: testing) {
            if (perceptron.classifyInstance(i) == getClassValue(i)) numCorrect++;
        }
        
        System.out.printf("%d/%d correct\n", numCorrect, total);
        
        numCorrect = 0;
        
        for(Instance i: testing) {
            if (random.classifyInstance(i) == getClassValue(i)) numCorrect++;
        }
        
        System.out.printf("%d/%d correct\n", numCorrect, total);
        
    }
    
    private static double getClassValue(Instance instance) {
        return instance.classValue() == 1 ? 1 : -1;
    }

}
