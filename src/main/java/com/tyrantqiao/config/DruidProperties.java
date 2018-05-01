package com.tyrantqiao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: 不知道有没有用
 *
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */

@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidProperties {

}
