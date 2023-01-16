package cc.nondeb.poc.ccpoc.admin;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class ConsumeGroupClient {

    public static KafkaAdminClient kafkaAdminClient;

    public void describeConsumerGroup(String groupId) throws Exception {
        kafkaAdminClient = AdminClientCreator.createKafkaAdminClient();
        DescribeConsumerGroupsResult describeConsumerGroupsResult =
        kafkaAdminClient.describeConsumerGroups(Arrays.asList(groupId));
        describeConsumerGroupsResult.all().get(20, TimeUnit.SECONDS).forEach(
                (k,v)-> System.out.println(v.toString()));
        Map<TopicPartition, OffsetAndMetadata> listOffsetsOptionsMap =
                kafkaAdminClient.listConsumerGroupOffsets(groupId).partitionsToOffsetAndMetadata()
                        .get(20, TimeUnit.SECONDS);
        Map<TopicPartition, OffsetSpec> latestOffsetSpecs = listOffsetsOptionsMap.entrySet()
                        .stream().collect(Collectors.toMap(Map.Entry::getKey, v->new OffsetSpec.LatestSpec()));

        kafkaAdminClient.listOffsets(latestOffsetSpecs).all().get(20, TimeUnit.SECONDS)
                .forEach(
                        (k, v)-> {
                            long lag = v.offset() - listOffsetsOptionsMap.get(k).offset();
                            System.out.println(String.format("groupId:%s, topic:%s, partition:%s, currentOffset:%s, latestOffset:%s, lag:%s",
                                    groupId, k.topic(), k.partition(), listOffsetsOptionsMap.get(k).offset(), v.offset(), lag));
                        }
                );

    }
}
