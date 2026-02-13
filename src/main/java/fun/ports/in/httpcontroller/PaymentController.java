package fun.ports.in.httpcontroller;

import fun.usecases.payment.BasePayment;
import fun.usecases.payment.PaymentInfo;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/payment")
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

    @GetMapping()
    public PaymentInfo findPaymentInfo(@RequestParam("paymentId") String paymentId){
        return basePayment.getPayment(UUID.fromString(paymentId));
    }
}
