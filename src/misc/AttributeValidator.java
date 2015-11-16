/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
