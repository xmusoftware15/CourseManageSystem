package xmu.crms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.text.SimpleDateFormat;

/**
 * @author Auto-generated
 */
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("xmu.crms.mapper")
public class CrmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmsApplication.class, args);
	}

	/**
	 *
	 * 序列化为json时相关配置
	 *
	 * 自动忽略值为null的字段
	 * 设置Date类型的json格式为 yyyy-MM-dd
	 *
	 * @return
	 *
	 */
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return mapper;
	}
}
