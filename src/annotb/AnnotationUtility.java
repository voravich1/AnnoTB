/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;

/**
 *
 * @author vorav
 */
public class AnnotationUtility {
    

        public void createHBVarDB(String variantValidatorFile, String HbVarDBSNP, String HbVarmicroAttribute, String HbVarPhenotype){
            boolean headerFlag = true;
            LinkedHashMap<String,String> varVMapHG37 = new LinkedHashMap();
            LinkedHashMap<String,String> varVMapHG38 = new LinkedHashMap();
            LinkedHashMap<String,String> dbSNPMap = new LinkedHashMap();
            LinkedHashMap<String,String> microAttMap = new LinkedHashMap();
            LinkedHashMap<String,String> phenoMap = new LinkedHashMap();
            
            File varV = new File(variantValidatorFile);
            
            
            try(BufferedReader in = new BufferedReader(new FileReader(varV.getPath()))) {
                String line;
                while ((line = in.readLine()) != null) {
                // process line here.
                    String[] data = line.split("\t");
                    
                    /**
                     * Create Map for hg37
                     */
                    if(data.length>5){
                        if(headerFlag == true){
                            
                            headerFlag = false;
                        }else if(!varVMapHG37.containsKey(data[0]) && headerFlag == false){
                            String hgvsGenomic = data[7];
                            String chr = data[8];
                            String start = data[9];
                            String ref = data[11];
                            String alt = data[12];
                            
                            String backPart = hgvsGenomic.split(":")[1].split(".")[1];
                            // stuck at How to find stop posiiton from hgvs 
                            
                        }
                    }
                }
            }catch(Exception e){
                System.out.println("File not Found");
            }
                
            
            
            
        }
    
}
