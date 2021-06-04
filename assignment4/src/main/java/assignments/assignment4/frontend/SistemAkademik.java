package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class SistemAkademik {

    
    public static void main(String[] args) { 
        new SistemAkademikGUI();
    }
}

class SistemAkademikGUI extends JFrame{
    private static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<Mahasiswa>();
    private static ArrayList<MataKuliah> daftarMataKuliah = new ArrayList<MataKuliah>();
    public static Font fontGeneral = new Font("Century Gothic", Font.PLAIN , 14);
    public static Font fontTitle = new Font("Century Gothic", Font.BOLD, 20);
    public static Font fontRingkasan = new Font("Century Gothic", Font.PLAIN , 11);
    public static Font fontTitleR = new Font("Century Gothic", Font.BOLD, 16);

    public SistemAkademikGUI(){

        // Membuat Frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        // Set judul frame dan Set lokasi frame di tengah
        frame.setTitle("Administrator - Sistem Akademik");
        frame.setLocationRelativeTo(null);
        
        new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
        frame.setVisible(true);


    }
}
