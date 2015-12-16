/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptroncoursework;

import classifiers.EnhancedLinearPerceptron;
import classifiers.LinearPerceptron;
import classifiers.RandomLinearPerceptron;
import java.util.ArrayList;
import misc.ArffReader;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Scott
 */
public class Evaluation {

    private static final String[] trainingPaths = {
        "../Datasets/adult/adult_train.arff",
        "../Datasets/monks-1/monks-1_train.arff",
        "../Datasets/monks-2/monks-2_train.arff",
        "../Datasets/monks-3/monks-3_train.arff"
    };

    private static final String[] testingPaths = {
        "../Datasets/adult/adult_test.arff",
        "../Datasets/monks-1/monks-1_test.arff",
        "../Datasets/monks-2/monks-2_test.arff",
        "../Datasets/monks-3/monks-3_test.arff"
    };

    public static void CompareLearningAlgorithms() throws Exception {
        for (int index = 0; index < trainingPaths.length; index++) {
            ArrayList<Double> onlineCorrect = new ArrayList<>();
            ArrayList<Double> offlineCorrect = new ArrayList<>();

            for (int x = 0; x < 25; x++) {
                Instances training = ArffReader.read(trainingPaths[index]);
                Instances testing = ArffReader.read(testingPaths[index]);

                EnhancedLinearPerceptron onlinePerceptron = new EnhancedLinearPerceptron(true, false);
                EnhancedLinearPerceptron offlinePerceptron = new EnhancedLinearPerceptron(true, true);

                onlinePerceptron.buildClassifier(training);
                offlinePerceptron.buildClassifier(training);

                double numCorrect = 0;
                double total = testing.size();

                for (Instance i : testing) {
                    if (onlinePerceptron.classifyInstance(i) == getClassValue(i)) {
                        numCorrect++;
                    }
                }

                onlineCorrect.add(numCorrect / total * 100);

                numCorrect = 0;

                for (Instance i : testing) {
                    if (offlinePerceptron.classifyInstance(i) == getClassValue(i)) {
                        numCorrect++;
                    }
                }

                offlineCorrect.add(numCorrect / total * 100);
            }

            System.out.printf("Online: %.2f%% correct\n", onlineCorrect.stream().mapToDouble(x -> x).average().getAsDouble());
            System.out.printf("Offline: %.2f%% correct\n", offlineCorrect.stream().mapToDouble(x -> x).average().getAsDouble());
        }
    }

    public static void TestEssemble() throws Exception {
        for (int index = 0; index < trainingPaths.length; index++) {
            ArrayList<Double> linearCorrect = new ArrayList<>();
            ArrayList<Double> randomCorrect = new ArrayList<>();

            for (int x = 0; x < 25; x++) {
                Instances training = ArffReader.read(trainingPaths[index]);
                Instances testing = ArffReader.read(testingPaths[index]);

                LinearPerceptron linearPerceptron = new LinearPerceptron();
                RandomLinearPerceptron randomLinearPerceptron = new RandomLinearPerceptron();

                linearPerceptron.buildClassifier(training);
                randomLinearPerceptron.buildClassifier(training);

                double numCorrect = 0;
                double total = testing.size();

                for (Instance i : testing) {
                    if (linearPerceptron.classifyInstance(i) == getClassValue(i)) {
                        numCorrect++;
                    }
                }

                linearCorrect.add(numCorrect / total * 100);

                numCorrect = 0;

                for (Instance i : testing) {
                    if (randomLinearPerceptron.classifyInstance(i) == getClassValue(i)) {
                        numCorrect++;
                    }
                }

                randomCorrect.add(numCorrect / total * 100);
            }

            System.out.printf("Linear: %.2f%% correct\n", linearCorrect.stream().mapToDouble(x -> x).average().getAsDouble());
            System.out.printf("RandomLinearPerceptron: %.2f%% correct\n", randomCorrect.stream().mapToDouble(x -> x).average().getAsDouble());
        }
    }

    private static double getClassValue(Instance instance) {
        return instance.classValue() == 1 ? 1 : -1;
    }

}
