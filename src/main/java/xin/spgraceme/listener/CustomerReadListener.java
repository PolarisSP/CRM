package xin.spgraceme.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import xin.spgraceme.entity.Customer;
import xin.spgraceme.service.CustomerService;
import xin.spgraceme.utils.StringUtil;

/**
 * @author 沈鹏
 * @date 2020/6/1 -21:53
 */
@Component
@Scope("prototype")
public class CustomerReadListener extends AnalysisEventListener<Customer> {


    @Autowired
    private CustomerService customerService;
    

    @Override
    public void invoke(Customer customer, AnalysisContext analysisContext) {
        customer.setCid(StringUtil.uuid());
        customerService.insertCustomer(customer);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
