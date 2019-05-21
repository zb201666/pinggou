package cn.itsource.pinggou.controller;

import cn.itsource.pinggou.util.AjaxResult;
import cn.itsource.pinggou.util.FastDfsApiOpr;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author zb
 * @version 1.0
 * @classname FileController
 * @description TODO
 * @date 2019/5/19
 */
@RestController
public class FileController {

    /**
     * @author zb
     * @description 文件上传
     * @date 2019/5/19
     * @name uploadFile
     * @param file
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @PostMapping("/file/upload")
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file){
        try {
            //取得文件后缀
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            //上传文件，并返回需要保存到数据库的信息
            String fileId = FastDfsApiOpr.upload(file.getBytes(), extension);
            return AjaxResult.me().setData(fileId);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败！！！");
        }
    }

    /**
     * @author zb
     * @description 文件删除
     * @date 2019/5/19
     * @name deleteFile
     * @param fileId
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @DeleteMapping("/file/delete")
    public AjaxResult deleteFile(@RequestParam("fileId") String fileId){
        try {
            String str = fileId.substring(1);
            String group = str.substring(0, str.indexOf("/"));
            String name = str.substring(str.indexOf("/") + 1);
            FastDfsApiOpr.delete(group, name);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败！！！");
        }
    }

    /**
     * @author zb
     * @description 批量删除
     * @date 2019/5/20
     * @name batchDeleteFile
     * @param fileIds
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @DeleteMapping(value = "/file/batchDelete")
    public AjaxResult batchDeleteFile(@RequestParam("fileIds") String fileIds){
        String[] split = fileIds.split(",");
        try {
            for (String fileId : split) {
                String str = fileId.substring(1);
                String group = str.substring(0, str.indexOf("/"));
                String name = str.substring(str.indexOf("/") + 1);
                FastDfsApiOpr.delete(group, name);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败！！！");
        }
    }
}
