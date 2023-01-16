package cc.nondeb.poc.ccpoc.producer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Header;
import org.springframework.stereotype.Component;


@Component
public class EventPublisher {

    public void producer(String topicName,String key, String value, Iterable<Header> headers) throws Exception{
        Producer<String, String> producer = ProducerCreator.createProducer();
        ProducerRecord<String, String> record = new ProducerRecord(topicName, null, key, value, headers);
        RecordMetadata metadata = producer.send(record).get();
        System.out.println(String.format("Event published:key=%s, value=%s, metadata=%s", key, value, metadata));
    }


}
