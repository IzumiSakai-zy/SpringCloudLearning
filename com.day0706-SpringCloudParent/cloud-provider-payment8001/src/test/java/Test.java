import com.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {
    @Autowired
    PaymentServiceImpl paymentService;

    @org.junit.Test
    public void test(){
        System.out.println(paymentService.getById(1));
    }
}
