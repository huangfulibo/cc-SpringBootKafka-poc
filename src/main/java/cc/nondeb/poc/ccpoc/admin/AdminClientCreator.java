package cc.nondeb.poc.ccpoc.admin;

import cc.nondeb.poc.ccpoc.utils.ClientConfigurationUtils;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;

import java.util.Objects;
import java.util.Properties;

import static cc.nondeb.poc.ccpoc.utils.constants.Configs.CONFIG_FILE;

public class AdminClientCreator {


    private static KafkaAdminClient kafkaAdminClient = null;
    public static KafkaAdminClient createKafkaAdminClient() throws Exception{
        final Properties props =
                ClientConfigurationUtils.loadConfig(CONFIG_FILE);
        if (Objects.isNull(kafkaAdminClient)) {
            kafkaAdminClient = (KafkaAdminClient) AdminClient.create(props);
            System.out.println("new kafka Admin Client.............");
        }
        return kafkaAdminClient;
    }

}
