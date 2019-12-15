
package com.bndrs.voice.common;

import com.bndrs.voice.ServiceApplication4UT;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <b><code>BaseJUnitTest</code></b>
 * <p/>
 * 通用普通抽象测试类，其他测试类直接继承这个抽象类，免去重复注解
 * <p/>
 * <b>Creation Time:</b> 2019/01/28 14:35.
 *
 * @author wujunbiao
 * @since bndrs-be 0.1.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication4UT.class)
public abstract class BaseJUnitTest {

}
