package com.liao.controller;

import com.liao.config.ControllerLog;
import com.liao.entity.DImg;
import com.liao.service.impl.DImgServiceImpl;
import com.liao.util.Rejson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/img")
public class DImgController {

    private Rejson rejson;

    private final DImgServiceImpl dImgService;

    /**
     * set注入
     * @param dImgService
     */
    public DImgController(DImgServiceImpl dImgService) {
        this.dImgService = dImgService;
    }


    /**
     * 图片添加
     *
     * @param imgList
     * @param productId
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    @ControllerLog(description = "图片添加接口")
    public Rejson addProductImgList(@RequestParam(value = "imgList[]") String imgList[], Integer productId) {
        rejson = new Rejson();
        List<DImg> dImgList = new ArrayList<>();
        // 拼装数据
        for (String img : imgList) {
            DImg dImg = new DImg();
            dImg.setProductId(productId);
            dImg.setImg(img);
            dImg.setCreateTime(new Date());
            dImgList.add(dImg);
        }
        // 执行添加
        return dImgService.addProductImgList(dImgList);
    }

    /**
     * 产品图片删除事件
     *
     * @param dImg
     * @return
     */
    @PostMapping("/del")
    @ResponseBody
    @ControllerLog(description = "图片删除接口")
    public Rejson delProductImg(DImg dImg) {
        // 执行添加
        return dImgService.delProductImg(dImg);
    }

}
