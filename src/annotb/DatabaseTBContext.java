/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotb;

import java.util.ArrayList;

/**
 *
 * @author worawich
 */
public class DatabaseTBContext {
     
    private ArrayList<String> posStart;
    private ArrayList<String> posStop;
    private ArrayList<String> refBase;
    private ArrayList<String> altBase;
    private ArrayList<String> gene;
    private ArrayList<String> lineage;
    private ArrayList<String> drug;
    private String[] header;
    private int dbSize = 0;

    public ArrayList<String> getPosStart() {
        return posStart;
    }

    public ArrayList<String> getPosStop() {
        return posStop;
    }

    public ArrayList<String> getRefBase() {
        return refBase;
    }

    public ArrayList<String> getAltBase() {
        return altBase;
    }

    public ArrayList<String> getGene() {
        return gene;
    }

    public ArrayList<String> getLineage() {
        return lineage;
    }

    public ArrayList<String> getDrug() {
        return drug;
    }

    public String[] getHeader() {
        return header;
    }
    
    public DatabaseTBContext(){
        this.posStart = new ArrayList();
        this.posStop = new ArrayList();
        this.refBase = new ArrayList();
        this.altBase = new ArrayList();
        this.gene = new ArrayList();
        this.lineage = new ArrayList();
        this.drug = new ArrayList();
    }

    void addHeader(String[] header) {
        this.header = header;
    }

    void addData(String[] data) {
        this.posStart.add(data[0]);
        this.posStop.add(data[1]);
        this.refBase.add(data[2]);
        this.altBase .add(data[3]);
        this.gene.add(data[4]);
        this.lineage.add(data[5]);
        this.drug.add(data[6]);
    }

    public int getDbSize() {
        this.dbSize = this.posStart.size();
        return dbSize;
    }
    
    
    
}
