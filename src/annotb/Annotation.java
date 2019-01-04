/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotb;

import htsjdk.samtools.util.CloseableIterator;
import htsjdk.variant.variantcontext.VariantContext;
import htsjdk.variant.vcf.VCFFileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author worawich
 */
public class Annotation {
    
    public Annotation(){
        
    }
    
    public static VCFFileReader readVCF(String vcfFileName){
        File vcf_File = new File(vcfFileName);
        VCFFileReader vcfReader = new VCFFileReader(vcf_File);
        return vcfReader;
    }
    
    public static DatabaseTBContext readTBDatabaseFile(String dbFilePath) throws FileNotFoundException, IOException{
        
        FileReader dbFileReader = new FileReader(dbFilePath);
        BufferedReader bf = new BufferedReader(dbFileReader);
        
        DatabaseTBContext dbTB = new DatabaseTBContext();
        String[] header = bf.readLine().split("\\t");
        dbTB.addHeader(header);
        
        String line;
        while((line = bf.readLine()) != null){
            String[] data = line.split("\\t");
            dbTB.addData(data);    
        }
        return dbTB;
    }
    
    public static void annotateTB(String vcfFilePath, String dbFilePath) throws IOException{
        
        AnnotationResult annoRes = new AnnotationResult();
        
        DatabaseTBContext dbTB = readTBDatabaseFile(dbFilePath);
        
        VCFFileReader vcfReader = readVCF(vcfFilePath);
        
        ArrayList<String> posStartList = dbTB.getPosStart();
        ArrayList<String> posStopList = dbTB.getPosStop();
        int dbSize = dbTB.getDbSize();
        
        /**
         * loop through DB and start annotate
         */
        for(int i=0;i<dbSize;i++){
            int posStart = Integer.parseInt(posStartList.get(i));
            int posStop = Integer.parseInt(posStopList.get(i));
            
            /**
             * extract first line of variant data to check chromosome name
             * because TB has single chromosome but input vcf may use different name of chromosome
             */
            CloseableIterator<VariantContext> vcf_info = vcfReader.iterator();
            VariantContext ctx = vcf_info.next();
            String chromosomeName = ctx.getContig();      
            /***************/
            CloseableIterator<VariantContext> vc = vcfReader.query(chromosomeName, posStart, posStop);
            
            List<VariantContext> listVariant = vc.toList();
            
            for(VariantContext varContext : listVariant){
                //add check db variant detail
            }
        }
    }
    
}
