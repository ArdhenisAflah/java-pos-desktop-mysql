#!/usr/bin/env bash
set -euo pipefail

# Build automation script for POS Desktop Application
# Generates Fat JAR, Linux Runnable binary, Windows PE Executable, and Portable Zip Bundle.

VERSION="v1.0.0"
DIST_DIR="dist"
BUILD_DIR="build/classes"
TMP_DIR="/tmp/pos-build-$$"

echo "=== Building POS Desktop Application (${VERSION}) ==="

# 1. Compile Java source files
echo "[1/6] Compiling Java source files..."
mkdir -p "${BUILD_DIR}"
javac -cp "lib/*:src" -d "${BUILD_DIR}" $(find src -name "*.java")

# 2. Sync runtime resources
echo "[2/6] Syncing report templates and configuration..."
cp src/penjualans/*.jasper "${BUILD_DIR}/penjualans/" 2>/dev/null || true
cp src/penjualans/*.jrxml "${BUILD_DIR}/penjualans/" 2>/dev/null || true
cp src/jasperreports.properties "${BUILD_DIR}/" 2>/dev/null || true

# 3. Create standalone Fat JAR
echo "[3/6] Packaging standalone executable Fat JAR..."
rm -rf "${DIST_DIR}" && mkdir -p "${DIST_DIR}"
rm -rf "${TMP_DIR}" && mkdir -p "${TMP_DIR}/unpacked"

for jar in lib/*.jar; do
    unzip -qo "${jar}" -d "${TMP_DIR}/unpacked"
done

cp -r "${BUILD_DIR}"/* "${TMP_DIR}/unpacked/"
rm -rf "${TMP_DIR}/unpacked/META-INF"/*.SF "${TMP_DIR}/unpacked/META-INF"/*.DSA "${TMP_DIR}/unpacked/META-INF"/*.RSA

echo "Main-Class: penjualans.login" > "${TMP_DIR}/MANIFEST.MF"
jar cmf "${TMP_DIR}/MANIFEST.MF" "${DIST_DIR}/pos-system-${VERSION}.jar" -C "${TMP_DIR}/unpacked" .

# 4. Generate Linux runnable executable (self-executing binary stub)
echo "[4/6] Creating Linux self-executing runnable..."
cat << 'STUB_EOF' > "${TMP_DIR}/stub.sh"
#!/bin/sh
exec java -jar "$0" "$@"
STUB_EOF

cat "${TMP_DIR}/stub.sh" "${DIST_DIR}/pos-system-${VERSION}.jar" > "${DIST_DIR}/pos-system-${VERSION}-linux.run"
chmod +x "${DIST_DIR}/pos-system-${VERSION}-linux.run"

# 5. Generate Windows .exe executable via Launch4j
echo "[5/6] Building Windows executable (.exe)..."
cat << XML_EOF > "${TMP_DIR}/launch4j.xml"
<launch4jConfig>
  <dontWrapJar>false</dontWrapJar>
  <headerType>gui</headerType>
  <jar>${PWD}/${DIST_DIR}/pos-system-${VERSION}.jar</jar>
  <outfile>${PWD}/${DIST_DIR}/POS-System-${VERSION}.exe</outfile>
  <errTitle>POS System</errTitle>
  <chdir>.</chdir>
  <priority>normal</priority>
  <downloadUrl>https://adoptium.net/</downloadUrl>
  <jre>
    <path>%JAVA_HOME%;%PATH%</path>
    <bundledJre64Bit>false</bundledJre64Bit>
    <bundledJreAsFallback>false</bundledJreAsFallback>
    <minVersion>1.8.0</minVersion>
    <jdkPreference>preferJre</jdkPreference>
    <runtimeBits>64/32</runtimeBits>
  </jre>
</launch4jConfig>
XML_EOF

if [ -f "/tmp/launch4j/launch4j" ]; then
    /tmp/launch4j/launch4j "${TMP_DIR}/launch4j.xml"
else
    echo "Notice: Launch4j not found at /tmp/launch4j, skipping .exe generation."
fi

# 6. Create convenience launchers and bundle portable zip
echo "[6/6] Packaging portable zip distribution..."
cat << 'RUNNER_SH' > "${DIST_DIR}/run-pos.sh"
#!/usr/bin/env sh
DIR="$(cd "$(dirname "$0")" && pwd)"
exec java -jar "${DIR}/pos-system-v1.0.0.jar" "$@"
RUNNER_SH
chmod +x "${DIST_DIR}/run-pos.sh"

cat << 'RUNNER_BAT' > "${DIST_DIR}/run-pos.bat"
@echo off
start javaw -jar "%~dp0pos-system-v1.0.0.jar" %*
RUNNER_BAT

cat << 'README_TXT' > "${DIST_DIR}/README.txt"
Point of Sales (POS) Desktop Application - Release v1.0.0

REQUIREMENTS:
1. Java Runtime Environment (JRE/JDK 8 or higher) installed.
2. MySQL / MariaDB server running with database 'program_aplikasi'.
   (Import database.sql before launching).

HOW TO RUN:
- Windows (Native Executable):
  Double-click POS-System-v1.0.0.exe OR run-pos.bat

- Linux (Executable Binary):
  Double-click or run from terminal:
  ./pos-system-v1.0.0-linux.run

- Any Platform (Cross-Platform JAR):
  java -jar pos-system-v1.0.0.jar

DEFAULT CREDENTIALS:
Username: admin
Password: admin
README_TXT

cp database.sql "${DIST_DIR}/"

# Build zip archive
(cd "${DIST_DIR}" && zip -qr "pos-system-${VERSION}-portable.zip" \
    "POS-System-${VERSION}.exe" \
    "pos-system-${VERSION}-linux.run" \
    "pos-system-${VERSION}.jar" \
    "run-pos.sh" \
    "run-pos.bat" \
    "database.sql" \
    "README.txt" 2>/dev/null || zip -qr "pos-system-${VERSION}-portable.zip" *)

rm -rf "${TMP_DIR}"

echo "=== Build Complete ==="
ls -lh "${DIST_DIR}"
