/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.io.FileReader;
import weka.core.Instances;

/**
 *
 * @author Scott
 */
public final class ArffReader {
    private ArffReader(){}
    
    public static Instances read(String path) throws Exception {
        FileReader r = new FileReader(path);
        Instances instances = new Instances(r);

        instances.setClassIndex(instances.numAttributes() - 1);

        return instances;
    }
}
