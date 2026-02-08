package fun.ports.in.httpcontroller;

import fun.usecases.payment.BasePayment;
import fun.usecases.payment.MakePayment;
import fun.usecases.payment.PaymentInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final BasePayment basePayment;
    public PaymentController (BasePayment basePayment){
        this.basePayment = basePayment;
    }
    @PostMapping()
    public String findPaymentInfo(@RequestBody PaymentInfo paymentInfo){
        basePayment.doPayment(paymentInfo);
        return "done";
    }
}
