package perceptroncoursework;

import classifiers.EnhancedLinearPerceptron;
import classifiers.LinearPerceptron;
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
        String path = "../Datasets/test.arff";
        Instances training = ArffReader.read(path);

        LinearPerceptron perceptron = new LinearPerceptron();
        EnhancedLinearPerceptron perceptron2 = new EnhancedLinearPerceptron();
        
        perceptron.buildClassifier(training);
        perceptron2.buildClassifier(training);
        
        for(Instance i: training) {
            System.out.println(perceptron.classifyInstance(i));
            System.out.println(i.classValue());
            System.out.println();
        }
        
        System.out.println("====================");
        
        for(Instance i: training) {
            System.out.println(perceptron2.classifyInstance(i));
            System.out.println(i.classValue());
            System.out.println();
        }
    }

}
