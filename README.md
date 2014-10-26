# About
A simple app to decode barcodes (or QR codes). 
Start the app, draw a selection around the code and click "Decode".
If successful, the decoded code is put into the system clipboard

# Linux
## Build
```
mvn package -Plinux
```

## Run
```
java -jar target/screenshot-decoder-1.0.0-full.jar
```
Notice that due to a clipboard persistence bug in X11-based operating systems, the decoded string
will not stay in the clipboard once the application quits. One workaraound is to not click "Ok"
on the popup before you've pasted the decoded code from the clipboard.

## Optional I: Wrap the jar into one single executable shell script
```
cat src/main/scripts/stub.sh target/screenshot-decoder-1.0.0-full.jar > target/screenshot-decoder-linux.run
chmod +x target/screenshot-decoder-linux.run
```
- Run by ./target/screenshot-decoder.run (or double click if your distro supports it). 
- If you get errors when running from the command line, you might have to set your DISPLAY env variable
```
export DISPLAY=":0.0"
```
## Optional II: Add a launcher (Ubuntu Unity etc)
- Edit the release/screenshot-decoder-linux.desktop file and fix the following paths:
```
Exec=/home/leerikss/dev/screenshot-decoder/release/screenshot-decoder-linux.run
Icon=/home/leerikss/dev/screenshot-decoder/src/main/resources/icons/screenshot-decoder.png
```
- Drag the "Screenshot Decoder" launcher into your launchbar

# OSX
## Build a bundled native app
- Requires you to clone and install this repo from github: https://github.com/federkasten/appbundler-plugin (follow the README for instructions)
- After you've installed the appbuilder-plugin, run
```
mvn package appbundle:bundle -Posx
```
- The app "Screenshot decoder" is being generated into the target/screenshot-decoder-1.0.0/

# Windows
- Run
```
mvn package -Pwin
```
- This generates the "screenshot-decoder-win.exe" file under the target/ folder

# Eclipse
If you're using the Eclipse IDE, add the necessary eclipse project files by running
```
mvn eclipse:eclipse
```

# Release
Prebuilt binaries for windows,OSX and Linux can be found in the release/ folder

# Credits
- zxing (https://github.com/zxing/zxing)
- appbundler-plugin (https://github.com/federkasten/appbundler-plugin)
- http://stackoverflow.com/questions/13948122/drawing-a-bounding-rectangle-to-select-what-area-to-record