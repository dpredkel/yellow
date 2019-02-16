package com.yellow.test.mapper.running;

import com.yellow.test.entity.Running;
import com.yellow.test.mapper.Mapper;
import com.yellow.test.model.running.CreateRunningDTO;
import com.yellow.test.util.Generator;
import com.yellow.test.util.LocalDateUtil;
import org.springframework.stereotype.Component;

@Component
public class CreateRunningDTOToRunningMapper implements Mapper<CreateRunningDTO, Running> {

    @Override
    public Running map(CreateRunningDTO value) {
        if (value == null)
            return null;

        return Running.builder()
                .uuid(Generator.uuidAsString())
                .distance(value.getDistance())
                .duration(value.getDuration())
                .date(LocalDateUtil.parse(value.getDate()))
                .build();
    }
}
