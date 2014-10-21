# About
TODO

# Linux
## Build
```
mvn package -Plinux
```

## Run
```
java -jar target/screenshot-decoder-1.0.0-full.jar
```
## Optional: wrap the jar into one single executable shell script
```
cat src/main/scripts/stub.sh target/screenshot-decoder-1.0.0-full.jar > target/screenshot-decoder-linux.run
chmod +x target/screenshot-decoder-linux.run
```
- Run by ./target/screenshot-decoder.run (or double click if your distro supports it). 
- If you get errors when running from the command line, you might have to set your DISPLAY env variable
```
export DISPLAY=":0.0"
```

# OSX
## Build a bundled native app
- Requires you to clone and install this repo from github: https://github.com/federkasten/appbundler-plugin (follow the README for instructions)
- After you've installed the appbuilder-plugin, run
```
mvn package appbundle:bundle -Posx
```
- The app "Screenshot decoder" is being generated into the target/screenshot-decoder-1.0.0/

## Windows
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
# Credits
- zxing (https://github.com/zxing/zxing)
- appbundler-plugin (https://github.com/federkasten/appbundler-plugin)
