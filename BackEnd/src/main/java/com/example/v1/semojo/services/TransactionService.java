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
    TransactionDao transactionDao;
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
        return transactionDao.save(transac);
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
        return transactionDao.save(transac);
    }

    public Transaction changeStatus(Long transactionId, String status){
        Transaction transaction = transactionDao.findTransactionById(transactionId);
        transaction.setStatus(Transaction.TransactionStatus.valueOf(status));
        Timestamp d = new Timestamp(System.currentTimeMillis());
        transaction.setUpdateTime(d);
        transactionDao.save(transaction);
        return transaction;
    }

    public Transaction findTransactionById(long transactionId){
        return transactionDao.findTransactionById(transactionId);
    }

    public List<Transaction> getUserTransactions(String username){
        UserAuth t_userAuth = userAuthDao.findUserAuthByUsername(username);
        User t_user = t_userAuth.getUser();
        List<Transaction> transactions = t_user.getUserTransec();
        return transactions;
    }

    public List<Transaction> getUserProductsactions(long productId){
        Product product = productDao.findProductByProductId(productId);
        return product.getProductTransac();
    }

    public List<Transaction> getTransactionDetail(String username, long productId){
        User user = userAuthDao.findUserAuthByUsername(username).getUser();
        List<Product> products = user.getOwnedProducts();
        Product t_product = new Product();
        for (Product product : products){
            if (productId == product.getProductId()){
                t_product = product;
                break;
            }
        }
        if (t_product.getProductId() == productId){
            return t_product.getProductTransac();
        }
        else return null;
    }
}
