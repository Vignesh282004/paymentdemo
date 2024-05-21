package com.stripe.paymentdemo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stripe.paymentdemo.Outs.PaymentServices;
import com.stripe.paymentdemo.Services.Response;

@Controller
public class PaymentsCons {
    
    @Value("${stripe.key.public}")
	private String PUBLIC_KEY;

    @Autowired
    private PaymentServices paymentServices;

    @GetMapping("/home")
    public String home() {
        return "homepage";
    }
    @GetMapping("/subs")
    public String subs(Model model) 
    {
    model.addAttribute("PUBLIC_KEY",PUBLIC_KEY);
    return "subscription";
    }
@GetMapping("/charge")
    public String couptonget(Model model)
    {
        model.addAttribute("PUBLIC_kEY", PUBLIC_KEY);
        return "charge";
    }

    @PostMapping("/create-sub")
    public @ResponseBody Response cratesub(String email,String token,String coupon,String plan) 
    {
            if(token == null || plan.isEmpty()) {
                return new Response(false,"Token is missing.....");
            }
            
            String customerId = paymentServices.createCustomer(email, token);

            if (customerId == null) {
                return new Response(false, "An error accurred while trying to create customer");
            }

            String sub_id = paymentServices.create_subsciption(customerId, coupon, plan);

            if (sub_id == null) {
                return new Response(false, "An error accurred while trying to create subscription");
            }
    
            return new Response(true, "Success! your subscription id is " + sub_id);

    }


}
