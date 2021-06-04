package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class DetailRingkasanMahasiswaGUI {
    public DetailRingkasanMahasiswaGUI(JFrame frame, Mahasiswa mahasiswa, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        // Judul dan Komponen Label
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Detail Ringkasan Mahasiswa");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitleR);

        JLabel nama = new JLabel();
        nama.setText("Nama: " + mahasiswa.getNama());
        nama.setHorizontalAlignment(JLabel.CENTER);
        nama.setFont(SistemAkademikGUI.fontRingkasan);

        JLabel npm = new JLabel();
        npm.setText("NPM: " + mahasiswa.getNpm());
        npm.setHorizontalAlignment(JLabel.CENTER);
        npm.setFont(SistemAkademikGUI.fontRingkasan);

        JLabel jurusan = new JLabel();
        jurusan.setText("Jurusan: " + mahasiswa.getJurusan());
        jurusan.setHorizontalAlignment(JLabel.CENTER);
        jurusan.setFont(SistemAkademikGUI.fontRingkasan);

        JLabel daftar = new JLabel();
        daftar.setText("Daftar Mata Kuliah:");
        daftar.setHorizontalAlignment(JLabel.CENTER);
        daftar.setFont(SistemAkademikGUI.fontRingkasan);

        JLabel sks = new JLabel();
        sks.setText("Total SKS: " + mahasiswa.getTotalSKS());
        sks.setHorizontalAlignment(JLabel.CENTER);
        sks.setFont(SistemAkademikGUI.fontRingkasan);
        
        JLabel hasil = new JLabel();
        hasil.setText("Hasil Pengecekan IRS:");
        hasil.setHorizontalAlignment(JLabel.CENTER);
        hasil.setFont(SistemAkademikGUI.fontRingkasan);
        // Button
        JButton jbtReturn = new JButton("Selesai");
        color(jbtReturn);
        // Panel
        int ukuranGrid = mahasiswa.getBanyakMasalahIRS() + mahasiswa.getBanyakMatkul() + 10;
        JPanel p = new JPanel(new GridLayout(ukuranGrid,1,0,10));
        JPanel pMain = new JPanel(new BorderLayout());
        
        // Menambahkan komponen pada panel
        p.add(titleLabel);
        p.add(nama);
        p.add(npm);
        p.add(jurusan);
        p.add(daftar);
        daftarMatkul(mahasiswa, p);
        p.add(sks);
        p.add(hasil);
        daftarMasalah(mahasiswa, p);
        p.add(jbtReturn);

        jbtReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setSize(500,500);
                frame.setLocationRelativeTo(null);
                frame.remove(pMain);
                new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
                frame.revalidate();
            }
        }
        );
        frame.setSize(500,750);
        frame.setLocationRelativeTo(null);
        // Border
        pMain.add(new JLabel("      "), BorderLayout.NORTH);
        pMain.add(new JLabel("              "), BorderLayout.WEST);
        pMain.add(new JLabel("              "), BorderLayout.EAST);
        pMain.add(new JLabel("      "), BorderLayout.SOUTH);
        pMain.add(p, BorderLayout.CENTER);
         
        frame.add(pMain);
    }
    // Method untuk mencetak daftar MataKuliah
    public void daftarMatkul(Mahasiswa mahasiswa, JPanel p){
        MataKuliah[] arr = mahasiswa.getMataKuliah();

        if (mahasiswa.getBanyakMatkul() == 0){
            JLabel matkul = new JLabel();
            matkul.setHorizontalAlignment(JLabel.CENTER);
            matkul.setFont(new Font("Century Gothic", Font.BOLD, 12));
            matkul.setText("Belum ada mata kuliah yang diambil.");
            p.add(matkul);
            return;
        }
        for (int i = 0; i < mahasiswa.getBanyakMatkul(); i++){
            JLabel matkul = new JLabel();
            matkul.setHorizontalAlignment(JLabel.CENTER);
            matkul.setFont(new Font("Century Gothic", Font.BOLD, 12));
            matkul.setText((i+1) + ". " + arr[i].getNama());
            p.add(matkul);
        }
    }
    // Method untuk mencetak daftar Masalah
    public void daftarMasalah(Mahasiswa mahasiswa, JPanel p){
        mahasiswa.cekIRS();
        String[] arr = mahasiswa.getMasalahIRS();

        if (mahasiswa.getBanyakMasalahIRS() == 0){
            JLabel masalah = new JLabel();
            masalah.setHorizontalAlignment(JLabel.CENTER);
            masalah.setFont(new Font("Century Gothic", Font.BOLD, 12));
            masalah.setText("IRS tidak bermasalah.");
            p.add(masalah);
            return;
        }
        for (int i = 0; i < mahasiswa.getBanyakMasalahIRS(); i++){
            JLabel masalah = new JLabel();
            masalah.setHorizontalAlignment(JLabel.CENTER);
            masalah.setFont(new Font("Century Gothic", Font.BOLD, 12));
            masalah.setText((i+1) + ". " + arr[i]);
            p.add(masalah);
        }
    }
    // Method set color
    public void color(JButton b){
        b.setBackground(Color.BLUE);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
    }
}
