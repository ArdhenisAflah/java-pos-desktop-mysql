# Java Point of Sales (POS) Desktop App with MySQL

A Desktop Point of Sales (Kasir & Penjualan) application built with Java Swing and MySQL, developed as part of vocational high school software engineering coursework.

## Features

- **Authentication System**: Secure login for operators and administrators.
- **Master Data Barang**:
  - Add, update, delete, and view product inventory.
  - Interactive table selection and real-time updates.
- **User Management**:
  - Add, edit, remove cashier/admin accounts.
- **Sales Transaction (Penjualan)**:
  - Select transaction dates via calendar chooser.
  - Dynamic user and product selection via dropdowns.
  - Automatic pricing and total calculation.
  - Full CRUD for sales records.
- **Reporting**:
  - Export and view printable sales reports using JasperReports integration.

## Architecture & Tech Stack

- **Language**: Java (JDK 8+)
- **GUI Framework**: Java Swing (NetBeans GUI Builder)
- **Database**: MySQL (`program_aplikasi`) via JDBC
- **Reporting Engine**: JasperReports & iReport
- **Calendar Component**: JCalendar

## Database Setup

1. Make sure MySQL / MariaDB (e.g. XAMPP, Laragon, or standalone MySQL) is running.
2. Import the provided schema file:
   ```bash
   mysql -u root -p < database.sql
   ```
   *(Or import `database.sql` via phpMyAdmin)*
3. Default credentials:
   - **Username**: `admin`
   - **Password**: `admin`

## Project Structure

```
.
├── database.sql               # Database schema & sample seed data
├── src/
│   └── penjualans/
│       ├── login.java         # Login window & authentication
│       ├── menu.java          # Main navigation dashboard
│       ├── barang.java        # Inventory management form
│       ├── user.java          # User account management form
│       ├── penjualan.java     # Transaction processing & report invocation
│       ├── laporan.java       # Report viewing interface
│       ├── reportPenjualan.jrxml  # JasperReport XML template
│       └── reportPenjualan.jasper # Compiled JasperReport binary
├── nbproject/                 # NetBeans project configuration
└── build.xml                  # Ant build script
```

## Running the Application

### Option 1: NetBeans IDE
1. Open NetBeans IDE.
2. Select **File > Open Project** and navigate to this repository folder.
3. Ensure MySQL server is active.
4. Add the required libraries to the project classpath if prompted (`mysql-connector-j`, `jcalendar-1.4.jar`, JasperReports libraries).
5. Right-click project and select **Run** (or press `F6`).

### Option 2: Command Line (with Ant)
```bash
ant compile
ant run
```

## License
Open source for educational and portfolio reference.
