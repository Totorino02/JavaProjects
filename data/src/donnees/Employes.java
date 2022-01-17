package donnees;

import java.io.File;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author totorino
 */
public class Employes {
    public BufferedInputStream myFile;
    public byte[] bites;
    StringBuilder maChaine = new StringBuilder();
    StringTokenizer newChaine;
    StringTokenizer subString;
    String[] title = {"Nom","HNombre", "HSupp"};
    int cpt;
    int nbTokens;
    public Employes(){
        try{
            myFile = new BufferedInputStream(new DataInputStream(new  FileInputStream(new File("/home/totorino/NetBeansProjects/data/src/donnees/info.txt"))));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void lireDonnees(){
        try{
            bites = new byte[8];
            while(this.myFile.read(bites) != -1){
                for(byte b: bites){
                    maChaine.append((char)b);
                }
                bites = new byte[8];
            }
            newChaine = new StringTokenizer(maChaine.toString(), "\n");
            
            while(newChaine.hasMoreTokens()){
                cpt = 0;
                subString = new StringTokenizer(newChaine.nextToken(), ":");
                /*  === learn more about StringTokenizer ===    */
                nbTokens = subString.countTokens();
                while(subString.hasMoreTokens()){
                    if(cpt != nbTokens-1){
                        System.out.print(title[cpt]+": "+subString.nextToken()+"\t");
                    }else{
                        System.out.println(title[cpt]+": "+subString.nextToken()+"\t");
                    }
                    cpt++;
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
