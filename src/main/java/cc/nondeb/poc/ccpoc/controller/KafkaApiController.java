package cc.nondeb.poc.ccpoc.controller;


import cc.nondeb.poc.ccpoc.service.KafkaService;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaApiController {

    @Autowired
    private KafkaService kafkaService;

    @GetMapping(value = "/test")
    public String queryByTenant() {
        return "hello cc-poc";
    }

    @GetMapping (value = "/publish/{topic}/{key}/{value}")
    @ResponseBody
    public String publishEvent(@PathVariable String topic, @PathVariable String key, @PathVariable String value) throws Exception{
        Header header = new RecordHeader("hhh", "kkkk".getBytes(StandardCharsets.UTF_8));
        List<Header> headerList = new LinkedList<>();
        headerList.add(header);
        kafkaService.publishEvent(topic, key, value, headerList);
        return "publish messages successfully";
    }

    @GetMapping(value = "/search/{topic}/{timeStamp}")
    public String describeConsumerGroup(@PathVariable String topic, @PathVariable String timeStamp) throws Exception {
        long ts = Long.parseLong(timeStamp);
        System.out.println(String.format("ts is %s", ts));
        kafkaService.searchEventByTimestamp(topic, ts);
        return "search topic records by timeStamp successfully";
    }

    @GetMapping(value = "/describe/{consumerGroup}")
    public String describeConsumerGroup(@PathVariable String consumerGroup) throws Exception {
        kafkaService.describeConsumerGroup(consumerGroup);
        return "describe consume group successfully";
    }


}
