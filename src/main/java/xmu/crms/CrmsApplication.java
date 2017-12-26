package xmu.crms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
}
