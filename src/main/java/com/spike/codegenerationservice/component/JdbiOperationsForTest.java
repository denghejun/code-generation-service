package com.spike.codegenerationservice.component;

import com.generation.model.QTPolicyProdLiab;
import com.generation.model.TPolicyProdLiab;
import com.querydsl.core.types.Path;
import com.querydsl.sql.ColumnMetadata;
import com.spike.codegenerationservice.repository.TPolicyProdLiabRepo;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class JdbiOperationsForTest {
    public void jdbiOperation() {
        try {
            Class.forName("oracle.jdbc.OracleDriver"); // important init oracle driver !.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Jdbi jdbi = Jdbi.create("jdbc:oracle:thin:@10.137.195.24:31236/xe", "system", "oracle");
        jdbi.installPlugin(new SqlObjectPlugin());
        TPolicyProdLiab tPolicyProdLiab = new TPolicyProdLiab();
        tPolicyProdLiab.setConfigId(100l);
        tPolicyProdLiab.setItemId(200l);
        tPolicyProdLiab.setLiabId(Short.valueOf("1"));
        tPolicyProdLiab.setLiabType(Byte.valueOf("1"));

        // insert
        jdbi.useExtension(TPolicyProdLiabRepo.class, repo -> repo.delete(100l));
        jdbi.useExtension(TPolicyProdLiabRepo.class, repo -> repo.insert(tPolicyProdLiab));
        List<TPolicyProdLiab> list1 = jdbi.withExtension(TPolicyProdLiabRepo.class, repo -> repo.findAll());
        this.log.info("list1: " + list1.size());

        // update
        tPolicyProdLiab.setItemId(999l);
        jdbi.useExtension(TPolicyProdLiabRepo.class, repo -> repo.update(tPolicyProdLiab));
        List<TPolicyProdLiab> list2 = jdbi.withExtension(TPolicyProdLiabRepo.class, repo -> repo.findAll());
        this.log.info("list2: " + list2.size());

        // selectById
        TPolicyProdLiab no = jdbi.withExtension(TPolicyProdLiabRepo.class, repo -> repo.findById(1l));
        TPolicyProdLiab yes = jdbi.withExtension(TPolicyProdLiabRepo.class, repo -> repo.findById(100l));

        // delete
        jdbi.useExtension(TPolicyProdLiabRepo.class, repo -> repo.delete(1l));
        List<TPolicyProdLiab> list3 = jdbi.withExtension(TPolicyProdLiabRepo.class, repo -> repo.findAll());
        jdbi.useExtension(TPolicyProdLiabRepo.class, repo -> repo.delete(100l));
        List<TPolicyProdLiab> noList = jdbi.withExtension(TPolicyProdLiabRepo.class, repo -> repo.findAll());
        this.log.info("noList: " + noList.size());


        List<Path<?>> paths = QTPolicyProdLiab.tPolicyProdLiab.getColumns();
        QTPolicyProdLiab.tPolicyProdLiab.getTableName();
        QTPolicyProdLiab.tPolicyProdLiab.getPrimaryKey();
        QTPolicyProdLiab.tPolicyProdLiab.getMetadata();
        for (Path path : paths) {
            ColumnMetadata metadata = QTPolicyProdLiab.tPolicyProdLiab.getMetadata(path);
            this.log.debug(metadata.getName());
        }
    }
}