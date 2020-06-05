package com.javaweb.ks.controller;

import com.javaweb.ks.result.Results;
import com.javaweb.ks.service.PicService;
import com.javaweb.ks.util.ImageUtil;
import com.javaweb.ks.util.TokenVerify;
import com.javaweb.ks.util.UploadedImageFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("api/v1/user")
public class PicController {

    @Autowired
    private PicService picService;

    @Autowired
    private TokenVerify tokenVerify;

    // 上传图片
    @PostMapping("/addAvatar")
    @ResponseBody
    public Results add(int userID, String token, UploadedImageFile uploadedImageFile) throws IOException {
        log.info("用户id："+ userID);
        log.info("用户token："+ token);
        log.info("图片：" + uploadedImageFile);
        log.info("路径" + (ResourceUtils.getURL("classpath:").getPath()+"static/img")); // 上传图片到static目录下
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase(); // 获取唯一的uuid
        if(tokenVerify.tokenVerify(userID, token)) {
            //  userService.userList(page,rows,example);
            File imageFolder = new File((ResourceUtils.getURL("classpath:").getPath()+"static/img")); // 获取要上传图片的目录
            File file = new File(imageFolder, uuid + ".jpg"); // new一个图片文件
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            uploadedImageFile.getImage().transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
            return picService.add(uuid, userID); // 添加图片
        }else {
            return new Results(0, "非法访问");
        }
    }
}
