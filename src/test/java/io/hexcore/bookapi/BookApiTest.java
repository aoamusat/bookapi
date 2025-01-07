package io.hexcore.bookapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.hexcore.bookapi.model.Book;
import io.hexcore.bookapi.repository.BookRepository;
import io.hexcore.bookapi.service.BookService;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks_ShouldReturnAllBooks() {
        // Arrange
        Book book1 = new Book();
        book1.setId(1L);
        Book book2 = new Book();
        book2.setId(2L);
        List<Book> expectedBooks = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(expectedBooks);

        // Act
        List<Book> actualBooks = bookService.getAllBooks();

        // Assert
        assertEquals(expectedBooks, actualBooks);
        verify(bookRepository).findAll();
    }

    @Test
    void getBookById_WhenBookExists_ShouldReturnBook() {
        // Arrange
        Book expectedBook = new Book();
        expectedBook.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(expectedBook));

        // Act
        Optional<Book> actualBook = bookService.getBookById(1L);

        // Assert
        assertTrue(actualBook.isPresent());
        assertEquals(expectedBook, actualBook.get());
        verify(bookRepository).findById(1L);
    }

    @Test
    void getBookById_WhenBookDoesNotExist_ShouldReturnEmpty() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Book> actualBook = bookService.getBookById(1L);

        // Assert
        assertFalse(actualBook.isPresent());
        verify(bookRepository).findById(1L);
    }

    @Test
    void createBook_ShouldReturnSavedBook() {
        // Arrange
        Book bookToCreate = new Book();
        Book expectedBook = new Book();
        expectedBook.setId(1L);

        when(bookRepository.save(bookToCreate)).thenReturn(expectedBook);

        // Act
        Book actualBook = bookService.createBook(bookToCreate);

        // Assert
        assertEquals(expectedBook, actualBook);
        verify(bookRepository).save(bookToCreate);
    }

    @Test
    void deleteBook_WhenBookExists_ShouldDeleteBook() {
        // Arrange
        when(bookRepository.existsById(1L)).thenReturn(true);

        // Act
        bookService.deleteBook(1L);

        // Assert
        verify(bookRepository).deleteById(1L);
    }

    @Test
    void deleteBook_WhenBookDoesNotExist_ShouldThrowException() {
        // Arrange
        when(bookRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> bookService.deleteBook(1L));
        verify(bookRepository, never()).deleteById(1L);
    }
}
