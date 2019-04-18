/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotb;

import static annotb.AnnotationUtility.createHBVarDB;
import java.io.IOException;

/**
 *
 * @author worawich
 */
public class TestBed {
    
    public static void main(String[] args) throws IOException{
        
        String varV = "/Users/worawich/Reference/Thalassemia_HbVar_DB/variantValidator.txt";
        String dbSNP = "/Users/worawich/Reference/Thalassemia_HbVar_DB/dbSNP_HbVar_DB.txt";
                
        createHBVarDB(varV,dbSNP);
        
    }
    
    
}
