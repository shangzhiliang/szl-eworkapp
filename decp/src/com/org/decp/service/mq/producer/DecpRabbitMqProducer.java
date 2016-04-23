package com.org.decp.service.mq.producer;

import org.springframework.amqp.core.AmqpTemplate;

import com.org.decp.msg.config.TradeMessageConfig;

public class DecpRabbitMqProducer
{
  private AmqpTemplate amqpTemplate;

  public void sendMessage(TradeMessageConfig tradeMessageConfig, String message)
  {
    this.amqpTemplate.convertAndSend(tradeMessageConfig.routintKey, message);
  }

  public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
    this.amqpTemplate = amqpTemplate;
  }
}