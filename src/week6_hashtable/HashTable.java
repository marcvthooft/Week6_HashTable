package week6_hashtable;

import java.util.LinkedList;
import java.util.Random;

public class HashTable {
    int tblSize = 997;
    Random random = new Random();
    LinkedList[] chainTable = new LinkedList[997];
    String[] probTable = new String[997];
    double A = (Math.sqrt(5) - 1)/2;
    Boolean divisionAlg;
    String algorithm;
    int h;      //hashed key
    
    /**
     * Choose true if you want division algorithm, false for multiplication.
     * @param alg 
     */
    public HashTable(Boolean divisionAlg) {
        this.divisionAlg = divisionAlg;
        
        for (int cel = 0; cel < tblSize; cel++) {
            chainTable[cel] = new LinkedList();
        }
    }
    
    public int hash(int K) {
        if (divisionAlg) {
            return  K % tblSize;
        } else {                //multiplicationAlg
            return (int) (tblSize * (K * A % 1));
        }
    }
    
    public void chaining(int K, String V) {
        h = hash(K);
        chainTable[h].add(V);
    }
    
    public void probing(int K, String V) {
        int i = 0;
        h = hash(K);
        while (probTable[h]!=null) {
            h = (h + 1) % tblSize;
            //Stop after a certain amount of attempts.
            i++;
            if (i==tblSize) {
                return;
            }
        }
        probTable[h] = V;
    }
    
    public void test(Boolean chain, int testAmount, int items) {
        testAmount++;
        if (testAmount==10) {
            return;
        }
        
        for (int i = 0; i < items; i++) {
            method(chain);
        }
        test(chain, testAmount, items);
    }
    
    /**
     * True for chaining, false for probing
     * @param chain 
     */
    private void method(Boolean chain) {
        if (chain) {
            chaining(random.nextInt(20000),"hoi");
        } else {
            probing(random.nextInt(20000),"hoi");
        }
    }

    public static void main(String[] args) {
        long before, total;
        HashTable divAlgTable;
        HashTable multAlgTable;
        int test = 1;
        
        while (test < 6) {
        System.out.println();
        System.out.println("Poging " + test + ":");
        //Table cells 997, items 745
        System.out.println("Chaining vs Probing -Table cells 997, items 745- :");
        
        //create tabels
        divAlgTable = new HashTable(true);
        multAlgTable = new HashTable(false);
        
        before = System.currentTimeMillis();
        divAlgTable.test(true, 0, 745);        
        multAlgTable.test(true, 0, 745);
        total = System.currentTimeMillis() - before;
        System.out.println("- Chaining: " + total + " ms");
        
        //create tabels
        divAlgTable = new HashTable(true);
        multAlgTable = new HashTable(false);
        
        before = System.currentTimeMillis();
        divAlgTable.test(false, 0, 745);        
        multAlgTable.test(false, 0, 745);
        total = System.currentTimeMillis() - before;
        System.out.println("- Probing: " + total + " ms");
        
        System.out.println();
        //Table cells: 997, items: 997
        System.out.println("Chaining vs Probing -Table cells 997, items 997- :");
        
        //create tabels
        divAlgTable = new HashTable(true);
        multAlgTable = new HashTable(false);
        
        before = System.currentTimeMillis();
        divAlgTable.test(true, 0, 997);        
        multAlgTable.test(true, 0, 997);
        total = System.currentTimeMillis() - before;
        System.out.println("- Chaining: " + total + " ms");
        
        //create tabels
        divAlgTable = new HashTable(true);
        multAlgTable = new HashTable(false);
        
        before = System.currentTimeMillis();
        divAlgTable.test(false, 0, 997);        
        multAlgTable.test(false, 0, 997);
        total = System.currentTimeMillis() - before;
        System.out.println("- Probing: " + total + " ms");
        
        test++;
        }
    }
}
