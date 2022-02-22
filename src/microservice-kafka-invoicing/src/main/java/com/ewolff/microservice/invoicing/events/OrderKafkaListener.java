package com.ewolff.microservice.invoicing.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.ewolff.microservice.invoicing.Invoice;
import com.ewolff.microservice.invoicing.InvoiceService;

import sun.misc.Signal;

@Component
public class OrderKafkaListener {

	private Boolean delayFlag = false;

	private final Logger log = LoggerFactory.getLogger(OrderKafkaListener.class);

	private InvoiceService invoiceService;

	public OrderKafkaListener(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;

		Signal.handle(new Signal("USR1"), signal -> {
			delayFlag = true;
			log.info("Received USR1 signal: invoice delay enabled");
        });
		Signal.handle(new Signal("USR2"), signal -> {
			delayFlag = false;
			log.info("Received USR2 signal: invoice delay disabled");
        });
	}

	@KafkaListener(topics = "order")
	public void order(Invoice invoice, Acknowledgment acknowledgment) {

		log.info("Received invoice " + invoice.getId());
		invoiceService.generateInvoice(invoice);
			if (delayFlag) {
                try {
                    Thread.sleep(1100);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
			}
		acknowledgment.acknowledge();
	}

}
