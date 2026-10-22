package penjualans;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Modern Classic Data Barang Window for POS Desktop Application.
 * Author: Ardhenis Muhammad Aflah
 */
public class barang extends javax.swing.JFrame {

    DefaultTableModel dfm;
    private JLabel countBadge;

    public barang() {
        initComponents();
        setupModernLayout();

        String[] headers = {"ID Barang", "Nama Barang", "Harga Satuan", "Stok Tersedia"};
        dfm = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabel.setModel(dfm);
        UITheme.styleTable(tabel, jScrollPane1);
        tampilkan();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        idbarang = new javax.swing.JTextField();
        namabarang = new javax.swing.JTextField();
        harga = new javax.swing.JTextField();
        stok = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        tambah = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        hapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Kelola Data Barang - Sistem Kasir & Penjualan");

        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        tambah.setText("Simpan Barang");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        ubah.setText("Perbarui");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void setupModernLayout() {
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(UITheme.BG_CANVAS);

        // Header
        JPanel header = UITheme.createTopHeader(
                "Manajemen Data Barang",
                "Kelola katalog produk, pembaruan harga satuan, dan kontrol ketersediaan stok",
                this,
                true
        );
        add(header, BorderLayout.NORTH);

        // Center Content: Left Form Card + Right Table Card
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(UITheme.BG_CANVAS);
        content.setBorder(new EmptyBorder(20, 24, 20, 24));
        GridBagConstraints gbc = new GridBagConstraints();

        // --- LEFT CARD: Input Form ---
        JPanel formCard = UITheme.createCard();
        formCard.setLayout(new GridBagLayout());
        GridBagConstraints fcGbc = new GridBagConstraints();
        fcGbc.fill = GridBagConstraints.HORIZONTAL;
        fcGbc.insets = new Insets(0, 0, 10, 0);
        fcGbc.gridx = 0;
        int row = 0;

        JLabel formTitle = new JLabel("Form Input Produk");
        formTitle.setFont(UITheme.FONT_SECTION);
        formTitle.setForeground(UITheme.PRIMARY);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 16, 0);
        formCard.add(formTitle, fcGbc);

        // ID Barang
        jLabel2.setText("Kode / ID Barang");
        jLabel2.setFont(UITheme.FONT_LABEL);
        jLabel2.setForeground(UITheme.TEXT_DARK);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(jLabel2, fcGbc);

        UITheme.styleTextField(idbarang);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 12, 0);
        formCard.add(idbarang, fcGbc);

        // Nama Barang
        jLabel3.setText("Nama Barang / Produk");
        jLabel3.setFont(UITheme.FONT_LABEL);
        jLabel3.setForeground(UITheme.TEXT_DARK);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(jLabel3, fcGbc);

