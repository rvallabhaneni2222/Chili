/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.gwt.config;

import com.google.gwt.i18n.client.TimeZone;
import java.util.List;

/**
 *
 * @author ayalamanchili
 */
public interface ClientConfig {

    public String getFileUploadUrl();

    public String getFileDownloadUrl();

    public TimeZone getTimeZone();

    public long getImageSizeLimit();

    public long getFileSizeLimit();

    public List<String> getAllowedFileExtensionsAsList();

}
