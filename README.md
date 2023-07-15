# Orion Downloader
Service allowing the downloading of files.

Please check the wiki

https://github.com/orionlibs/downloader/wiki

You can import the library by using:
```xml
<dependency>
    <groupId>io.github.orionlibs</groupId>
    <artifactId>downloader</artifactId>
    <version>1.0.0</version>
</dependency>
```

To use this service, you can do:
```java
DownloadService.download("http://example.com/file.zip", "C:/downloads");
```