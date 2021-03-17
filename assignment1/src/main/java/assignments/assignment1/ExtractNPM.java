package assignments.assignment1;

import java.util.Scanner;

public class ExtractNPM {
    /*
    You can add other method do help you solve
    this problem
    
    Some method you probably need like
    - Method to get tahun masuk or else
    - Method to help you do the validation
    - and so on
    */

    public static long getTahunMasuk(long npm){
        String tahunMasukString = String.valueOf(npm).substring(0,2);
        long tahunMasuk = java.lang.Long.parseLong(tahunMasukString);
        return tahunMasuk + 2000;
    }

    public static long getKode(long npm){
        String kodeString = String.valueOf(npm).substring(2,4);
        long kodeJurusan = java.lang.Long.parseLong(kodeString);
        return kodeJurusan;
    }
    
    public static String getJurusan(long npm){
        if (getKode(npm) == 1){
            return "Ilmu Komputer";
        }
        else if (getKode(npm) == 2){
            return "Sistem Informasi";
        }
        else if (getKode(npm) == 3){
            return "Teknologi Informasi";
        }
        else if (getKode(npm) == 11){
            return "Teknik Telekomunikasi";
        }
        else if (getKode(npm) == 12){
            return "Teknik Elektro";
        }
        else{
            return "invalid";
        }
    }

    public static long getTanggal(long npm){
        String tanggalString = String.valueOf(npm).substring(4,6);
        long tanggal = java.lang.Long.parseLong(tanggalString);
        return tanggal;
    }

    public static long getBulan(long npm){
        String bulanString = String.valueOf(npm).substring(6,8);
        long bulan = java.lang.Long.parseLong(bulanString);
        return bulan;
    }

    public static long getTahun(long npm){
        String tahunString = String.valueOf(npm).substring(8,12);
        long tahun = java.lang.Long.parseLong(tahunString);
        return tahun;
    }

    public static long getUrutan(long npm){
        String urutanString = String.valueOf(npm).substring(12,13);
        long urutan = java.lang.Long.parseLong(urutanString);
        return urutan;
    }

    public static long adder(long npm){
        long total = 0;
        for (int i = 0; i < String.valueOf(npm).length(); i++){
            total += java.lang.Long.parseLong(String.valueOf(npm).substring(i,i + 1));
        }
        return total;
    }

    public static boolean validate(long npm) {
        int npmLength = String.valueOf(npm).length();
        if (npmLength > 14 || getTahunMasuk(npm) < 2010 || getTahunMasuk(npm) - getTahun(npm) < 15 
            || getJurusan(npm).equals("invalid")){
            return false;
        }

        long res = 0;
        for (int i = 0, j = npmLength - 2; i < npmLength - 8 || j > 6; i++, j--){
            long start = java.lang.Long.parseLong(String.valueOf(npm).substring(i,i + 1));
            long end = java.lang.Long.parseLong(String.valueOf(npm).substring(j,j + 1));
            res += start*end;
        }
        res += java.lang.Long.parseLong(String.valueOf(npm).substring(6,7));
        while (res >= 10){
            res = adder(res);
        }
        if (res == java.lang.Long.parseLong(String.valueOf(npm).substring(13))){
            return true;
        }
        else{
            return false;
        }
    }

    public static String extract(long npm) {
        // TODO: Extract information from NPM, return string with given format
        return "";
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        boolean exitFlag = false;
        while (!exitFlag) {
            long npm = input.nextLong();
            if (npm < 0) {
                exitFlag = true;
                break;
            }
            if (validate(npm) == false){
                System.out.println("NPM tidak valid!");
            }
            else{
                System.out.println(extract(npm));
            }
            
        }
        input.close();
    }
}