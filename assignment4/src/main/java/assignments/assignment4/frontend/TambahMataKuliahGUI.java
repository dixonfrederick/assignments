package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class TambahMataKuliahGUI{

    public TambahMataKuliahGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        // Judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Tambah Mata Kuliah");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        // Label dan TextField kode
        JLabel kode = new JLabel();
        kode.setText("Kode Mata Kuliah:");
        kode.setHorizontalAlignment(JLabel.CENTER);
        kode.setFont(SistemAkademikGUI.fontGeneral);        
        JTextField jtfKode = new JTextField();
        // Label dan TextField nama
        JLabel nama = new JLabel();
        nama.setText("Nama Mata Kuliah:");
        nama.setHorizontalAlignment(JLabel.CENTER);
        nama.setFont(SistemAkademikGUI.fontGeneral);
        JTextField jtfName = new JTextField();
        // Label dan TextField sks
        JLabel sks = new JLabel();
        sks.setText("SKS:");
        sks.setHorizontalAlignment(JLabel.CENTER);
        sks.setFont(SistemAkademikGUI.fontGeneral);        
        JTextField jtfSks = new JTextField();
        // Label dan TextField kapasitas
        JLabel kapasitas = new JLabel();
        kapasitas.setText("Kapasitas:");
        kapasitas.setHorizontalAlignment(JLabel.CENTER);
        kapasitas.setFont(SistemAkademikGUI.fontGeneral);
        JTextField jtfKapasitas = new JTextField();
        // Button
        JButton jbtAdd = new JButton("Tambahkan");
        JButton jbtReturn = new JButton("Kembali");
        // Panel
        JPanel p = new JPanel(new GridLayout(11,1,0,17));
        JPanel pMain = new JPanel(new BorderLayout());

        // Menambahkan komponen pada panel
        p.add(titleLabel);
        p.add(kode);
        p.add(jtfKode);
        p.add(nama);
        p.add(jtfName);
        p.add(sks);
        p.add(jtfSks);
        p.add(kapasitas);
        p.add(jtfKapasitas);
        p.add(jbtAdd);
        p.add(jbtReturn);

        // Listener
        jbtAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle untuk TextField kosong
                if (jtfKode.getText().equals("") || jtfName.getText().equals("") || 
                    jtfSks.getText().equals("") || jtfKapasitas.getText().equals("")){
                    JOptionPane.showMessageDialog(
                        null,
                        "Mohon isi seluruh Field",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String namaMatkul = jtfName.getText();
                String kodeMatkul = jtfKode.getText();
                int sksMatkul = Integer.parseInt(jtfSks.getText());
                // Handle untuk matkul sudah ada dalam daftar
                int KapasitasMatkul = Integer.parseInt(jtfKapasitas.getText());
                for (int i = 0; i < daftarMataKuliah.size(); i++){
                    if (daftarMataKuliah.get(i).getNama().equals(namaMatkul)){
                        JOptionPane.showMessageDialog(
                            null,
                            "Mata Kuliah " + namaMatkul + " sudah pernah ditambahkan sebelumnya", 
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                        jtfKode.setText("");
                        jtfName.setText("");
                        jtfSks.setText("");
                        jtfKapasitas.setText("");
                        return;
                    }
                }
                daftarMataKuliah.add(new MataKuliah(kodeMatkul, namaMatkul, sksMatkul, KapasitasMatkul));
                JOptionPane.showMessageDialog(
                    null,
                    "Mata Kuliah " + namaMatkul + " berhasil ditambahkan",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);
                jtfKode.setText("");
                jtfName.setText("");
                jtfSks.setText("");
                jtfKapasitas.setText("");
            }
        }
        );

        jbtReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(pMain);
                new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
                frame.revalidate();
            }
        }
        );

        // Border
        pMain.add(new JLabel("      "), BorderLayout.NORTH);
        pMain.add(new JLabel("              "), BorderLayout.WEST);
        pMain.add(new JLabel("              "), BorderLayout.EAST);
        pMain.add(new JLabel("      "), BorderLayout.SOUTH);
        pMain.add(p, BorderLayout.CENTER);

        frame.add(pMain);
    }
    
}
