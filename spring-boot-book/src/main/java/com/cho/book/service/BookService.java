package com.cho.book.service;

import com.cho.book.model.BookVO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public List<BookVO> selectAll();


    /*
        Spring-data 와 관련된 method 에서 findById() 의
        return type 이 Optional<T> 이다.

        보통 findById(id) 는 한 개의 데이터를 SELECT 하여 VO 에 담는 method 이다.
        그런데 id 에 해당하는 데이터가 없을 경우
        findById() 는 null 값을 return 한다.
        null 값을 return 한다는 것은 잠재적으로 NullPointException 을 일으킬 수 있다.

        보통 이 return 된 데이터를 취급할 때 값이 null 인가를 검사하는 코드가 상당히 번거롭게 많이 만들어져야 한다.

        이러한 번거로움을 줄이기 위하여 null 값이 될만한 데이터는 Optional 로 감싸서
        null 검사를 조금 쉽게 할 수 있도록 만들어놓은 새로운 type 이다.

        실제 데이터를 감싸는 wrapper 이며 null 취급을 쉽게 할 수 있도록 하는 도구이다.

     */
    public BookVO findById(String isbn);
    public int insert(BookVO bookVO);
    public int update(BookVO bookVO);
    public int delete(String isbn);

    public List<BookVO> findByTitle(String title);
    public List<BookVO> findByCompany(String company);
    public List<BookVO> findByAuthor(String author);
}
