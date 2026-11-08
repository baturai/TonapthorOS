# Avar Calculator

Simple Java (Swing) calculator. The window title follows the system language.

## Build
    sudo apt install default-jdk
    ./build.sh

This creates `avar-calculator_1.0_all.deb`.

## Install (test)
    sudo apt install ./avar-calculator_1.0_all.deb

## Pre-install in the ISO (live-build)
Copy the .deb to `config/packages.chroot/` and add `default-jre`
to `config/package-lists/avar.list.chroot`.
