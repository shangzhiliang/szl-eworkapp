package com.org.decp.service.mq.listener;

import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

import com.org.decp.msg.DecpMsgService;

public class DecpRabbitMqListener
implements MessageListener
{
private DecpMsgService decpMsgService;

public void onMessage(Message message)
{
  System.out.println(message.getBody().toString());
  SimpleMessageConverter simpleMessageCoverter = new SimpleMessageConverter();
  String context = (String)simpleMessageCoverter.fromMessage(message);
  this.decpMsgService.parseAndForwardMessage(context);
}

public void setDecpMsgService(DecpMsgService decpMsgService) {
  this.decpMsgService = decpMsgService;
}
}