package org.wecancodeit.librarydemo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;



import java.lang.annotation.Target;
import java.util.Optional;

@DataJpaTest
public class JPAWiringTest {

    @Autowired
    private TestEntityManager entityManage;

    @Autowired
    private CampusRepository campusRepo;
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private BookRepository bookRepo;

    @Test
    public void  campusShouldHaveAListOfBooks(){
        Campus testCampus = new  Campus("Test Location");
        Author testAuthor1 = new Author ("Test firstName", "Test lastName");
        Book testBook = new Book("Title", "Descriptions", testCampus, testAuthor1 );
        campusRepo.save(testCampus);
        authorRepo.save(testAuthor1);
        bookRepo.save(testBook);

        entityManage.flush();
        entityManage.clear();

        Optional<Campus> retrievedCampusOpt = campusRepo.findById(testCampus.getId());
        Campus retrievedCampus = retrievedCampusOpt.get();
        Book retrievedBook = bookRepo.findById(testBook.getId()).get();
        assertThat(retrievedCampus.getBooks()).contains(testBook);
    }
}
