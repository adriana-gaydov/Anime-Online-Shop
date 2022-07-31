package bg.softuni.onlineshop.config.app;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppBeanConfiguration {

    @Bean
    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", "adrianapersonalprj");
        config.put("api_key", "628952652338638");
        config.put("api_secret", "fvXunCZTU2CDU-J3oM_TxmTxizc");
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }

}
