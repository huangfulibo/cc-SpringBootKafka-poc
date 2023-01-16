package cc.nondeb.poc.ccpoc;

import cc.nondeb.poc.ccpoc.consumer.ConsumerCreator;
import cc.nondeb.poc.ccpoc.utils.ClientConfigurationUtils;
import org.apache.kafka.clients.consumer.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import static cc.nondeb.poc.ccpoc.utils.constants.Configs.CONFIG_FILE;

@SpringBootApplication
public class CcPocApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(CcPocApplication.class, args);
//		final Properties props =
//				ClientConfigurationUtils.loadConfig(CONFIG_FILE);
//		props.put(ConsumerConfig.GROUP_ID_CONFIG, "cc-poc");
//		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
//
//		Consumer<String, String> consumer = ConsumerCreator.createConsumer();
//		consumer.subscribe(Arrays.asList("badi-poc", "badi-poc-cd"));
//		while (true) {
//			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//			for (ConsumerRecord<String, String> record : records) {
//				System.out.println(String.format("Event subscribed:topic=%s, partition=%d, offset=%d, key=%s, value=%s",
//						record.topic(), record.partition(), record.offset(), record.key(), record.value()));
//				Thread.sleep(10);
//			}
//		}
	}

}
