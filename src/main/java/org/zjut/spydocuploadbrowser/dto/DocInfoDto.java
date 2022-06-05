package org.zjut.spydocuploadbrowser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.ServletRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocInfoDto {
    /**
     * "fileName": "string",
     * "filePath": "string",
     * "fileType": "string",
     * "uuid": "string
     */
    public String fileName;
    public String filePath;
    public String fileType;
    public String uuid;
}
