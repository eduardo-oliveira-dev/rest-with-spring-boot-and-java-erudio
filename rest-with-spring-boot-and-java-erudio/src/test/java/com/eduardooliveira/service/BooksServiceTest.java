package com.eduardooliveira.service;

import com.eduardooliveira.dto.BooksDTO;
import com.eduardooliveira.exception.RequiredObjectIsNullException;
import com.eduardooliveira.model.Books;
import com.eduardooliveira.repository.BooksRepository;
import com.eduardooliveira.unittests.mocks.MockBooks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BooksServiceTest {

    MockBooks input;
    @Mock
    BooksRepository booksRepository;
    @InjectMocks
    private BooksService booksService;

    @BeforeEach
    void setUp() {
        input = new MockBooks();
    }


    @Test
    void findById() {

        Books book = input.mockEntity(1);
        book.setId(1L);
        when(booksRepository.findById(1L)).thenReturn(Optional.of(book));
        var result = booksService.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/books/v1/1") && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("PUT")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/api/books/v1/1") && link.getType().equals("DELETE")));


        assertEquals("Some Author1", result.getAuthor());
        assertEquals(new BigDecimal("25.00"), result.getPrice());
        assertEquals("Some Title1", result.getTitle());
        assertNotNull(result.getLaunchDate());


    }

    @Test
    void create() {
        Books book = input.mockEntity(1);
        Books persisted = book;
        persisted.setId(1L);

        BooksDTO dto = input.mockDTO(1);

        when(booksRepository.save(any(Books.class))).thenReturn(persisted);

        var result = booksService.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/books/v1/1") && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("PUT")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/api/books/v1/1") && link.getType().equals("DELETE")));


        assertEquals("Some Author1", result.getAuthor());
        assertEquals(new BigDecimal("25.00"), result.getPrice());
        assertEquals("Some Title1", result.getTitle());
        assertNotNull(result.getLaunchDate());

    }

    @Test
    void update() {

        Books book = input.mockEntity(1);
        Books persisted = book;
        persisted.setId(1L);

        BooksDTO dto = input.mockDTO(1);

        when(booksRepository.findById(1L)).thenReturn(Optional.of(book));
        when(booksRepository.save(any(Books.class))).thenReturn(persisted);

        var result = booksService.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/books/v1/1") && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("PUT")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/api/books/v1/1") && link.getType().equals("DELETE")));

        assertEquals("Some Author1", result.getAuthor());
        assertEquals(new BigDecimal("25.00"), result.getPrice());
        assertEquals("Some Title1", result.getTitle());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void delete() {
        Books book = input.mockEntity(1);
        book.setId(1L);
        when(booksRepository.findById(1L)).thenReturn(Optional.of(book));

        booksService.delete(1L);
        verify(booksRepository, times(1)).findById(anyLong());
        verify(booksRepository, times(1)).delete(any(Books.class));
        verifyNoMoreInteractions(booksRepository);
    }

    @Test
    void findAll() {
        List<Books> list = input.mockEntityList();
        when(booksRepository.findAll()).thenReturn(list);
        List<BooksDTO> people = booksService.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var bookOne = people.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getId());
        assertNotNull(bookOne.getLinks());

        assertTrue(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/books/v1/1") && link.getType().equals("GET")));

        assertTrue(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("GET")));

        assertTrue(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("POST")));

        assertTrue(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("PUT")));

        assertTrue(bookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/api/books/v1/1") && link.getType().equals("DELETE")));

        var bookFour = people.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookFour.getId());
        assertNotNull(bookFour.getLinks());

        assertTrue(bookFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/books/v1/4") && link.getType().equals("GET")));

        assertTrue(bookFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("GET")));

        assertTrue(bookFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("POST")));

        assertTrue(bookFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("PUT")));

        assertTrue(bookFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/api/books/v1/4") && link.getType().equals("DELETE")));

        assertEquals("Some Author4", bookFour.getAuthor());
        assertEquals(new BigDecimal("25.00"), bookFour.getPrice());
        assertEquals("Some Title4", bookFour.getTitle());
        assertNotNull(bookFour.getLaunchDate());

        var bookSeven = people.get(7);

        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getId());
        assertNotNull(bookSeven.getLinks());

        assertTrue(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/books/v1/7") && link.getType().equals("GET")));

        assertTrue(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("GET")));

        assertTrue(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("POST")));

        assertTrue(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/api/books/v1") && link.getType().equals("PUT")));

        assertTrue(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/api/books/v1/7") && link.getType().equals("DELETE")));

        assertEquals("Some Author7", bookSeven.getAuthor());
        assertEquals(new BigDecimal("25.00"), bookSeven.getPrice());
        assertEquals("Some Title7", bookSeven.getTitle());
        assertNotNull(bookSeven.getLaunchDate());

    }

    @Test
    void testUpdateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> booksService.update(null));

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}