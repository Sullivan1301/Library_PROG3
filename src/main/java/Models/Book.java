package Models;

import lombok.*;


import java.time.LocalDate;

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

   enum Topic{
      COMEDY,
      ROMANCE,
      OTHER
   }
}
