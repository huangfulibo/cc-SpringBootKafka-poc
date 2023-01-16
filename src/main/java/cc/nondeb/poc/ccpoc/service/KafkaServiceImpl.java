package cc.nondeb.poc.ccpoc.service;

import cc.nondeb.poc.ccpoc.admin.ConsumeGroupClient;
import cc.nondeb.poc.ccpoc.producer.EventPublisher;
import cc.nondeb.poc.ccpoc.search.SearchEngine;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.header.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private EventPublisher eventPublisher;

    @Autowired
    private ConsumeGroupClient consumeGroupClient;

    @Autowired
    private SearchEngine searchEngine;

    @Override
    public void publishEvent(String topicName,String key, String value, Iterable<Header> headers) throws Exception{
        eventPublisher.producer(topicName, key, value, headers);
    }

    @Override
    public void describeConsumerGroup(String groupId) throws Exception {
        consumeGroupClient.describeConsumerGroup(groupId);
    }

    @Override
    public ConsumerRecords searchEventByTimestamp(String topic, Long timeStamp) throws Exception {
        searchEngine.searchEventByTimestamp(topic, timeStamp);
        return null;
    }

}
