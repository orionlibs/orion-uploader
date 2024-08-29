package io.github.orionlibs.orion_uploader;

import java.io.IOException;
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
class TextUploaderToServletResponse extends AUploaderToServletResponse
{
    private String contentToUpload;


    void upload()
    {
        try(OutputStream responseOutputStream = response.getOutputStream())
        {
            writeContentToResponse(responseOutputStream, contentToUpload);
            ServletResponseConfigurator.configure(ServletResponseConfiguration.builder()
                            .response(response)
                            .MIMEType(MIMEType)
                            .fileNameForClient(fileNameForClient)
                            .contentLength(contentLength)
                            .build());
            finalise(responseOutputStream);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}