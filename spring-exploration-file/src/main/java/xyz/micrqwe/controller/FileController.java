package xyz.micrqwe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.micrqwe.config.MultipartConfig;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by le on 2017/5/3.
 */
@Controller
@RequestMapping("/home/file")
@Slf4j
public class FileController {
    @Value("${tokens}")
    private String tokens;

    /**
     * description  上传文件
     *
     * @param file
     * @param request
     * @return java.lang.String
     * @author swx
     * @date 2021/7/29 8:00 下午
     */
    @PostMapping("upload")
    public String fileUpload(MultipartFile file, HttpServletRequest request) {
        String[] token = request.getHeader("Referer").split("\\?");
        if (token.length != 2) {
            return "error";
        }
        if (!token[1].equals(tokens)) {
            return "error";
        }
        String fileName = MultipartConfig.fileTmp + System.currentTimeMillis() + file.getOriginalFilename();
        File dest = new File(fileName);
        log.info("upload is img : {}", fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); // 保存文件
        } catch (Exception e) {
            log.error("e", e);
        }
        return "login";
    }
}
