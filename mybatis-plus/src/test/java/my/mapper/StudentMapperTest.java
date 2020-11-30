package my.mapper;

import my.domain.Student;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.List;

@SpringBootTest
class StudentMapperTest {
    @Autowired
    private StudentMapper mapper;

    @BeforeTestMethod
    public void before() {
        Assert.assertNotNull(mapper);
    }

    @Test
    public void t1() {
        List<Student> list = mapper.selectList(null);
        list.stream().forEach(System.out::println);
        Assert.assertEquals(5, list.size());
    }
}