package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.MsgBox;
public interface MsgBoxRepository extends PagingAndSortingRepository<MsgBox, Long>,JpaSpecificationExecutor<MsgBox>{
}

