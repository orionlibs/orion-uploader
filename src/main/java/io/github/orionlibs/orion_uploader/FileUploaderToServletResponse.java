package io.github.orionlibs.orion_uploader;

import io.github.orionlibs.orion_file_system.file.FileService;
import java.io.File;
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
class FileUploaderToServletResponse extends AUploaderToServletResponse
{
    private String fileLocation;


    void upload()
    {
        File fileToUpload = new File(fileLocation);
        try(OutputStream responseOutputStream = response.getOutputStream())
        {
            InputStream fileInputStream = FileService.getFileAsInputStream(fileToUpload);
            writeContentToResponse(responseOutputStream, fileInputStream);
            ServletResponseConfigurator.configure(ServletResponseConfiguration.builder()
                            .response(response)
                            .MIMEType(MIMEType)
                            .fileNameForClient(fileNameForClient)
                            .contentLength((int)fileToUpload.length())
                            .build());
            finalise(responseOutputStream, fileInputStream);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}