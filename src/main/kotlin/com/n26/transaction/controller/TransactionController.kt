package com.n26.transaction.controller

import com.n26.transaction.request.SumRequest
import com.n26.transaction.request.TransactionRequest
import com.n26.transaction.service.TransactionService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/transactionservice"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TransactionController(var transactionService: TransactionService) {

    @PutMapping("/transaction/{transaction_id}")
    fun addOrUpdateTransaction(@PathVariable(value = "transaction_id") transaction_id: Long,
                               @RequestBody @Valid request: TransactionRequest) {
        transactionService.addOrUpdateTransaction(transactionService.convertTransactionRequest(transaction_id, request))
    }

    @GetMapping("/transaction/{transaction_id}")
    fun getTransaction(@PathVariable(value = "transaction_id") transaction_id: Long): TransactionRequest {
        return transactionService.getTransaction(transaction_id)
    }

    @GetMapping("/types/{type}")
    fun getTransactionIdsOfSameType(@PathVariable(value = "type") type: String): ArrayList<Long> {
        return transactionService.getTransactionIdOfSameType(type)
    }

    @GetMapping("/sum/{transaction_id}")
    fun getSum(@PathVariable(value = "transaction_id") transaction_id: Long): SumRequest {
        return transactionService.getSum(transaction_id)
    }


}
