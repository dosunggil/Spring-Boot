package com.cho.book.service.impl;

import com.cho.book.model.BookVO;
import com.cho.book.persistence.BookDao;
import com.cho.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImplV1 implements BookService {


    private final BookDao bookDao;

    public BookServiceImplV1(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<BookVO> selectAll() {
        List<BookVO> bookList = bookDao.findAll();
        return bookList;
    }

    @Override
    public BookVO findById(String isbn) {
        /*
            일반적인 한 개의 데이터를 SELECT 하는 코드
            BookVO bookVO = findById(isbn);
            if(bookVO != null || !bookVO.imEmpty() {
            log.debug(bookVO.toString());
            }
         */

        /*
            JPA 의 findById() 는 return type 이 Optional<T>  이다.
            Optional type 은 데이터가 null 인 경우를 좀 더 쉽게 처리할 수 있도록
            도와주는 도구이다.
         */
        Optional<BookVO> opBookVO = bookDao.findById(isbn);

        /*
            Optional 데이터에서 실제 필요한 데이터(bookVO) 를 추출하기 위하여
            get() method 를 사용할 수 있는데,
            보통은 orELse() 와 같은 method 를 사용하여
            만약 포함된 데이터(bookVO) 가 null 일 경우에는
            다른 값을 생성하여 bookVO 데이터가 절대 null 이 아니도록 차리할 수 있다.
         */
        BookVO bookVO = opBookVO.orElse(new BookVO());
        return bookVO;
    }


    @Override
    public int insert(BookVO bookVO) {
        log.debug("서비스 insert{}", bookVO);
        BookVO result = bookDao.save(bookVO);
        return  0;
    }

    @Override
    public int update(BookVO bookVO) {
        return 0;
    }

    @Override
    public int delete(String isbn) {
        return 0;
    }

    @Override
    public List<BookVO> findByTitle(String title) {
        return null;
    }

    @Override
    public List<BookVO> findByCompany(String company) {
        return null;
    }

    @Override
    public List<BookVO> findByAuthor(String author) {
        return null;
    }
}
