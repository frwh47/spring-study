package my.websocket.config;

import my.websocket.controller.MyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/myHandler");
//                .addInterceptors(new HttpSessionHandshakeInterceptor());
//                .setAllowedOrigins("http://127.0.0.1");
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new MyHandler();
    }


//    @Bean
//    public ServletServerContainerFactoryBean createWebSocketContainer() {
//        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
//        container.setMaxTextMessageBufferSize(8192);
//        container.setMaxBinaryMessageBufferSize(8192);
//        return container;
//    }

}
