package my.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.domain.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}