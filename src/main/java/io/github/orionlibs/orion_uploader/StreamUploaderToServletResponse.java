package io.github.orionlibs.orion_uploader;

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
class StreamUploaderToServletResponse extends AUploaderToServletResponse
{
    private InputStream stream;


    void upload()
    {
        try(OutputStream responseOutputStream = response.getOutputStream())
        {
            writeContentToResponse(responseOutputStream, stream);
            ServletResponseConfigurator.configure(ServletResponseConfiguration.builder()
                            .response(response)
                            .MIMEType(MIMEType)
                            .fileNameForClient(fileNameForClient)
                            .contentLength(contentLength)
                            .build());
            finalise(responseOutputStream, stream);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}