#!/bin/bash
mvn clean
mvn package -Plinux
cat src/main/scripts/stub.sh target/screenshot-decoder-1.0.0-full.jar > target/screenshot-decoder-linux.run
chmod +x target/screenshot-decoder-linux.run
cp target/screenshot-decoder-linux.run release/
cp target/screenshot-decoder-1.0.0-full.jar release/

#mvn package -Pwin
#cp target/screenshot-decoder-win.exe release/

