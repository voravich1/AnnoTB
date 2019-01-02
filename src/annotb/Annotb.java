/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotb;

import htsjdk.samtools.util.CloseableIterator;
import htsjdk.samtools.util.Interval;
import htsjdk.samtools.util.IntervalList;
import htsjdk.variant.variantcontext.Allele;
import htsjdk.variant.variantcontext.VariantContext;
import htsjdk.variant.vcf.VCFFileReader;
import java.io.File;
import java.util.List;

/**
 *
 * @author worawich
 */
public class Annotb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String vcfFileName = "/Users/worawich/Download_dataset/testTB/vcf/ERR718196.dr.vcf";
        File vcf_File = new File(vcfFileName);
        VCFFileReader vcfReader = new VCFFileReader(vcf_File);
        CloseableIterator<VariantContext> vc = vcfReader.query("Chromosome", 6000, 7000);
        List l;
        l = vc.toList();
        System.out.println();
        VariantContext var;
        var = (VariantContext) l.get(1);
        
        Allele al = var.getReference();
        System.out.println("Ref is " + al.getBaseString());
        List<Allele> listAl = var.getAlternateAlleles();
        
        for (Allele al2 : listAl){
            System.out.println("ALT is " + al2.getBaseString());
        }
        
        CloseableIterator<VariantContext> vcf_info = vcfReader.iterator();
        int count = 0;
        while(vcf_info.hasNext()){
            VariantContext ctx = vcf_info.next();
            System.out.println(count++);
        }
        
        
        
        System.out.println();
        
        
        
        
        
        
        
        
        
        
        
   
    }
    
}
