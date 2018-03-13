package kata;

import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.reactivestreams.client.*;
import io.reactivex.subscribers.TestSubscriber;
import org.assertj.core.api.Assert;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.reactivestreams.tck.TestEnvironment;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Thread.*;
import static org.assertj.core.api.Assertions.assertThat;


public class ReactiveMongoShould {

    @Test
    public void tryMongod() throws InterruptedException {
        MongoCollection<Document> testCollection = insertALot();

        TestSubscriber<Document> resultSubscriber = new TestSubscriber<>();
        TestSubscriber<Document> loggingSubscriber = makeLoggingSubsriber();
        testCollection.find().subscribe(loggingSubscriber);
        TestSubscriber slowsubscriber = makeSlowSubsriber();
        testCollection.find().subscribe(slowsubscriber);


        loggingSubscriber.await();
        slowsubscriber.await();
//        resultSubscriber.await();
//        resultSubscriber.assertValueCount(50007);
    }

    private TestSubscriber makeLoggingSubsriber() {
        return new TestSubscriber() {

            @Override
            public void onNext(Object o) {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("fast");
            }
        };
    }
    private TestSubscriber makeSlowSubsriber() {
        return new TestSubscriber() {

            @Override
            public void onNext(Object o) {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("slow");
            }
        };
    }

    private MongoCollection<Document> insertALot() throws InterruptedException {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost");

        MongoDatabase database = mongoClient.getDatabase("mydb");

        MongoCollection<Document> testCollection = database.getCollection("test");



        TestSubscriber<Success> insertSubscriber = new TestSubscriber<>();
        List<Document> docs = IntStream.range(0, 10000).mapToObj(this::makeDoc).collect(Collectors.toList());
//        testCollection.insertMany(docs).subscribe(insertSubscriber);
//        insertSubscriber.await();

        return testCollection;
    }

    private Document makeDoc(int i) {
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new Document("x", 203).append("y", 102));

        return doc;
    }

    private static class LogSubscriber implements Subscriber<Document> {

        @Override
        public void onSubscribe(Subscription subscription) {

        }

        @Override
        public void onNext(Document document) {
            System.out.println("got doc " + document.getString("type"));

        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {

        }
    }
}
