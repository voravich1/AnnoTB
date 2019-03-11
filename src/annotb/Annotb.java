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
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
//        String vcfFileName = "/Users/worawich/Download_dataset/testTB/vcf/ERR718196.dr.vcf";
//        File vcf_File = new File(vcfFileName);
//        VCFFileReader vcfReader = new VCFFileReader(vcf_File);
//        CloseableIterator<VariantContext> vc = vcfReader.query("Chromosome", 6000, 7000);
//        List l;
//        l = vc.toList();
//        System.out.println();
//        VariantContext var;
//        var = (VariantContext) l.get(1);
//        
//        Allele al = var.getReference();
//        System.out.println("Ref is " + al.getBaseString());
//        List<Allele> listAl = var.getAlternateAlleles();
//        
//        for (Allele al2 : listAl){
//            System.out.println("ALT is " + al2.getBaseString());
//        }
//        
//        CloseableIterator<VariantContext> vcf_info = vcfReader.iterator();
//        int count = 0;
//        while(vcf_info.hasNext()){
//            VariantContext ctx = vcf_info.next();
//            System.out.println(count++);
//        }
//        
//        
//        
//        System.out.println();
        
        String dbFilePath = "/Users/worawich/sqlite/db/test.db";
        createNewDatabase(dbFilePath);
        connectToDatabase(dbFilePath);
   
    }
    
    public static Connection connectToDatabase(String dbFilePath){
        Connection conn = null;
        
        try{
            // db parameters
            String url = "jdbc:sqlite:" + dbFilePath;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return conn;
    }
    
    public static void createNewDatabase(String dbFilePath) {
 
        String url = "jdbc:sqlite:" + dbFilePath;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createNewTable(String dbFilePath) {
        // SQLite connection string
        String url = "jdbc:sqlite:"+dbFilePath;
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void selectAll(){
        String sql = "SELECT id, name, capacity, FROM warehouse";
        
//        try(Connection conn = this.connectToDatabase()){
            
//        }
    }
    
}
