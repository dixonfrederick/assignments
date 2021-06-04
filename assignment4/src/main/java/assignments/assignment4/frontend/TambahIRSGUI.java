package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class TambahIRSGUI {

    public TambahIRSGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        // Judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Tambah IRS");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        // Label dan ComboBox npm
        JLabel npm = new JLabel();
        npm.setText("Pilih NPM");
        npm.setHorizontalAlignment(JLabel.CENTER);
        npm.setFont(SistemAkademikGUI.fontGeneral);
        JComboBox jcbNpm = new JComboBox(sortNPM(daftarMahasiswa));
        // Label dan ComboBox matkul
        JLabel matkul = new JLabel();
        matkul.setText("Pilih Nama Matkul");
        matkul.setHorizontalAlignment(JLabel.CENTER);
        matkul.setFont(SistemAkademikGUI.fontGeneral);
        JComboBox jcbName = new JComboBox(sortNama(daftarMataKuliah));
        // Button
        JButton jbtAdd = new JButton("Tambahkan");
        JButton jbtReturn = new JButton("Kembali");
        // Panel
        JPanel p = new JPanel(new GridLayout(7,1,0,17));
        JPanel pMain = new JPanel(new BorderLayout());

        // Menambahkan komponen pada panel
        p.add(titleLabel);
        p.add(npm);
        p.add(jcbNpm);
        p.add(matkul);
        p.add(jcbName);
        p.add(jbtAdd);
        p.add(jbtReturn);

        // Listener
        jbtAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle untuk ComboBox kosong
                if (jcbNpm.getSelectedItem()==null || jcbName.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(
                        null,
                        "Mohon isi seluruh Field",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                long npmMhs = (long)jcbNpm.getSelectedItem();
                String namaMatkul = (String)jcbName.getSelectedItem();
                
                Mahasiswa mhs = getMahasiswa(npmMhs, daftarMahasiswa);
                MataKuliah matkul = getMataKuliah(namaMatkul, daftarMataKuliah);
                
                JOptionPane.showMessageDialog(
                            null,
                            mhs.addMatkul(matkul), 
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                jcbNpm.setSelectedIndex(0);
                jcbName.setSelectedIndex(0);
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
    // Sorting NPM
    public Long[] sortNPM(ArrayList<Mahasiswa> arr){
        Long[] res = new Long[arr.size()];
        for (int i = 0; i < arr.size(); i++){
            res[i] = arr.get(i).getNpm();
        }    
        for (int i = 0; i < arr.size(); i++){
            for (int j = i+1; j < arr.size(); j++){
                if (res[i] > res[j]){
                    Long temp = res[i];
                    res[i] = res[j];
                    res[j] = temp;
                }
            }
        }
        return res;
    }
    // Sorting nama
    public String[] sortNama(ArrayList<MataKuliah> arr){
        String[] res = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++){
            res[i] = arr.get(i).getNama();
        }    
        for (int i = 0; i < arr.size(); i++){
            for (int j = i+1; j < arr.size(); j++){
                if (res[i].compareToIgnoreCase(res[j]) > 0){
                    String temp = res[i];
                    res[i] = res[j];
                    res[j] = temp;
                }
            }
        }
        return res;
    }
    
    private MataKuliah getMataKuliah(String nama, ArrayList<MataKuliah> daftarMataKuliah) {

        for (MataKuliah mataKuliah : daftarMataKuliah) {
            if (mataKuliah.getNama().equals(nama)){
                return mataKuliah;
            }
        }
        return null;
    }

    private Mahasiswa getMahasiswa(long npm, ArrayList<Mahasiswa> daftarMahasiswa) {

        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            if (mahasiswa.getNpm() == npm){
                return mahasiswa;
            }
        }
        return null;
    }

}
