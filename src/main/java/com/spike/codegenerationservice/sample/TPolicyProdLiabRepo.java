//package com.spike.codegenerationservice.sample;
//
//import com.generation.model.TPolicyProdLiab;
//import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
//import org.jdbi.v3.sqlobject.customizer.BindBean;
//import org.jdbi.v3.sqlobject.statement.SqlQuery;
//import org.jdbi.v3.sqlobject.statement.SqlUpdate;
//
//import java.util.List;
//
//public interface TPolicyProdLiabRepo {
//    @SqlUpdate("INSERT INTO T_POLICY_PROD_LIAB(CONFIG_ID, ITEM_ID, LIAB_ID, LIAB_TYPE) VALUES (:configId, :itemId, :liabId, :liabType)")
//    void insert(@BindBean TPolicyProdLiab tPolicyProdLiab);
//
//    @SqlUpdate("DELETE FROM T_POLICY_PROD_LIAB WHERE CONFIG_ID=:configId")
//    void delete(Long configId);
//
//    @SqlUpdate("UPDATE T_POLICY_PROD_LIAB SET ITEM_ID=:itemId, LIAB_ID=:liabId, LIAB_TYPE=:liabType WHERE CONFIG_ID=:configId")
//    void update(@BindBean TPolicyProdLiab tPolicyProdLiab);
//
//    @SqlQuery("SELECT * FROM T_POLICY_PROD_LIAB")
//    @RegisterBeanMapper(TPolicyProdLiab.class)
//    List<TPolicyProdLiab> findAll();
//
//    @SqlQuery("SELECT * FROM T_POLICY_PROD_LIAB WHERE CONFIG_ID=:configId")
//    @RegisterBeanMapper(TPolicyProdLiab.class)
//    TPolicyProdLiab findById(Long configId);
//}
