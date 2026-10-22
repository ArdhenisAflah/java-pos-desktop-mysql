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
 * Modern Classic Sales Report Window for POS Desktop Application.
 * Author: Ardhenis Muhammad Aflah
 */
public class laporan extends javax.swing.JFrame {

    private DefaultTableModel model;
    private JLabel totalTrxLbl;
    private JLabel totalRevenueLbl;
    private JLabel totalQtyLbl;
    private JTable reportTable;
    private JScrollPane reportScroll;

    public laporan() {
        initComponents();
        setupModernLayout();
        loadReportData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Laporan Rekapitulasi Penjualan - Sistem Kasir");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void setupModernLayout() {
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(UITheme.BG_CANVAS);

        // Header
        JPanel header = UITheme.createTopHeader(
                "Laporan Rekapitulasi Penjualan",
                "Ringkasan transaksi kasir, akumulasi omset penjualan, dan cetak dokumen resmi",
                this,
                true
        );
        add(header, BorderLayout.NORTH);

        // Main Wrapper
        JPanel mainWrapper = new JPanel(new BorderLayout(0, 18));
        mainWrapper.setBackground(UITheme.BG_CANVAS);
        mainWrapper.setBorder(new EmptyBorder(20, 24, 20, 24));

        // Top Row: 3 Summary KPI Cards
        JPanel kpiGrid = new JPanel(new GridLayout(1, 3, 16, 0));
        kpiGrid.setBackground(UITheme.BG_CANVAS);

        // KPI 1: Total Transaksi
        totalTrxLbl = new JLabel("0 Transaksi");
        kpiGrid.add(createKpiCard("TOTAL TRANSAKSI", totalTrxLbl, UITheme.PRIMARY));

        // KPI 2: Total Pendapatan
        totalRevenueLbl = new JLabel("Rp 0");
        kpiGrid.add(createKpiCard("TOTAL OMSET PENJUALAN", totalRevenueLbl, UITheme.SUCCESS));

        // KPI 3: Total Unit Terjual
        totalQtyLbl = new JLabel("0 Unit");
        kpiGrid.add(createKpiCard("TOTAL BARANG TERJUAL", totalQtyLbl, UITheme.ACCENT));

        mainWrapper.add(kpiGrid, BorderLayout.NORTH);

        // Center: Report Table Card with Action Buttons
        JPanel tableCard = UITheme.createCard();
        tableCard.setLayout(new BorderLayout(0, 14));

        JPanel tableHeader = new JPanel(new BorderLayout());
        tableHeader.setBackground(UITheme.BG_CARD);

        JLabel tableTitle = new JLabel("Rincian Riwayat Transaksi");
        tableTitle.setFont(UITheme.FONT_SECTION);
        tableTitle.setForeground(UITheme.PRIMARY);

        // Right side buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionPanel.setBackground(UITheme.BG_CARD);

        JButton refreshBtn = new JButton("Segarkan Data");
        UITheme.styleOutlineButton(refreshBtn, UITheme.BORDER_COLOR, UITheme.TEXT_MUTED);
        refreshBtn.addActionListener(e -> loadReportData());

        JButton printBtn = new JButton("Cetak Dokumen JasperReports");
        UITheme.styleButton(printBtn, UITheme.PRIMARY, Color.WHITE);
        printBtn.addActionListener(e -> printJasperReport());

        actionPanel.add(refreshBtn);
        actionPanel.add(printBtn);

        tableHeader.add(tableTitle, BorderLayout.WEST);
        tableHeader.add(actionPanel, BorderLayout.EAST);
        tableCard.add(tableHeader, BorderLayout.NORTH);

        // Table
        String[] headers = {"ID Penjualan", "Tanggal", "Nama Kasir", "Nama Barang", "Jumlah (Qty)", "Total Pembayaran"};
        model = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        reportTable = new JTable(model);
        reportScroll = new JScrollPane(reportTable);
        UITheme.styleTable(reportTable, reportScroll);
        reportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int[] colWidths = {120, 100, 130, 150, 90, 130};
        for (int i = 0; i < colWidths.length && i < reportTable.getColumnCount(); i++) {
            reportTable.getColumnModel().getColumn(i).setPreferredWidth(colWidths[i]);
        }

        tableCard.add(reportScroll, BorderLayout.CENTER);
        mainWrapper.add(tableCard, BorderLayout.CENTER);

        add(UITheme.createPageScrollPane(mainWrapper), BorderLayout.CENTER);

        setSize(1040, 680);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    private JPanel createKpiCard(String title, JLabel valLabel, Color valColor) {
        JPanel card = UITheme.createCard();
        card.setLayout(new BorderLayout(0, 6));

        JLabel titleLbl = new JLabel(title);
        titleLbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        titleLbl.setForeground(UITheme.TEXT_MUTED);

        valLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        valLabel.setForeground(valColor);

        card.add(titleLbl, BorderLayout.NORTH);
        card.add(valLabel, BorderLayout.CENTER);
        return card;
    }

    private void printJasperReport() {
        ReportService.printSalesReport(this);
    }

    private void loadReportData() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            ResultSet res = con.createStatement().executeQuery(
                    "SELECT id_penjualan, tanggal, id_user, id_barang, jumlah, total FROM penjualan ORDER BY tanggal DESC, id_penjualan DESC"
            );
            model.setRowCount(0);

            long totalRevenue = 0;
            long totalQty = 0;
            int totalTrx = 0;

            while (res.next()) {
                String id = res.getString("id_penjualan");
                String tgl = res.getString("tanggal");
                String user = res.getString("id_user");
                String barang = res.getString("id_barang");
                String qtyStr = res.getString("jumlah");
                String totStr = res.getString("total");

                try {
                    long q = Long.parseLong(qtyStr);
                    totalQty += q;
                } catch (Exception e) {}

                try {
                    long t = Long.parseLong(totStr);
                    totalRevenue += t;
                } catch (Exception e) {}

                totalTrx++;
                model.addRow(new String[]{id, tgl, user, barang, qtyStr + " unit", UITheme.formatRupiah(Long.parseLong(totStr))});
            }

            totalTrxLbl.setText(totalTrx + " Transaksi");
            totalRevenueLbl.setText(UITheme.formatRupiah(totalRevenue));
            totalQtyLbl.setText(totalQty + " Unit");

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
                new laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
