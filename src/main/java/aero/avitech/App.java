/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package aero.avitech;

import aero.avitech.consumer.UserConsumer;
import aero.avitech.producer.UserProducer;
import aero.avitech.repository.UserRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class App {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("users");

        BlockingQueue queue = new LinkedBlockingQueue();

        UserProducer producer = new UserProducer(queue);
        UserConsumer consumer = new UserConsumer(queue, new UserRepositoryImpl(entityManagerFactory.createEntityManager()));

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}