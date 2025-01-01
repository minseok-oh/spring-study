package com.java.study.infra;

import static org.assertj.core.api.Assertions.assertThat;

import com.java.study.customer.Customer;
import com.java.study.customer.CustomerRepository;
import com.java.study.context.RepositoryTest;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

class DataJdbcCustomerRepositoryTest extends RepositoryTest {

    @Autowired
    @Qualifier("dataJdbcCustomerRepository")
    CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        List<Customer> customers = Arrays.asList(
                new Customer(null, "John", "Woo"),
                new Customer(null, "Jeff", "Dean"),
                new Customer(null, "Josh", "Bloch"),
                new Customer(null, "Josh", "Long")
        );

        customers.forEach(customerRepository::insert);
    }

    @Test
    void 레포지토리_테스트_환경을_확인한다() {
        assertThat(customerRepository).isInstanceOf(DataJdbcCustomerRepository.class);
    }

    @Nested
    class selectByFirstName_메소드는 {

        @Test
        void 이름에_해당하는_손님들을_반환한다() {
            List<Customer> result = customerRepository.selectByFirstName("Josh");

            assertThat(result).hasSize(2);
            assertThat(result).extracting(Customer::getFirstName).containsOnly("Josh");
            assertThat(result).extracting(Customer::getLastName).containsExactlyInAnyOrder("Bloch", "Long");
        }
    }
}