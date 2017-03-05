package com.jee.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Intro {

    @XmlAttribute(name = "pages")
    private int pages;

    public Intro()
    {
    }

    public Intro(int pages)
    {
        this.pages = pages;
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
        return "Intro{" +
                "pages=" + pages +
                '}';
    }
}