        UITheme.styleTextField(namabarang);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 12, 0);
        formCard.add(namabarang, fcGbc);

        // Harga
        jLabel5.setText("Harga Satuan (Rp)");
        jLabel5.setFont(UITheme.FONT_LABEL);
        jLabel5.setForeground(UITheme.TEXT_DARK);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(jLabel5, fcGbc);

        UITheme.styleTextField(harga);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 12, 0);
        formCard.add(harga, fcGbc);

        // Stok
        jLabel4.setText("Jumlah Stok Tersedia (Unit)");
        jLabel4.setFont(UITheme.FONT_LABEL);
        jLabel4.setForeground(UITheme.TEXT_DARK);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(jLabel4, fcGbc);

        UITheme.styleTextField(stok);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 18, 0);
        formCard.add(stok, fcGbc);

        // Buttons Grid
        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        btnPanel.setBackground(UITheme.BG_CARD);

        UITheme.styleButton(tambah, UITheme.SUCCESS, Color.WHITE);
        UITheme.styleButton(ubah, UITheme.PRIMARY, Color.WHITE);
        UITheme.styleButton(hapus, UITheme.DANGER, Color.WHITE);

        JButton resetBtn = new JButton("Reset");
        UITheme.styleOutlineButton(resetBtn, UITheme.BORDER_COLOR, UITheme.TEXT_MUTED);
        resetBtn.addActionListener(e -> resetForm());

        btnPanel.add(tambah);
        btnPanel.add(ubah);
        btnPanel.add(hapus);
        btnPanel.add(resetBtn);

        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 0, 0);
        formCard.add(btnPanel, fcGbc);

        // Add Left Card
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.35;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 18);
        content.add(formCard, gbc);

        // --- RIGHT CARD: Table & List ---
        JPanel tableCard = UITheme.createCard();
        tableCard.setLayout(new BorderLayout(0, 12));

        JPanel tableTop = new JPanel(new BorderLayout());
        tableTop.setBackground(UITheme.BG_CARD);

        JLabel tableTitle = new JLabel("Daftar Inventaris Produk");
        tableTitle.setFont(UITheme.FONT_SECTION);
        tableTitle.setForeground(UITheme.PRIMARY);

        countBadge = new JLabel("Total Produk: 0 items");
        countBadge.setFont(UITheme.FONT_LABEL);
        countBadge.setForeground(UITheme.ACCENT);

        tableTop.add(tableTitle, BorderLayout.WEST);
        tableTop.add(countBadge, BorderLayout.EAST);
        tableCard.add(tableTop, BorderLayout.NORTH);

        tableCard.add(jScrollPane1, BorderLayout.CENTER);

        gbc.gridx = 1;
        gbc.weightx = 0.65;
        gbc.insets = new Insets(0, 0, 0, 0);
        content.add(tableCard, gbc);

        add(content, BorderLayout.CENTER);

        setSize(1000, 640);
        setLocationRelativeTo(null);
    }

    private void resetForm() {
        idbarang.setText("");
        namabarang.setText("");
        harga.setText("");
        stok.setText("");
        tabel.clearSelection();
    }

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        if (idbarang.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan pilih barang yang ingin diperbarui dari tabel.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            java.sql.PreparedStatement ps = koneksi.prepareStatement("UPDATE barang SET nama_barang=?, harga=?, stok=? WHERE id_barang=?");
            ps.setString(1, namabarang.getText().trim());
            ps.setString(2, harga.getText().trim());
            ps.setString(3, stok.getText().trim());
            ps.setString(4, idbarang.getText().trim());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data barang berhasil diperbarui.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            tampilkan();
            resetForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ubahActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        int i = tabel.getSelectedRow();
        if (i > -1) {
            idbarang.setText(dfm.getValueAt(i, 0).toString());
            namabarang.setText(dfm.getValueAt(i, 1).toString());
            harga.setText(dfm.getValueAt(i, 2).toString());
            stok.setText(dfm.getValueAt(i, 3).toString());
        }
    }//GEN-LAST:event_tabelMouseClicked

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        if (idbarang.getText().trim().isEmpty() || namabarang.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID Barang dan Nama Barang tidak boleh kosong.", "Input Tidak Lengkap", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection sql = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            java.sql.PreparedStatement ps = sql.prepareStatement("INSERT INTO barang (id_barang, nama_barang, harga, stok) VALUES (?, ?, ?, ?)");
            ps.setString(1, idbarang.getText().trim());
            ps.setString(2, namabarang.getText().trim());
            ps.setString(3, harga.getText().trim());
            ps.setString(4, stok.getText().trim());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Barang baru berhasil ditambahkan ke inventaris.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            tampilkan();
            resetForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data barang:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tambahActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        if (idbarang.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih barang yang ingin dihapus dari tabel.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus barang '" + namabarang.getText() + "'?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection sql = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
                java.sql.PreparedStatement ps = sql.prepareStatement("DELETE FROM barang WHERE id_barang=?");
                ps.setString(1, idbarang.getText().trim());
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data barang berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                tampilkan();
                resetForm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void tampilkan() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            ResultSet res = con.createStatement().executeQuery("SELECT id_barang, nama_barang, harga, stok FROM barang ORDER BY id_barang ASC");
            dfm.setRowCount(0);
            int count = 0;
            while (res.next()) {
                String[] data = {res.getString(1), res.getString(2), res.getString(3), res.getString(4)};
                dfm.addRow(data);
                count++;
            }
            if (countBadge != null) {
                countBadge.setText("Total Produk: " + count + " items");
            }
        } catch (SQLException e) {
            // DB connection handling
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // fallback
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hapus;
    private javax.swing.JTextField harga;
    private javax.swing.JTextField idbarang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField namabarang;
    private javax.swing.JTextField stok;
    private javax.swing.JTable tabel;
    private javax.swing.JButton tambah;
    private javax.swing.JButton ubah;
    // End of variables declaration//GEN-END:variables
}
