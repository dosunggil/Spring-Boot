package com.cho.book.service;

import com.cho.book.model.BookVO;

import java.util.List;

public interface BookService {

    public List<BookVO> selectAll();
    public BookVO findById(String isbn);
    public int insert(BookVO bookVO);
    public int update(BookVO bookVO);
    public int delete(String isbn);

    public List<BookVO> findByTitle(String title);
    public List<BookVO> findByCompany(String company);
    public List<BookVO> findByAuthor(String author);
}
