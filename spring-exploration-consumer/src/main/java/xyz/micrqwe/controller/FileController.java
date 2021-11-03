package xyz.micrqwe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.micrqwe.model.File;
import xyz.micrqwe.service.TableFileService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fyt
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private TableFileService tableFileService;

    @RequestMapping("insert")
    public String insert() {
        File file = new File();
        file.setFileName("hahah");
//        file.setColumnName("{xxx:xx}");
        file.setColumnNum(10);
        file.setRowNum(10);
        tableFileService.getFileMapper().insert(file);
        return file.getId() + "";
    }

    @RequestMapping("get")
    public Object get() {

        return tableFileService.getFileMapper().selectById(1);
    }
    @RequestMapping("getByXml")
    public Object getByXml() {

        return tableFileService.getByXml(1);
    }
}

