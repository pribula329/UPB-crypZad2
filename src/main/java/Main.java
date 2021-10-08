import sun.awt.SunHints;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Ahoj ideme na to?");

        System.out.println("Vyber možnosť:");
        System.out.println("1 - šifrovanie     2 - dešifrovanie");
        Scanner volba1 = new Scanner(System.in);
        String volbaSD = volba1.nextLine();
        if (volbaSD.equals("1")){
            System.out.println("Vybral si šifrovanie");
            KlucGen key =  new KlucGen();
            System.out.println("Napiš nazov súboru aj s príponou:");
            Scanner volba2 = new Scanner(System.in);
            String volbaSuboru = volba2.nextLine();
            long startTime1 = System.currentTimeMillis();
            File inputFile = new File(volbaSuboru);
            File encryptedFile = new File("document_encrypted");
            FileWriter ulozenieKluca = new FileWriter("kluc.txt");
            ulozenieKluca.write(key.klucDoStringu());
            ulozenieKluca.close();
            Crypto.crypto(key.getKluc(),inputFile,encryptedFile,1);
            long endTime   = System.currentTimeMillis();
            NumberFormat formatter = new DecimalFormat("#0.00000");
            System.out.println("Zašifrované pozri súbor document_encrypted a subor kluc.txt");
            System.out.println("Zašifrovanie trvalo: " + formatter.format((endTime-startTime1) / 1000d) + "s");
            System.out.println("Ukonči program ľubovoľnim tlačidlom a choď dešifrovať");
            Scanner volba5 = new Scanner(System.in);
            String volba6 = volba1.nextLine();

        }
        else if (volbaSD.equals("2")){
            System.out.println("Vybral si dešifrovanie");
            long startTime1 = System.currentTimeMillis();
            File encryptedFile = new File("document_encrypted");
            File decryptedFile = new File("document_decrypted");

            Scanner myReader = new Scanner(new File("kluc.txt"));
            String data = null;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            Key dataKey = KlucGen.stringDoKluca(data);
            Crypto.crypto(dataKey,encryptedFile,decryptedFile,2);
            long endTime   = System.currentTimeMillis();
            NumberFormat formatter = new DecimalFormat("#0.00000");
            System.out.println("Dešifrované pozri súbor document_decrypted a otvor ho v požadovamon programe");
            System.out.println("Zašifrovanie trvalo: " + formatter.format((endTime-startTime1) / 1000d) + "s");
            System.out.println("Ukonči program ľubovoľnim tlačidlom");
            Scanner volba15 = new Scanner(System.in);
            String volba06 = volba1.nextLine();
        }
        else {
            System.out.println("Chyba");
        }






    }
}
