package com.jee.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Chapter {

    @XmlAttribute(name = "title", required = true)
    private String title;

    @XmlAttribute(name="pages",required = true)
    private int pages;

    public Chapter()
    {
    }

    public Chapter(String title, int pages)
    {
        this.title = title;
        this.pages = pages;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getPages()
    {
        return pages;
    }

    public void setPages(int pages)
    {
        this.pages = pages;
    }

    @Override
    public String toString()
    {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", pages=" + pages +
                '}';
    }
}
