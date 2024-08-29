package io.github.orionlibs.orion_uploader;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
class ServletResponseConfiguration
{
    private HttpServletResponse response;
    private String MIMEType;
    private String fileNameForClient;
    private int contentLength;
}