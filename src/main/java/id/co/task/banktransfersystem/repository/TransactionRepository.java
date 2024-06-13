package id.co.task.banktransfersystem.repository;

import id.co.task.banktransfersystem.model.entity.TransactionDetail;
import id.co.task.banktransfersystem.model.entity.Transactions;

import java.util.List;

/**
 * @author Mika Silaen
 * @created on 6/11/2024
 */
public interface TransactionRepository {
   void insertTransactionDetails(Transactions transactions);
}
