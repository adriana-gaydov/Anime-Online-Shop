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
        config.put("cloud_name", "YOUR VAL");
        config.put("api_key", "YOUR VAL");
        config.put("api_secret", "YOUR VAL");
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }

}
