package xyz.micrqwe.test;

import org.junit.jupiter.api.Test;
import xyz.micrqwe.service.impl.SqlCustomerServiceImpl;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class SqlCustomerTests extends Common {
    //    @Autowired
    private SqlCustomerServiceImpl customerService;

    @Test
    public void testExample() throws Exception {
        Object o = customerService.querySql("select CPT_INST_ID,FILE_NAME from CDE_DOWNLOAD_CONF where CPT_INST_ID in (1,2,3,4)");
        System.out.println(toJson(o));
    }

}