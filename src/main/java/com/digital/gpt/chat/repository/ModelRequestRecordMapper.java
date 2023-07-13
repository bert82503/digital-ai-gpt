package com.digital.gpt.chat.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.digital.gpt.chat.repository.entity.ModelRequestRecord;

import org.apache.ibatis.annotations.Mapper;

/**
 * 模型请求记录映射器
 *
 * @author lihuagang
 * @date 2023/5/17
 * @see com.baomidou.mybatisplus.core.mapper.BaseMapper
 */
@Mapper
public interface ModelRequestRecordMapper extends BaseMapper<ModelRequestRecord> {
    //
}
