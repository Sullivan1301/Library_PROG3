package org.example;

import Models.Author;
import Models.Book;
import Models.Subscriber;
import Repositories.AuthorCrudOperations;
import Repositories.BookCrudOperations;
import Repositories.SubscriberCrudOperations;
import Settings.DataBaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataBaseConnection.ManagingDataBaseConnection connectionManager = new DataBaseConnection.ManagingDataBaseConnection();

        try (Connection connection = connectionManager.getConnection()) {
            SubscriberCrudOperations subscribersCrudOperations = new SubscriberCrudOperations();

            List<Subscriber> allSubscribers = subscribersCrudOperations.findAll();
            System.out.println("All Subscribers:");
            for (Subscriber subscriber : allSubscribers) {
                System.out.println(subscriber.toString());
            }

            Subscriber newSubscriber = new Subscriber();
            newSubscriber.setName("Andy Loic");
            newSubscriber.setSex('M');
            newSubscriber.setUsername("andyLoic");
            newSubscriber.setPassword("12Loic34");

            Subscriber createdSubscriber = subscribersCrudOperations.save(newSubscriber);
            if (createdSubscriber != null) {
                System.out.println("A new subscriber created: " + createdSubscriber);
            } else {
                System.out.println("Failed to create the new subscriber.");
            }

            Subscriber subscriberToDelete = new Subscriber();
            subscriberToDelete.setId("2");
            Subscriber deletedSubscriber = subscribersCrudOperations.delete(subscriberToDelete);
            if (deletedSubscriber != null) {
                System.out.println("The subscriber has been deleted: " + deletedSubscriber);
            } else {
                System.out.println("Failed to delete the subscriber.");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while performing subscriber operations: " + e.getMessage());
        }

        try (Connection connection = connectionManager.getConnection()) {
            AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations();
            List<Author> allAuthors = authorCrudOperations.findAll();
            System.out.println("All authors:");
            for (Author author : allAuthors) {
                System.out.println(author.toString());
            }

            Author newAuthor = new Author();
            newAuthor.setName("Kazue Kato");
            newAuthor.setSex('F');

            Author createdAuthor = authorCrudOperations.save(newAuthor);
            if (createdAuthor != null) {
                System.out.println("A new author created: " + createdAuthor);
            } else {
                System.out.println("Failed to create the new author.");
            }

            Author authorToDelete = new Author();
            authorToDelete.setId("1");
            Author deletedAuthor = authorCrudOperations.delete(authorToDelete);
            if (deletedAuthor != null) {
                System.out.println("The author has been deleted: " + deletedAuthor);
            } else {
                System.out.println("Failed to delete the author.");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while performing author operations: " + e.getMessage());
        }

        try (Connection connection = connectionManager.getConnection()) {
            BookCrudOperations bookCrudOperations = new BookCrudOperations();

            List<Book> allBooks = bookCrudOperations.findAll();
            System.out.println("All books:");
            for (Book book : allBooks) {
                System.out.println(book.toString());
            }

            Book newBook = new Book();
            newBook.setBookName("Demon Slayer");
            newBook.setPageNumbers(140);
            newBook.setReleaseDate(Date.valueOf("2023-11-15").toLocalDate());
            newBook.setAuthorID(1);
            newBook.setTopic(Book.Topic.OTHER);
            newBook.setStatus("Available");

            Book createdBook = bookCrudOperations.save(newBook);
            if (createdBook != null) {
                System.out.println("A new book created: " + createdBook);
            } else {
                System.out.println("Failed to create the new book.");
            }

            Book bookToDelete = new Book();
            bookToDelete.setId("3");
            Book deletedBook = bookCrudOperations.delete(bookToDelete);
            if (deletedBook != null) {
                System.out.println("The Book has been deleted: " + deletedBook);
            } else {
                System.out.println("Failed to delete the book.");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while performing book operations: " + e.getMessage());
        }
    }
}
