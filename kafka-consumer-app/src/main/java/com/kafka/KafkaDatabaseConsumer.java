package com.kafka;


import com.kafka.mode.Data;
import com.kafka.repo.DataRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);


    private DataRepo dataRepo;


    public KafkaDatabaseConsumer(DataRepo dataRepo) {
        this.dataRepo = dataRepo;
    }

    @KafkaListener(
            topics = "new_topic",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(String eventMessage){

        LOGGER.info(String.format("Event message received -> %s", eventMessage));

        Data data = new Data();
        data.setEventData(eventMessage);

        dataRepo.save(data);
    }
}
