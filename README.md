# About
A very simple app for decoding an image from the clipboard, placing the decoded code (if successfull) back code to the clipboard.
I created this for personal usage when paying invoices.
I basically take a screenshot of a barcode (usually from a PDF file) into the clipboard, then run this app, and finally paste the
decoded code from the clipboard into a specific field on my online banking site.

# How to build
As a standalone jar:
```
mvn install
```
As an OSX app:
- Requires you to first clone and install this repo from github:
https://github.com/federkasten/appbundler-plugin
- When appbuilder-plugin is properly installed (build.sh && mvn install), run
```
mvn package appbundle:bundle
```
# How to use
Copy a barcode into the clipboard, then run this app:
```
java -jar barcode-decoder-1.0-jar-with-dependencies.jar
```
Or if using OSX, double click the "Barcode Decoder" app.
A popup will show if the decoding was successfull.
If successfull, the code is now in your clipboard, paste it wherever.

# Credits
- zxing (https://github.com/zxing/zxing) - is being used to decode the bitmap
- appbundler-plugin (https://github.com/federkasten/appbundler-plugin) - is being used to bundle the jar as a OSX app
