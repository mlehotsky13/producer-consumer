package aero.avitech.producer;

import aero.avitech.entity.User;
import aero.avitech.model.AddUserOperation;
import aero.avitech.model.Operation;
import aero.avitech.model.cst.OperationType;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class UserProducer implements Runnable {

    private final BlockingQueue queue;

    public UserProducer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        List<? super Operation> operations = new ArrayList<>();
        operations.add(new AddUserOperation(new User(1, "a1", "Robert")));
        operations.add(new AddUserOperation(new User(2, "a2", "Martin")));
        operations.add(new Operation(OperationType.PRINT_ALL));
        operations.add(new Operation(OperationType.DELETE_ALL));
        operations.add(new Operation(OperationType.PRINT_ALL));
        operations.add(new Operation(OperationType.POISON_PILL));

        for (Object operation : operations) {
            try {
                log.info("Producing operation {}", operation);
                queue.put(operation);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}