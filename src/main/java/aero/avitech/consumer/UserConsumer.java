package aero.avitech.consumer;

import aero.avitech.entity.User;
import aero.avitech.model.AddUserOperation;
import aero.avitech.model.Operation;
import aero.avitech.model.cst.OperationType;
import aero.avitech.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class UserConsumer implements Runnable {

    private final BlockingQueue queue;
    private final UserRepository userRepository;

    public UserConsumer(BlockingQueue queue, UserRepository userRepository) {
        this.queue = queue;
        this.userRepository = userRepository;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Operation operation = (Operation) queue.take();

                if (operation.getOperationType() == OperationType.POISON_PILL) {
                    log.info("Shutting down consumer!");
                    return;
                }

                log.info("Consuming operation {}", operation);
                if (operation.getOperationType() == OperationType.DELETE_ALL) {
                    log.info("Deleting all users");
                    userRepository.deleteAllUsers();
                } else if (operation.getOperationType() == OperationType.PRINT_ALL) {
                    List<User> users = userRepository.getAllUsers();
                    log.info("Printing all {} users", users.size());
                    users.forEach(user -> log.info(user.toString()));
                } else if (operation instanceof AddUserOperation) {
                    User user = ((AddUserOperation) operation).getUser();
                    log.info("Adding user {}", user);
                    userRepository.saveUser(user);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}