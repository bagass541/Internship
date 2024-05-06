package com.bagas.utils;

import com.bagas.entities.Credit;
import com.bagas.entities.Result;
import com.bagas.entities.User;
import com.bagas.entities.enums.StatusType;

public class ResultMapper {

    public static Result createResult(Credit credit, User user, int countTransactions) {
        return Result.builder()
                .creditId(credit.getId())
                .userId(user.getId())
                .fullName(user.getName() + " " + user.getSecondName())
                .countTransactions(countTransactions)
                .debt(credit.getMoney())
                .periodType(credit.getPeriod())
                .statusType(StatusType.getStatusType(credit.getMoney()))
                .dateRepayment(credit.getRepayment())
                .build();
    }
}
