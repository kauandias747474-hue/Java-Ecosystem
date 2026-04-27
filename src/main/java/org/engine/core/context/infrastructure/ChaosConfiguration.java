package management.infrastructure;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class ChaosConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ChaosConfiguration.class);

    @PostConstruct
    public void activateChaosMode() {
        logger.info("========================================");
        logger.info("SISTEMA INICIADO: MODO CHAOS ATIVADO");
        logger.info("========================================");
    }
}
