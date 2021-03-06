package org.springframework.cloud.rsocket.sample.gateway;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.rsocket.RSocket;
import io.rsocket.micrometer.MicrometerRSocketInterceptor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Component
	@Slf4j
	@Profile("pongproxy")
	public static class PongProxy implements ApplicationListener<ApplicationReadyEvent> {

		//@Autowired
		//private Registry registry;

		@Autowired
		private MeterRegistry meterRegistry;

		//@Autowired
		//private GatewayRSocket.Factory rsocketFactory;

		@Override
		@SuppressWarnings("Duplicates")
		public void onApplicationEvent(ApplicationReadyEvent event) {
			ConfigurableEnvironment env = event.getApplicationContext().getEnvironment();
			log.info("Starting Pong Proxy");
			MicrometerRSocketInterceptor interceptor = new MicrometerRSocketInterceptor(meterRegistry, Tag
					.of("component", "pongproxy"));
			/*ByteBuf announcementMetadata = Metadata.from("pong").with("id", "pongproxy1").encode();
			RSocketFactory.connect()
					.metadataMimeType(Metadata.ROUTING_MIME_TYPE)
					.setupPayload(DefaultPayload.create(EMPTY_BUFFER, announcementMetadata))
					.addClientPlugin(interceptor)
					.acceptor(this::accept)
					.transport(TcpClientTransport.create(7002)) // gateway1
					.start()
					.subscribe();*/
		}

		@SuppressWarnings("Duplicates")
		RSocket accept(RSocket rSocket) {
			/*RouteSetup metadata = new RouteSetup(Metadata.from("ping")
					.with("id", "pingproxy1")
					.build());

			registry.register(metadata, rSocket);
			return rsocketFactory.create(metadata);*/
			return null;
		}
	}
}

