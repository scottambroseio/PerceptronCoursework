package perceptroncoursework;

import classifiers.EnhancedLinearPerceptron;
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
        
        LinearPerceptron linear = new LinearPerceptron();
        EnhancedLinearPerceptron enhanced = new EnhancedLinearPerceptron();
        RandomLinearPerceptron random = new RandomLinearPerceptron();
        
        enhanced.modelSelection(training);
        
        linear.buildClassifier(training);
        enhanced.buildClassifier(training);
        random.buildClassifier(training);
        
        int numCorrect = 0;
        int total = testing.size();
        
        for(Instance i: testing) {
            if (linear.classifyInstance(i) == getClassValue(i)) numCorrect++;
        }
        
        System.out.printf("%d/%d correct\n", numCorrect, total);
        
        numCorrect = 0;
        
        for(Instance i: testing) {
            if (enhanced.classifyInstance(i) == getClassValue(i)) numCorrect++;
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
