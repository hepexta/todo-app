package com.hepexta.todoapp.repository.impl

import com.hepexta.todoapp.TodoApplication
import com.hepexta.todoapp.repository.CategoryRepository
import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import org.flywaydb.test.annotation.FlywayTest
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import com.hepexta.todoapp.model.db.Category
import spock.lang.Specification

import java.time.Instant

@FlywayTest
@ActiveProfiles("db-test")
@AutoConfigureMybatis
@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY)
@SpringBootTest(classes = TodoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CategoryRepositoryImplSpec extends Specification {

    @Autowired
    CategoryRepository categoryRepository

    def "Test UpsertAll"() {
        given:
        def now = Instant.now()
        def user = "Sergei Kuznetsov"
        def randomUUID = UUID.randomUUID()
        def category = Category.builder()
                .categoryId(randomUUID)
                .categoryName("Test Category")
                .description("asd")
                .createdAt(now)
                .createdBy(user)
                .updatedAt(now)
                .updatedBy(user)
                .build()
        when:
        categoryRepository.upsertAll(List.of(category))
        then:
        1 == categoryRepository.find(Category.builder()
                .categoryId(randomUUID)
                .build())
                .size()
    }
}
