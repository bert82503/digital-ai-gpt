package com.digital.gpt.chat.service.impl;

import com.digital.gpt.chat.repository.ModelRequestRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.digital.gpt.chat.repository.entity.ModelRequestRecord;
import com.digital.gpt.chat.service.ModelRequestRecordService;

import java.time.LocalDateTime;

/**
 * 模型请求记录服务实现
 *
 * @author lihuagang
 * @date 2023/5/17
 * @see com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 */
@Slf4j
@Service("modelRequestRecordService")
public class ModelRequestRecordServiceImpl extends ServiceImpl<ModelRequestRecordMapper, ModelRequestRecord>
        implements ModelRequestRecordService {
    //

    @Override
    public boolean save(ModelRequestRecord entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setCreateTime(now)
                .setUpdateTime(now);
        try {
            boolean result = super.save(entity);
            if (!result) {
                log.error("save ModelRequestRecord fail, entity={}", entity);
            }
            return result;
        } catch (Exception e) {
            log.error("save ModelRequestRecord exception, entity={}", entity, e);
        }
        return false;
    }
}
