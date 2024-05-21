package com.stripe.paymentdemo.Outs;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Coupon;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;

@Service
public class PaymentServices {
    
        @Value("${stripe.key.secret}")
        private String SECRET_KEY;

        public String createCustomer(String email,String token) 
        {
            String id =null;

            try {
                
                Stripe.apiKey = SECRET_KEY;
                Map<String,Object>  rand_customer= new HashMap<>();
                rand_customer.put("desc", "Customer for" + email);
                rand_customer.put("email", email);
                rand_customer.put("source",token);
                Customer customer = Customer.create(rand_customer);
                id = customer.getId();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return id;
        }


        public String create_subsciption(String customerId,String coupon,String plan)
        {
                String sub_id = null;
                try {
                    
                    Stripe.apiKey = SECRET_KEY;
                    Map<String,Object> item = new HashMap<>();
                    item.put("plan",plan);
                    Map<String,Object> items = new HashMap<>();
                    item.put("0",item);
                    Map<String, Object> params = new HashMap<>();
                    params.put("customer", customerId);
                    params.put("items", items);

                    if(!coupon.isEmpty()) {
                        params.put("coupon",coupon);
                    }

                    Subscription subscription = Subscription.create(params);
                    sub_id = subscription.getId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return sub_id;
        }

        public boolean cancelSubs(String sub_id) 
        {
            boolean subscription_status;

            try {
                Subscription subscription = Subscription.retrieve(sub_id);
                subscription.cancel();
                subscription_status = true;
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                subscription_status = false;
            }
            return subscription_status;
        }


        public Coupon get_coupon(String code) 
        {
          try {
              Stripe.apiKey = SECRET_KEY;
              return Coupon.retrieve(code);
          } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
          }
          return null;
        }


        public String charge(String email,String token ,String amt) 
        {
            String charge_id = null;

            try{
            Stripe.apiKey = SECRET_KEY;
            
            Map<String,Object> maps = new HashMap<>(); 
            maps.put("charge for the ", email);
            maps.put("source", token);
                maps.put("amt",amt);
                maps.put("currency","usd");

                Charge charge = Charge.create(maps);
                charge.getId();

            }catch(Exception e) {
                e.printStackTrace();
            }
            return charge_id;
        }
}
