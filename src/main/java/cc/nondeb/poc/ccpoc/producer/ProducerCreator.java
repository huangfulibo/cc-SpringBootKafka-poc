package cc.nondeb.poc.ccpoc.producer;

import cc.nondeb.poc.ccpoc.utils.ClientConfigurationUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Objects;
import java.util.Properties;

import static cc.nondeb.poc.ccpoc.utils.constants.Configs.CONFIG_FILE;


public class ProducerCreator {

    private static KafkaProducer kafkaProducer = null;
    public static Producer<String, String> createProducer() throws Exception{
        final Properties props =
                ClientConfigurationUtils.loadConfig(CONFIG_FILE);
        if (Objects.isNull(kafkaProducer)) {
            kafkaProducer = new KafkaProducer<>(props);
            System.out.println("new KafkaProducer.............");
        }
        return kafkaProducer;
    }

}
