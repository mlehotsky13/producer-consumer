package aero.avitech.producer;

import aero.avitech.entity.User;
import aero.avitech.model.AddUserOperation;
import aero.avitech.model.Operation;
import aero.avitech.model.cst.OperationType;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class UserProducerTest {

    @Test
    public void testProducer() {
        BlockingQueue blockingQueue = new LinkedBlockingQueue();
        UserProducer userProducer = new UserProducer(blockingQueue);
        userProducer.run();

        List<? super Operation> expectedOperations = getOperations();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(blockingQueue.size()).as("Queue size").isEqualTo(expectedOperations.size());
            for (int i = 0; i < expectedOperations.size(); i++) {
                String msg = "Operation " + (i + 1);
                try {
                    softly.assertThat(blockingQueue.take()).as(msg).isEqualTo(expectedOperations.get(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<? super Operation> getOperations() {
        List<? super Operation> operations = new ArrayList<>();
        operations.add(new AddUserOperation(new User(1, "a1", "Robert")));
        operations.add(new AddUserOperation(new User(2, "a2", "Martin")));
        operations.add(new Operation(OperationType.PRINT_ALL));
        operations.add(new Operation(OperationType.DELETE_ALL));
        operations.add(new Operation(OperationType.PRINT_ALL));
        operations.add(new Operation(OperationType.POISON_PILL));

        return operations;
    }
}
