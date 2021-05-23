package com.cgh.ro635bot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wudi
 * @since 2021-05-22
 */
@TableName("notice_param")
public class NoticeParam extends Model<NoticeParam> {


      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String type;

    private String noticeUrl;

    private String xpath;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "NoticeParam{" +
        "id=" + id +
        ", type=" + type +
        ", noticeUrl=" + noticeUrl +
        ", xpath=" + xpath +
        "}";
    }
}
