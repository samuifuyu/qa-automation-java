import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.HashMapMessageRepository;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Collection;

import static com.tcs.edu.service.Severity.MAJOR;
import static com.tcs.edu.service.Severity.MINOR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HashMapMessageRepository")
public class HashMapMessageRepositoryTest {

    @Test
    @DisplayName("create repository")
    public void testCreate() {
        new HashMapMessageRepository();
    }

    @Test
    @DisplayName("create message in repository")
    public void testCreateMessage() {
        // Given
        HashMapMessageRepository repository = new HashMapMessageRepository();
        Message message = new Message(MINOR, "message");

        // When
        repository.create(message);

        // Then
        assertTrue(repository.findAll().contains(message));
    }

    @Nested
    @DisplayName("search functions")
    class FindMessages {

        private HashMapMessageRepository repository = null;

        @BeforeEach
        public void setUp() {
            repository = new HashMapMessageRepository();
        }

        @Test
        @DisplayName("getting message by primary key")
        public void testFindByPrimaryKey() {
            // Given
            Message message = new Message(MINOR, "message");
            repository.create(message);

            // When
            Message result = repository.findByPrimaryKey(message.getId());

            // Then Hamcrest
            assertThat(result, equalTo(message));
        }

        @Test
        @DisplayName("getting all messages in repository")
        public void testFindAll() {
            // Given
            Message message1 = new Message(MINOR, "message1");
            Message message2 = new Message(MAJOR, "message2");
            repository.create(message1);
            repository.create(message2);

            // When
            Collection<Message> result = repository.findAll();

            // Then AssertJ
            assertThat(result).containsAll(Arrays.asList(message1, message2));
        }
    }
}
