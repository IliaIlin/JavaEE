package com.jee.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

public class XmlParser {

    private static JAXBContext context;
    private static InputStream in;
    private static OutputStream out;

    public static void main(String[] args) throws FileNotFoundException, JAXBException
    {
        context = JAXBContext.newInstance(BookCase.class);
        in = new FileInputStream("src/main/resources/xml/books.xml");
        out = new FileOutputStream
                ("src/main/resources/xml/booksOut.xml");
        BookCase bookCase = readFromXML();
        writeToXml(bookCase);
    }

    public static BookCase readFromXML() throws JAXBException
    {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (BookCase) unmarshaller.unmarshal(in);
    }

    public static void writeToXml(BookCase bookCase) throws JAXBException
    {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        validateAndCorrectTotalPages(bookCase);
        marshaller.marshal(bookCase, out);
    }

    public static void validateAndCorrectTotalPages(BookCase bookCase){
        List<BookShelf> bookShelves = bookCase.getBookShelves();
        for(BookShelf bookShelf:bookShelves){
            List<Book> books = bookShelf.getBooks();
            for(Book book:books){
                if(!book.isSpecifiedTotalCorrect())
                    book.setPages(book.countTotalPages());
            }
        }
    }

}

