package io.github.orionlibs.downloader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class DownloadService
{
    public static BufferedInputStream downloadFileAsStream(String URLResourceToDownload) throws IOException
    {
        Response response = Jsoup.connect(URLResourceToDownload).followRedirects(true).ignoreHttpErrors(true).ignoreContentType(true).execute();
        return response.bodyStream();
    }


    public static void download(String urlResourceToDownload, String fileToDownloadTo) throws IOException
    {
        if(urlResourceToDownload == null || urlResourceToDownload.isEmpty())
        {
            throw new IllegalArgumentException("urlResourceToDownload is null/empty.");
        }
        if(fileToDownloadTo == null || fileToDownloadTo.isEmpty())
        {
            throw new IllegalArgumentException("fileToDownloadTo is null/empty.");
        }
        BufferedInputStream input = downloadFileAsStream(urlResourceToDownload);
        ByteArrayOutputStream output = null;
        OutputStream fos = null;
        try
        {
            output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while((n = input.read(buf)) != -1)
            {
                output.write(buf, 0, n);
            }
            fos = Files.newOutputStream(Paths.get(fileToDownloadTo));
            fos.write(output.toByteArray());
        }
        finally
        {
            CloseResourceTask.closeResource(input);
            CloseResourceTask.closeResource(output);
            CloseResourceTask.closeResource(fos);
        }
    }
}
