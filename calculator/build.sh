#!/bin/sh
# Builds avar-calculator_1.0_all.deb
# Run on Debian (or WSL). Needs: default-jdk and dpkg-deb
set -e

rm -rf build build_classes
mkdir -p build_classes build/DEBIAN build/usr/share/java build/usr/share/applications

javac --release 11 -d build_classes Calculator.java
jar cfm build/usr/share/java/calculator.jar manifest.txt -C build_classes .

cp DEBIAN/control build/DEBIAN/control
cp calculator.desktop build/usr/share/applications/calculator.desktop

dpkg-deb --root-owner-group --build build avar-calculator_1.0_all.deb
echo "Done: avar-calculator_1.0_all.deb"
