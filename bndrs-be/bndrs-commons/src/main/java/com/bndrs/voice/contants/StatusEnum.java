package com.bndrs.voice.contants;

/**
 * @author wujunbiao
 * @create 2019-02-26 19:31
 */
public enum StatusEnum {
    NORMAL("Normal", "正常"),
    INVALID("Invalid", "失效"),
    CRAWLED("Crawled", "已爬取"),
    NOTCRAWLING("NotCrawling", "未爬取"),
    DELETE("Delete", "删除");

    String name;
    String description;


    StatusEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
}
