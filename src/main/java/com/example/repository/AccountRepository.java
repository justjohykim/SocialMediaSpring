package com.example.repository;
import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>{

//Account findbyUsername(String username);



}
