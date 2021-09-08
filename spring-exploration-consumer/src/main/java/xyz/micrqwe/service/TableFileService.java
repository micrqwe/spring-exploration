package xyz.micrqwe.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.micrqwe.dao.FileMapper;
import xyz.micrqwe.model.File;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fyt
 * @since 2021-09-08
 */
@Service
public class TableFileService extends ServiceImpl<FileMapper, File>  {
    @Autowired
    private FileMapper fileMapper;
    public File getByXml(int id){

        return fileMapper.getByXml(id);
    }
}
