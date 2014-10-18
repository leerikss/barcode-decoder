# About
A simple app for decoding an image that is copied into the clipboard, placing the decoded back code to the clipboard.
I created this for personal usage when paying invoices using online banking services.
I basically take a screenshot of the barcode (usually from a PDF) into the clipboard, than run this app, then paste
the decoded code (now in clipboard) into a specific field on my online banking web page.

# How to build
As a standalone jar:
```
mvn install
```
As an OSX app
- Requires you to first clone and install this from github:
https://github.com/federkasten/appbundler-plugin
- When appbuilder-plugin is installed (build.sh && mvn install), run
```
mvn package appbundle:bundle
```
# How to user
Copy a barcode into the clipboard, then run this app:
```
java -jar barcode-decoder-1.0-jar-with-dependencies.jar
```
Or for OSX, just double click the "Barcode Decoder" app.
A popup will show if decoding was successfull.
If successfull, the code is now in your clipboard, paste it wherever.
