package misc;

import java.util.Enumeration;
import weka.core.Attribute;
import weka.core.Instances;

/**
 *
 * @author 6523617
 */
public final class AttributeValidator {
    private AttributeValidator(){}
    
    public static boolean validateAttributes(Instances instances) {
        for (Enumeration<Attribute> e = instances.enumerateAttributes(); e.hasMoreElements();)
            if (!e.nextElement().isNumeric()) return false;
        
        return true;
    }
}
