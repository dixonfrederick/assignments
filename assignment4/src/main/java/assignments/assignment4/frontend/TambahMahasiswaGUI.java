package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class TambahMahasiswaGUI{

    public TambahMahasiswaGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        // Judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Tambah Mahasiswa");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        // Label dan TextField nama
        JLabel nama = new JLabel();
        nama.setText("Nama:");
        nama.setHorizontalAlignment(JLabel.CENTER);
        nama.setFont(SistemAkademikGUI.fontGeneral);
        JTextField jtfName = new JTextField();
        // Label dan TextField npm
        JLabel npm = new JLabel();
        npm.setText("NPM:");
        npm.setHorizontalAlignment(JLabel.CENTER);
        npm.setFont(SistemAkademikGUI.fontGeneral);
        JTextField jtfNpm = new JTextField();
        // Button
        JButton jbtAdd = new JButton("Tambahkan");
        color(jbtAdd);
        JButton jbtReturn = new JButton("Kembali");
        color(jbtReturn);
        // Panel
        JPanel p = new JPanel(new GridLayout(7,1,0,17));
        JPanel pMain = new JPanel(new BorderLayout());

        // Menambahkan komponen pada panel
        p.add(titleLabel);
        p.add(nama);
        p.add(jtfName);
        p.add(npm);
        p.add(jtfNpm);
        p.add(jbtAdd);
        p.add(jbtReturn);

        // Listener
        jbtAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle untuk TextField kosong
                if (jtfName.getText().equals("") || jtfNpm.getText().equals("")){
                    JOptionPane.showMessageDialog(
                        null,
                        "Mohon isi seluruh Field",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String namaMhs = jtfName.getText();
                long npmMhs = Long.valueOf(jtfNpm.getText());
                // Handle untuk NPM sudah ada dalam daftar
                for (int i = 0; i < daftarMahasiswa.size(); i++){
                    if (daftarMahasiswa.get(i).getNpm() == npmMhs){
                        JOptionPane.showMessageDialog(
                            null,
                            "NPM " + npmMhs + " sudah pernah ditambahkan sebelumnya", 
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                        jtfName.setText("");
                        jtfNpm.setText("");
                        return;
                    }
                }
                daftarMahasiswa.add(new Mahasiswa(namaMhs, npmMhs));
                JOptionPane.showMessageDialog(
                    null,
                    "Mahasiswa " + npmMhs + "-" + namaMhs + " berhasil ditambahkan",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);
                jtfName.setText("");
                jtfNpm.setText("");
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
    // Method set color
    public void color(JButton b){
        b.setBackground(Color.BLUE);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
    }
}
