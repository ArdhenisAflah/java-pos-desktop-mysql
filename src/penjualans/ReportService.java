package penjualans;

import java.awt.Component;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 * ReportService - Safe invocation of JasperReports with graceful fallbacks.
 * Author: Ardhenis Muhammad Aflah
 */
public class ReportService {

    /**
     * Safely opens and displays the JasperReports sales report without hard-linking
     * Jasper classes at startup. This prevents NoClassDefFoundError if libraries are missing.
     */
    public static void printSalesReport(Component parent) {
        try {
            // Check if JasperReports engine is present on classpath
            Class<?> fillManagerClass = Class.forName("net.sf.jasperreports.engine.JasperFillManager");
            Class<?> viewerClass = Class.forName("net.sf.jasperreports.view.JasperViewer");
            Class<?> printClass = Class.forName("net.sf.jasperreports.engine.JasperPrint");

            // Load compiled report stream
            InputStream reportStream = ReportService.class.getResourceAsStream("reportPenjualan.jasper");
            if (reportStream == null) {
                // Fallback: try loading from class loader root
                reportStream = ReportService.class.getClassLoader().getResourceAsStream("penjualans/reportPenjualan.jasper");
            }

            if (reportStream == null) {
                JOptionPane.showMessageDialog(parent,
                        "Berkas template laporan ('reportPenjualan.jasper') tidak ditemukan dalam classpath.",
                        "Template Laporan Tidak Ditemukan",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");

            // Reflective call: JasperFillManager.fillReport(InputStream, Map, Connection)
            Method fillMethod = fillManagerClass.getMethod("fillReport", InputStream.class, Map.class, Connection.class);
            Object jasperPrint = fillMethod.invoke(null, reportStream, null, con);

            // Reflective call: JasperViewer.viewReport(JasperPrint, boolean isExitOnClose)
            Method viewMethod = viewerClass.getMethod("viewReport", printClass, boolean.class);
            viewMethod.invoke(null, jasperPrint, false);

        } catch (ClassNotFoundException | NoClassDefFoundError ex) {
            JOptionPane.showMessageDialog(parent,
                    "Pustaka JasperReports tidak ditemukan pada classpath saat ini.\n\n" +
                    "Detail dependensi yang dibutuhkan: net.sf.jasperreports (ada di dalam folder 'lib/*').\n" +
                    "Jalankan aplikasi dengan menyertakan folder lib:\n" +
                    "java -cp \"lib/*:build/classes\" penjualans.login",
                    "Pustaka JasperReports Tidak Ditemukan",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parent,
                    "Terjadi kesalahan saat memproses laporan penjualan:\n" + ex.getMessage(),
                    "Laporan Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
