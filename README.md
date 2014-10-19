# About
A very simple app for decoding a barcode screenshot from the clipboard, placing the decoded code back to the clipboard (if successfull).
I created this for personal usage when paying invoices.
I basically take a screenshot of a barcode (usually from a PDF file) into the clipboard, then run this app, and finally paste the
decoded code from the clipboard into a specific field on my online banking site.

# How to build
## Build the jar (with all dependencies)
```
mvn package
```
## Linux: Create a executable file (shell script with a generic binary payload)
- Credits to: https://coderwall.com/p/ssuaxa
```
cat src/main/scripts/stub.sh target/barcode-decoder-1.0-0.one-jar.jar > target/barcode-decoder.run
chmod +x target/barcode-decoder.run
```
- Run by ./target/barcode-decoder.run

## OSX: Bundle jar as a native app
- Requires you to clone and install this repo first from github:
https://github.com/federkasten/appbundler-plugin
- When appbuilder-plugin is properly installed (build.sh && mvn install), run
```
mvn package appbundle:bundle
```
- The app is being generated into the ls target/barcode-decoder-1.0.0 folder

## Windows: TODO 

# Eclipse
If you're using the Eclipse IDE, add the necessary eclipse project files by running
```
mvn eclipse:eclipse
```
# Credits
- zxing (https://github.com/zxing/zxing)
- appbundler-plugin (https://github.com/federkasten/appbundler-plugin)
