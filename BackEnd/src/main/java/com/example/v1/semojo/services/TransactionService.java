package com.example.v1.semojo.services;

import com.example.v1.semojo.dao.ProductDao;
import com.example.v1.semojo.dao.TransactionDao;
import com.example.v1.semojo.dao.UserAuthDao;
import com.example.v1.semojo.dao.UserDao;
import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.entities.Transaction;
import com.example.v1.semojo.entities.User;
import com.example.v1.semojo.entities.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionDao transacDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    UserAuthDao userAuthDao;

    public Transaction createTransaction(Long projectId, String username) throws Exception {
        Product product = productDao.findProductByProductId(projectId);
        if (product == null){
            throw new Exception("Product not exist by wrong ID");
        }
        UserAuth bookerAuth = userAuthDao.findUserAuthByUsername(username);
        Transaction transac = new Transaction();
        User booker = bookerAuth.getUser();
        transac.setBooker(booker);
        List<Product> perchasedProducts = new ArrayList<>();
        perchasedProducts.add(product);
        transac.setProducts(perchasedProducts);
        transac.setStatus(Transaction.TransactionStatus.WaitingProcess);
        transac.setCreateTime(new Timestamp(System.currentTimeMillis()));
        transac.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return transacDao.save(transac);
    }

    public Transaction createTransaction(List<Long> productIds, String username) throws Exception {
        Transaction transac = new Transaction();
        UserAuth bookerAuth = userAuthDao.findUserAuthByUsername(username);
        User booker = bookerAuth.getUser();
        transac.setBooker(booker);
        transac.setStatus(Transaction.TransactionStatus.WaitingProcess);
        transac.setCreateTime(new Timestamp(System.currentTimeMillis()));
        transac.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        List<Product> perchasedProducts = new ArrayList<>();
        for (Long id: productIds) {
            Product product = productDao.findProductByProductId(id);
            if (product == null) {
                throw new Exception("Product not exist by wrong ID");
            }
            perchasedProducts.add(product);
        }
        transac.setProducts(perchasedProducts);
        return transacDao.save(transac);
    }

    public Transaction changeStatus(Long transactionId, String status){
        Transaction transac;
        return null;
    }
}
