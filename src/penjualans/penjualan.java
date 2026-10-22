package penjualans;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Modern Classic Cashier & Sales Transaction Window for POS Desktop Application.
 * Author: Ardhenis Muhammad Aflah
 */
public class penjualan extends javax.swing.JFrame {

    private String tgl = "";
    private DefaultTableModel dfm;
    private JLabel totalDisplayLabel;
    private JLabel trxCountBadge;

    public penjualan() {
        initComponents();
        setupModernLayout();

        String[] headers = {"ID Penjualan", "Tanggal", "Nama Kasir", "Nama Barang", "Harga Satuan", "Jumlah", "Total"};
        dfm = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabel.setModel(dfm);
        UITheme.styleTable(tabel, jScrollPane1);

        // Set default date to today
        SimpleDateFormat format_tanggal = new SimpleDateFormat("yyyy-MM-dd");
        tgl = format_tanggal.format((Date) tanggal.getValue());

        combobox();
        combobarang();
        tampilkan();
        autoGenerateId();
    }

    private void autoGenerateId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        idpenjualan.setText("TRX-" + sdf.format(new Date()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        idpenjualan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        combobox = new javax.swing.JComboBox<>();
        tanggal = new javax.swing.JSpinner(new javax.swing.SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        javax.swing.JSpinner.DateEditor de = new javax.swing.JSpinner.DateEditor(tanggal, "yyyy-MM-dd");
        tanggal.setEditor(de);

        jLabel5 = new javax.swing.JLabel();
        combobarang = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        harga = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        total = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        tambah = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Transaksi Kasir & Penjualan - Sistem Kasir");

        combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Kasir / User --" }));

        combobarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Produk Barang --" }));
        combobarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobarangActionPerformed(evt);
            }
        });

