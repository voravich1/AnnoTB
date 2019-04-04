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
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author vorav
 */
public class AnnotationUtility {
    

        public static void createHBVarDB(String variantValidatorFile, String HbVarDBSNP) throws IOException{
            boolean headerFlag = true;
            LinkedHashMap<String,String> varVMapHG37 = new LinkedHashMap();
            LinkedHashMap<String,String> varVMapHG38 = new LinkedHashMap();
            LinkedHashMap<String,String> dbSNPMap = new LinkedHashMap();
            LinkedHashMap<String,String> microAttMap = new LinkedHashMap();
            LinkedHashMap<String,String> phenoMap = new LinkedHashMap();
            

            /**
             * Convert variantValidator data to map
             */

            File varV = new File(variantValidatorFile);
            try(BufferedReader in = new BufferedReader(new FileReader(varV))) {
                String line;
                while ((line = in.readLine()) != null) {
                // process line here.
                    String[] data = line.split("\t");
                    String hgvsKey = data[0];
                    
                    /**
                     * Create Map for hg37
                     */
                    if(data.length>5){
                        if(headerFlag == true){
                            
//                            headerFlag = false;
                        }else if(!varVMapHG37.containsKey(hgvsKey) && headerFlag == false && !data[7].isEmpty()){
                            // If hgvskey is not exist before, line is not header and data in column 7 has value 
                            
                            String hgvsGenomic = data[7];
                            String chr = data[8];
                            String start = data[9];
                            String ref = data[11];
                            String alt = data[12];
                            String svType = "";
                            String stop = "";
                            String dbType = "";
                            
                            String hgvsRefPart = hgvsGenomic.split(":")[0];
                            String hgvsVaraintPart = hgvsGenomic.split(":")[1];
                            String varPosInfo = hgvsVaraintPart.split("\\.")[1];
                            
                            if(varPosInfo.contains("_")){
                                // has range of pos
                                String backpart = varPosInfo.split("_")[1];
                                
                                if(backpart.contains("delins")){
                                    svType = "delins";
                                    stop = backpart.split("delins")[0];
                                }else if(backpart.contains("del")){
                                    svType = "del";
                                    stop = backpart.split("del")[0];
                                }else if(backpart.contains("ins")){
                                    svType = "ins";
                                    stop = backpart.split("ins")[0];
                                }else if(backpart.contains("inv")){
                                    svType = "inv";
                                    stop = backpart.split("inv")[0];
                                }else if(backpart.contains("dup")){
                                    svType = "dup";
                                    stop = backpart.split("dup")[0];
                                }else if(backpart.contains("con")){
                                    svType = "con";
                                    stop = backpart.split("con")[0];
                                }else{
                                    svType = "null";
                                    stop = "null";
                                    dbType = "null";
                                }
                                
                                if(start.isEmpty()){
                                    dbType = "null";
                                }else if(Math.abs(Integer.parseInt(start) - Integer.parseInt(stop)) > 50){
                                    dbType = "bed";
                                }else if(Math.abs(Integer.parseInt(start) - Integer.parseInt(stop)) <= 50){
                                    dbType = "vcf";
                                }
                            }else{
                                // has single pos
                                
                                String backpart = varPosInfo;
                                if(backpart.contains("delins")){
                                    svType = "delins";
                                    stop = "null";
                                }else if(backpart.contains("del")){
                                    svType = "del";
                                    stop = "null";
                                }else if(backpart.contains("ins")){
                                    svType = "ins";
                                    stop = "null";
                                }else if(backpart.contains("inv")){
                                    svType = "inv";
                                    stop = "null";
                                }else if(backpart.contains("dup")){
                                    svType = "dup";
                                    stop = "null";
                                }else if(backpart.contains("con")){
                                    svType = "con";
                                    stop = "null";
                                }else{
                                    svType = "SNP";
                                    stop = "null";
                                }
                                
                                if(start.isEmpty()){
                                    dbType = "null";
                                }else{
                                    dbType = "vcf";
                                }
                            }
                            
                            String value = chr+","+start+","+stop+","+ref+","+alt+","+svType+","+dbType;   // value will be a string of variant info in this format [chr,start,stop,ref,alt,svType]
                            
                            varVMapHG37.put(hgvsKey, value);
                        }
                    }
                    
                    /*******************************************/
                    
                    /**
                     * Create Map for hg38
                     */
                    if(data.length>5){
                        if(headerFlag == true){
                            
//                            headerFlag = false;
                        }else if(!varVMapHG38.containsKey(hgvsKey) && headerFlag == false && !data[13].isEmpty()){
                            // If hgvsKey not exist before, line is not header and data column 13 has value
                            
                            String hgvsGenomic = data[13];
                            String chr = data[14];
                            String start = data[15];
                            String ref = data[17];
                            String alt = data[18];
                            String svType = "";
                            String stop = "";
                            String dbType = "";
                            
                            String hgvsRefPart = hgvsGenomic.split(":")[0];
                            String hgvsVaraintPart = hgvsGenomic.split(":")[1];
                            String varPosInfo = hgvsVaraintPart.split("\\.")[1];
                            
                            if(varPosInfo.contains("_")){
                                // has range of pos
                                String backpart = varPosInfo.split("_")[1];
                                
                                if(backpart.contains("delins")){
                                    svType = "delins";
                                    stop = backpart.split("delins")[0];
                                }else if(backpart.contains("del")){
                                    svType = "del";
                                    stop = backpart.split("del")[0];
                                }else if(backpart.contains("ins")){
                                    svType = "ins";
                                    stop = backpart.split("ins")[0];
                                }else if(backpart.contains("inv")){
                                    svType = "inv";
                                    stop = backpart.split("inv")[0];
                                }else if(backpart.contains("dup")){
                                    svType = "dup";
                                    stop = backpart.split("dup")[0];
                                }else if(backpart.contains("con")){
                                    svType = "con";
                                    stop = backpart.split("con")[0];
                                }else{
                                    svType = "null";
                                    stop = "null";
                                    dbType = "null";
                                }
                                
                                if(start.isEmpty()){
                                    dbType = "null";
                                }else if(Math.abs(Integer.parseInt(start) - Integer.parseInt(stop)) > 50){
                                    dbType = "bed";
                                }else if(Math.abs(Integer.parseInt(start) - Integer.parseInt(stop)) <= 50){
                                    dbType = "vcf";
                                }
                                
                            }else{
                                // has single pos
                                
                                String backpart = varPosInfo;
                                if(backpart.contains("delins")){
                                    svType = "delins";
                                    stop = "null";
                                }else if(backpart.contains("del")){
                                    svType = "del";
                                    stop = "null";
                                }else if(backpart.contains("ins")){
                                    svType = "ins";
                                    stop = "null";
                                }else if(backpart.contains("inv")){
                                    svType = "inv";
                                    stop = "null";
                                }else if(backpart.contains("dup")){
                                    svType = "dup";
                                    stop = "null";
                                }else if(backpart.contains("con")){
                                    svType = "con";
                                    stop = "null";
                                }else{
                                    svType = "SNP";
                                    stop = "null";
                                }
                                
                                if(start.isEmpty()){
                                    dbType = "null";
                                }else{
                                    dbType = "vcf";
                                }
                                
                            }
                            
                            String value = chr+","+start+","+stop+","+ref+","+alt+","+svType+","+dbType;   // value will be a string of variant info in this format [chr,start,stop,ref,alt,svType]
                            
                            varVMapHG38.put(hgvsKey, value);
                        }
                        headerFlag = false;
                    }
                    
                    /*******************************************/
                    
                }
            }catch(Exception e){
                System.out.println(e);
            }
            /***************************************/
            
            System.out.println();
            
            /**
             * Convert hbVarDBSNP data to map
             */
            headerFlag = true;
            
            File hbVarDBSNP = new File(HbVarDBSNP);
            try(BufferedReader in = new BufferedReader(new FileReader(hbVarDBSNP))) {
                String line;
                while ((line = in.readLine()) != null) {
                    String[] data = line.split("\t");
                    String hgvsKey = data[0];
                    
                    /**
                     * Create Map for 
                     */
                    if(data.length>5){
                        if(headerFlag == true){
                            
//                            headerFlag = false;
                        }else if(!dbSNPMap.containsKey(hgvsKey) && headerFlag == false){
                            String hbVarID = data[5];
                            String pubMedID = data[4];
                                
                            String hbvarInfo = hbVarID+","+pubMedID;
                            dbSNPMap.put(hgvsKey, hbvarInfo);
                            
                        }
                    }
                    headerFlag = false;
                }
            }catch(Exception e){
                System.out.println(e);
            }
            /***************************************/
            
            /**
             * Export to DB file
             * For large SV export to BED format 
             * For SNV and small SV export to vcf format
             * To judge, it is small or large we fixed cut off at 50 bp
             */
            
            String savePath = hbVarDBSNP.getParent();
            String hg37Vcf = savePath+File.separator+"HbVar_SNV_Indel_DB_hg37.vcf";
            String hg37Bed = savePath+File.separator+"HbVar_SV_DB_hg37.bed";
            String hg38Vcf = savePath+File.separator+"HbVar_SNV_Indel_DB_hg38.vcf";
            String hg38Bed = savePath+File.separator+"HbVar_SV_DB_hg38.bed";
            
            
            
            /**
             * Export For hg37
             */
            FileWriter writerBed;
            FileWriter writerVcf;
            /**
             * Check File existing
             */

            File bed = new File(hg37Bed); //File object        
            if(bed.exists()){
                writerBed = new FileWriter(hg37Bed,false);
            }else{
                writerBed = new FileWriter(hg37Bed);
            }
            
            File vcf = new File(hg37Vcf); //File object        
            if(vcf.exists()){
                writerVcf = new FileWriter(hg37Vcf,false);
            }else{
                writerVcf = new FileWriter(hg37Vcf);
            }
            
            /**
             * Write vcf header
             */
            writerVcf.write("##fileformat=VCFv4.3");
            writerVcf.write("\n");
            writerVcf.write("##reference=GRCh37");
            writerVcf.write("\n");
            writerVcf.write("##INFO=<ID=PUBMED,Number=.,Type=String,Description=\"PubMed ID\">");
            writerVcf.write("\n");
            writerVcf.write("##INFO=<ID=HBID,Number=1,Type=String,Description=\"Hbvar ID\">");
            writerVcf.write("\n");
            writerVcf.write("##INFO=<ID=SVTYPE,Number=1,Type=String,Description=\"Structural variant type\">");
            writerVcf.write("\n");
            writerVcf.write("#CHROM\tPOS\tID\tREF\tALT\tQUAL\tFILTER\tINFO\tFORMAT");
            writerVcf.write("\n");
            /*********************/
            for(Map.Entry<String,String> entry : dbSNPMap.entrySet()){
                
                String hgvs = entry.getKey();
                String[] dbSNPInfo = entry.getValue().split(",");
                String hbVarID = dbSNPInfo[0];
                String pubMedID = dbSNPInfo[1];
                String pubMedInfo = "";
                
                String[] dummyPubMedID = pubMedID.split(";");
                boolean firstFlag = true;
                if(dummyPubMedID.length>1){
                    for(String a:dummyPubMedID){
                        if(firstFlag){
                            pubMedInfo = a;
                            firstFlag=false;
                        }else{
                            pubMedInfo = pubMedInfo+","+a;
                        }   
                    }
                }else{
                    pubMedInfo=dummyPubMedID[0];
                }
                
                
                
                String[] varVInfo = varVMapHG37.get(hgvs).split(",");
                String chr = varVInfo[0]; 
                String start = varVInfo[1];
                String ref = varVInfo[3];
                String alt = varVInfo[4];
                String svType = varVInfo[5];
                String stop = varVInfo[2];
                String dbType = varVInfo[6];
                
                if(dbType.equals("bed")){
                    String info = hbVarID+"|"+pubMedInfo;
                    String bedInfo = chr+"\t"+start+"\t"+stop+"\t"+info;
                    writerBed.write(bedInfo);
                    writerBed.write("\n");
                }else if(dbType.equals("vcf")){
                    String info = "SVTYPE="+svType+";HBID="+hbVarID+";PUBMED="+pubMedInfo;
                    String writeInfo = chr+"\t"+start+"\t"+hbVarID+"\t"+ref+"\t"+alt+"\t"+"."+"\t"+"."+"\t"+info+"\t"+".";
                    writerVcf.write(writeInfo);
                    writerVcf.write("\n");
                }   
            }
            
            writerBed.flush();
            writerBed.close();
            writerVcf.flush();
            writerVcf.close();
            /***********************************/
            
            /**
             * Export for hg38
             */
            
            /**
             * Check File existing
             */

            bed = new File(hg38Bed); //File object        
            if(bed.exists()){
                writerBed = new FileWriter(hg38Bed,false);
            }else{
                writerBed = new FileWriter(hg38Bed);
            }
            
            vcf = new File(hg38Vcf); //File object        
            if(vcf.exists()){
                writerVcf = new FileWriter(hg38Vcf,false);
            }else{
                writerVcf = new FileWriter(hg38Vcf);
            }
            
            /**
             * Write vcf header
             */
            writerVcf.write("##fileformat=VCFv4.3");
            writerVcf.write("\n");
            writerVcf.write("##reference=GRCh38");
            writerVcf.write("\n");
            writerVcf.write("##INFO=<ID=PUBMED,Number=.,Type=String,Description=\"PubMed ID\">");
            writerVcf.write("\n");
            writerVcf.write("##INFO=<ID=HBID,Number=1,Type=String,Description=\"Hbvar ID\">");
            writerVcf.write("\n");
            writerVcf.write("##INFO=<ID=SVTYPE,Number=1,Type=String,Description=\"Structural variant type\">");
            writerVcf.write("\n");
            writerVcf.write("#CHROM\tPOS\tID\tREF\tALT\tQUAL\tFILTER\tINFO\tFORMAT");
            writerVcf.write("\n");
            /*********************/
            for(Map.Entry<String,String> entry : dbSNPMap.entrySet()){
                
                String hgvs = entry.getKey();
                String[] dbSNPInfo = entry.getValue().split(",");
                String hbVarID = dbSNPInfo[0];
                String pubMedID = dbSNPInfo[1];
                String pubMedInfo = "";
                
                String[] dummyPubMedID = pubMedID.split(";");
                boolean firstFlag = true;
                if(dummyPubMedID.length>1){
                    for(String a:dummyPubMedID){
                        if(firstFlag){
                            pubMedInfo = a;
                            firstFlag=false;
                        }else{
                            pubMedInfo = pubMedInfo+","+a;
                        }   
                    }
                }else{
                    pubMedInfo=dummyPubMedID[0];
                }
                
                
                
                String[] varVInfo = varVMapHG38.get(hgvs).split(",");
                String chr = varVInfo[0]; 
                String start = varVInfo[1];
                String ref = varVInfo[3];
                String alt = varVInfo[4];
                String svType = varVInfo[5];
                String stop = varVInfo[2];
                String dbType = varVInfo[6];
                
                if(dbType.equals("bed")){
                    String info = hbVarID+"|"+pubMedInfo;
                    String bedInfo = chr+"\t"+start+"\t"+stop+"\t"+info;
                    writerBed.write(bedInfo);
                    writerBed.write("\n");
                }else if(dbType.equals("vcf")){
                    String info = "SVTYPE="+svType+";HBID="+hbVarID+";PUBMED="+pubMedInfo;
                    String writeInfo = chr+"\t"+start+"\t"+hbVarID+"\t"+ref+"\t"+alt+"\t"+"."+"\t"+"."+"\t"+info+"\t"+".";
                    writerVcf.write(writeInfo);
                    writerVcf.write("\n");
                }   
            }
            writerBed.flush();
            writerBed.close();
            writerVcf.flush();
            writerVcf.close();
            /***********************************/
            
        }
    
}
