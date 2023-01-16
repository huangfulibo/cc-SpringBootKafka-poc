package cc.nondeb.poc.ccpoc.search;

import cc.nondeb.poc.ccpoc.admin.AdminClientCreator;
import cc.nondeb.poc.ccpoc.consumer.ConsumerCreator;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SearchEngine {


    public ConsumerRecords searchEventByTimestamp(String topic, Long timeStamp) throws Exception {

        Consumer consumer = ConsumerCreator.createConsumer();
        AdminClientCreator.createKafkaAdminClient();
        List<PartitionInfo> partitionInfoList = consumer.partitionsFor(topic);
        List<Integer> list =partitionInfoList.stream().map(s->Integer.valueOf(s.partition()))
                .collect(Collectors.toList());
        Map<TopicPartition, Long> timestampsToSearch = new HashMap<>();
       list.stream().forEach(
               (c)-> timestampsToSearch.put(new TopicPartition(topic, c.intValue()), timeStamp)
       );
       timestampsToSearch.forEach(
               (k, v) -> System.out.println("Search TopicPartition:" + k.toString() + ", Search Timestamp:" + v.toString())
       );

        Map<TopicPartition, OffsetAndTimestamp> partitionOffsetAndTimestampMap =
                consumer.offsetsForTimes(timestampsToSearch);
        partitionOffsetAndTimestampMap.forEach(
                (k, v) -> System.out.println("Search TopicPartition:" + k.toString() + " ,Search Offset And Timestamp mapping:" + v.toString())
        );
        partitionOffsetAndTimestampMap.forEach(
                (k, v) -> {
                    if (Objects.nonNull(v)) {
                        consumer.assign(Collections.singleton(k));
                        consumer.seek(k, v.offset());
                        ConsumerRecords<String, String> messages = consumer.poll(Duration.ofMillis(5000L));
                        messages.forEach(record -> {
                            System.out.println(String.format("Event searched from topic:%s, partition:%s, offset:%s, Key:%s, value:%s",
                                    record.topic(), record.partition(), record.offset(), record.key(), record.value()));
                        });
                    }
                }
        );
        return null;
    }







}
