package cc.nondeb.poc.ccpoc.consumer;

import cc.nondeb.poc.ccpoc.utils.ClientConfigurationUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Objects;
import java.util.Properties;

import static cc.nondeb.poc.ccpoc.utils.constants.Configs.CONFIG_FILE;

public class ConsumerCreator {

    private static KafkaConsumer kafkaConsumer = null;
    public static Consumer<String, String> createConsumer() throws Exception{
        final Properties props =
                ClientConfigurationUtils.loadConfig(CONFIG_FILE);
        if (Objects.isNull(kafkaConsumer)) {
            kafkaConsumer = new KafkaConsumer<>(props);
            System.out.println("new KafkaConsumer.............");
        }
        return kafkaConsumer;
    }


}
