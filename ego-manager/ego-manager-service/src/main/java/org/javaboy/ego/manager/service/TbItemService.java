package org.javaboy.ego.manager.service;

import org.javaboy.commons.GridData;
import org.javaboy.ego.manager.mapper.TbItemMapper;
import org.javaboy.ego.manager.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-11-14 11:02
 */
@Service
public class TbItemService {
    @Autowired
    TbItemMapper tbItemMapper;

    public GridData getTbItemByPage(Integer page, Integer rows) {
        GridData data = new GridData();
        data.setTotal(tbItemMapper.getTotal());
        data.setRows(tbItemMapper.getTbItemByPage(page, rows));
        return data;
    }

    public Integer addItem(TbItem tbItem) {
        tbItem.setUpdated(new Date());
        tbItem.setCreated(new Date());
        return tbItemMapper.insertSelective(tbItem);
    }
}
