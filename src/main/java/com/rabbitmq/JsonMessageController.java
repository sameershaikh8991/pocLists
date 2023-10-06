package com.rabbitmq;


import com.rabbitmq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/json")
public class JsonMessageController {

    @Autowired
    private JsonProducer jsonProducer;

    public JsonMessageController(JsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User data){
        System.out.println("user :"+data);

        User  user = new User(data.getId(), data.getName(), data.getEmail());
        System.out.println("user :"+user);
        jsonProducer.sendJsonProducer(user);
        return ResponseEntity.ok("message send");

    }

}
