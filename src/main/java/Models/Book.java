package Models;

import lombok.*;


import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Book {
   private String id;
   private String bookName;
   private int pageNumbers;
   private Topic topic;
   private LocalDate releaseDate;
   private int authorID;
   private String status;

   public Book(String id, String bookName, int pageNumbers, Topic topic, Date releaseDate, int authorID, String status) {
   }

   public enum Topic{
      COMEDY,
      ROMANCE,
      OTHER
   }
}
