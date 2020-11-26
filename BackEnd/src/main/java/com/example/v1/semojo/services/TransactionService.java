package com.example.v1.semojo.services;

import com.example.v1.semojo.api.model.TransactionModel;
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

    public TransactionModel createTransaction(Long projectId, String username) throws Exception {
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
        transactionDao.save(transac);
        return new TransactionModel(transac);
    }

    public TransactionModel createTransaction(List<Long> productIds, String username) throws Exception {
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
        transactionDao.save(transac);
        return new TransactionModel(transac);
    }

    public TransactionModel changeStatus(String username, Long transactionId, String status){
        Transaction transaction = transactionDao.findTransactionById(transactionId);
        transaction.setStatus(Transaction.TransactionStatus.valueOf(status));
        User user = userAuthDao.findUserAuthByUsername(username).getUser();
        if (Transaction.TransactionStatus.valueOf(status) == Transaction.TransactionStatus.PurchasedSuccess){
            transaction.setBooker(user);
            Timestamp d = new Timestamp(System.currentTimeMillis());
            transaction.setUpdateTime(d);
            transactionDao.save(transaction);
            List<Transaction> userTransaction;
            if (user.getUserTransec()!=null){
                userTransaction = user.getUserTransec();
            }else {
                userTransaction = new ArrayList<>();
            }
            userTransaction.add(transaction);
            user.setUserTransec(userTransaction);
            userDao.save(user);
        }
        Timestamp d = new Timestamp(System.currentTimeMillis());
        transaction.setUpdateTime(d);
        transactionDao.save(transaction);
        return new TransactionModel(transaction);
    }

    public Transaction findTransactionById(long transactionId){
        return transactionDao.findTransactionById(transactionId);
    }

    public List<TransactionModel> getUserTransactions(String username){
        UserAuth t_userAuth = userAuthDao.findUserAuthByUsername(username);
        User t_user = t_userAuth.getUser();
        List<Transaction> transactions = t_user.getUserTransec();
        List<TransactionModel> transactionModels = new ArrayList<>();
        for (Transaction transaction:transactions){
            transactionModels.add(new TransactionModel(transaction));
        }
        return transactionModels;
    }

    public List<TransactionModel> getUserProductsactions(long productId){
        Product product = productDao.findProductByProductId(productId);
        List<Transaction> transactions =  product.getProductTransac();
        List<TransactionModel> transactionModels = new ArrayList<>();
        for (Transaction transaction:transactions){
            transactionModels.add(new TransactionModel(transaction));
        }
        return transactionModels;
    }

    public List<TransactionModel> getTransactionDetail(String username, long productId){
        User user = userAuthDao.findUserAuthByUsername(username).getUser();
        List<Product> products = user.getOwnedProducts();
        Product t_product = new Product();
        List<Transaction> transactions;
        List<TransactionModel> transactionModels = new ArrayList<>();
        for (Product product : products){
            if (productId == product.getProductId()){
                t_product = product;
                break;
            }
        }
        if (t_product.getProductId() == productId){
            transactions = t_product.getProductTransac();
            for (Transaction transaction:transactions){
                transactionModels.add(new TransactionModel(transaction));
            }
            return transactionModels;
        }
        else return null;
    }
}
