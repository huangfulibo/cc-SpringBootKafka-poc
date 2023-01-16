package cc.nondeb.poc.ccpoc.service;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.header.Header;

public interface KafkaService {

    void publishEvent(String topicName,String key, String value, Iterable<Header> headers) throws Exception;

    void describeConsumerGroup(String groupId) throws Exception;

    ConsumerRecords searchEventByTimestamp(String topic, Long timeStamp) throws Exception;

}
