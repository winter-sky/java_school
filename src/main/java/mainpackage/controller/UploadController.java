package mainpackage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {
    /** */
    private static final Logger log = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        modelMap.addAttribute("file", file);

        if (!file.isEmpty()) {
            try {
                String uploadsDir = "/resources/";

                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);

                if (!new File(realPathtoUploads).exists()) {
                    if (!new File(realPathtoUploads).mkdir()) {
                        modelMap.addAttribute("error", "Cannot create directory [path=" + realPathtoUploads + ']');

                        log.error("Cannot create directory [dir=" + realPathtoUploads + ']');

                        return "uploaded";
                    };
                }

                log.info("realPathtoUploads = {}", realPathtoUploads);

                String origName = file.getOriginalFilename();

                String filePath = realPathtoUploads + origName;

                String url = uploadsDir + origName;

                File dest = new File(filePath);

                file.transferTo(dest);

                modelMap.addAttribute("path", filePath);
                modelMap.addAttribute("url", url);
            } catch (IOException e) {
                log.error("Error uploading file [file=" + file + ']', e);

                modelMap.addAttribute("error", e.getMessage());
            }
        }

        return "uploaded";
    }

    @RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
    public String uploadForm(Model model) {
        return "upload";
    }
}
