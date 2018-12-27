/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotb;

import htsjdk.variant.vcf.VCFFileReader;
import java.io.File;

/**
 *
 * @author worawich
 */
public class Annotation {
    
    public Annotation(){
        
    }
    
    public static void readVCF(String vcfFileName){
        File vcf_File = new File(vcfFileName);
        VCFFileReader vcfReader = new VCFFileReader(vcf_File);
        
     
        
    }
    
    public void readDBFile(String dbFile){
        
    }
    
}
