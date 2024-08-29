package io.github.orionlibs.orion_uploader;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
abstract class AUploaderToServletResponse
{
    protected HttpServletResponse response;
    protected String MIMEType;
    protected String fileNameForClient;
    protected int contentLength;


    abstract void upload();


    void writeContentToResponse(OutputStream responseOutputStream, InputStream stream) throws IOException
    {
        contentLength = 0;
        int bytes = 0;
        while((bytes = stream.read()) != -1)
        {
            contentLength++;
            responseOutputStream.write(bytes);
        }
    }


    void writeContentToResponse(OutputStream responseOutputStream, String contentToUpload) throws IOException
    {
        contentLength = contentToUpload.length();
        responseOutputStream.write(contentToUpload.getBytes());
    }


    void finalise(OutputStream responseOutputStream, InputStream stream) throws IOException
    {
        responseOutputStream.flush();
        stream.close();
    }


    void finalise(OutputStream responseOutputStream) throws IOException
    {
        responseOutputStream.flush();
    }
}