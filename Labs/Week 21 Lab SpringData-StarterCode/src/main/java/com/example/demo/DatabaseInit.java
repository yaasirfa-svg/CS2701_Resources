package com.example.demo;

import com.example.demo.Models.*;
import com.example.demo.Repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProduceRepository produceRepository;
    @Autowired
    private SellerProduceRepository sellerProduceRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        produceRepository.deleteAll();

        User bob = new User("Bob", "bob@sample.com", "bob_pass", UserType.BUYER);
        User prapanch = new User("Prapanch", "prapanch@sample.com", "prap_pass", UserType.SELLER);
        User ademola = new User("Ademola", "ademola@sample.com", "adem_pass", UserType.BOTH);
        User zhixian = new User("Zhixian", "zhixian@sample.com", "zhix_pass", UserType.BUYER);
        userRepository.save(bob);
        userRepository.save(prapanch);
        userRepository.save(ademola);
        userRepository.save(zhixian);

        Produce apple = new Produce("Apple");
        Produce lettuce = new Produce("Lettuce");
        Produce potatoes = new Produce("Potatoes");
        produceRepository.save(apple);
        produceRepository.save(lettuce);
        produceRepository.save(potatoes);

        SellerProduce prapanchApples = new SellerProduce(prapanch, apple, 0.15, 100);
        SellerProduce prapanchLettuce = new SellerProduce(prapanch, lettuce, 0.25, 20);
        SellerProduce ademolaApples = new SellerProduce(ademola, apple, 0.30, 50);
        SellerProduce ademolaPotatoes = new SellerProduce(ademola, potatoes, 0.05, 30);
        sellerProduceRepository.save(prapanchApples);
        sellerProduceRepository.save(prapanchLettuce);
        sellerProduceRepository.save(ademolaApples);
        sellerProduceRepository.save(ademolaPotatoes);

        Order bobOrder = new Order(bob);
        orderRepository.save(bobOrder);
        orderItemRepository.save(new OrderItem(bobOrder, ademolaApples, 2, ademolaApples.getPrice()));
        orderItemRepository.save(new OrderItem(bobOrder, prapanchLettuce, 1, prapanchLettuce.getPrice()));

        Order zhixianOrder = new Order(zhixian);
        orderRepository.save(zhixianOrder);
        orderItemRepository.save(new OrderItem(zhixianOrder, prapanchApples, 10, prapanchApples.getPrice()));
        orderItemRepository.save(new OrderItem(zhixianOrder, ademolaPotatoes, 15, ademolaPotatoes.getPrice()));
    }
}