import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.tcs.edu.service.Doubling.DISTINCT;
import static com.tcs.edu.service.Severity.*;
import static org.junit.jupiter.api.Assertions.*;

public class OrderedDistinctMessageServiceTest {

    private HashMapMessageRepository repository = null;
    private MessageService service = null;

    @BeforeEach
    public void setUp() {
        repository = new HashMapMessageRepository();
        service = new OrderedDistinctMessageService(repository);
    }

    @Test
    public void testLogWithOneMessage() {
        // Given
        Message message = new Message(MINOR, "message");

        // When
        service.log(message);

        // Then
        assertAll(
                () -> assertEquals(1, repository.findAll().size(), "Incorrect size of messages"),
                () -> assertTrue(
                        repository.findAll().contains(message),
                        "Message not found in test repository"
                )
        );
    }

    @Test
    public void testLogWithSeveralMessages() {
        // Given
        Message message1 = new Message(MINOR, "message1");
        Message message2 = new Message(MAJOR, "message2");
        Message message3 = new Message(REGULAR, "message3");

        // When
        service.log(message1, message2, message3);

        // Then

        assertAll(
                () -> assertEquals(3, repository.findAll().size(), "Incorrect size of messages"),
                () -> assertTrue(
                        repository.findAll().containsAll(Arrays.asList(message1, message2, message3)),
                        "Not all messages are found in test repository"

                )
        );
    }

    @Test
    public void testLogWithDistinctMessages() {
        // Given
        Message message1 = new Message(REGULAR, "message");
        Message message2 = new Message(MAJOR, "message");
        Message message3 = new Message(MAJOR, "message");

        // When
        service.log(DISTINCT, message1, message2, message3);

        // Then
        assertAll(
                () -> assertEquals(2, repository.findAll().size(), "Incorrect size of messages"),
                () -> assertTrue(
                        repository.findAll().containsAll(Arrays.asList(message1, message2)),
                        "Not all messages are found in test repository"
                )
        );
    }
}
