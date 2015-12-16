package misc;

import java.io.FileReader;
import weka.core.Instances;

/**
 *
 * @author 6523617
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