        jButton1.setText("Hitung Total");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        tambah.setText("Simpan Transaksi");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        edit.setText("Perbarui");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        cetak.setText("Cetak Struk");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
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
                "Transaksi Kasir & Penjualan",
                "Input order penjualan barang, hitung total otomatis, dan cetak struk transaksi",
                this,
                true
        );
        add(header, BorderLayout.NORTH);

        // Main Grid: Left (Form + Total Card) & Right (Table Card)
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(UITheme.BG_CANVAS);
        content.setBorder(new EmptyBorder(18, 24, 18, 24));
        GridBagConstraints gbc = new GridBagConstraints();

        // --- LEFT COLUMN: Form + Payment Summary ---
        JPanel leftCol = new JPanel();
        leftCol.setLayout(new BoxLayout(leftCol, BoxLayout.Y_AXIS));
        leftCol.setBackground(UITheme.BG_CANVAS);

        // Card 1: Input Form
        JPanel formCard = UITheme.createCard();
        formCard.setLayout(new GridBagLayout());
        GridBagConstraints fcGbc = new GridBagConstraints();
        fcGbc.fill = GridBagConstraints.HORIZONTAL;
        fcGbc.insets = new Insets(0, 0, 8, 0);
        fcGbc.gridx = 0;
        int row = 0;

        JLabel formTitle = new JLabel("Detail Transaksi Kasir");
        formTitle.setFont(UITheme.FONT_SECTION);
        formTitle.setForeground(UITheme.PRIMARY);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 14, 0);
        formCard.add(formTitle, fcGbc);

        // ID Transaksi
        jLabel2.setText("ID Transaksi");
        jLabel2.setFont(UITheme.FONT_LABEL);
        jLabel2.setForeground(UITheme.TEXT_DARK);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(jLabel2, fcGbc);

        UITheme.styleTextField(idpenjualan);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 10, 0);
        formCard.add(idpenjualan, fcGbc);

        // Tanggal
        jLabel3.setText("Tanggal Transaksi (yyyy-MM-dd)");
        jLabel3.setFont(UITheme.FONT_LABEL);
        jLabel3.setForeground(UITheme.TEXT_DARK);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(jLabel3, fcGbc);

        tanggal.setFont(UITheme.FONT_INPUT);
        tanggal.setBackground(UITheme.BG_CARD);
        tanggal.setBorder(new CompoundBorder(
                new LineBorder(UITheme.BORDER_COLOR, 1, true),
                new EmptyBorder(4, 6, 4, 6)
        ));
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 10, 0);
        formCard.add(tanggal, fcGbc);

        // User / Kasir
        jLabel4.setText("Nama Kasir / Petugas");
        jLabel4.setFont(UITheme.FONT_LABEL);
        jLabel4.setForeground(UITheme.TEXT_DARK);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(jLabel4, fcGbc);

        UITheme.styleComboBox(combobox);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 10, 0);
        formCard.add(combobox, fcGbc);

        // Barang
        jLabel5.setText("Pilih Produk / Barang");
        jLabel5.setFont(UITheme.FONT_LABEL);
        jLabel5.setForeground(UITheme.TEXT_DARK);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(jLabel5, fcGbc);

        UITheme.styleComboBox(combobarang);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 10, 0);
        formCard.add(combobarang, fcGbc);

        // Harga & Jumlah side-by-side
        JPanel priceQtyPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        priceQtyPanel.setBackground(UITheme.BG_CARD);

        JPanel priceBlock = new JPanel(new BorderLayout(0, 4));
        priceBlock.setBackground(UITheme.BG_CARD);
        jLabel6.setText("Harga Satuan (Rp)");
        jLabel6.setFont(UITheme.FONT_LABEL);
        jLabel6.setForeground(UITheme.TEXT_DARK);
        UITheme.styleTextField(harga);
        priceBlock.add(jLabel6, BorderLayout.NORTH);
        priceBlock.add(harga, BorderLayout.CENTER);

        JPanel qtyBlock = new JPanel(new BorderLayout(0, 4));
        qtyBlock.setBackground(UITheme.BG_CARD);
        jLabel7.setText("Jumlah (Qty)");
        jLabel7.setFont(UITheme.FONT_LABEL);
        jLabel7.setForeground(UITheme.TEXT_DARK);
        UITheme.styleTextField(jumlah);
        qtyBlock.add(jLabel7, BorderLayout.NORTH);
        qtyBlock.add(jumlah, BorderLayout.CENTER);

        priceQtyPanel.add(priceBlock);
        priceQtyPanel.add(qtyBlock);

        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 10, 0);
        formCard.add(priceQtyPanel, fcGbc);

        // Cek Total Button + Field
        JPanel calcRow = new JPanel(new BorderLayout(8, 0));
        calcRow.setBackground(UITheme.BG_CARD);
        UITheme.styleTextField(total);
        total.setEditable(false);
        UITheme.styleButton(jButton1, UITheme.ACCENT, Color.WHITE);
        calcRow.add(total, BorderLayout.CENTER);
        calcRow.add(jButton1, BorderLayout.EAST);

        jLabel8.setText("Subtotal Perhitungan (Rp)");
        jLabel8.setFont(UITheme.FONT_LABEL);
        jLabel8.setForeground(UITheme.TEXT_DARK);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(jLabel8, fcGbc);

        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 14, 0);
        formCard.add(calcRow, fcGbc);

        leftCol.add(formCard);
        leftCol.add(Box.createRigidArea(new Dimension(0, 12)));

        // Card 2: Total Payment Banner & Action Buttons
        JPanel summaryCard = new JPanel(new BorderLayout(0, 10));
        summaryCard.setBackground(UITheme.PRIMARY);
        summaryCard.setBorder(new CompoundBorder(
                new LineBorder(UITheme.PRIMARY, 1, true),
                new EmptyBorder(14, 18, 14, 18)
        ));

        JLabel sumTitle = new JLabel("TOTAL TAGIHAN");
        sumTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        sumTitle.setForeground(new Color(148, 163, 184)); // Muted slate

        totalDisplayLabel = new JLabel("Rp 0");
        totalDisplayLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        totalDisplayLabel.setForeground(Color.WHITE);

        summaryCard.add(sumTitle, BorderLayout.NORTH);
        summaryCard.add(totalDisplayLabel, BorderLayout.CENTER);

        // Action Buttons Row
        JPanel actionRow = new JPanel(new GridLayout(1, 4, 8, 0));
        actionRow.setBackground(UITheme.BG_CANVAS);
        actionRow.setBorder(new EmptyBorder(10, 0, 0, 0));

        UITheme.styleButton(tambah, UITheme.SUCCESS, Color.WHITE);
        UITheme.styleButton(edit, UITheme.PRIMARY, Color.WHITE);
        UITheme.styleButton(hapus, UITheme.DANGER, Color.WHITE);
        UITheme.styleButton(cetak, UITheme.ACCENT, Color.WHITE);

        actionRow.add(tambah);
        actionRow.add(edit);
        actionRow.add(hapus);
        actionRow.add(cetak);

        leftCol.add(summaryCard);
        leftCol.add(actionRow);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.40;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 18);
        content.add(leftCol, gbc);

        // --- RIGHT COLUMN: Table of Transactions ---
        JPanel tableCard = UITheme.createCard();
        tableCard.setLayout(new BorderLayout(0, 12));

        JPanel tableHeader = new JPanel(new BorderLayout());
        tableHeader.setBackground(UITheme.BG_CARD);

        JLabel tableTitle = new JLabel("Riwayat Transaksi Penjualan");
        tableTitle.setFont(UITheme.FONT_SECTION);
        tableTitle.setForeground(UITheme.PRIMARY);

        trxCountBadge = new JLabel("Total: 0 transaksi");
        trxCountBadge.setFont(UITheme.FONT_LABEL);
        trxCountBadge.setForeground(UITheme.ACCENT);

        tableHeader.add(tableTitle, BorderLayout.WEST);
        tableHeader.add(trxCountBadge, BorderLayout.EAST);
        tableCard.add(tableHeader, BorderLayout.NORTH);

        tableCard.add(jScrollPane1, BorderLayout.CENTER);

        gbc.gridx = 1;
        gbc.weightx = 0.60;
        gbc.insets = new Insets(0, 0, 0, 0);
        content.add(tableCard, gbc);

        add(content, BorderLayout.CENTER);

        setSize(1080, 680);
        setLocationRelativeTo(null);
    }

    private void combobox() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM user ORDER BY nama_user ASC");
            while (res.next()) {
                combobox.addItem(res.getString("nama_user"));
            }
        } catch (SQLException e) {
            // DB handling
        }
    }

    private void combobarang() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM barang ORDER BY nama_barang ASC");
            while (res.next()) {
                combobarang.addItem(res.getString("nama_barang"));
            }
        } catch (SQLException e) {
            // DB handling
        }
    }

    private void resetForm() {
        autoGenerateId();
        tanggal.setValue(new Date());
        combobox.setSelectedIndex(0);
        combobarang.setSelectedIndex(0);
        harga.setText("");
        jumlah.setText("");
        total.setText("");
        totalDisplayLabel.setText("Rp 0");
        tabel.clearSelection();
    }

    private void combobarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobarangActionPerformed
        if (combobarang.getSelectedIndex() <= 0) {
            harga.setText("");
            return;
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            PreparedStatement ps = con.prepareStatement("SELECT harga FROM barang WHERE nama_barang = ?");
            ps.setString(1, combobarang.getSelectedItem().toString());
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                harga.setText(res.getString("harga"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(penjualan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_combobarangActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int a = Integer.parseInt(harga.getText().trim());
            int b = Integer.parseInt(jumlah.getText().trim());
            int cektotal = a * b;
            total.setText(String.valueOf(cektotal));
            totalDisplayLabel.setText(UITheme.formatRupiah(cektotal));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Pastikan harga dan jumlah terisi dengan angka valid.", "Input Tidak Valid", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        if (combobox.getSelectedIndex() <= 0 || combobarang.getSelectedIndex() <= 0 || jumlah.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan lengkapi data kasir, produk, dan jumlah belanja.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        jButton1ActionPerformed(null); // Ensure total is calculated

        SimpleDateFormat format_tanggal = new SimpleDateFormat("yyyy-MM-dd");
        tgl = format_tanggal.format((Date) tanggal.getValue());

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            PreparedStatement ps = con.prepareStatement("INSERT INTO penjualan (id_penjualan, tanggal, id_user, id_barang, jumlah, total) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, idpenjualan.getText().trim());
            ps.setString(2, tgl);
            ps.setString(3, combobox.getSelectedItem().toString());
            ps.setString(4, combobarang.getSelectedItem().toString());
            ps.setString(5, jumlah.getText().trim());
            ps.setString(6, total.getText().trim());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Transaksi penjualan berhasil disimpan ke sistem.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            tampilkan();
            resetForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tambahActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        int i = tabel.getSelectedRow();
        if (i > -1) {
            idpenjualan.setText(dfm.getValueAt(i, 0).toString());
            try {
                Date format_tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(dfm.getValueAt(i, 1).toString());
                tanggal.setValue(format_tanggal);
            } catch (ParseException ex) {
                // Ignore parse error
            }
            combobox.setSelectedItem(dfm.getValueAt(i, 2));
            combobarang.setSelectedItem(dfm.getValueAt(i, 3));
            harga.setText(dfm.getValueAt(i, 4).toString());
            jumlah.setText(dfm.getValueAt(i, 5).toString());
            String totVal = dfm.getValueAt(i, 6).toString();
            total.setText(totVal);
            try {
                totalDisplayLabel.setText(UITheme.formatRupiah(Long.parseLong(totVal)));
            } catch (Exception e) {
                totalDisplayLabel.setText("Rp " + totVal);
            }
        }
    }//GEN-LAST:event_tabelMouseClicked

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        if (idpenjualan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih transaksi yang ingin diperbarui dari tabel.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        jButton1ActionPerformed(null);

        SimpleDateFormat format_tanggal = new SimpleDateFormat("yyyy-MM-dd");
        tgl = format_tanggal.format((Date) tanggal.getValue());

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            PreparedStatement ps = con.prepareStatement("UPDATE penjualan SET id_user=?, tanggal=?, id_barang=?, jumlah=?, total=? WHERE id_penjualan=?");
            ps.setString(1, combobox.getSelectedItem().toString());
            ps.setString(2, tgl);
            ps.setString(3, combobarang.getSelectedItem().toString());
            ps.setString(4, jumlah.getText().trim());
            ps.setString(5, total.getText().trim());
            ps.setString(6, idpenjualan.getText().trim());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data transaksi penjualan berhasil diperbarui.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            tampilkan();
            resetForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui transaksi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        int i = tabel.getSelectedRow();
        if (i < 0) {
            JOptionPane.showMessageDialog(this, "Pilih transaksi yang ingin dihapus dari tabel.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String trxId = dfm.getValueAt(i, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin membatalkan & menghapus transaksi ID " + trxId + "?",
                "Konfirmasi Hapus Transaksi",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
                PreparedStatement ps = con.prepareStatement("DELETE FROM penjualan WHERE id_penjualan = ?");
                ps.setString(1, trxId);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data transaksi berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                tampilkan();
                resetForm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus transaksi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        ReportService.printSalesReport(this);
    }//GEN-LAST:event_cetakActionPerformed

    private void tampilkan() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            ResultSet res = con.createStatement().executeQuery(
                    "SELECT p.id_penjualan, p.tanggal, p.id_user, p.id_barang, b.harga, p.jumlah, p.total " +
                    "FROM penjualan p LEFT JOIN barang b ON p.id_barang = b.nama_barang " +
                    "ORDER BY p.tanggal DESC, p.id_penjualan DESC"
            );
            dfm.setRowCount(0);
            int count = 0;
            while (res.next()) {
                String hargaSatuan = res.getString("harga") != null ? res.getString("harga") : "-";
                String[] data = {
                    res.getString("id_penjualan"),
                    res.getString("tanggal"),
                    res.getString("id_user"),
                    res.getString("id_barang"),
                    hargaSatuan,
                    res.getString("jumlah"),
                    res.getString("total")
                };
                dfm.addRow(data);
                count++;
            }
            if (trxCountBadge != null) {
                trxCountBadge.setText("Total: " + count + " transaksi tercatat");
            }
        } catch (SQLException e) {
            // Error handling
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
                new penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cetak;
    private javax.swing.JComboBox<String> combobarang;
    private javax.swing.JComboBox<String> combobox;
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField harga;
    private javax.swing.JTextField idpenjualan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTable tabel;
    private javax.swing.JButton tambah;
    private javax.swing.JSpinner tanggal;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
