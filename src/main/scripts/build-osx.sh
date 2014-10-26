#!/bin/bash
mvn clean
mvn package appbundle:bundle -Posx
zip -r release/screenshot-decoder-osx.zip target/screenshot-decoder-1.0.0/Screenshot\ Decoder.app
cp -r target/screenshot-decoder-1.0.0/Screenshot\ Decoder.app /Applications/ 
