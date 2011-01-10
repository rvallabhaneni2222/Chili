package info.yalamanchili.commons;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.Name;

//TODO add @Servlet annotation per servlet 3.0 specification.
@Name("fileUploadServlet")
// @WebServlet(urlPatterns = { "/y.file.upload" })
// TODO fix 404 error pointing to locahost//y.file.upload
public class FileUploadServlet extends HttpServlet {
	private static final Log log = LogFactory.getLog(FileUploadServlet.class);
	private String fileTargetDirectory;
	private static final long serialVersionUID = 1L;

	public void init() {
		fileTargetDirectory = System.getProperty("file.target.directory");
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) {
		if (fileTargetDirectory == null) {
			throw new RuntimeException("Target directory for images not set");
		}
		if (ServletFileUpload.isMultipartContent(request)) {
			processImageUpload(request, response);
		} else {
			processFileUpload(request, response);
		}

	}

	protected void processImageUpload(HttpServletRequest request,
			HttpServletResponse response) {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<?> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			throw new RuntimeException("Error on Image upload", e);
		}
		for (Iterator<?> i = items.iterator(); i.hasNext();) {
			FileItem item = (FileItem) i.next();
			if (item.isFormField())
				continue;
			File imageurl = new File(fileTargetDirectory + "/" + item.getName());
			try {
				System.out.println("writing image to:"
						+ imageurl.getAbsolutePath());
				log.info("writing image to:" + imageurl.getAbsolutePath());
				item.write(imageurl);
			} catch (Exception e) {
				throw new RuntimeException("Error saving image:" + imageurl
						+ ": to disk.", e);
			}
		}
	}

	protected void processFileUpload(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO
	}

	public String getFileTargetDirectory() {
		return fileTargetDirectory;
	}

	public void setFileTargetDirectory(String fileTargetDirectory) {
		this.fileTargetDirectory = fileTargetDirectory;
	}

}
