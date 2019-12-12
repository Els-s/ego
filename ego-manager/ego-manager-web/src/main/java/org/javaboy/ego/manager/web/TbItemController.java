package org.javaboy.ego.manager.web;

import org.javaboy.commons.FtpUtil;
import org.javaboy.commons.GridData;
import org.javaboy.commons.RespBean;
import org.javaboy.ego.manager.pojo.TbItem;
import org.javaboy.ego.manager.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 1. 文件访问 403  ，chmod 777 /home/ftpuser
 * 2. 文件上传后，是 0KB  ls -la   ftp.enterLocalPassiveMode();
 */
@RestController
public class TbItemController {
    @Autowired
    TbItemService tbItemService;
    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    @GetMapping("/item/list")
    public GridData getTbItemByPage(Integer page, Integer rows) {
        return tbItemService.getTbItemByPage(page, rows);
    }

    @PostMapping("/item/save")
    public RespBean addTbItem(TbItem tbItem) {
        RespBean respBean = new RespBean();
        if (tbItemService.addItem(tbItem) == 1) {
            respBean.setStatus(200);
            respBean.setMsg("添加成功");
            return respBean;
        }
        respBean.setMsg("添加失败");
        respBean.setStatus(500);
        return respBean;
    }

    @PostMapping("/pic/upload")
    public Map<String, Object> fileUpload(MultipartFile uploadFile, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String format = sdf.format(new Date());
        String s = req.getServletContext().getRealPath("/img") + format;
        File folder = new File(s);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
//            uploadFile.transferTo(new File(folder, newName));
            FtpUtil.uploadFile("192.168.66.128", 21, "ftpuser", "123", "/home/ftpuser", format, newName, uploadFile.getInputStream());
            result.put("error", 0);
//            result.put("url", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/img" + format + newName);
            result.put("url", "http://192.168.66.128" + format + newName);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.put("error", 1);
        result.put("message", "上传失败");
        return result;
    }
}
