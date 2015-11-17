package perceptroncoursework;

import classifiers.LinearPerceptron;
import misc.ArffReader;
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
        
        perceptron.buildClassifier(training);
        
        System.out.println(perceptron.getWeights());
    }

}
