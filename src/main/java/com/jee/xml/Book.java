package com.jee.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    @XmlAttribute(name = "title", required = true)
    private String title;

    @XmlElement
    private Intro intro;

    @XmlElement(name="chapter")
    private List<Chapter> chapters;

    private int pages;

    public Book()
    {
    }

    public Book(String title, Intro intro, List<Chapter> chapters)
    {
        this.title = title;
        this.intro = intro;
        this.chapters = chapters;
    }

    public Book(String title, Intro intro, List<Chapter> chapters, int pages)
    {
        this.title = title;
        this.intro = intro;
        this.chapters = chapters;
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

    public Intro getIntro()
    {
        return intro;
    }

    public void setIntro(Intro intro)
    {
        this.intro = intro;
    }

    public List<Chapter> getChapters()
    {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters)
    {
        this.chapters = chapters;
    }

    public int getPages()
    {
        return pages;
    }

    public void setPages(int pages)
    {
        this.pages = pages;
    }

    public boolean isSpecifiedTotalCorrect()
    {
        return countTotalPages() == pages;
    }

    public int countTotalPages()
    {
        return intro.getPages() + chapters.stream().mapToInt(Chapter::getPages).sum();
    }

    @Override
    public String toString()
    {
        return "Book{" +
                "title='" + title + '\'' +
                ", intro=" + intro +
                ", chapters=" + chapters +
                ", pages=" + pages +
                '}';
    }
}
