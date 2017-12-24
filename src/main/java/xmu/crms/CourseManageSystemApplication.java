package xmu.crms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.text.SimpleDateFormat;

/**
 *
 * @author badcode
 * @date 2017/12/01
 */
@SpringBootApplication
@MapperScan(basePackages = "xmu.crms.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CourseManageSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseManageSystemApplication.class, args);
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
